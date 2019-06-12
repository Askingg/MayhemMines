package me.askingg.mayhem.burriedtreasure;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class TreasureCore {

	public static HashMap<Player, Location> map = new HashMap<Player, Location>();

	public static void spawnChest(Player p, Location l) {
		if (map.containsKey(p)) {
			despawnChest(p, map.get(p));
			map.remove(p);
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				l.getBlock().setType(Material.CHEST);
				map.put(p, new Location(l.getWorld(), l.getX(), l.getY(), l.getZ()));
				l.setX(l.getX() + 0.5);
				l.setY(l.getY() + 2);
				l.setZ(l.getZ() + 0.5);
				Hologram h = HologramsAPI.createHologram(Main.instance, l);
				h.appendTextLine(Format.color("&b&l" + p.getName() + "'s &c&lBuried Treasure"));
				Message.player("You found a &cBurried Treasure&7: Click the chest to open it", p);
			}
		}, 20);
	}

	public static void despawnChest(Player p, Location l) {
		org.bukkit.block.Block b = l.getBlock();
		if (b.getType().equals(Material.CHEST)) {
			Chest c = (Chest) b.getState();
			c.getInventory().clear();	
			b.setType(Material.AIR);
			l.setX(l.getX() + 0.5);
			l.setY(l.getY() + 2);
			l.setZ(l.getZ() + 0.5);
			for (Hologram h : HologramsAPI.getHolograms(Main.instance)) {
				if (l.equals(h.getLocation())) {
					h.delete();
					map.remove(p);
					return;
				}
			}
		}
	}

	public static void shutDown() {
		for (OfflinePlayer p : map.keySet()) {
			despawnChest((Player) p, map.get(p));
		}
	}
}
