package me.askingg.mayhem.pickaxe;

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

import me.askingg.mayhem.enchant.EnchantGUI;
import me.askingg.mayhem.exp.ExpGUI;
import me.askingg.mayhem.utils.Format;

public class PickaxeGUI implements Listener {

	public static List<Player> open = new ArrayList<Player>();

	public static Inventory menu(Player p) {
		Inventory menu = Bukkit.createInventory(null, 27, Format.color("&c&lPickaxe Options"));
		borders(menu);
		ItemStack i = new ItemStack(Material.SLIME_BALL);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&lEnchanting"));
		l.add(Format.color("&7"));
		l.add(Format.color("&b● &7Click to go to the"));
		l.add(Format.color("&7 &7 enchanting menu"));
		m.setLore(l);
		i.setItemMeta(m);
		menu.addItem(i);
		ItemStack i2 = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta m2 = i2.getItemMeta();
		List<String> l2 = new ArrayList<String>();
		m2.setDisplayName(Format.color("&c&lLevelling"));
		l2.add(Format.color("&7"));
		l2.add(Format.color("&b● &7Click to go to the"));
		l2.add(Format.color("&7 &7 levelling menu"));
		m2.setLore(l2);
		i2.setItemMeta(m2);
		menu.addItem(i2);
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
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lEnchanting"))) {
						if (i.getType() == Material.SLIME_BALL) {
							p.closeInventory();
							EnchantGUI.open.add(p);
							p.openInventory(EnchantGUI.menu(p));
							return;
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lLevelling"))) {
						if (i.getType() == Material.EXP_BOTTLE) {
							p.closeInventory();
							ExpGUI.open.add(p);
							p.openInventory(ExpGUI.menu(p));
							return;
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
		inv.setItem(10, i);
		inv.setItem(12, i);
		inv.setItem(13, i);
		inv.setItem(14, i);
		inv.setItem(16, i);
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
