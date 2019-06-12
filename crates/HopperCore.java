package me.askingg.mayhem.crates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Format;

public class HopperCore implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
//		ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
//	    Is hopper check
	}

	public static ItemStack hopper(String rarity) {
		Random r = new Random();
		int x = r.nextInt(5);
		String c1 = "";
		String c2 = "";
		if (x == 0) {
			c1 = "&f";
			c2 = "&7";
		}
		if (x == 1) {
			c1 = "&b";
			c2 = "&9";
		}
		if (x == 2) {
			c1 = "&a";
			c2 = "&2";
		}
		if (x == 3) {
			c1 = "&c";
			c2 = "&4";
		}
		if (x == 4) {
			c1 = "&d";
			c2 = "&5";
		}
		ItemStack i = new ItemStack(Material.HOPPER);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color(c1 + "&l" + rarity + " KeyHopper"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8● " + c2 + "Rarity " + c1 + rarity));
		l.add(Format.color("&8● " + c2 + "Level  " + c1 + "1"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8● " + c2 + "Argon  " + c1 + "0"));
		l.add(Format.color("&8● " + c2 + "Krypton  " + c1 + "0"));
		l.add(Format.color("&8● " + c2 + "Xenon  " + c1 + "0"));
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

}
