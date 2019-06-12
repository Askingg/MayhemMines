package me.askingg.mayhem.enchant.enchants;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import me.askingg.mayhem.enchant.EnchantCore;

public class BlockBreak implements Listener {

	@EventHandler
	public void blockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (p.getGameMode() == GameMode.SURVIVAL) {
			if (e.getBlock().getWorld().getName().equals("world")) {
				if (WorldGuardPlugin.inst().canBuild(p, e.getBlock())) {
					EnchantCore.applyEnchants(p);
				}
			}
		}
	}
}
