package me.askingg.mayhem.rankup;

import java.util.ArrayList;
import java.util.HashMap;
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

public class ColorGUI implements Listener {

	public static List<Player> open = new ArrayList<Player>();
	public static HashMap<String, Integer> colors = new HashMap<String, Integer>();

	public static Inventory menu(Player p) {
		colors.put("&fWhite", 0);
		colors.put("&cRed", 14);
		colors.put("&6Orange", 1);
		colors.put("&eYellow", 4);
		colors.put("&aLime", 5);
		colors.put("&2Green", 13);
		colors.put("&bBlue", 3);
		colors.put("&3Cyan", 9);
		colors.put("&dPink", 2);
		colors.put("&5Purple", 10);
		Inventory menu = Bukkit.createInventory(null, 36, Format.color("&c&lRank color"));
		borders(menu);
		ItemStack i = new ItemStack(Material.WOOL);
		for (String str : colors.keySet()) {
			i.setDurability((byte) ((int) colors.get(str)));
			List<String> l = new ArrayList<String>();
			ItemMeta m = i.getItemMeta();
			m.setDisplayName(Format.color(str));
			l.add(Format.color("&7"));
			if (p.hasPermission("mayhem.rankcolor." + ChatColor.stripColor(Format.color(str)))) {
				l.add(Format.color("&8● &7Click apply " + str));
			} else {
				l.add(Format.color("&8● &7You don't have permission for " + str));
			}
			m.setLore(l);
			i.setItemMeta(m);
			menu.addItem(i);
		}
		return menu;
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (open.contains(p)) {
			e.setCancelled(true);
			ItemStack i = e.getCurrentItem();
			if (i != null) {
				if (i.hasItemMeta() && i.getItemMeta().hasDisplayName()) {
					if (i.getType() == Material.WOOL) {
						if (p.hasPermission("mayhem.rankcolor."
								+ ChatColor.stripColor(Format.color(i.getItemMeta().getDisplayName())))) {
							Message.player(
									"&7You applied the " + i.getItemMeta().getDisplayName() + "&7 rankprefix color", p);
							try {
								if (RankupCore.rank(p) >= 1000) {
									Files.data.set("Users." + p.getUniqueId() + ".RankupColor",
											"&" + i.getItemMeta().getDisplayName().charAt(1) + "&l");
								} else {
									Files.data.set("Users." + p.getUniqueId() + ".RankupColor",
											"&" + i.getItemMeta().getDisplayName().charAt(1));
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	private static void borders(Inventory inv) {
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 7);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		ItemStack i2 = new ItemStack(Material.STAINED_GLASS_PANE);
		i2.setDurability((byte) 15);
		ItemMeta m2 = i2.getItemMeta();
		m2.setDisplayName(Format.color("&7"));
		i2.setItemMeta(m2);
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

		inv.setItem(10, i2);
		inv.setItem(16, i2);
		inv.setItem(17, i);
		inv.setItem(18, i);
		inv.setItem(19, i2);
		inv.setItem(25, i2);

		inv.setItem(26, i);
		inv.setItem(27, i);
		inv.setItem(28, i);
		inv.setItem(29, i);
		inv.setItem(30, i);
		inv.setItem(31, i);
		inv.setItem(32, i);
		inv.setItem(33, i);
		inv.setItem(34, i);
		inv.setItem(35, i);
	}

	@EventHandler
	public void close(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
		}
	}

}
