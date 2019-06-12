package me.askingg.mayhem.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class RenameTokens implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+------------&8( &c&lRename&b&lCommands&8 )&m-----------+", sender);
			Message.senderRaw("&8 ● &b/RenameTokens &7View the help list", sender);
			Message.senderRaw("&8 ● &b/Rename &7Rename your items with rename tokens", sender);
			Message.senderRaw("&8 ● &b/RenameTokens Give &7Give a user rename tokens", sender);
			Message.footer(sender);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("give")) { //rt give <player> <amount>
				if (args.length == 3) {
					Player p = Bukkit.getPlayer(args[1]);
					if (p == null) {
						Message.sender("Sorry, but &c" + args[1] + "&7 is an invalid player", sender);
						return true;
					}
					int x = 0;
					try {
						x = Integer.parseInt(args[2]);
					} catch (Exception ex) {
						Message.sender("Sorry, but &c" + args[2] + "&7 is an invalid integer", sender);
						return true;
					}
					if (x <= 0) {
						Message.sender("Sorry, but the amount must be greater than 0", sender);
						return true;
					}
					ItemStack i = new ItemStack(Material.NAME_TAG);
					i.setAmount(x);
					ItemMeta m = i.getItemMeta();
					List<String> l = new ArrayList<String>();
					m.setDisplayName(Format.color("&8&m&l«&8&m-<&c&k&li&8&m[--|&c&k&li&b&l Rename Token &c&k&li&8&m|--]&c&k&li&8&m>-&8&l&m»"));
					l.add(Format.color("&7"));
					l.add(Format.color("&7Hold the item you wish to rename and"));
					l.add(Format.color("&7execute &b/Rename <Name>&7 (Colors supported)"));
					m.setLore(l);
					i.setItemMeta(m);
					p.getInventory().addItem(i);
					p.updateInventory();
					Message.player("&a + " + x + " RenameToken(s)", p);
					return true;
				} else {
					Message.sender("Usage &b/RT Give <Player> <Amount>", sender);
					return true;
				}
			}
		}
		return false;
	}
}
