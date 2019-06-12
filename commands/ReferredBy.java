package me.askingg.mayhem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Message;

public class ReferredBy implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.sender("&7Usage &b/Referredby <Player>", sender);
		} else {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (Files.data.getString("Users." + p.getUniqueId() + ".ReferredBy") == null) {
					Player p2 = Bukkit.getPlayer(args[1]);
					if (p != p2) {
						if (p.getAddress() != p2.getAddress()) {
							
						} else {
							Message.player("&7Sorry, but you cannot be referred by someone on the same ip as you", p);
						}
					} else {
						Message.player("&7Sorry, but you cannot be referred by yourself", p);
					}
				} else {
					Message.player("&7Sorry, but you can only use the referredby command once", p);
					return true;
				}
			}
		}
		return false;
	}
}
