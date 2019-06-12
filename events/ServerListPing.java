package me.askingg.mayhem.events;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import me.askingg.mayhem.utils.Files;

public class ServerListPing implements Listener {

	@EventHandler
	public void motd(ServerListPingEvent e) {
		String m = "&8&l|&4&ki&c &lMayhem&b&lMines&3 &ki&8&l|&3&ki&b &lCustom {and} Unique &c&lOP Prison&4 &ki&8&l|\n";
		Random r = new Random();
		int x = r.nextInt(Files.config.getStringList("motd").size());
		m+=Files.config.getStringList("motd").get(x);
		e.setMotd(m.replaceAll("&", "\u00A7").replace("{and}", "&"));
	}
}
