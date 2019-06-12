package me.askingg.mayhem.tokens;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.echo.EchoCore;
import me.askingg.mayhem.exp.EXPCore;
import me.askingg.mayhem.gangs.GangCore;
import me.askingg.mayhem.multiplier.MultiplierCore;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class TokensCore {

	public static ItemStack tokenStack(Integer x) {
		ItemStack i = new ItemStack(Material.SLIME_BALL);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&lToken&b&lStack"));
		l.add(Format.color("&7"));
		l.add(Format.color("&7Tokens &b" + x));
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

	public static Boolean isTokenStack(ItemStack i) {
		if (i.getType() == Material.SLIME_BALL) {
			if (i.hasItemMeta()) {
				ItemMeta m = i.getItemMeta();
				if (m.hasDisplayName()) {
					if (m.getDisplayName().equals(Format.color("&c&lToken&b&lStack"))) {
						if (m.hasLore()) {
							if (m.getLore().get(1).contains(Format.color("Tokens"))) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static Integer getTokenStackWorth(ItemStack i) {
		return Integer.valueOf(ChatColor.stripColor(Format.color(i.getItemMeta().getLore().get(1))).split(" ")[1]);
	}

	public static Double balance(Player p) {
		return Files.data.getDouble("Users." + p.getUniqueId() + ".Tokens");
	}

	public static Boolean hasTokens(Player p, Double d) {
		if (Files.data.getDouble("Users." + p.getUniqueId() + ".Tokens") >= d) {
			return true;
		}
		return false;
	}

	public static void add(Player p, Double d) {
		try {
			d = d * multi(p);
			Files.data.set("Users." + p.getUniqueId() + ".Tokens",
					Files.data.getDouble("Users." + p.getUniqueId() + ".Tokens") + d);
			Files.data.save(Files.dataFile);
			Message.player("&a+ " + Format.decimals(0, d) + " Tokens", p);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void addNoMulti(Player p, Double d) {
		try {
			Files.data.set("Users." + p.getUniqueId() + ".Tokens",
					Files.data.getDouble("Users." + p.getUniqueId() + ".Tokens") + d);
			Files.data.save(Files.dataFile);
			Message.player("&a+ " + Format.decimals(0, d) + " Tokens", p);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void addNoMsg(Player p, Double d) {
		try {
			d = d * (multi(p) + 1);
			Files.data.set("Users." + p.getUniqueId() + ".Tokens",
					Files.data.getDouble("Users." + p.getUniqueId() + ".Tokens") + d);
			Files.data.save(Files.dataFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void addNoMultiNoMsg(Player p, Double d) {
		try {
			Files.data.set("Users." + p.getUniqueId() + ".Tokens",
					Files.data.getDouble("Users." + p.getUniqueId() + ".Tokens") + d);
			Files.data.save(Files.dataFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void remove(Player p, Double d) {
		try {
			Files.data.set("Users." + p.getUniqueId() + ".Tokens",
					Files.data.getDouble("Users." + p.getUniqueId() + ".Tokens") - d);
			Files.data.save(Files.dataFile);
			Message.player("&c- " + Format.decimals(0, d) + " Tokens", p);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Double multi(Player p) {
		Double d = 0.0;
		d = d + gangMulti(p) + personalMulti(p) + permissionMulti(p) + artefactMulti(p) + pickaxeBoostMulti(p);
		return d;
	}
	
	public static Double personalMulti(Player p) {
		if (MultiplierCore.tokenMulti.containsKey(p)) {
			return MultiplierCore.tokenMulti.get(p);
		}
		return 0.0;
	}

	public static Double permissionMulti(Player p) {
		if (p.hasPermission("mayhem.sell.multi.pluto")) {
			return 1.0;
		}
		if (p.hasPermission("mayhem.sell.multi.neptune")) {
			return 0.8;
		}
		if (p.hasPermission("mayhem.sell.multi.uranus")) {
			return 0.7;
		}
		if (p.hasPermission("mayhem.sell.multi.saturn")) {
			return 0.6;
		}
		if (p.hasPermission("mayhem.sell.multi.jupiter")) {
			return 0.5;
		}
		if (p.hasPermission("mayhem.sell.multi.mars")) {
			return 0.3;
		}
		if (p.hasPermission("mayhem.sell.multi.earth")) {
			return 0.2;
		}
		if (p.hasPermission("mayhem.sell.multi.venus")) {
			return 0.1;
		}
		if (p.hasPermission("mayhem.sell.multi.mercury")) {
			return 0.05;
		}
		return 0.0;
	}

	public static Double gangMulti(Player p) {
		if (GangCore.hasGang(p)) {
			return (Files.data.getDouble("Gangs." + GangCore.getGang(p) + ".Boost.Token") / 100);
		}
		return 0.0;
	}

	public static Double artefactMulti(Player p) {
		if (EchoCore.hasEquippedArtefact(p)) {
			return (EchoCore.getArtefactBoost(p) / 100.0);
		}
		return 0.0;
	}

	public static Double pickaxeBoostMulti(Player p) {
		if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
			return ((EXPCore.tokenBoost(p.getInventory().getItemInMainHand()) + 0.0) / 100);
		}
		return 0.0;
	}
}
