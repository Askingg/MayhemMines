package me.askingg.mayhem.reaction;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.askingg.mayhem.utils.Compare;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class ReactionCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+-----------&8( &c&lReaction&b&lCommands&8 )&m-----------+", sender);
			Message.senderRaw("&8 ● &b/Reaction &7View the help list", sender);
			Message.senderRaw("&8 ● &b/Reaction Top &7View the top 10 reaction winners", sender);
			Message.footer(sender);
		} else {
			if (args[0].equalsIgnoreCase("top")) { // Reaction Top
				HashMap<String, Double> bals = new HashMap<String, Double>();
				Compare bvc = new Compare(bals);
				TreeMap<String, Double> top = new TreeMap<String, Double>(bvc);
				for (String str : Files.data.getConfigurationSection("Users").getKeys(false)) {
					bals.put(str, Files.data.getDouble("Users." + str + ".ReactionWins"));
				}
				top.putAll(bals);
				int x = 0;
				Message.senderRaw("&8&m+-------&8( &c&lReactionWins&b&lLeaderboard&8 )&m-------+", sender);
				for (String str : top.keySet()) {
					x++;
					if (x < 11) {
						Message.senderRaw(
								"&8 ● &7&l" + x + ".&7 &c" + Bukkit.getOfflinePlayer(UUID.fromString(str)).getName()
										+ " &b" + Format.number(Files.data.getDouble("Users." + str + ".ReactionWins")),
								sender);
					}
				}
				Message.footer(sender);
				return true;
			}
		}
		return false;
	}
}
