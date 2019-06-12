package me.askingg.mayhem.entities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.askingg.mayhem.entities.bosses.BossCore;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class PlayerInteract implements Listener {

	@EventHandler
	public void interact(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock() != null || !e.getClickedBlock().getType().equals(Material.AIR)) {
				if (e.getClickedBlock().getType() == Material.SIGN
						|| e.getClickedBlock().getType() == Material.SIGN_POST
						|| e.getClickedBlock().getType() == Material.WALL_SIGN) {
					Sign s = (Sign) e.getClickedBlock().getState();
					if (s.getLine(0).equals(Format.color("[&4&lDungeons&0]"))) {
						if (s.getLine(2).equals(Format.color("&bExit"))) {
							Location l = new Location(Bukkit.getWorld("world"), 469.5, 67.0, -130.5,
									e.getPlayer().getLocation().getYaw(), e.getPlayer().getLocation().getPitch());
							e.getPlayer().teleport(l);
							return;
						}
						if (s.getLine(1).equals(Format.color("&bEnter"))) {
							if (s.getLine(2).equals(Format.color("&bDungeon1"))) {
								Location l = new Location(Bukkit.getWorld("world"), 476.5, 64.0, -130.5,
										e.getPlayer().getLocation().getYaw(), e.getPlayer().getLocation().getPitch());
								e.getPlayer().teleport(l);
								return;
							}
							if (s.getLine(2).equals(Format.color("&bDungeon2"))) {
								Location l = new Location(Bukkit.getWorld("world"), -34.5, 48.0, -24.5,
										e.getPlayer().getLocation().getYaw(), e.getPlayer().getLocation().getPitch());
								e.getPlayer().teleport(l);
								return;
							}
						}
					}
					if (s.getLine(0).equals(Format.color("[&4&lBosses&0]"))) {
						if (s.getLine(2).equals(Format.color("&7Spawn:"))) {
							if (s.getLine(3).equals(Format.color("&4Magma&fLord"))) {
								if (!BossCore.magmaAlive) {
									if ((System.currentTimeMillis() >= (BossCore.magmaSpawnTime + (1800 * 1000))) || BossCore.magmaSpawnTime == 0) {
										Location l = new Location(Bukkit.getWorld("world"), 419.5, 4, -107.5);
										BossCore.magmaLord(l);
										return;
									} else {
										Message.player(
												"Sorry, but the &4&lMagma&f&lLord&7 cannot be spawned for another "
														+ Format.time((int) Math.round(
																(BossCore.magmaSpawnTime + (1800 * 1000)) -System.currentTimeMillis())
																/ 1000),
												e.getPlayer());
										return;
									}
								} else {
									Message.player(
											"Sorry, but the &4&lMagma&f&lLord&7 cannot be spawned as it is already alive",
											e.getPlayer());
									return;
								}
							}
						}
					}
				}
			}
		}
	}
}
