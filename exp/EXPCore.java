package me.askingg.mayhem.exp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.enchant.enchants.Lazy;
import me.askingg.mayhem.utils.Format;

public class EXPCore {

	public static Double firstLevelCost() {
		return 1000.0;
	}

	public static Double levelCost(Player p, Integer i) {
		if (i == 1) {
			return 1000.0;
		}
		if (i > 1) {
			return (firstLevelCost() * Math.pow(1 + 0.375, (i * 0.3)))
					- ((firstLevelCost() * Math.pow(1 + 0.375, (i * 0.3))) * Lazy.reducedCost(p));
		}
		return 0.0;
	}

	public static void levelUp(Player p, ItemStack i) {
		ItemMeta m = i.getItemMeta();
		Random r = new Random();
		if (m.hasLore()) {
			List<String> l = m.getLore();
			if (l.get(1).contains("Level")) {
				l.set(1, Format.color("&9Level &b" + (level(i) + 1)));
				l.set(2, Format.color("&9Cost &b" + Format.decimals(0, (double) Math
						.round(EXPCore.levelCost(p, EXPCore.level(p.getInventory().getItemInMainHand()) + 1)))));
				m.setLore(l);
				i.setItemMeta(m);
				addPoints(i, r.nextInt(50 - 25) + 26);
				p.updateInventory();
			}
		}
	}

	public static void Prestige(Player p, ItemStack i) {
		ItemMeta m = i.getItemMeta();
		Random r = new Random();
		if (m.hasLore()) {
			List<String> l = m.getLore();
			if (l.get(1).contains("Level")) {
				int x = (level(i) - 60) * 25;
				l.set(1, Format.color("&9Level &b1"));
				l.set(2, Format.color("&9Cost &b" + Format.decimals(0, (double) Math.round((EXPCore.levelCost(p, 1))))));
				int y = -1;
				for (String str : m.getLore()) {
					y++;
					if (str.contains("Prestige")) {
						l.set(y, Format.color("&4Prestige &c" + (Integer.valueOf(ChatColor.stripColor(Format.color(str)).split(" ")[1]) + 1)));
						break;
					}
				}
				m.setLore(l);
				i.setItemMeta(m);
				addPoints(i, r.nextInt(200 - 150) + 151 + x);
				p.updateInventory();
			}
		}
	}

	public static Integer level(ItemStack i) {
		ItemMeta m = i.getItemMeta();
		if (m.hasLore()) {
			if (m.getLore().get(1).contains("Level")) {
				String[] s = ChatColor.stripColor(m.getLore().get(1)).split(" ");
				return Integer.valueOf(s[1]);
			}
		}
		return 1;
	}

	public static Integer tokenBoost(ItemStack i) {
		if (i.getType().toString().contains("PICKAXE")) {
			ItemMeta m = i.getItemMeta();
			if (m.hasLore()) {
				for (String str : m.getLore()) {
					if (str.contains("Token Boost")) {
						String[] s = ChatColor.stripColor(Format.color(str)).split("\\%");
						String[] s2 = s[0].split(Format.color("\\+"));
						return Integer.valueOf(s2[1]);
					}
				}
			}
		}
		return 0;
	}

	public static Integer sellBoost(ItemStack i) {
		if (i.getType().toString().contains("PICKAXE")) {
			ItemMeta m = i.getItemMeta();
			if (m.hasLore()) {
				for (String str : m.getLore()) {
					if (str.contains("Sell Boost")) {
						String[] s = ChatColor.stripColor(Format.color(str)).split("\\%");
						String[] s2 = s[0].split(Format.color("\\+"));
						return Integer.valueOf(s2[1]);
					}
				}
			}
		}
		return 0;
	}

	public static Integer tokenBoostCost(ItemStack i) {
		return (5 + (5 * tokenBoost(i)));
	}

	public static Integer sellBoostCost(ItemStack i) {
		return (5 + (5 * sellBoost(i)));
	}

