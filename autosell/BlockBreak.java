package me.askingg.mayhem.autosell;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Files;

public class BlockBreak implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void blockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (WorldGuardPlugin.inst().canBuild(p, e.getBlock().getLocation())) {
			if (p.getGameMode() == GameMode.SURVIVAL) {
				if (Files.data.getBoolean("Users." + p.getUniqueId() + ".Settings.Autosell")) {
					Material m = e.getBlock().getType();
					if (m == Material.STONE || m == Material.STAINED_CLAY || m == Material.CONCRETE) {
						if (AutosellCore.worth(p, e.getBlock().getType(), (int) e.getBlock().getData()) == 0.0) {
							for (ItemStack i : e.getBlock().getDrops()) {
								Random r = new Random();
								i.setAmount(r.nextInt((p.getInventory().getItemInMainHand().getItemMeta()
										.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1)));
								p.getInventory().addItem(i);
								e.setDropItems(false);
								break;
							}
						}
						Double d = 1.0;
						d = d + AutosellCore.multi(p);
						Main.eco.depositPlayer(p,
								(AutosellCore.worth(p, e.getBlock().getType(), (int) e.getBlock().getData()) * d));
						e.setDropItems(false);
					} else {
						for (ItemStack i : e.getBlock().getDrops()) {
							p.getInventory().addItem(i);
							e.setDropItems(false);
						}
					}
				} else {
					for (ItemStack i : e.getBlock().getDrops()) {
						Random r = new Random();
						i.setAmount(r.nextInt((p.getInventory().getItemInMainHand().getItemMeta()
								.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1)));
						p.getInventory().addItem(i);
						e.setDropItems(false);
						return;
					}
				}
			}
		}
	}
}
