package me.askingg.mayhem.rainbow;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class RainbowCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("rainbow.get")) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("Voter")) {
						ItemStack i = new ItemStack(Material.LEATHER_HELMET);
						ItemMeta m = i.getItemMeta();
						List<String> l = new ArrayList<String>();
						m.setDisplayName(Format.color("&b&k&li&4&lTo&c&lp Vo&4&lter &c&lArm&4&lor&b&k&li"));
						l.add(Format.color("&7"));
						l.add(Format.color("&6Color changing armor obtained from"));
						l.add(Format.color("&6being the top voter in April 2019"));
						m.setLore(l);
						i.setItemMeta(m);
						p.getInventory().addItem(i);
						i.setType(Material.LEATHER_CHESTPLATE);
						p.getInventory().addItem(i);
						i.setType(Material.LEATHER_LEGGINGS);
						p.getInventory().addItem(i);
						i.setType(Material.LEATHER_BOOTS);
						p.getInventory().addItem(i);
						return true;
					}

					if (args[0].equalsIgnoreCase("Donator")) {
						ItemStack i = new ItemStack(Material.LEATHER_HELMET);
						ItemMeta m = i.getItemMeta();
						List<String> l = new ArrayList<String>();
						m.setDisplayName(Format.color("&c&k&li&3&lTo&b&lp Don&3&lator Ar&b&lmor&c&k&li"));
						l.add(Format.color("&7"));
						l.add(Format.color("&6Color changing armor obtained from"));
						l.add(Format.color("&6being the top doantor in April 2019"));
						m.setLore(l);
						i.setItemMeta(m);
						p.getInventory().addItem(i);
						i.setType(Material.LEATHER_CHESTPLATE);
						p.getInventory().addItem(i);
						i.setType(Material.LEATHER_LEGGINGS);
						p.getInventory().addItem(i);
						i.setType(Material.LEATHER_BOOTS);
						p.getInventory().addItem(i);
					}
				} else {
					Message.player("Usage &b/Rainbow <Voter/Donator>", p);
				}
			} else {
				Message.player("Sorry, but you don't have permission to get rainbow armor", p);
				return true;
			}
		}
		return false;
	}
}
