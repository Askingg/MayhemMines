package me.askingg.mayhem.crates;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;
import me.askingg.mayhem.utils.Misc;

public class BlockPlace implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		ItemStack i = e.getItemInHand();
		if (i.hasItemMeta()) {
			ItemMeta m = i.getItemMeta();
			if (m.hasDisplayName() && m.hasLore()) {
				if (m.getDisplayName().contains("Key")) {
					if (ChatColor.stripColor(Format.color(m.getLore().get(4)))
							.equals(" ● Use this key at /Warp Crates")) {
						e.setCancelled(true);
					}
				}
				if (m.getDisplayName().contains("Crate")) {
					if (m.getLore().get(0).equals(Format.color("&7Place to create a chest location"))) {
						Location l = e.getBlock().getLocation();
						String str = Misc.randomCode();
						Files.config.set("Crates.Locations." + str + ".Type",
								ChatColor.stripColor(Format.color(m.getDisplayName())).split(" ")[0]);
						Files.config.set("Crates.Locations." + str + ".World", l.getWorld().getName());
						Files.config.set("Crates.Locations." + str + ".X", l.getX());
						Files.config.set("Crates.Locations." + str + ".Y", l.getY());
						Files.config.set("Crates.Locations." + str + ".Z", l.getZ());
						try {
							Files.config.save(Files.configFile);
							Message.player("You placed a crate", e.getPlayer());
							return;
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}
}
