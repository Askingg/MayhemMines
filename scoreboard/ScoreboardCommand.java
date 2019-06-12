package me.askingg.mayhem.scoreboard;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Message;

public class ScoreboardCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+---------&8( &c&lScoreBoard&b&lCommands&8 )&m---------+", sender);
			Message.senderRaw("&8 ● &b/ScoreBoard &7View the help list", sender);
			Message.senderRaw("&8 ● &b/ScoreBoard Edit &7Edit your scoreboard", sender);
			Message.footer(sender);
		} else {
			if (args[0].equalsIgnoreCase("edit")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					ScoreboardGUI.open.add(p);
					p.openInventory(ScoreboardGUI.menu(p));
				}
			}
		}
		return false;
	}
}
