package me.askingg.mayhem.echo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Format;

public class EchoUpgradeGUI implements Listener {

	public static List<Player> open = new ArrayList<Player>();

	public static Inventory menu(Player p, String type) { // Player, <Artefact/Relic>
		Inventory menu = Bukkit.createInventory(null, 27, Format.color("&c&l" + type + " Upgrade"));
		borders(menu);
		
		return menu;
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (open.contains(p)) {
			
		}
	}

	@EventHandler
	public void close(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
		}
	}

	private static void borders(Inventory menu) {
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 7);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		menu.setItem(0, i);
		menu.setItem(1, i);
		menu.setItem(2, i);
		menu.setItem(3, i);
		//SIGN: menu.setItem(4, i);
		menu.setItem(5, i);
		menu.setItem(6, i);
		menu.setItem(7, i);
		menu.setItem(8, i);
		menu.setItem(9, i);
		menu.setItem(10, i);
		//RELIC/ARTEFACT SLOTmenu.setItem(13, i);
		menu.setItem(16, i);
		menu.setItem(17, i);
		menu.setItem(18, i);
		menu.setItem(19, i);
		menu.setItem(20, i);
		menu.setItem(21, i);
		//CONFIRM: menu.setItem(22, i);
		menu.setItem(23, i);
		menu.setItem(24, i);
		menu.setItem(25, i);
		menu.setItem(26, i);
		i.setDurability((byte) 15);
		menu.setItem(11, i);
		menu.setItem(12, i);
		menu.setItem(14, i);
		menu.setItem(15, i);
}
}