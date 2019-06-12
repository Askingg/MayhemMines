package me.askingg.mayhem.dropparty;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Format;

public class DropPartyGUIMain implements Listener {

	public static List<Player> open = new ArrayList<Player>();

	public static Inventory menu(Player p) {
		Inventory menu = Bukkit.createInventory(null, 27, Format.color("&c&lUpcoming DropParties"));
		borders(menu);
		ItemStack i = new ItemStack(Material.STAINED_CLAY);
		i.setDurability((byte) 14);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&lBlock Goal"));
		l.add(Format.color("&7"));
		l.add(Format.color("&7This dropparty occors every time the global block goal is reached"));
		l.add(Format.color("&7"));
		l.add(Format.color("&7Blocks Broken &b&l<blocksmined>&8/&c&l1,000,000"));
		m.setLore(l);
		i.setItemMeta(m);
		menu.addItem(i);
		ItemStack i2 = new ItemStack(Material.STAINED_CLAY);
		i2.setDurability((byte) 1);
		ItemMeta m2 = i2.getItemMeta();
		List<String> l2 = new ArrayList<String>();
		m2.setDisplayName(Format.color("&c&lBlock Goal"));
		l2.add(Format.color("&7"));
		l2.add(Format.color("&7This dropparty occors every day at 11PM GMT"));
		l2.add(Format.color("&7"));
		l2.add(Format.color("&7Next In &b&l<nextdailydp>"));
		l2.add(Format.color("&7Players Required &b&l" + Bukkit.getOnlinePlayers().size() + "&8/&b&l5"));
		m2.setLore(l2);
		i2.setItemMeta(m2);
		menu.addItem(i2);
		ItemStack i3 = new ItemStack(Material.STAINED_CLAY);
		i3.setDurability((byte) 4);
		ItemMeta m3 = i3.getItemMeta();
		List<String> l3 = new ArrayList<String>();
		m3.setDisplayName(Format.color("&c&lWeekly"));
		l3.add(Format.color("&7"));
		l3.add(Format.color("&7This dropparty occors every sunday at 10PM GMT"));
		l3.add(Format.color("&7"));
		l3.add(Format.color("&7Next In &b&l<nextweeklydp>"));
		l3.add(Format.color("&7Players Required &b&l" + Bukkit.getOnlinePlayers().size() + "&8/&b&l10"));
		m3.setLore(l3);
		i3.setItemMeta(m3);
		menu.addItem(i3);
		ItemStack i4 = new ItemStack(Material.STAINED_CLAY);
		i4.setDurability((byte) 5);
		ItemMeta m4 = i4.getItemMeta();
		List<String> l4 = new ArrayList<String>();
		m4.setDisplayName(Format.color("&c&lDonation"));
		l4.add(Format.color("&7"));
		l4.add(Format.color("&7This dropparty occors every time there is a donation"));
		m4.setLore(l4);
		i4.setItemMeta(m4);
		menu.addItem(i4);
		ItemStack i5 = new ItemStack(Material.STAINED_CLAY);
		i5.setDurability((byte) 3);
		ItemMeta m5 = i5.getItemMeta();
		List<String> l5 = new ArrayList<String>();
		m5.setDisplayName(Format.color("&c&lVote Party"));
		l5.add(Format.color("&7"));
		l5.add(Format.color("&7This dropparty occors every time the vote goal is reached"));
		m5.setLore(l5);
		i5.setItemMeta(m5);
		menu.addItem(i5);
		return menu;
	}

	private static void borders(Inventory inv) {
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 7);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
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

	@EventHandler
	public void close(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
		}
	}

}
