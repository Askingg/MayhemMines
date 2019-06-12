package me.askingg.mayhem.reaction;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.askingg.mayhem.echo.EchoCore;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Message;

public class PlayerChat implements Listener {

	@EventHandler
	public void chat(AsyncPlayerChatEvent e) {
		if (ReactionCore.reaction) {
			if (e.getMessage().equals(ReactionCore.reactionWord)) {
				Double t = Double.valueOf(ReactionCore.getTime(System.currentTimeMillis()));
				ReactionCore.reaction = false;
				ReactionCore.reactionWord = null;
				Message.broadcast("&b" + e.getPlayer().getName() + "&7 won the reaction in &b"
						+ t + " &7 seconds");
				ReactionCore.startTime = 0L;
				try {
					Files.data.set("Users." + e.getPlayer().getUniqueId() + ".ReactionWins",
							(Files.data.getInt("Users." + e.getPlayer().getUniqueId() + ".ReactionWins") + 1));
					if (t >= Files.data.getDouble("Users." + e.getPlayer().getUniqueId() + ".ReactionTime")) {
						Files.data.set("Users." + e.getPlayer().getUniqueId() + ".ReactionTime", t);
					}
					Files.data.save(Files.dataFile);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				Random r = new Random();
				Integer x = r.nextInt(3) + 1;
				if (x == 1) {
					e.getPlayer().getInventory().addItem(EchoCore.randomArtefact());
				}
				if (x == 2) {
					e.getPlayer().getInventory().addItem(EchoCore.randomRelic());
				}
				if (x == 3) {
					e.getPlayer().getInventory().addItem(EchoCore.randomCharm());
				}
				e.setCancelled(true);
			}
		}
	}
}
