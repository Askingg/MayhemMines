package me.askingg.mayhem.autosell;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import me.askingg.mayhem.echo.EchoCore;
import me.askingg.mayhem.exp.EXPCore;
import me.askingg.mayhem.gangs.GangCore;
import me.askingg.mayhem.multiplier.MultiplierCore;
import me.askingg.mayhem.rankup.RankupCore;
import me.askingg.mayhem.utils.Files;

public class AutosellCore {

	public static Double worth(Player p, Material mat, int data) {
		for (String str : Files.config.getConfigurationSection("Shops").getKeys(false)) {
			if (p.hasPermission("mayhem.shops." + str)) {
				for (String st : Files.config.getConfigurationSection("Shops." + str).getKeys(false)) {
					String s[] = st.split("\\;");
					if (s[0].toUpperCase().equals(mat.toString())) {
						if (s[1].equals("" + data)) {
							if (p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
								Double worth = Files.config.getDouble("Shops." + str + "." + st);
								if (p.getInventory().getItemInMainHand().getItemMeta()
										.hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)) {
									Random r = new Random();
									Integer c = r.nextInt(100) + 1;
									if (c <= 50) {
										worth = worth * ((r.nextInt((p.getInventory().getItemInMainHand().getItemMeta()
												.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS)) / 10) + 1));
									}
									if (c <= 80 && c > 50) {
										worth = worth * ((r.nextInt((p.getInventory().getItemInMainHand().getItemMeta()
												.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS)) / 7) + 1));
									}
									if (c <= 95 && c > 80) {
										worth = worth * ((r.nextInt((p.getInventory().getItemInMainHand().getItemMeta()
												.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS)) / 5) + 1));
									}
									if (c > 95) {
										worth = worth * (r.nextInt((p.getInventory().getItemInMainHand().getItemMeta()
												.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS))) + 1);
									}
								}
								return worth;
							}
						}
					}
				}
			}
		}
		return 0.0;
	}

	public static Double multi(Player p) {
		double d = 0.0;
		d = d + personalMulti(p) + permissionMulti(p) + rankupMulti(p) + relicMulti(p) + gangMulti(p) + pickaxeBoostMulti(p);
		return d;
	}

	public static Double personalMulti(Player p) {
		if (MultiplierCore.moneyMulti.containsKey(p)) {
			return MultiplierCore.moneyMulti.get(p);
		}
		return 0.0;
	}
	
	public static Double permissionMulti(Player p) {
		if (p.hasPermission("mayhem.sell.multi.pluto")) {
			return 1.25;
		}
		if (p.hasPermission("mayhem.sell.multi.neptune")) {
			return 1.0;
		}
		if (p.hasPermission("mayhem.sell.multi.uranus")) {
			return 0.8;
		}
		if (p.hasPermission("mayhem.sell.multi.saturn")) {
			return 0.6;
		}
		if (p.hasPermission("mayhem.sell.multi.jupiter")) {
			return 0.5;
		}
		if (p.hasPermission("mayhem.sell.multi.mars")) {
			return 0.4;
		}
		if (p.hasPermission("mayhem.sell.multi.earth")) {
			return 0.3;
		}
		if (p.hasPermission("mayhem.sell.multi.venus")) {
			return 0.2;
		}
		if (p.hasPermission("mayhem.sell.multi.mercury")) {
			return 0.1;
		}
		return 0.0;
	}

	public static Double rankupMulti(Player p) {
		return (0.01 * RankupCore.rank(p));
	}

	public static Double relicMulti(Player p) {
		if (EchoCore.hasEquippedRelic(p)) {
			return (EchoCore.getRelicBoost(p) / 100.0);
		}
		return 0.0;
	}

	public static Double gangMulti(Player p) {
		if (GangCore.hasGang(p)) {
			return (Files.data.getDouble("Gangs." + GangCore.getGang(p) + ".Boost.Money") / 100);
		}
		return 0.0;
	}

	public static Double pickaxeBoostMulti(Player p) {
		if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
			return ((EXPCore.sellBoost(p.getInventory().getItemInMainHand()) + 0.0) / 100);
		}
		return 0.0;
	}
}
