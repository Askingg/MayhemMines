package me.askingg.mayhem.enchant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.askingg.mayhem.autominer.MinerUpgradeCore;
import me.askingg.mayhem.enchant.enchants.ArtefactHunter;
import me.askingg.mayhem.enchant.enchants.CharmHunter;
import me.askingg.mayhem.enchant.enchants.KeyEnchants;
import me.askingg.mayhem.enchant.enchants.RelicHunter;
import me.askingg.mayhem.enchant.enchants.Scavenger;
import me.askingg.mayhem.utils.Files;

public class EnchantCore {

	public static Double cost(String str) {
		if (str.equalsIgnoreCase("efficiency")) {
			return 100.0;
		}
		if (str.equalsIgnoreCase("fortune")) {
			return 250.0;
		}
		if (str.equalsIgnoreCase("scavenger")) {
			return 1000.0;
		}
		if (str.equalsIgnoreCase("tokenhunter")) {
			return 500.0;
		}
		if (str.equalsIgnoreCase("gangcredits")) {
			return 100000.0;
		}
		if (str.equalsIgnoreCase("flight")) {
			return 500000.0;
		}
		if (str.equalsIgnoreCase("gatherer")) {
			return 100000.0;
		}
		if (str.equalsIgnoreCase("lazy")) {
			return 50000.0;
		}
		if (str.equalsIgnoreCase("keyhunter")) {
			return 25000000.0;
		}
		if (str.equalsIgnoreCase("keyquality")) {
			return 10000000.0;
		}
		return null;
	}

	public static Double costIncrease(String str) {
		if (str.equalsIgnoreCase("efficiency")) {
			return 50.0;
		}
		if (str.equalsIgnoreCase("fortune")) {
			return 125.0;
		}
		if (str.equalsIgnoreCase("scavenger")) {
			return 600.0;
		}
		if (str.equalsIgnoreCase("tokenhunter")) {
			return 750.0;
		}
		if (str.equalsIgnoreCase("gangcredits")) {
			return 75000.0;
		}
		if (str.equalsIgnoreCase("flight")) {
			return 0.0;
		}
		if (str.equalsIgnoreCase("charity")) {
			return 0.0;
		}
		if (str.equalsIgnoreCase("gatherer")) {
			return 60000.0;
		}
		if (str.equalsIgnoreCase("lazy")) {
			return 75000.0;
		}
		if (str.equalsIgnoreCase("keyhunter")) {
			return 25000000.0;
		}
		if (str.equalsIgnoreCase("keyquality")) {
			return 35000000.0;
		}
		return null;
	}

	public static Integer maxLevel(Player p, String str) {
		int pres = Files.data.getInt("Users." + p.getUniqueId() + ".Prestige");
		if (str.equalsIgnoreCase("efficiency")) {
			return 250 + (pres * 50);
		}
		if (str.equalsIgnoreCase("fortune")) {
			return 10000 + (pres * 1000);
		}
		if (str.equalsIgnoreCase("scavenger")) {
			return 100 + (pres * 10);
		}
		if (str.equalsIgnoreCase("tokenhunter")) {
			return 2500 + (pres * 250);
		}
		if (str.equalsIgnoreCase("gangcredits")) {
			return 250 + (pres * 50);
		}
		if (str.equalsIgnoreCase("flight")) {
			return 1;
		}
		if (str.equalsIgnoreCase("gatherer")) {
			return 25 + (pres * 5);
		}
		if (str.equalsIgnoreCase("lazy")) {
			return 50 + (pres * 10);
		}
		if (str.equalsIgnoreCase("keyhunter")) {
			return 15 + (pres * 5);
		}
		if (str.equalsIgnoreCase("keyquality")) {
			return 10 + (pres * 5);
		}
		return null;
	}

	public static Integer maxLevelBlood(String str) {
		if (str.equalsIgnoreCase("relichunter")) {
			return 10;
		}
		if (str.equalsIgnoreCase("artefacthunter")) {
			return 10;
		}
		if (str.equalsIgnoreCase("charmhunter")) {
			return 10;
		}
		// Missing echo device item hunter
		// Instant rankup
		// Instant gang levelup
		// Instant pickaxea levelup
		return null;
	}

	public static Integer bloodMaxTotal() {
		Integer i = 0;
		for (String str : bloodEnchantList()) {
			i = i + maxLevelBlood(str);
		}
		return i;
	}

	public static Boolean hasMaxedBloodEnchants(Player p) {
		if (bloodTotal(p) >= bloodMaxTotal()) {
			return true;
		} else {
			return false;
		}
	}

	public static List<String> bloodEnchantList() {
		List<String> l = new ArrayList<String>();
		l.add("RelicHunter");
		l.add("ArtefactHunter");
		l.add("CharmHunter");
		return l;
	}

