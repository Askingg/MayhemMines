package me.askingg.mayhem.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;
import net.md_5.bungee.api.ChatColor;

public class New implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.new")) {
			if (args.length == 0) {
				Message.sender("Usage &b/New <Added/Edited/Removed> <Update>", sender);
				return true;
			} else {
				if (args.length > 1) {
					if (args[0].equalsIgnoreCase("added") || args[0].equalsIgnoreCase("edited")
							|| args[0].equalsIgnoreCase("removed")) {
						String str = "";
						if (args[0].equalsIgnoreCase("added")) {
							str = "&a &l●&7 ";
						}
						if (args[0].equalsIgnoreCase("edited")) {
							str = "&b &l●&7 ";
						}
						if (args[0].equalsIgnoreCase("removed")) {
							str = "&c &l●&7 ";
						}
						String str2 = "";
						for (int i = 1; i < args.length; i++)
							str2 += args[i] + " ";
						Message.broadcastRaw("&3&lNEW: &b" + str2);
						str += ChatColor.stripColor(Format.color(str2));
						try {
							List<String> u = Files.config.getStringList("Updates");
							if (u.size() >= 10) {
								u.remove(9);
							}
							u.add(0, str);
							Files.config.set("Updates", u);
							Files.config.save(Files.configFile);
						} catch (Exception ex) {
							ex.printStackTrace();
							return false;
						}
					}
				} else {
					Message.sender("Usage &b/New <Added/Edited/Removed> <Update>", sender);
					return true;
				}
			}
		} else {
			Message.sender("&7Sorry, but you don't have permission to do this", sender);
			return true;
		}
		return false;
	}
}
