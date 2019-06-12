package me.askingg.mayhem.commands;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class PlayTime implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+-----------&8( &c&lPlayTime&b&lCommands&8 )&m----------+", sender);
			Message.senderRaw("&8 ● &b/PlayTime &7View the help list", sender);
			Message.senderRaw("&8 ● &b/PlayTime Check &7View a player's playtime", sender);
			Message.footer(sender);
		} else {
			if (args[0].equalsIgnoreCase("check")) {
				if (args.length == 1 || args.length == 2) {
					if (args.length == 1) {
						if (sender instanceof Player) {
							Player p = (Player) sender;
							Message.player("&7Your Playtime: &b" + Format.time((p.getStatistic(Statistic.PLAY_ONE_TICK) / 20)), p);
							return true;
						}
					}
					if (args.length == 2) {
						Player p = Bukkit.getPlayer(args[1]);
						if (p != null) {
							Message.sender(p.getName() + "'s &7 Playtime: &b" + Format.time((p.getStatistic(Statistic.PLAY_ONE_TICK) / 20)), sender);
						} else {
							Message.sender("Sorry, but &c" + args[1] + "&7 is an invalid player", sender);
						}
					}
				} else {
					Message.sender("Usage &b/PlayTime Check <Player>", sender);
				}
			}
		}
		return false;
	}
}
