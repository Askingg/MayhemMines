package me.askingg.mayhem.enchant.enchants;

import java.util.Random;

import org.bukkit.entity.Player;

import me.askingg.mayhem.enchant.EnchantCore;

public class GangCredits {

	public static Double credits(Player p) {
		Integer max = 10;
		Integer min = 4;
		if (EnchantCore.hasCE(p, "GangCredits")) {
			max = max + (EnchantCore.level(p, "GangCredits") * 5);
			min = min + (EnchantCore.level(p, "GangCredits") * 2);
		}
		Random r = new Random();
		return (r.nextInt(max - min) + min + 1.0);
	}

	public static Integer max(Player p) {
		Integer i = 10;
		if (EnchantCore.hasCE(p, "GangCredits")) {
			i = i + (EnchantCore.level(p, "GangCredits") * 5);
		}
		return i;
	}

	public static Integer min(Player p) {
		Integer i = 4;
		if (EnchantCore.hasCE(p, "GangCredits")) {
			i = i + (EnchantCore.level(p, "GangCredits") * 2);
		}
		return i;
	}
}
