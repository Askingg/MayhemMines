package me.askingg.mayhem.reports;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import me.askingg.mayhem.utils.Format;

public class ReportsGUI implements Listener {

	public static ArrayList<Player> open = new ArrayList<Player>();
	
	public static Inventory menu(Player p) {
		open.add(p);
		Inventory inv = Bukkit.createInventory(null, 36, Format.color("&c&lReports menu"));
		
		return inv;
	}
}
