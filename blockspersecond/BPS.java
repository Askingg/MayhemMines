package me.askingg.mayhem.blockspersecond;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Message;

public class BPS implements Listener {

	public static HashMap<Player, Integer> bps = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> bpssb = new HashMap<Player, Integer>();

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (bps.containsKey(p)) {
			bps.put(p, (bps.get(p) + 1));
			return;
		} else {
			bps.put(p, 1);
			return;
		}
	}

	public static void setupBPS() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				HashMap<Player, Integer> players = new HashMap<Player, Integer>();
				for (Player p : bps.keySet()) {
					bpssb.put(p, Math.round((bps.get(p) / 10)));
					if (bps.containsKey(p)) {
						if (bps.get(p) > 200) {
							players.put(p, Math.round((bps.get(p) / 10)));
						}
					}
				}
				String m = "&8&l[&4&lBPSCheck&8&l]&7 Players getting more than 20 bps: ";
				for (Player p : players.keySet()) {
					m += "&c" + p.getName() + "&8 (&c" + players.get(p) + "&8) ";
					if (Files.bps.getIntegerList(p.getName()) != null) {
						List<Integer> l = Files.bps.getIntegerList(p.getName());
						l.add(players.get(p));
						Files.bps.set(p.getName(), l);
					}
				}
				try {
					Files.bps.save(Files.bpsFile);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				if (!m.equals("&8&l[&4&lBPSCheck&8&l]&7 Players getting more than 20 bps: ")) {
					for (Player pl : Bukkit.getOnlinePlayers()) {
						if (pl.hasPermission("mayhem.bps.check")) {
							Message.playerRaw(m, pl);
						}
					}
					Message.console(m);
				}
				bps.clear();
			}
		}, 200, 200);
	}

	// }
}
