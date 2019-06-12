 package me.askingg.mayhem.autominer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;
import net.md_5.bungee.api.ChatColor;

public class MinerListGUI implements Listener {

	public static ArrayList<Player> open = new ArrayList<Player>();

	@SuppressWarnings("deprecation")
	public static Inventory menu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, Format.color("&c&lAutoMiners"));
		ItemStack i = new ItemStack(Material.COBBLESTONE);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&lPublic Autominers:"));
		i.setItemMeta(m);
		inv.setItem(10, i);
		i = new ItemStack(Material.STONE);
		m = i.getItemMeta();
		m.setDisplayName(Format.color("&c&lDonator Autominers:"));
		i.setItemMeta(m);
		inv.setItem(19, i);
		i = new ItemStack(Material.getMaterial(2266));
		m = i.getItemMeta();
		m.setDisplayName(Format.color("&c&lYour current AutoMining time:"));
		l.add(Format.color("&8 ● &b" + Format.time(Files.data.getInt("Users." + p.getUniqueId().toString() + ".MinerTime") / 1000)));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(4, i);
		inv.setItem(31, i);
		i = new ItemStack(Material.CONCRETE);
		m = i.getItemMeta();
		for (String str : MinerCore.miners.keySet()) {
			l.clear();
			if (MinerCore.miners.get(str) == null) {
				i.setDurability((byte) 5);
				m.setDisplayName(Format.color("&a" + str));
				l.add(Format.color("&7"));
				l.add(Format.color("&7This autominer is available:"));
				l.add(Format.color("&7Click to join it"));
			} else {
				i.setDurability((byte) 14);
				m.setDisplayName(Format.color("&c" + str));
				l.add(Format.color("&7"));
				l.add(Format.color("&7This autominer is taken:"));
				l.add(Format.color("&7By: &c" + MinerCore.miners.get(str).getName()));
			}
			int s = Integer.valueOf(str.split(" ")[1]);
			s += 10;
			m.setLore(l);
			i.setItemMeta(m);
			inv.setItem(s, i);
		}
		for (String str : MinerCore.dminers.keySet()) {
			l.clear();
			if (!p.hasPermission("mayhem.miners.donator")) {
				i = new ItemStack(Material.BARRIER);
				m.setDisplayName(Format.color("&c" + str));
				l.add(Format.color("&7"));
				l.add(Format.color("&cSorry, but you don't have permission"));
				l.add(Format.color("&cto use donator autominers"));
			} else {
				if (MinerCore.miners.get(str) == null) {
					i.setDurability((byte) 5);
					m.setDisplayName(Format.color("&a" + str));
					l.add(Format.color("&7"));
					l.add(Format.color("&7This autominer is available:"));
					l.add(Format.color("&7Click to join it"));
				} else {
					i.setDurability((byte) 14);
					m.setDisplayName(Format.color("&c" + str));
					l.add(Format.color("&7"));
					l.add(Format.color("&7This autominer is taken:"));
					l.add(Format.color("&7By: &c" + MinerCore.miners.get(str).getName()));
				}
			}
			int s = Integer.valueOf(str.split(" ")[1]);
			s += 19;
			m.setLore(l);
			i.setItemMeta(m);
			inv.setItem(s, i);
		}
		return inv;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (open.contains(e.getWhoClicked())) {
			e.setCancelled(true);
			if (e.getSlot() <= 35) {
				ItemStack i = e.getCurrentItem();
				if (i != null && i.getType() != Material.AIR) {
					if (i.getType() == Material.CONCRETE) {
						Player p = (Player) e.getWhoClicked();
						ItemMeta m = i.getItemMeta();
						if (m.hasDisplayName()) {
							String id = ChatColor.stripColor(Format.color(m.getDisplayName()));
							if (m.getDisplayName().contains("Public")) {
								if (MinerCore.miners.get(id) == null) {
									MinerCore.joinMiner(p, id);
									return;
								} else {
									Message.player("Sorry, but this miner is taken by &c" + MinerCore.miners.get(id), p);
									return;
								}
							}
							if (m.getDisplayName().contains("Donator")) {
								if (p.hasPermission("mayhem.miners.donator")) {
									if (MinerCore.dminers.get(id) == null) {
										MinerCore.joinMiner(p, id);
										return;
									} else {
										Message.player("Sorry, but this miner is taken by &c" + MinerCore.dminers.get(id), p);
										return;
									}
								} else {
									Message.player("Sorry, but you don't have permission to use donator autominers", p);
									return;
								}
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer()))
			open.remove(e.getPlayer());
	}
}
