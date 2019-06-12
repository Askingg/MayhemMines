package me.askingg.mayhem.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class Rename implements CommandExecutor {

	// m.setDisplayName(Format.color("&8&m&l«&8&m-<&c&k&li&8&m[--|&c&k&li&b&l Rename
	// Token &c&k&li&8&m|--]&c&k&li&8&m>-&8&l&m»"));
	// l.add(Format.color("&7"));
	// l.add(Format.color("&7Hold the item you wish to rename and"));
	// l.add(Format.color("&7execute &b/Rename <Name>&7 (Colors supported)"));
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				Message.player("Usage &b/Rename <Name>", p);
			} else {
				if (p.getInventory().getItemInMainHand().getType() == Material.AIR) {
					Message.player("Sorry, but you cannot rename air", p);
					return true;
				}
				Boolean b = false;
				int x = -1;
				for (ItemStack i : p.getInventory()) {
					x++;
					if (i != null && i.getType() != Material.AIR) {
						ItemMeta m = i.getItemMeta();
						if (i.getType() == Material.NAME_TAG && m.hasDisplayName() && m.hasLore()) {
							if (m.getDisplayName().equals(Format.color(
									"&8&m&l«&8&m-<&c&k&li&8&m[--|&c&k&li&b&l Rename Token &c&k&li&8&m|--]&c&k&li&8&m>-&8&l&m»"))) {
								if (m.getLore().get(1).equals(Format.color("&7Hold the item you wish to rename and"))
										&& m.getLore().get(2).equals(
												Format.color("&7execute &b/Rename <Name>&7 (Colors supported)"))) {
									b = true;
									break;
								}
							}
						}
					}
				}
				if (b) {
					String name = "";
					for (String str : args) {
						name += str + " ";
					}
					p.getInventory().getItem(x).setAmount(p.getInventory().getItem(x).getAmount() - 1);
					ItemStack i = p.getInventory().getItemInMainHand();
					ItemMeta m = i.getItemMeta();
					m.setDisplayName(Format.color(name));
					i.setItemMeta(m);
					p.updateInventory();
				} else {
					Message.player("Sorry, but you need a rename token to do this", p);
					return true;
				}
			}
		}
		return false;
	}
}
