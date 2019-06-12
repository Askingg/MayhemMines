package me.askingg.mayhem.marry;

import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Files;

public class MarryCore {

	public static Boolean isMarried(Player p) {
		if (Files.data.getString("Users." + p.getUniqueId() + ".Married").equals("")) {
			return false;
		}
		return true;
	}

	public static String getPartner(Player p) {
		return Files.data.getString("Users." + p.getUniqueId() + ".Married");
	}

	public static String getPartnerUUID(Player p) {
		return Files.data.getString("Users." + p.getUniqueId() + ".MarriedUUID");
	}
}
