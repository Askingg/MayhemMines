package me.askingg.mayhem.enchant.enchants;

import org.bukkit.entity.Player;

import me.askingg.mayhem.enchant.EnchantCore;

public class Lazy {

	public static Double reducedCost(Player p) {
		if (EnchantCore.hasCE(p, "Lazy")) {
			return (EnchantCore.level(p, "Lazy") * 0.5) / 100;
		}
		return 0.0;
	}
}
