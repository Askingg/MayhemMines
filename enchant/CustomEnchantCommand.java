package me.askingg.mayhem.enchant;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.enchant.sword.CEGui;

public class CustomEnchantCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			CEGui.open.add(p);
			p.openInventory(CEGui.menu(p));
			return true;
		}
		return false;
	}
}
