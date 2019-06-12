package me.askingg.mayhem.enchant.sword;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Format;

public class CECore {

	public static int level(ItemStack i, String str) {
		if (i.hasItemMeta()) {
			ItemMeta m = i.getItemMeta();
			if (m.hasLore()) {
				for (String l : m.getLore()) {
					if (l.contains(str)) {
						return Integer.valueOf(ChatColor.stripColor(Format.color(l)).split(" \\/ ")[1]);
					}
				}
			}
		}
		return 0;
	}

	public static int max(String str) {
		if (str.equalsIgnoreCase("sharpness")) {
			return 50;
		}
		if (str.equalsIgnoreCase("protection")) {
			return 50;
		}
		if (str.equalsIgnoreCase("unbreaking")) {
			return 100;
		}
		if (str.equalsIgnoreCase("splash")) {
			return 10;
		}
		if (str.equalsIgnoreCase("leach")) {
			return 10;
		}
		if (str.equalsIgnoreCase("quake")) {
			return 5;
		}
		if (str.equalsIgnoreCase("leap")) {
			return 3;
		}
		if (str.equalsIgnoreCase("vaccum")) {
			return 1;
		}
		return 0;
	}

	public static Double cost(ItemStack i, String str) {
		if (str.equalsIgnoreCase("sharpness")) {
			if (i.getEnchantmentLevel(Enchantment.DAMAGE_ALL) == 0) {
				return 20.0;
			} else {
				return Math.round((20 * Math.pow(1 + 0.2, i.getEnchantmentLevel(Enchantment.DAMAGE_ALL)))) + 0.0;
			}
		}
		if (str.equalsIgnoreCase("protection")) {
			if (i.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 0) {
				return 25.0;
			} else {
				return Math.round(
						(25 * Math.pow(1 + 0.15, i.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL)))) + 0.0;
			}
		}
		if (str.equalsIgnoreCase("unbreaking")) {
			if (i.getEnchantmentLevel(Enchantment.DURABILITY) == 0) {
				return 15.0;
			} else {
				return Math.round((15 * Math.pow(1 + 0.1, i.getEnchantmentLevel(Enchantment.DURABILITY)))) + 0.0;
			}
		}
		if (str.equalsIgnoreCase("splash")) {
			if (CECore.level(i, "Splash") == 0) {
				return 200.0;
			} else {
				return Math.round((200 * Math.pow(1 + 0.3, CECore.level(i, "Splash")))) + 0.0;
			}
		}
		if (str.equalsIgnoreCase("leach")) {
			if (CECore.level(i, "Leach") == 0) {
				return 300.0;
			} else {
				return Math.round((300 * Math.pow(1 + 0.25, CECore.level(i, "Leach")))) + 0.0;
			}
		}
		if (str.equalsIgnoreCase("quake")) {
			if (CECore.level(i, "Quake") == 0) {
				return 150.0;
			} else {
				return Math.round((150 * Math.pow(1 + 0.1, CECore.level(i, "Quake")))) + 0.0;
			}
		}
		if (str.equalsIgnoreCase("leap")) {
			if (CECore.level(i, "leap") == 0) {
				return 500.0;
			} else {
				return 500 + (CECore.level(i, "Leap") * 500.0);
			}
		}
		if (str.equalsIgnoreCase("vaccum")) {
			return 2000.0;
		}
		return 0.0;
	}

	public static boolean isMaxVanilla(ItemStack i, Enchantment ench) {
		if (ench == Enchantment.DAMAGE_ALL) {
			if (i.getEnchantmentLevel(ench) >= max("Sharpness")) {
				return true;
			}
		}
		if (ench == Enchantment.PROTECTION_ENVIRONMENTAL) {
			if (i.getEnchantmentLevel(ench) >= max("Protection")) {
				return true;
			}
		}
		if (ench == Enchantment.DURABILITY) {
			if (i.getEnchantmentLevel(ench) >= max("Unbreaking")) {
				return true;
			}
		}
		return false;
	}
}
