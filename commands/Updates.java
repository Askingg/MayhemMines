package me.askingg.mayhem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Message;

public class Updates implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Message.senderRaw("&8&m+-------------&8( &c&lRecent&b&lUpdates&8 )&m------------+", sender);
		for (String str : Files.config.getStringList("Updates")) {
			Message.senderRaw(str, sender);
		}
		Message.senderRaw("&7", sender);
		Message.senderRaw("&a &l●&7 Added&a &l● &8&l| &b &l●&7 Edited&b &l● &8&l|&c &l●&7 Removed&c &l●", sender);
		Message.footer(sender);
		return false;
	}
}
