package me.askingg.mayhem.zombies;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class ZombieDeath implements Listener {

	@EventHandler
	public void death(EntityDeathEvent e) {
		LivingEntity ent = e.getEntity();
		if (ZombiesCore.gameRunning) {
			if (ent instanceof Zombie) {
				if (ent.getName().equals(Format.color("&4Nazi &fZombie"))) {
					ZombiesCore.remaining--;
					ZombiesCore.kills.put(ent.getKiller().getName(),
							ZombiesCore.kills.get(ent.getKiller().getName()) + 1);
					ZombiesCore.points.put(ent.getKiller().getName(),
							ZombiesCore.points.get(ent.getKiller().getName()) + 90);
					if (ZombiesCore.remaining == 0) {
						ZombiesCore.wave++;
						Message.broadcastRaw("&4&lZombies &8// &7Spawning wave &c" + (ZombiesCore.wave + 1));
						List<Location> l = new ArrayList<Location>();
						if (ZombiesCore.mapName.equalsIgnoreCase("testing")) {
							l.add(new Location(Bukkit.getWorld("zombies"), 1433.5, 4.0, -318.5));
							l.add(new Location(Bukkit.getWorld("zombies"), 1433.5, 4.0, -318.5));
							l.add(new Location(Bukkit.getWorld("zombies"), 1427.5, 4.0, -321.5));
							ZombiesCore.spawnZombies(l, ZombiesCore.wave);
							ZombiesCore.remaining = (int) Math.round(5 * Math.pow(1 + 0.375, (ZombiesCore.wave * 0.5)));
						}
					}
				}
			}
		}
	}
}
