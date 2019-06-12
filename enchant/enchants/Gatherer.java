package me.askingg.mayhem.enchant.enchants;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import me.askingg.mayhem.enchant.EnchantCore;

public class Gatherer implements Listener {

	@EventHandler
	public void blockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getInventory().getItemInMainHand();
		if (i.getType().toString().contains("PICKAXE")) {
			if (p.getGameMode() == GameMode.SURVIVAL) {
				p.setLevel(p.getLevel() + blockXP(p, e.getBlock().getLocation()));
				p.updateInventory();
				return;
			}
		}
	}

	public static Integer blockXP(Player p, Location loc) {
		if (WorldGuardPlugin.inst().canBuild(p, loc)) {
			if (EnchantCore.hasCE(p, "Gatherer")) {
				Random r = new Random();
				Integer x = r.nextInt(100) + 1;
				if (EnchantCore.level(p, "Gatherer") == 1) {
					if (x <= 90) {
						return 1;
					}
					if (x > 90) {
						return 2;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 2) {
					if (x <= 80) {
						return 1;
					}
					if (x > 20) {
						return 2;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 3) {
					if (x <= 60) {
						return 1;
					}
					if (x > 60 && x <= 95) {
						return 2;
					}
					if (x > 95) {
						return 3;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 4) {
					if (x <= 50) {
						return 1;
					}
					if (x > 50 && x <= 90) {
						return 2;
					}
					if (x > 90) {
						return 3;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 5) {
					if (x <= 30) {
						return 1;
					}
					if (x > 30 && x <= 80) {
						return 2;
					}
					if (x > 80) {
						return 3;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 6) {
					if (x <= 50) {
						return 2;
					}
					if (x > 50 && x <= 85) {
						return 3;
					}
					if (x > 85) {
						return 4;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 7) {
					if (x <= 40) {
						return 2;
					}
					if (x > 40 && x <= 80) {
						return 3;
					}
					if (x > 80) {
						return 4;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 8) {
					if (x <= 60) {
						return 3;
					}
					if (x > 60 && x <= 90) {
						return 4;
					}
					if (x > 90) {
						return 5;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 9) {
					if (x <= 60) {
						return 4;
					}
					if (x > 60) {
						return 5;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 10) {
					return 5;
				}
				if (EnchantCore.level(p, "Gatherer") == 11) {
					if (x <= 75) {
						return 5;
					}
					if (x > 75) {
						return 6;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 12) {
					if (x <= 60) {
						return 5;
					}
					if (x > 60) {
						return 6;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 13) {
					if (x <= 40) {
						return 5;
					}
					if (x > 40 && x <= 90) {
						return 6;
					}
					if (x > 90) {
						return 7;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 14) {
					if (x <= 20) {
						return 5;
					}
					if (x > 20 && x <= 85) {
						return 6;
					}
					if (x > 85) {
						return 7;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 15) {
					if (x <= 75) {
						return 6;
					}
					if (x > 75) {
						return 7;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 16) {
					if (x <= 60) {
						return 6;
					}
					if (x > 60) {
						return 7;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 17) {
					if (x <= 40) {
						return 6;
					}
					if (x > 40 && x <= 80) {
						return 7;
					}
					if (x > 80) {
						return 8;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 18) {
					if (x <= 20) {
						return 6;
					}
					if (x > 20 && x <= 60) {
						return 7;
					}
					if (x > 60) {
						return 8;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 19) {
					if (x <= 40) {
						return 7;
					}
					if (x > 40) {
						return 8;
					}
				}
				if (EnchantCore.level(p, "Gatherer") == 20) {
					return 8;
				}
				if (EnchantCore.level(p, "Gatherer") == 21) {
					return 9;
				}
				if (EnchantCore.level(p, "Gatherer") == 22) {
					return 10;
				}
				if (EnchantCore.level(p, "Gatherer") == 23) {
					return 11;
				}
				if (EnchantCore.level(p, "Gatherer") == 24) {
					return 12;
				}
				if (EnchantCore.level(p, "Gatherer") == 25) {
					return 15;
				}
				if (EnchantCore.level(p, "Gatherer") == 26) {
					return 17;
				}
				if (EnchantCore.level(p, "Gatherer") == 27) {
					return 20;
				}
				if (EnchantCore.level(p, "Gatherer") == 28) {
					return 25;
				}
				if (EnchantCore.level(p, "Gatherer") == 29) {
					return 30;
				}
				if (EnchantCore.level(p, "Gatherer") == 30) {
					return 35;
				}
				if (EnchantCore.level(p, "Gatherer") == 31) {
					return 40;
				}
				if (EnchantCore.level(p, "Gatherer") == 32) {
					return 45;
				}
				if (EnchantCore.level(p, "Gatherer") == 33) {
					return 50;
				}
				if (EnchantCore.level(p, "Gatherer") == 34) {
					return 55;
				}
				if (EnchantCore.level(p, "Gatherer") == 35) {
					return 60;
				}
			}
			return 1;
		}
		return 0;
	}

	public static Integer blockXP(Player p) {
		if (EnchantCore.hasCE(p, "Gatherer")) {
			Random r = new Random();
			Integer x = r.nextInt(100) + 1;
			if (EnchantCore.level(p, "Gatherer") == 1) {
				if (x <= 90) {
					return 1;
				}
				if (x > 90) {
					return 2;
				}
			}
			if (EnchantCore.level(p, "Gatherer") == 2) {
				if (x <= 80) {
					return 1;
				}
				if (x > 20) {
					return 2;
				}
			}
			if (EnchantCore.level(p, "Gatherer") == 3) {
				if (x <= 60) {
					return 1;
				}
				if (x > 60 && x <= 95) {
					return 2;
				}
				if (x > 95) {
					return 3;
				}
			}
			if (EnchantCore.level(p, "Gatherer") == 4) {
				if (x <= 50) {
					return 1;
				}
				if (x > 50 && x <= 90) {
					return 2;
				}
				if (x > 90) {
					return 3;
				}
			}
			if (EnchantCore.level(p, "Gatherer") == 5) {
				if (x <= 30) {
					return 1;
				}
				if (x > 30 && x <= 80) {
					return 2;
				}
				if (x > 80) {
					return 3;
				}
			}
			if (EnchantCore.level(p, "Gatherer") == 6) {
				if (x <= 50) {
					return 2;
				}
				if (x > 50 && x <= 85) {
					return 3;
				}
				if (x > 85) {
					return 4;
				}
			}
			if (EnchantCore.level(p, "Gatherer") == 7) {
				if (x <= 40) {
					return 2;
				}
				if (x > 40 && x <= 80) {
					return 3;
				}
				if (x > 80) {
					return 4;
				}
			}
			if (EnchantCore.level(p, "Gatherer") == 8) {
				if (x <= 60) {
					return 3;
				}
				if (x > 60 && x <= 90) {
					return 4;
				}
				if (x > 90) {
					return 5;
				}
			}
			if (EnchantCore.level(p, "Gatherer") == 9) {
				if (x <= 60) {
					return 4;
				}
				if (x > 40) {
					return 5;
				}
			}
			if (EnchantCore.level(p, "Gatherer") == 10) {
				return 5;
			}
		}
		return 1;
	}
}
