package me.askingg.mayhem.rankup;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Message;

public class PrestigeCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				Integer r = RankupCore.rank(p);
				if (r < 2000) {
					Message.player("&7Sorry, but you need to be rank &c2,000&7 to prestige", p);
					return true;
				}
				RankupCore.prestige(p);
			} else {
				Message.sender("Sorry, but you must be a player to use this command", sender);
				return true;
			}
			return true;
		}
		return false;
	}
}
