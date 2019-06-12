package me.askingg.mayhem.crates;

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

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;

public class PreviewGUI implements Listener {

	public static List<Player> open = new ArrayList<Player>();

	public static Inventory menu(Player p, String crate) {
		Inventory inv = Bukkit.createInventory(null, size(crate),
				Format.color(Files.config.getString("Crates." + crate + ".Display")));
		for (String str : Files.config.getConfigurationSection("Crates." + crate + ".Prizes").getKeys(false)) {
			@SuppressWarnings("deprecation")
			ItemStack i = new ItemStack(
					Material.getMaterial(Files.config.getInt("Crates." + crate + ".Prizes." + str + ".id")));
			i.setAmount(Files.config.getInt("Crates." + crate + ".Prizes." + str + ".amount"));
			ItemMeta m = i.getItemMeta();
			List<String> l = new ArrayList<String>();
			m.setDisplayName(Format.color(Files.config.getString("Crates." + crate + ".Prizes." + str + ".name")));
			if (Files.config.getStringList("Crates." + crate + ".Prizes." + str + ".lore") != null
					|| Files.config.getStringList("Crates." + crate + ".Prizes." + str + ".lore").size() > 0) {
				for (String lore : Files.config.getStringList("Crates." + crate + ".Prizes." + str + ".lore")) {
					l.add(Format.color(lore));
				}
			}
			l.add(Format.color("&8 &m---------------"));
			l.add(Format.color("&8 ● &7Rarity " + CratesCore.rarityToColor
					.get(Files.config.getString("Crates." + crate + ".Prizes." + str + ".rarity"))));
			m.setLore(l);
			i.setItemMeta(m);
			inv.addItem(i);
		}
		return inv;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (open.contains((Player) e.getWhoClicked())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer()))
			open.remove(e.getPlayer());
	}

	public static Integer size(String crate) {
		int i = 0;
		for (@SuppressWarnings("unused")
		String str : Files.config.getConfigurationSection("Crates." + crate + ".Prizes").getKeys(false)) {
			i++;
		}
		if (i > 0 && i <= 9)
			return 9;
		if (i > 9 && i <= 18)
			return 18;
		if (i > 18 && i <= 27)
			return 27;
		if (i > 27 && i <= 36)
			return 36;
		if (i > 36 && i <= 45)
			return 45;
		if (i > 45)
			return 54;
		return null;
	}
}
