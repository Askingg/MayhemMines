package me.askingg.mayhem.rankup;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.entities.dungeons.DungeonsCore;
import me.askingg.mayhem.utils.Compare;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class RankupCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				Integer r = RankupCore.rank(p);
				if (r == 2000) {
					Message.player("&7Sorry, but &b2,000&7 is the max... Congratulations", p);
					return true;
				}
				RankupCore.rankUp(p);
			} else {
				Message.sender("Sorry, but you must be a player to use this command", sender);
				return true;
			}
			return true;
		} else {
			if (args[0].equalsIgnoreCase("test")) {
				Player p = (Player) sender;
				Message.sender("Flesh: &c" + DungeonsCore.getFlesh(p), sender);
				if (DungeonsCore.getFlesh(p) >= 5) {
					DungeonsCore.removeFlesh(p, 5);
					Message.sender("Flesh: &c" + DungeonsCore.getFlesh(p), sender);
				}
			}
			if (args[0].equalsIgnoreCase("help")) {
				Message.senderRaw("&8&m+------------&8( &c&lRankUp&b&lCommands&8 )&m------------+", sender);
				Message.senderRaw("&8 ● &b/Rankup &7Rankup to the next rank", sender);
				Message.senderRaw("&8 ● &b/Rankup Help &7View the help list", sender);
				Message.senderRaw("&8 ● &b/Rankup Max &7Rankup as much as you  can afford", sender);
				Message.senderRaw("&8 ● &b/Rankup Top &7View the top 10 ranked players", sender);
				Message.footer(sender);
				return true;
			}
			if (args[0].equalsIgnoreCase("color")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					ColorGUI.open.add(p);
					p.openInventory(ColorGUI.menu(p));
				} else {
					Message.sender("Sorry, but you must be a player to use this command", sender);
				}
			}
			if (args[0].equalsIgnoreCase("max")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (RankupCore.rank(p) == 2000) {
						Message.player("&7Sorry, but &b2,000&7 is the max... Congratulations", p);
						return true;
					}
					while (RankupCore.rankUp(p)) {
					}
					return true;
				} else {
					Message.sender("Sorry, but you must be a player to use this command", sender);
				}

			}
			if (args[0].equalsIgnoreCase("top")) {
				HashMap<String, Double> bals = new HashMap<String, Double>();
				Compare bvc = new Compare(bals);
				TreeMap<String, Double> top = new TreeMap<String, Double>(bvc);
				for (String str : Files.data.getConfigurationSection("Users").getKeys(false)) {
					bals.put(str, Files.data.getDouble("Users." + str + ".RankUp"));
				}
				top.putAll(bals);
				int x = 0;
				Message.senderRaw("&8&m+----------&8( &c&lRankup&b&lLeaderboard&8 )&m----------+", sender);
				for (String str : top.keySet()) {
					x++;
					if (x < 11) {
						Message.senderRaw(
								"&8 ● &7&l" + x + ".&7 &c" + Bukkit.getOfflinePlayer(UUID.fromString(str)).getName()
										+ " &b" + Format.decimals(0, Files.data.getDouble("Users." + str + ".RankUp")),
								sender);
					}
				}
				Message.footer(sender);
				return true;
			}
			return false;
		}
	}
}
