package me.askingg.mayhem.enchant.sword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.enchant.EnchantCore;
import me.askingg.mayhem.entities.dungeons.DungeonsCore;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class CEGui implements Listener {

	public static List<Player> open = new ArrayList<Player>();

	public static Inventory menu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, Format.color("&c&lCustomEnchant Menu"));
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
		inv.setItem(11, i);
		inv.setItem(17, i);
		inv.setItem(18, i);
		inv.setItem(20, i);
		inv.setItem(26, i);
		inv.setItem(27, i);
		inv.setItem(29, i);
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
		i.setDurability((byte) 11);
		m.setDisplayName(Format.color("&bPlace item between the blue glass"));
		i.setItemMeta(m);
		inv.setItem(10, i);
		inv.setItem(28, i);
		i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 8);
		m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		while (inv.firstEmpty() != -1) {
			inv.setItem(inv.firstEmpty(), i);
		}
		return inv;
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		if (open.contains(e.getWhoClicked())) {
			Player p = (Player) e.getWhoClicked();
			ItemStack ci = e.getCurrentItem();
			if (ci.getType().toString().contains("GLASS_PANE")) {
				e.setCancelled(true);
			}
			if (e.getRawSlot() < 44) {
				e.setCancelled(true);
				if (ci.getType().equals(Material.BOOK)
						&& (e.getCursor() == null || e.getCursor().getType().equals(Material.AIR))) {
					ItemMeta cm = ci.getItemMeta();
					if (cm.hasDisplayName()) {
						ItemStack i = e.getInventory().getItem(19);
						if (cm.getDisplayName().contains(Format.color("&c&lSharpness"))) {
							if (!CECore.isMaxVanilla(i, Enchantment.DAMAGE_ALL)) {
								if (DungeonsCore.getFlesh(p) >= CECore.cost(i, "Sharpness")) {
									applyEnchant(p, i, e.getRawSlot(), Enchantment.DAMAGE_ALL, e.getInventory(),
											Arrays.asList("&7Deal more damage"));
									return;
								}
							}
						}
						if (cm.getDisplayName().contains(Format.color("&c&lProtection"))) {
							if (!CECore.isMaxVanilla(i, Enchantment.PROTECTION_ENVIRONMENTAL)) {
								if (DungeonsCore.getFlesh(p) >= CECore.cost(i, "Protection")) {
									applyEnchant(p, i, e.getRawSlot(), Enchantment.PROTECTION_ENVIRONMENTAL,
											e.getInventory(), Arrays.asList("&7Take less damage"));
									return;
								}
							}
						}
						if (cm.getDisplayName().contains(Format.color("&c&lUnbreaking"))) {
							if (!CECore.isMaxVanilla(i, Enchantment.DURABILITY)) {
								if (DungeonsCore.getFlesh(p) >= CECore.cost(i, "Unbreaking")) {
									applyEnchant(p, i, e.getRawSlot(), Enchantment.DURABILITY, e.getInventory(),
											Arrays.asList("&7Make your item last longer"));
									return;
								}
							}
						}
						if (cm.getDisplayName().contains(Format.color("&c&lSplash"))) {
							if (CECore.level(i, "Splash") < CECore.max("Splash")) {
								if (DungeonsCore.getFlesh(p) >= CECore.cost(i, "Splash")) {
									applyCustomEnchant(p, i, e.getRawSlot(), "Splash", e.getInventory(),
											Arrays.asList("Deal damage to nearby mobs"));
								}
							}
						}
						if (cm.getDisplayName().contains(Format.color("&c&lQuake"))) {
							if (CECore.level(i, "Quake") < CECore.max("Quake")) {
								if (DungeonsCore.getFlesh(p) >= CECore.cost(i, "Quake")) {
									applyCustomEnchant(p, i, e.getRawSlot(), "Quake", e.getInventory(),
											Arrays.asList("Launch nearby mobs away from you"));
								}
							}
						}
						if (cm.getDisplayName().contains(Format.color("&c&lLeach"))) {
							if (CECore.level(i, "Leach") < CECore.max("Leach")) {
								if (DungeonsCore.getFlesh(p) >= CECore.cost(i, "Leach")) {
									applyCustomEnchant(p, i, e.getRawSlot(), "Leach", e.getInventory(),
											Arrays.asList("Heal yourself when dealing damage"));
								}
							}
						}
						if (cm.getDisplayName().contains(Format.color("&c&lLeap"))) {
							if (CECore.level(i, "Leap") < CECore.max("Leap")) {
								if (DungeonsCore.getFlesh(p) >= CECore.cost(i, "Leap")) {
									applyCustomEnchant(p, i, e.getRawSlot(), "Leap", e.getInventory(),
											Arrays.asList("RightClick to launch yourself forwards"));
								}
							}
						}
						if (cm.getDisplayName().contains(Format.color("&c&lVaccum"))) {
							if (CECore.level(i, "Vaccum") < CECore.max("Vaccum")) {
								if (DungeonsCore.getFlesh(p) >= CECore.cost(i, "Vaccum")) {
									applyCustomEnchant(p, i, e.getRawSlot(), "Vaccum", e.getInventory(),
											Arrays.asList("Instantly collect Flesh from killed mobs"));
								}
							}
						}
					}
				}
			}
			if (e.getRawSlot() == 19) {
				if (e.getCursor() != null) {
					if (ci.getType().equals(Material.STAINED_GLASS_PANE)) {
						String t = e.getCursor().getType().toString();
						if (!(t.contains("SWORD") || t.contains("HELMET") || t.contains("CHESTPLATE")
								|| t.contains("LEGGINGS") || t.contains("BOOTS"))) {
							return;
						}
						Inventory inv = e.getInventory();
						enchantBooks(t, e.getCursor(), inv);
						inv.setItem(19, e.getCursor());
						e.getCursor().setType(Material.AIR);
						;
						p.updateInventory();
						return;
					}
				} else {
					if (!ci.getType().equals(Material.STAINED_GLASS_PANE)) {
						if (p.getInventory().firstEmpty() != -1) {
							p.getInventory().addItem(e.getInventory().getItem(19));
							p.updateInventory();
							ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
							i.setDurability((byte) 8);
							ItemMeta m = i.getItemMeta();
							m.setDisplayName(Format.color("&7"));
							i.setItemMeta(m);
							e.getInventory().setItem(19, i);
							return;
						} else {
							Message.player("&7Sorry, but your inventory is full", p);
							return;
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void close(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if (open.contains(p)) {
			open.remove(p);
			if (e.getInventory().getItem(19) != null
					&& e.getInventory().getItem(19).getType() != Material.STAINED_GLASS_PANE) {
				p.getInventory().addItem(e.getInventory().getItem(19));
				p.updateInventory();
				return;
			}
		}
	}

	private static void applyCustomEnchant(Player p, ItemStack i, int slot, String ench, Inventory inv,
			List<String> desc) {
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		if (m.hasLore()) {
			l = m.getLore();
			int x = -1;
			for (String str : l) {
				x++;
				if (str.contains(ench)) {
					DungeonsCore.removeFlesh(p, (int) Math.round(CECore.cost(i, ench)));
					String s = EnchantCore.randomColor();
					l.set(x, Format.color(s + ench + " &8/ " + s + (CECore.level(i, str) + 1)));
					m.setLore(l);
					i.setItemMeta(m);
					inv.setItem(slot, bookCustom(i, ench, desc));
					Message.player("&b+ 1 " + ench + " level", p);
					return;
				}
			}
			DungeonsCore.removeFlesh(p, (int) Math.round(CECore.cost(i, ench)));
			String s = EnchantCore.randomColor();
			l.add(Format.color(s + ench + " &8/ " + s + "1"));
			m.setLore(l);
			i.setItemMeta(m);
			inv.setItem(slot, bookCustom(i, ench, desc));
			Message.player("&b+ 1 " + ench + " level", p);
			return;
		} else {
			DungeonsCore.removeFlesh(p, (int) Math.round(CECore.cost(i, ench)));
			String s = EnchantCore.randomColor();
			l.add(Format.color(s + ench + " &8/ " + s + "1"));
			m.setLore(l);
			i.setItemMeta(m);
			inv.setItem(slot, bookCustom(i, ench, desc));
			Message.player("&b+ 1 " + ench + " level", p);
			return;
		}
	}

	private static void applyEnchant(Player p, ItemStack i, int slot, Enchantment ench, Inventory inv,
			List<String> desc) {
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		if (!m.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
			m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		i.setItemMeta(m);
		String enchs = "";
		if (ench.equals(Enchantment.DAMAGE_ALL)) {
			enchs = "Sharpness";
		}
		if (ench.equals(Enchantment.PROTECTION_ENVIRONMENTAL)) {
			enchs = "Protection";
		}
		if (ench.equals(Enchantment.DURABILITY)) {
			enchs = "Unbreaking";
		}
		if (m.hasEnchant(ench)) {
			m.addEnchant(ench, m.getEnchantLevel(ench) + 1, true);
		} else {
			m.addEnchant(ench, 1, true);
		}
		if (m.hasLore()) {
			l = m.getLore();
			int x = -1;
			for (String str : l) {
				x++;
				if (str.contains(enchs)) {
					DungeonsCore.removeFlesh(p, (int) Math.round(CECore.cost(i, enchs)));
					String s = EnchantCore.randomColor();
					l.set(x, Format.color(s + enchs + " &8/ " + s + m.getEnchantLevel(ench)));
					m.setLore(l);
					i.setItemMeta(m);
					inv.setItem(slot, bookVanilla(i, enchs, ench, desc));
					Message.player("&b+ 1 " + enchs + " level", p);
					return;
				}
			}
			DungeonsCore.removeFlesh(p, (int) Math.round(CECore.cost(i, enchs)));
			String s = EnchantCore.randomColor();
			l.add(Format.color(s + enchs + " &8/ " + s + m.getEnchantLevel(ench)));
			m.setLore(l);
			i.setItemMeta(m);
			inv.setItem(slot, bookVanilla(i, enchs, ench, desc));
			Message.player("&b+ 1 " + enchs + " level", p);
			return;
		} else {
			DungeonsCore.removeFlesh(p, (int) Math.round(CECore.cost(i, enchs)));
			String s = EnchantCore.randomColor();
			l.add(Format.color(s + enchs + " &8/ " + s + m.getEnchantLevel(ench)));
			m.setLore(l);
			i.setItemMeta(m);
			inv.setItem(slot, bookVanilla(i, enchs, ench, desc));
			Message.player("&b+ 1 " + enchs + " level", p);
			return;
		}
	}

	public static ItemStack bookCustom(ItemStack item, String ench, List<String> desc) {
		ItemStack i = new ItemStack(Material.BOOK);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&l" + ench));
		l.add(Format.color("&7"));
		l.add(Format.color("&7Level &c" + CECore.level(item, ench)));
		if (CECore.level(i, ench) < CECore.max(ench)) {
			l.add(Format.color("&7Cost &c" + CECore.cost(item, ench) + " flesh"));
			l.add(Format.color("&7Max &c" + CECore.max(ench)));
		} else {
			l.add(Format.color("&7This enchantment is maxed"));
		}
		l.add(Format.color("&7"));
		for (String str : desc) {
			l.add(Format.color("&7" + str));
		}
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

	public static ItemStack bookVanilla(ItemStack item, String enchant, Enchantment ench, List<String> desc) {
		ItemStack i = new ItemStack(Material.BOOK);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&l" + enchant));
		l.add(Format.color("&7"));
		if (item.getItemMeta().hasEnchant(ench)) {
			l.add(Format.color("&7Level &c" + item.getItemMeta().getEnchantLevel(ench)));
		} else {
			l.add(Format.color("&7Level &c0"));
		}
		if (item.getEnchantmentLevel(ench) < CECore.max(enchant)) {
			l.add(Format.color("&7Cost &c" + CECore.cost(item, enchant) + " flesh"));
			l.add(Format.color("&7Max &c" + CECore.max(enchant)));
		} else {
			l.add(Format.color("&7This enchantment is maxed"));
		}
		l.add(Format.color("&7"));
		for (String str : desc) {
			l.add(Format.color("&7" + str));
		}
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

	public static void enchantBooks(String type, ItemStack item, Inventory inv) {
		ItemStack i = new ItemStack(Material.AIR);
		ItemMeta m = i.getItemMeta();
		inv.setItem(12, i);
		inv.setItem(13, i);
		inv.setItem(14, i);
		inv.setItem(15, i);
		inv.setItem(16, i);
		inv.setItem(21, i);
		inv.setItem(22, i);
		inv.setItem(23, i);
		inv.setItem(24, i);
		inv.setItem(25, i);
		inv.setItem(30, i);
		inv.setItem(31, i);
		inv.setItem(32, i);
		inv.setItem(33, i);
		inv.setItem(34, i);
		if (type.contains("SWORD")) {
			inv.addItem(bookVanilla(item, "Sharpness", Enchantment.DAMAGE_ALL, Arrays.asList("&7Deal more damage")));
		}
		if (type.contains("HELMET") || type.contains("CHESTPLATE") || type.contains("LEGGINGS")
				|| type.contains("BOOTS")) {
			inv.addItem(bookVanilla(item, "Protection", Enchantment.PROTECTION_ENVIRONMENTAL,
					Arrays.asList("&7Take less damage")));
		}
		if (type.contains("SWORD") || type.contains("HELMET") || type.contains("CHESTPLATE")
				|| type.contains("LEGGINGS") || type.contains("BOOTS")) {
			inv.addItem(bookVanilla(item, "Unbreaking", Enchantment.DURABILITY,
					Arrays.asList("&7Make your item last longer")));
		}
		if (type.contains("SWORD")) {
			inv.addItem(bookCustom(item, "Splash", Arrays.asList("&7Deal damage to nearby mobs")));
			inv.addItem(bookCustom(item, "Quake", Arrays.asList("&7Launch nearby mobs away from you")));
			inv.addItem(bookCustom(item, "Leach", Arrays.asList("&7Heal yourself when dealing damage")));
			inv.addItem(bookCustom(item, "Leap", Arrays.asList("RightClick to launch yourself forwards")));
			inv.addItem(bookCustom(item, "Vaccum", Arrays.asList("Instantly collect Flesh from killed mobs")));
		}
		i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 8);
		m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		while (inv.firstEmpty() != -1) {
			inv.setItem(inv.firstEmpty(), i);
		}
	}
}
