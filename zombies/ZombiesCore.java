package me.askingg.mayhem.zombies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class ZombiesCore {

	public static String mapName = null;
	public static boolean gameRunning = false;
	public static int wave = 0;
	public static int remaining = 0;
	public static HashMap<String, Integer> points = new HashMap<String, Integer>();
	public static HashMap<String, Integer> kills = new HashMap<String, Integer>();
	
	public static void startGame(List<Player> players, String map) {
		mapName = map;
		for (Player p : players) {
			kills.put(p.getName(), 0);
			points.put(p.getName(), 500);
		}
		gameRunning = true;
		wave = 1;
		remaining = (int) Math.round(5 * Math.pow(1 + 0.375, (wave * 0.5)));
		if (map.equalsIgnoreCase("testing")) {
			List<Location> l = new ArrayList<Location>();
			l.add(new Location(Bukkit.getWorld("zombies"), 1433.5, 4.0, -318.5));
			l.add(new Location(Bukkit.getWorld("zombies"), 1433.5, 4.0, -318.5));
			l.add(new Location(Bukkit.getWorld("zombies"), 1427.5, 4.0, -321.5));
			spawnZombies(l, wave);
		}
		Message.broadcastRaw("&4&lZombies &8// &7The game is starting:");
		String pm = "&8 ● &7Players: &b";
		for (Player pl : players) {
			if (pm.equals("&8 ● &7Players: &b")) {
				pm += "&c" + pl.getName();
				continue;
			} else {
				pm += "&7, &c" + pl.getName();
				continue;
			}
		}
		Message.broadcastRaw(pm);
		Message.broadcastRaw("&8 ● &7Map: &b" + map);
	}
	
	public static void endGame() {
		gameRunning = false;
		wave = 0;
		remaining = 0;
		points.clear();
		kills.clear();
	}
	
	@SuppressWarnings("deprecation")
	public static void spawnZombies(List<Location> locs, int wave) {
		Random r = new Random();
		int amnt = (int) Math.round(5 * Math.pow(1 + 0.375, (wave * 0.5)));
		Message.broadcastRaw("&4&lZombies &8// &c" + amnt + "&7 zombies have spawned");
		while (amnt > 0) {
			int x = r.nextInt(locs.size());
			Location l = locs.get(x);
			Zombie z = (Zombie) l.getWorld().spawnEntity(l, EntityType.ZOMBIE);
			z.setCustomName(Format.color("&4Nazi &fZombie"));
			((Damageable) z).setMaxHealth(100 + ((wave - 1) * 10));
			z.setHealth(100 + ((wave - 1) * 10));
			amnt--;
		}
	}
}
