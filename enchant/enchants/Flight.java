package me.askingg.mayhem.enchant.enchants;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import me.askingg.mayhem.enchant.EnchantCore;

public class Flight implements Listener {

	@EventHandler
	public void hold(PlayerItemHeldEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getInventory().getItem(e.getNewSlot());
		if (!EnchantCore.hasCE2(i, "Flight")) {
			disable(p);
			return;
		}
		enable(p);
		return;
	}

	private void enable(Player p) {
		p.setAllowFlight(true);
		p.setFlying(true);
	}

	private void disable(Player p) {
		if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (!p.hasPermission("essentials.fly")) {
			p.setAllowFlight(false);
			p.setFlying(false);
		}
	}
}
