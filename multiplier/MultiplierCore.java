package me.askingg.mayhem.multiplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class MultiplierCore {

	public static HashMap<Player, Double> moneyMulti = new HashMap<Player, Double>(); // Player, Multiplier
	public static HashMap<Player, Long> moneyTime = new HashMap<Player, Long>(); // Player, StartTime
	public static HashMap<Player, Double> tokenMulti = new HashMap<Player, Double>(); // Player, Multiplier
	public static HashMap<Player, Long> tokenTime = new HashMap<Player, Long>(); // Player, StartTime

	public static void setupMultipliers() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				if (moneyTime.size() > 0) {
					for (Player p : moneyTime.keySet()) {
						moneyTime.put(p, (moneyTime.get(p) - 1000));
						if (moneyTime.get(p) < 1000) {
							Message.player("Your &c" + moneyMulti.get(p) + "&7 money multiplier expired", p);
							moneyMulti.remove(p);
							moneyTime.remove(p);
						}
					}
				}
				if (tokenTime.size() > 0) {
					for (Player p : tokenTime.keySet()) {
						tokenTime.put(p, (tokenTime.get(p) - 1000));
						if (tokenTime.get(p) < 1000) {
							Message.player("Your &c" + tokenMulti.get(p) + "&7 token multiplier expired", p);
							tokenMulti.remove(p);
							tokenTime.remove(p);
						}
					}
				}
			}
		}, 0, 20);
	}

	public static ItemStack multiItem(String type, double multi, int time) {
		ItemStack i = new ItemStack(Material.CHORUS_FLOWER);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&l" + type + "Multiplier: &b&l" + multi + "x &b&l &b&l " + Format.time(time)));
		l.add(Format.color("&7"));
		l.add(Format.color("&7Type &b" + type));
		l.add(Format.color("&7Multi &b" + multi));
		l.add(Format.color("&7Milis &b" + (time * 1000)));
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

	public static String getType(ItemStack i) {
		ItemMeta m = i.getItemMeta();
		return ChatColor.stripColor(Format.color(m.getLore().get(1))).split(" ")[1];
	}

	public static Double getMulti(ItemStack i) {
		ItemMeta m = i.getItemMeta();
		return Double.valueOf(ChatColor.stripColor(Format.color(m.getLore().get(2))).split(" ")[1]);
	}

	public static Long getTime(ItemStack i) {
		ItemMeta m = i.getItemMeta();
		return Long.valueOf(ChatColor.stripColor(Format.color(m.getLore().get(3))).split(" ")[1]);
	}

	public static Boolean isMultiplier(ItemStack i) {
		if (i != null && i.getType() != Material.AIR) {
			ItemMeta m = i.getItemMeta();
			if (m.hasDisplayName() && m.hasLore()) {
				if (m.getDisplayName().contains(Format.color("Multiplier:"))) {
					if (m.getLore().get(1).contains(Format.color("&7Type"))
							&& m.getLore().get(2).contains(Format.color("&7Multi"))) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