	public static void addTokenBoost(Player p, ItemStack i) {
		ItemMeta m = i.getItemMeta();
		if (m.hasLore()) {
			List<String> l = m.getLore();
			Integer c = -1;
			for (String str : m.getLore()) {
				c++;
				if (str.contains("Points")) {
					String[] s = ChatColor.stripColor(Format.color(str)).split(" ");
					l.set(c, Format.color("&4Points &c" + (Integer.valueOf(s[1]) - tokenBoostCost(i))));
					continue;
				}
				if (str.contains("Token Boost")) {
					l.set(c, Format.color("&c+" + (tokenBoost(i) + 1) + "%&4 Token Boost"));
					continue;
				} else {
					continue;
				}
			}
			m.setLore(l);
			i.setItemMeta(m);
			p.updateInventory();
		}
	}

	public static void addSellBoost(Player p, ItemStack i) {
		ItemMeta m = i.getItemMeta();
		if (m.hasLore()) {
			List<String> l = m.getLore();
			Integer c = -1;
			for (String str : m.getLore()) {
				c++;
				if (str.contains("Points")) {
					String[] s = ChatColor.stripColor(Format.color(str)).split(" ");
					l.set(c, Format.color("&4Points &c" + (Integer.valueOf(s[1]) - sellBoostCost(i))));
					continue;
				}
				if (str.contains("Sell Boost")) {
					l.set(c, Format.color("&c+" + (sellBoost(i) + 1) + "%&4 Sell Boost"));
					continue;
				} else {
					continue;
				}
			}
			m.setLore(l);
			i.setItemMeta(m);
			p.updateInventory();
		}
	}

	public static Integer points(ItemStack i) {
		ItemMeta m = i.getItemMeta();
		if (m.hasLore()) {
			for (String str : m.getLore()) {
				if (str.contains("Points")) {
					String[] s = ChatColor.stripColor(Format.color(str)).split("\\ ");
					return Integer.valueOf(s[1]);
				}
			}
		}
		return 0;
	}

	public static void addPoints(ItemStack i, Integer x) {
		ItemMeta m = i.getItemMeta();
		if (m.hasLore()) {
			Integer c = -1;
			List<String> l = m.getLore();
			for (String str : l) {
				c++;
				if (str.contains("Points")) {
					String[] s = ChatColor.stripColor(Format.color(str)).split(" ");
					l.set(c, Format.color("&4Points &c" + (Integer.valueOf(s[1]) + x)));
					m.setLore(l);
					i.setItemMeta(m);
				}
			}
		}
	}

	public static void removePoints(ItemStack i, Integer x) {
		ItemMeta m = i.getItemMeta();
		if (m.hasLore()) {
			Integer c = -1;
			List<String> l = m.getLore();
			for (String str : l) {
				c++;
				if (str.contains("Points")) {
					String[] s = ChatColor.stripColor(Format.color(str)).split(" ");
					l.set(c, Format.color("&4Points &c" + (Integer.valueOf(s[1]) - x)));
					m.setLore(l);
					i.setItemMeta(m);
				}
			}
		}
	}

	public static void addLore(Player p, ItemStack i) {
		ItemMeta m = i.getItemMeta();
		if (m.hasLore()) {
			if (m.getLore().get(0).equals(Format.color("&8&m+-----------------------+"))) {
				return;
			}
		}
		List<String> l = new ArrayList<String>();
		l.add(Format.color("&8&m+-----------------------+"));
		l.add(Format.color("&9Level &b1"));
		l.add(Format.color("&9Cost &b" + Format.decimals(0, levelCost(p, 1))));
		l.add(Format.color("&8&m-------------------------"));
		// ENCHANTS
		l.add(Format.color("&8&m-------------------------"));
		l.add(Format.color("&4Prestige &c0"));
		l.add(Format.color("&4Points &c0"));
		l.add(Format.color("&c+0%&4 Token Boost"));
		l.add(Format.color("&c+0%&4 Sell Boost"));
		l.add(Format.color("&8&m-------------------------"));
		l.add(Format.color("&5Broken &d0"));
		l.add(Format.color("&8&m+-----------------------+"));
		m.setLore(l);
		i.setItemMeta(m);
		p.updateInventory();
	}
}