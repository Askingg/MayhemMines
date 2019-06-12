package me.askingg.mayhem.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class Showcase implements CommandExecutor, Listener {

	private static List<Player> open = new ArrayList<Player>();
	public static Player current = null;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+-----------&8( &c&lShowcase&b&lCommands&8 )&m----------+", sender);
			Message.senderRaw("&8 ● &b/ShowCase &7View the help list", sender);
			Message.senderRaw("&8 ● &b/ShowCase Hand &7Showcase the item in your hand", sender);
			Message.senderRaw("&8 ● &b/ShowCase All &7Showcase your whole inventory", sender);
			Message.footer(sender);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("hand")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					ItemStack i = p.getInventory().getItemInMainHand();
					if (i.getType() != Material.AIR) {
						String n = i.getType().toString().toLowerCase();
						if (i.hasItemMeta() && i.getItemMeta().hasDisplayName()) {
							n = i.getItemMeta().getDisplayName();
						}
						for (Player pl : Bukkit.getOnlinePlayers()) {
							Format.item(pl, i, "&8&l[&c&lShow&b&lCase&8&l] &b" + p.getName()
									+ "&7 is showcasing their '&f" + n + "&7'");
						}
					} else {
						Message.player("Sorry, but you must hold an item to showcase one", p);
						return true;
					}
				}
			}
			if (args[0].equalsIgnoreCase("all")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (p.hasPermission("mayhem.showcase.all")) {
						current = p;
						for (Player pl : Bukkit.getOnlinePlayers()) {
							Format.inventory(pl,
									"&8&l[&c&lShow&b&lCase&8&l] &b" + p.getName()
											+ "&7 is showcasing their inventory &8(&7Click&8)",
									"/showcase showcurrent", "&7Click to view &b" + p.getName() + "'s &7Inventory");
						}
					} else {
						Message.player("Sorry, but you don't have permission to showcase your inventory", p);
					}
				}
			}
			if (args[0].equalsIgnoreCase("showcurrent")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (current != null) {
						open.add(p);
						p.openInventory(current.getInventory());
					} else {
						Message.player("Sorry, but there is no current showcase", p);
					}
				}
			}
		}
		return false;
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		if (open.contains(e.getWhoClicked())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void close(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
		}
	}
}
