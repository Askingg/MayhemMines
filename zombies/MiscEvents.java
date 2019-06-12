package me.askingg.mayhem.zombies;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class MiscEvents implements Listener {

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		if (ZombiesCore.points.containsKey(e.getPlayer().getName())) {
			ZombiesCore.points.remove(e.getPlayer().getName());
		}
		if (ZombiesCore.kills.containsKey(e.getPlayer().getName())) {
			ZombiesCore.kills.remove(e.getPlayer().getName());
		}
	}
}
