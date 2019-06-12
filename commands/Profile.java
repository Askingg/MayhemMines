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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class Profile implements CommandExecutor, Listener {

	public static List<Player> open = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				open.add(p);
				p.openInventory(grinder(p));
			}
			if (args.length == 1) {
				Player pl = Bukkit.getPlayer(args[0]);
				if (pl == null) {
					Message.player("Sorry, but &7" + args[0] + "&7 is an invalid player", p);
					return true;
				}
			}
			Message.player("Usage: &b/Profile <Player>", p);
		}
		return false;
	}

	public static Inventory grinder(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, Format.color("&c&l" + p.getName() + "'s Profile"));
		ItemStack i = new ItemStack(Material.SIGN);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&lComing Soon.."));
		l.add(Format.color("&7"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(4, i);
		return inv;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (open.contains(e.getWhoClicked())) {
			if (e.getRawSlot() == 4) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if (open.contains(p)) {
			open.remove(p);
		}
	}
}
