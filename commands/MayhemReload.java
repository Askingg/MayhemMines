package me.askingg.mayhem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Message;

public class MayhemReload implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.reload")) {
			Files.config = YamlConfiguration.loadConfiguration(Files.configFile);
			Files.data = YamlConfiguration.loadConfiguration(Files.dataFile);
			Message.sender("Configuration files successfully reloaded", sender);
		} else {
			Message.sender("&7Sorry, but you don't have permission to do this", sender);
		}
		return false;
	}
}
