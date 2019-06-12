package me.askingg.mayhem.enchant.enchants;

import java.util.Random;

import org.bukkit.entity.Player;

import me.askingg.mayhem.enchant.EnchantCore;

public class TokenHunter {

	public static Double tokens(Player p) {
		Integer max = 750;
		Integer min = 200;
		if (EnchantCore.hasCE(p, "TokenHunter")) {
			max = max + (EnchantCore.level(p, "TokenHunter") * 150);
			min = min + (EnchantCore.level(p, "TokenHunter") * 50);
		}
		Random r = new Random();
		return (r.nextInt(max - min) + min + 1.0);
	}

	public static Integer max(Player p) {
		Integer i = 500;
		if (EnchantCore.hasCE(p, "TokenHunter")) {
			i = i + (EnchantCore.level(p, "TokenHunter") * 150);
		}
		return i;
	}

	public static Integer min(Player p) {
		Integer i = 100;
		if (EnchantCore.hasCE(p, "TokenHunter")) {
			i = i + (EnchantCore.level(p, "TokenHunter") * 50);
		}
		return i;
	}
}
