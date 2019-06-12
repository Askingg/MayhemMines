package me.askingg.mayhem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Message;

public class ClearChat implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.clearchat")) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (!p.hasPermission("mayhem.clearchat.bypass")) {
					int x = 250;
					while (x > 0) {
						x--;
						Message.playerRaw("&7", p);
					}
					Message.player("The chat has been cleared by &c" + sender.getName(), p);
				} else {
					int x = 5;
					while (x > 0) {
						x--;
						Message.playerRaw("&7", p);
					}
					Message.player("&7The chat was just cleared by &c" + sender.getName() + "&7 but you are bypassing it", p);
				}
			}
		} else {
			Message.sender("&7Sorry, but you don't have permission to do this", sender);
			return true;
		}
		return false;
	}
}
