package me.askingg.mayhem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Message;

public class Replace implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Message.senderRaw("&8&m+------------&8( &c&lChat&b&lReplacements&8 )&m-----------+", sender);
		for (String str : Files.config.getConfigurationSection("Chat.Replace").getKeys(false)) {
			Message.senderRaw("&8 ● &7." + str + " &b" + Files.config.getString("Chat.Replace." + str), sender);
		}
		Message.footer(sender);
		return false;
	}
}