	public static Integer bloodTotal(Player p) {
		Integer x = 0;
		ItemStack i = p.getInventory().getItemInMainHand();
		if (i != null) {
			if (i.getType().toString().contains("PICKAXE")) {
				if (i.getItemMeta().hasLore()) {
					for (String str : i.getItemMeta().getLore()) {
						for (String s : bloodEnchantList()) {
							if (str.contains(s)) {
								str = ChatColor.stripColor(str);
								String[] lv = str.split(" \\/ ");
								x = x + Integer.valueOf(lv[1]);
							}
						}
					}
				}
			}
		}
		return x;
	}

	public static Double bloodCost(Player p) {
		Double d = 250.0;
		d = d + (bloodTotal(p) * 250);
		return d;
	}

	public static String randomBloodEnchant(Player p) {
		Random r = new Random();
		Boolean b = false;
		while (!b) {
			Integer i = r.nextInt(bloodEnchantList().size());
			String s = bloodEnchantList().get(i);
			if (level(p, s) < maxLevelBlood(s)) {
				return s;
			}
		}
		return null;
	}

	public static String randomColor() {
		Random r = new Random();
		Integer x = r.nextInt(7);
		if (x == 0) {
			return "&a";
		}
		if (x == 1) {
			return "&b";
		}
		if (x == 2) {
			return "&c";
		}
		if (x == 3) {
			return "&d";
		}
		if (x == 4) {
			return "&e";
		}
		if (x == 5) {
			return "&3";
		}
		if (x == 6) {
			return "&9";
		}
		return null;
	}

	public static String randomColor2() {
		Random r = new Random();
		Integer x = r.nextInt(4);
		if (x == 0) {
			return "&2&l";
		}
		if (x == 1) {
			return "&4&l";
		}
		if (x == 2) {
			return "&5&l";
		}
		if (x == 3) {
			return "&6&l";
		}
		return null;
	}

	public static Boolean hasCE(Player p, String s) {
		ItemStack i = p.getInventory().getItemInMainHand();
		if (i != null) {
			if (i.getType().toString().contains("PICKAXE")) {
				if (i.getItemMeta().hasLore()) {
					List<String> l = i.getItemMeta().getLore();
					for (String str : l) {
						if (str.contains(s)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static Boolean hasCE2(ItemStack i, String s) {
		if (i != null) {
			if (i.getType().toString().contains("PICKAXE")) {
				if (i.getItemMeta().hasLore()) {
					List<String> l = i.getItemMeta().getLore();
					for (String str : l) {
						if (str.contains(s)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static Integer level(Player p, String s) {
		ItemStack i = p.getInventory().getItemInMainHand();
		if (i != null) {
			if (i.getType().toString().contains("PICKAXE")) {
				if (i.getItemMeta().hasLore()) {
					List<String> l = i.getItemMeta().getLore();
					for (String str : l) {
						if (str.contains(s)) { // 'Scavenger / 1'
							str = ChatColor.stripColor(str);
							String[] lv = str.split(" \\/ ");
							try {
								return Integer.valueOf(lv[1]);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			}
		}
		return 0;
	}
	public static void applyEnchants(Player p){
		Scavenger.applyScavenger(p);
		RelicHunter.applyRelichunter(p);
		ArtefactHunter.applyArtefactHunter(p);
		CharmHunter.applyCharmHunter(p, false);
		KeyEnchants.applyKeyEnchants(p);

	}

	public static void applyMinerEnchants(Player p){

	    UUID uuid = p.getUniqueId();

	    double tokenMulti = 1 + MinerUpgradeCore.getDoubleResult(p, "MoreTokens");
	    double creditsMulti = 0.5 + MinerUpgradeCore.getDoubleResult(p, "MoreCredits");

	    double extraDroprateRelic = MinerUpgradeCore.getDoubleResult(p,  "DroprateRelic");
	    double minimumRelic = MinerUpgradeCore.getDoubleResult(p,  "MinimumRelic");
	    double extraDroprateArtefact = MinerUpgradeCore.getDoubleResult(p,  "DroprateArtefact");
	    double minimumArtefact = Files.data.getDouble("Users." + uuid + ".MinerUpgrades.MinimumArtefact");

	    boolean rewards = MinerUpgradeCore.getBooleanResult(p, "RewardsInventory");

	    Scavenger.applyScavenger(p, tokenMulti, creditsMulti);
        RelicHunter.applyRelichunter(p, extraDroprateRelic, minimumRelic, rewards);
        ArtefactHunter.applyArtefactHunter(p, extraDroprateArtefact, minimumArtefact, rewards);
        CharmHunter.applyCharmHunter(p, rewards);
        KeyEnchants.applyKeyEnchants(p);

    }
}
