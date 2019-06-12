package me.askingg.mayhem.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.askingg.mayhem.utils.Format;

public class BlockPlace implements Listener {

	@EventHandler
	public void place(BlockPlaceEvent e) {
		if (e.getBlock().getType() == Material.ENDER_CHEST) {
			if (e.getItemInHand().hasItemMeta()) {
				if (e.getItemInHand().getItemMeta().hasDisplayName() && e.getItemInHand().getItemMeta().hasLore()) {
					if (e.getItemInHand().getItemMeta().getDisplayName().equals(Format.color("&c&lEcho Device"))) {
						e.setCancelled(true);
						return;
					}
				}
			}
		}
	}
}
