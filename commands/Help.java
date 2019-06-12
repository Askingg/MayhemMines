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

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Format;

public class Help implements CommandExecutor, Listener {

	List<Player> open = new ArrayList<Player>();
	@SuppressWarnings("unused")
	private Main plugin;

	public Help(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("help").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			open.add(p);
			p.openInventory(help(p));
		}
		return false;
	}

	public static Inventory help(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, Format.color("&c&lMayhem&b&lHelp"));
		borders(inv);
		ItemStack i = new ItemStack(Material.ENDER_CHEST);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&7"));
		l.add(Format.color("&8&m+----------------&8( &c&lEcho&b&lHelp&8 )&m---------------+"));
		l.add(Format.color("&8 ● &7Open your echo device either by running &b/echo "));
		l.add(Format.color("&7 &7 &7 open&7, or right clicking the &cEcho Device&7 item"));
		l.add(Format.color("&8 ● &7In this menu, you can apply: &bRelics&7, &cArtefacts&7 and"));
		l.add(Format.color("&7 &7 &7 &dCharms&7 which grant unique effects"));
		l.add(Format.color("&8 ● &bRelics&7:"));
		l.add(Format.color("&8 &8 ● &7Grants a permanent sell multiplier"));
		l.add(Format.color("&8 &8 ● &7Equip one in the &cEcho Device&7 by dragging it and"));
		l.add(Format.color("&7 &7 &7 &7 placing it on the the &9Dark Blue Glass "));
		l.add(Format.color("&8 ● &cArtefacts&7:"));
		l.add(Format.color("&8 &8 ● &7Grants a permanent token multiplier"));
		l.add(Format.color("&8 &8 ● &7Equip one in the &cEcho Device&7 by dragging it and"));
		l.add(Format.color("&7 &7 &7 &7 placing it on the the &5Purple Glass"));
		l.add(Format.color("&8 ● &dCharms&7:"));;
		l.add(Format.color("&8 &8 ● &7Give permanent potion effects"));
		l.add(Format.color("&8 &8 ● &7Equip one in the &cEcho Device&7 by dragging it and"));
		l.add(Format.color("&7 &7 &7 &7 placing it on the the &dPink Glass"));
		l.add(Format.color("&8 ● &7Unequip them by clicking on them in the menu while"));
		l.add(Format.color("&7 &7 &7 having an open slot in your inventory"));
		l.add(Format.color("&8&m+-------------------------------------------+"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.addItem(i);
		return inv;
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		if (open.contains((Player) e.getWhoClicked())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void close(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
		}
	}

	private static void borders(Inventory inv) {
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 7);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(Format.color(""));
		i.setItemMeta(m);
		inv.setItem(0, i);
		inv.setItem(1, i);
		inv.setItem(2, i);
		inv.setItem(3, i);
		inv.setItem(4, i);
		inv.setItem(5, i);
		inv.setItem(6, i);
		inv.setItem(7, i);
		inv.setItem(8, i);
		inv.setItem(9, i);
		inv.setItem(17, i);
		inv.setItem(18, i);
		inv.setItem(19, i);
		inv.setItem(20, i);
		inv.setItem(21, i);
		inv.setItem(22, i);
		inv.setItem(23, i);
		inv.setItem(24, i);
		inv.setItem(25, i);
		inv.setItem(26, i);
	}
}
