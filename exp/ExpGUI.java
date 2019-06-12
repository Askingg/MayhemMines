package me.askingg.mayhem.exp;

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

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class ExpGUI implements Listener {

	public static List<Player> open = new ArrayList<Player>();

	public static Inventory menu(Player p) {
		Inventory menu = Bukkit.createInventory(null, 45, Format.color("&c&lPickaxe Options"));
		borders(menu);
		ItemStack i = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&lLevel Up"));
		l.add(Format.color("&7"));
		l.add(Format.color("&b● &7Click to level up"));
		l.add(Format.color("&7 &7 your pickaxe"));
		l.add(Format.color(Format.color("&7")));
		if (p.getLevel() >= EXPCore.levelCost(p, EXPCore.level(p.getInventory().getItemInMainHand()))) {
			l.add(Format.color("&b" + Format.decimals(0, p.getLevel() + 0.0) + "&8/&b" + Format.decimals(0,
					(double) Math.round(EXPCore.levelCost(p, EXPCore.level(p.getInventory().getItemInMainHand()))))));
		} else {
			l.add(Format.color("&c" + Format.decimals(0, p.getLevel() + 0.0) + "&8/&b" + Format.decimals(0,
					(double) Math.round(EXPCore.levelCost(p, EXPCore.level(p.getInventory().getItemInMainHand()))))));
		}
		m.setLore(l);
		i.setItemMeta(m);
		menu.addItem(i);
		l = new ArrayList<String>();
		i.setType(Material.SLIME_BALL);
		m.setDisplayName(Format.color("&c&lToken Multiplier"));
		l.add(Format.color("&7"));
		l.add(Format.color("&c● &7Current &b+" + EXPCore.tokenBoost(p.getInventory().getItemInMainHand()) + "%"));
		l.add(Format.color("&c● &7Cost &b" + EXPCore.tokenBoostCost(p.getInventory().getItemInMainHand()) + " Points"));
		m.setLore(l);
		i.setItemMeta(m);
		menu.addItem(i);
		l = new ArrayList<String>();
		i.setType(Material.PAPER);
		m.setDisplayName(Format.color("&c&lSell Multiplier"));
		l.add(Format.color("&7"));
		l.add(Format.color("&c● &7Current &b+" + EXPCore.sellBoost(p.getInventory().getItemInMainHand()) + "%"));
		l.add(Format.color("&c● &7Cost &b" + EXPCore.sellBoostCost(p.getInventory().getItemInMainHand()) + " Points"));
		m.setLore(l);
		i.setItemMeta(m);
		menu.addItem(i);
		if (EXPCore.level(p.getInventory().getItemInMainHand()) >= 60) {
			i = new ItemStack(Material.BEACON);
			m = i.getItemMeta();
			l.clear();
			m.setDisplayName(Format.color("&b&lPickaxe Prestige"));
			l.add(Format.color("&7"));
			l.add(Format.color("&7Click to prestige your pickaxe:"));
			l.add(Format.color("&b● &7This will reset the 'Level' of your pickaxe"));
			l.add(Format.color("&b● &7This will give you a random amount of points"));
			l.add(Format.color("&7 &7 &7 between 150 and 200 + every level above 60"));
			l.add(Format.color("&7 &7 &7 gives an additional 25"));
			m.setLore(l);
			i.setItemMeta(m);
		} else {
			i = new ItemStack(Material.BARRIER);
			m = i.getItemMeta();
			l.clear();
			m.setDisplayName(Format.color("&c&lPrestige Unavailable"));
			l.add(Format.color("&7"));
			l.add(Format.color("&cAvailable once your pickaxe's level"));
			l.add(Format.color("&cis greater than or equal to 60"));
			m.setLore(l);
			i.setItemMeta(m);
		}
		menu.addItem(i);
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
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lLevel Up"))) {
						if (i.getType() == Material.EXP_BOTTLE) {
							if (p.getLevel() >= EXPCore.levelCost(p,
									EXPCore.level(p.getInventory().getItemInMainHand()))) {
								Integer x = p.getLevel() - (int) Math.round((int) Math.round(
										EXPCore.levelCost(p, EXPCore.level(p.getInventory().getItemInMainHand()))));
								p.setLevel(x);
								EXPCore.levelUp(p, p.getInventory().getItemInMainHand());
								ExpGUI.open.add(p);
								p.openInventory(ExpGUI.menu(p));
							} else {
								Message.player("&7Sorry, but you don't have the &c"
										+ Format.decimals(0,
												(double) Math.round(EXPCore.levelCost(p,
														EXPCore.level(p.getInventory().getItemInMainHand()))))
										+ " &7exp levels required", p);
							}
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lToken Multiplier"))) {
						if (i.getType() == Material.SLIME_BALL) {
							if (EXPCore.points(p.getInventory().getItemInMainHand()) >= EXPCore
									.tokenBoostCost(p.getInventory().getItemInMainHand())) {
								EXPCore.addTokenBoost(p, p.getInventory().getItemInMainHand());
								Message.player("&b+ 1% Token Boost", p);
								ExpGUI.open.add(p);
								p.openInventory(ExpGUI.menu(p));
							} else {
								Message.player("&7Sorry, but you don't have enough points", p);
							}
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lSell Multiplier"))) {
						if (i.getType() == Material.PAPER) {
							if (EXPCore.points(p.getInventory().getItemInMainHand()) >= EXPCore
									.sellBoostCost(p.getInventory().getItemInMainHand())) {
								EXPCore.addSellBoost(p, p.getInventory().getItemInMainHand());
								Message.player("&b+ 1% Sell Boost", p);
								ExpGUI.open.add(p);
								p.openInventory(ExpGUI.menu(p));
							} else {
								Message.player("&7Sorry, but you don't have enough points", p);
							}
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&b&lPickaxe Prestige"))) {
						if (i.getType() == Material.BEACON) {
							if (EXPCore.level(p.getInventory().getItemInMainHand()) >= 60) {
								EXPCore.Prestige(p, p.getInventory().getItemInMainHand());
								return;
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
		i2.setDurability((byte) 11);
		ItemMeta m2 = i2.getItemMeta();
		m2.setDisplayName(Format.color("&7"));
		i2.setItemMeta(m2);
		ItemStack i3 = new ItemStack(Material.STAINED_GLASS_PANE);
		i3.setDurability((byte) 3);
		ItemMeta m3 = i3.getItemMeta();
		m3.setDisplayName(Format.color("&7"));
		i3.setItemMeta(m3);
		ItemStack i4 = new ItemStack(Material.STAINED_GLASS_PANE);
		i4.setDurability((byte) 14);
		ItemMeta m4 = i4.getItemMeta();
		m4.setDisplayName(Format.color("&7"));
		i4.setItemMeta(m4);
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
		inv.setItem(11, i3);
		inv.setItem(12, i2);
		inv.setItem(14, i2);
		inv.setItem(15, i3);
		inv.setItem(16, i);
		inv.setItem(17, i);
		inv.setItem(18, i);
		inv.setItem(19, i4);
		inv.setItem(21, i4);
		inv.setItem(22, i);
		inv.setItem(23, i4);
		inv.setItem(25, i4);
		inv.setItem(26, i);
		inv.setItem(27, i);
		inv.setItem(28, i);
		inv.setItem(29, i);
		inv.setItem(30, i);
		inv.setItem(32, i);
		inv.setItem(33, i);
		inv.setItem(34, i);
		inv.setItem(35, i);
		inv.setItem(36, i);
		inv.setItem(37, i);
		inv.setItem(38, i);
		inv.setItem(39, i);
		inv.setItem(40, i);
		inv.setItem(41, i);
		inv.setItem(42, i);
		inv.setItem(43, i);
		inv.setItem(44, i);
	}

	@EventHandler
	public void close(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
		}
	}

}
