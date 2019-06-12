package me.askingg.mayhem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.askingg.mayhem.utils.Message;

public class Links implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Message.sender("Useful links:", sender);
		Message.senderRaw("&8 ● &7IP: &bplay.mayhemmines.us", sender);
		Message.senderRaw("&8 ● &7Discord: &bhttps://discord.gg/FbsRCFr", sender);
		Message.senderRaw("&8 ● &7Store: &bhttps://mayhemmines.craftingstore.net", sender);
		return true;
	}
}
