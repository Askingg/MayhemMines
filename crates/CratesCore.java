package me.askingg.mayhem.crates;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Files;

public class CratesCore {

	public static Boolean canOpen = true;
	public static HashMap<String, Double> rarityToChance = new HashMap<String, Double>();
	public static HashMap<String, String> rarityToColor = new HashMap<String, String>();
	public static HashMap<String, Double> totalChance = new HashMap<String, Double>();
	public static HashMap<String, ConfigurationSection> cratePrizes = new HashMap<String, ConfigurationSection>();

	public static void setupCrates() {
		rarityToChance.put("Common", 20.0);
		rarityToChance.put("Uncommon", 15.0);
		rarityToChance.put("Rare", 10.0);
		rarityToChance.put("Epic", 7.0);
		rarityToChance.put("Legendary", 5.0);
		rarityToChance.put("Ultimate", 1.0);
		rarityToChance.put("Hopeless", 0.1);
		rarityToChance.put("Insane", 0.01);
		rarityToChance.put("Absurd", 0.001);
		rarityToChance.put("Impossible", 0.0001);
		rarityToColor.put("Common", "&7Common");
		rarityToColor.put("Uncommon", "&fUncommon");
		rarityToColor.put("Rare", "&aRare");
		rarityToColor.put("Epic", "&dEpic");
		rarityToColor.put("Legendary", "&eLegendary");
		rarityToColor.put("Ultimate", "&b&lUltimate");
		rarityToColor.put("Hopeless", "&c&lHopeless");
		rarityToColor.put("Insane", "&3&lInsane");
		rarityToColor.put("Absurd", "&6&lAbsurd");
		rarityToColor.put("Impossible", "&4&lIMPOSSIBLE");
		for (String key : Files.config.getConfigurationSection("Crates").getKeys(false)) {
			if (key.equals("Locations")) {
				continue;
			}
			totalChance.put(key, getTotalChance(key));
			cratePrizes.put(key, Files.config.getConfigurationSection("Crates." + key + ".Prizes"));

		}

	}

	public static Double getTotalChance(String crate) {
		double d = 0.0;
		for (String str : Files.config.getConfigurationSection("Crates." + crate + ".Prizes").getKeys(false)) {
			d += rarityToChance.get(Files.config.getString("Crates." + crate + ".Prizes." + str + ".rarity"));
		}
		return d;
	}

	public static void openCrate(Player p, String crate) {
		Random r = new Random();
		double prize = 0.0 + (totalChance.get(crate) - 0.0) * r.nextDouble();
		double total = 0.0;

		ConfigurationSection sec = (ConfigurationSection) cratePrizes.get(crate);

		for (String str : sec.getKeys(false)) {
			total += rarityToChance.get(sec.getString(str + ".rarity"));
			if (total >= prize) {
				for (String cmd : sec.getStringList(str + ".commands")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("%player%", p.getName()));
				}
				return;
			}
		}
	}
}