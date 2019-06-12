package me.askingg.mayhem.chatbot;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.askingg.mayhem.utils.Message;

public class PlayerChat implements Listener {

	@EventHandler
	public void chat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String m = e.getMessage();
		m = m.toLowerCase();
		if (m.contains("what") && m.contains("token")) {
			Message.broadcastRaw(
					"&8&l[&4&lAnswer&9&lBot&8&l] &7Hey, &b" + p.getName() + " &7I answered your question in PMs");
			Message.playerRaw(
					"&8&l[&4&lAnswer&9&lBot&8&l] &bTokens&7 are used to &benchant&7 your pickaxe. &bRightClick&7 your pickaxe and click on the &bslime ball&7 to get started",
					p);
		}
		if (m.contains("what") && m.contains("relic")) {
			Message.broadcastRaw(
					"&8&l[&4&lAnswer&9&lBot&8&l] &7Hey, &b" + p.getName() + " &7I answered your question in PMs");
			Message.playerRaw(
					"&8&l[&4&lAnswer&9&lBot&8&l] &bRelics&7 are a custom item that can be placed in the &bEcho Menu&7. &bRelics&7 grant a &bmoney multiplier&7.",
					p);
		}
		if (m.contains("what") && m.contains("artefact")) {
			Message.broadcastRaw(
					"&8&l[&4&lAnswer&9&lBot&8&l] &7Hey, &b" + p.getName() + " &7I answered your question in PMs");
			Message.playerRaw(
					"&8&l[&4&lAnswer&9&lBot&8&l] &bArtefacts&7 are a custom item that can be placed in the &bEcho Menu&7. &bArtefacts&7 grant a &btoken multiplier&7.",
					p);
		}
		if (m.contains("what") && m.contains("charm")) {
			Message.broadcastRaw(
					"&8&l[&4&lAnswer&9&lBot&8&l] &7Hey, &b" + p.getName() + " &7I answered your question in PMs");
			Message.playerRaw(
					"&8&l[&4&lAnswer&9&lBot&8&l] &bCharms&7 are a custom item that can be placed in the &bEcho Menu&7. &bCharms&7 grant permanent potion effects&7.",
					p);
		}
		if (m.contains("what") && m.contains("flesh")) {
			Message.broadcastRaw(
					"&8&l[&4&lAnswer&9&lBot&8&l] &7Hey, &b" + p.getName() + " &7I answered your question in PMs");
			Message.playerRaw(
					"&8&l[&4&lAnswer&9&lBot&8&l] &bDecayed Flesh&7 is a custom item that can be used to obtain &bblood enchantments&7 in the enchanting gui. &bRightClick&7 your pickaxe, click on the &bslime ball&7, then it's the &bEye of ender&7 to purchase blood enchants",
					p);
		}
	}
}
