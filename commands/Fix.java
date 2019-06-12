package me.askingg.mayhem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.askingg.mayhem.entities.dungeons.DungeonsCore;
import me.askingg.mayhem.utils.Message;

public class Fix implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.getInventory().getItemInMainHand() != null) {
				ItemStack i = p.getInventory().getItemInMainHand();
				if (i.getType().isBlock() || i.getDurability() == 0) {
					Message.player("This item cannot be repaired", p);
					return true;
				}
				if (DungeonsCore.getFlesh(p) >= 75) {
					DungeonsCore.removeFlesh(p, 75);
					i.setDurability((short) 0);
					return true;
				} else {
					Message.player("Sorry, but you don't have the &c75&7 flesh to repair your &c" + i.getType().toString().toLowerCase().replace("_", " "), p);
				}
			}
		}
		return false;
	}
}
