package me.askingg.mayhem.events;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import me.askingg.mayhem.burriedtreasure.TreasureCore;
import me.askingg.mayhem.gangs.GangCore;
import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Message;

public class BlockBreak implements Listener {

	@EventHandler
	public void blockBreak(org.bukkit.event.block.BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode() == GameMode.SURVIVAL) {
			// if (p.getName().equals("Askingg")) {
			// TreasureCore.spawnChest(p, e.getBlock().getLocation());
			// }
			if (e.getBlock().getType() == Material.CHEST) {
				if (TreasureCore.map.containsValue(e.getBlock().getLocation())) {
					Message.player("Sorry, but you cannot break &cburied treasures", p);
					e.setCancelled(true);
					return;
				}
			}
			if (com.sk89q.worldguard.bukkit.WorldGuardPlugin.inst().canBuild(p, e.getBlock())) {
				String u = p.getUniqueId().toString();
				Main.broken.put(u.toString(), (Main.broken.get(u.toString()) + 1));
				if (Main.onlinetimebroken.containsKey(p)) {
					Main.onlinetimebroken.put(p, (Main.onlinetimebroken.get(p) + 1));
				} else {
					Main.onlinetimebroken.put(p, 1);
				}
				if (GangCore.hasGang(p)) {
					String g = GangCore.getGang(p);
					if (Main.gangBroken.containsKey(g)) {
						Main.gangBroken.put(g, Main.gangBroken.get(g) + 1);
					} else {
						Main.gangBroken.put(g, 1);
					}
				}
				Random r = new Random();
				if (r.nextDouble() <= 0.0002) {
					TreasureCore.spawnChest(p, e.getBlock().getLocation());
				}
			}
		}
		Location origin = p.getLocation();
		Vector target = e.getBlock().getLocation().toVector();
		origin.setDirection(target.subtract(origin.toVector()));
		if (!get(p.getEyeLocation().getYaw()).equals(get(origin.getYaw()))) {
			if (Main.violations.containsKey(p.getName())) {
				Main.violations.put(p.getName(), (Main.violations.get(p.getName()) + 1));
			} else {
				Main.violations.put(p.getName(), 1);
			}
		}
	}

	public static String get(float yaw) {
		if (yaw < 0) {
			yaw += 360;
		}
		if (yaw >= 315 || yaw < 45) {
			return "south";
		} else if (yaw < 135) {
			return "west";
		} else if (yaw < 225) {
			return "north";
		} else if (yaw < 315) {
			return "east";
		}
		return "invalid-direction";
	}
}
