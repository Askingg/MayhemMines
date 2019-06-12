package me.askingg.mayhem.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Message;
import me.askingg.mayhem.utils.Misc;

public class PlayerChat implements Listener {

	@EventHandler
	public void chat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		Misc.setTablist(p);
		String m = e.getMessage();
		Boolean ms = false;
		for (String str : Files.config.getConfigurationSection("Chat.Replace").getKeys(false)) {
			if (m.contains("." + str)) {
				if (p.hasPermission("mayhem.chat.replace")) {
					m = m.replaceAll("." + str, Files.config.getString("Chat.Replace." + str));
				} else {
					ms = true;
					break;
				}
			}
		}
		if (ms) {
			Message.player("Sorry, but you don't have permission to use chat replacements", p);
		}
		e.setMessage(m);
	}
}
