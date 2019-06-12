package me.askingg.mayhem.burriedtreasure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.echo.EchoCore;
import me.askingg.mayhem.miningrewards.RewardsCore;
import me.askingg.mayhem.tokens.TokensCore;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class OpenContainer implements Listener {

	public static HashMap<Player, Location> open = new HashMap<Player, Location>();

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.CHEST) {
				Player p = e.getPlayer();
				if (TreasureCore.map.containsKey(p)) {
					if (TreasureCore.map.get(p).equals(e.getClickedBlock().getLocation())) {
						e.setCancelled(true);
						TreasureCore.despawnChest(p, e.getClickedBlock().getLocation());
						Message.player("You opened a &cBuried Treasure&7: Claim your rewards with &b/Rewards", p);
						Random r = new Random();
						Integer c = -1;
						while (c < 26) {
							c++;
							Double d = r.nextDouble();
							if (d <= 0.0025) {
								RewardsCore.setReward((Player) e.getPlayer(), TokensCore.tokenStack(100000));
								continue;
							}
							if (d <= 0.0025) {
								RewardsCore.setReward((Player) e.getPlayer(), EchoCore.randomCharm());
								continue;
							}
							d = r.nextDouble();
							if (d <= 0.0025) {
								RewardsCore.setReward((Player) e.getPlayer(), EchoCore.randomRelic());
								continue;
							}
							d = r.nextDouble();
							if (d <= 0.0025) {
								RewardsCore.setReward((Player) e.getPlayer(), EchoCore.randomCharm());
								continue;
							}
							d = r.nextDouble();
							if (d <= 0.0025) {
								ItemStack i = new ItemStack(Material.NAME_TAG, 1);
								ItemMeta m = i.getItemMeta();
								List<String> l = new ArrayList<String>();
								m.setDisplayName(Format.color(
										"&8&m&l«&8&m-<&c&k&li&8&m[--|&c&k&li&b&l Rename Token &c&k&li&8&m|--]&c&k&li&8&m>-&8&l&m»"));
								m.addEnchant(Enchantment.LUCK, 0, true);
								m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
								l.add(Format.color("&7"));
								l.add(Format.color("&8● &7Drag and drop onto the item you wish"));
								l.add(Format.color("&7 &7 &7 to rename, then type your item's"));
								l.add(Format.color("&7 &7 &7 new name in chat (Colors supported)"));
								m.setUnbreakable(true);
								m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
								m.setLore(l);
								i.setItemMeta(m);
								RewardsCore.setReward((Player) e.getPlayer(), i);
								continue;
							}
							d = r.nextDouble();
							if (d <= 0.05) {
								RewardsCore.setReward((Player) e.getPlayer(), TokensCore.tokenStack(10000));
								continue;
							}
							d = r.nextDouble();
							if (d <= 0.025) {
								RewardsCore.setReward((Player) e.getPlayer(), TokensCore.tokenStack(25000));
								continue;
							}
							d = r.nextDouble();
							if (d <= 0.01) {
								RewardsCore.setReward((Player) e.getPlayer(), TokensCore.tokenStack(50000));
								continue;
							}
						}
					}
				}
			}
		}
	}

	// public void onInventoryOpenEvent(InventoryOpenEvent e) {
	// if (e.getInventory().getHolder() instanceof Chest) {
	// for (OfflinePlayer pl : TreasureCore.map.keySet()) {
	// if (TreasureCore.map.get(pl).equals(e.getInventory().getLocation())) {
	// if (e.getPlayer().equals((Player) pl)) {
	// e.setCancelled(true);
	// Random r = new Random();
	// Message.player("You opened a &cBuried Treasure&7: Claim your rewards with
	// &b/Rewards", (Player) pl);
	// Integer c = -1;
	// open.put((Player) pl, TreasureCore.map.get((Player) pl));
	// while (c < 26) {
	// c++;
	// Double d = r.nextDouble();
	// if (d <= 0.0025) {
	// RewardsCore.setReward((Player) e.getPlayer(), TokensCore.tokenStack(100000));
	// continue;
	// }
	// if (d <= 0.0025) {
	// RewardsCore.setReward((Player) e.getPlayer(), EchoCore.randomCharm());
	// continue;
	// }
	// d = r.nextDouble();
	// if (d <= 0.0025) {
	// RewardsCore.setReward((Player) e.getPlayer(), EchoCore.randomRelic());
	// continue;
	// }
	// d = r.nextDouble();
	// if (d <= 0.0025) {
	// RewardsCore.setReward((Player) e.getPlayer(), EchoCore.randomCharm());
	// continue;
	// }
	// d = r.nextDouble();
	// if (d <= 0.0025) {
	// ItemStack i = new ItemStack(Material.NAME_TAG, 1);
	// ItemMeta m = i.getItemMeta();
	// List<String> l = new ArrayList<String>();
	// m.setDisplayName(Format.color(
	// "&8&m&l«&8&m-<&c&k&li&8&m[--|&c&k&li&b&l Rename Token
	// &c&k&li&8&m|--]&c&k&li&8&m>-&8&l&m»"));
	// m.addEnchant(Enchantment.LUCK, 0, true);
	// m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	// l.add(Format.color("&7"));
	// l.add(Format.color("&8● &7Drag and drop onto the item you wish"));
	// l.add(Format.color("&7 &7 &7 to rename, then type your item's"));
	// l.add(Format.color("&7 &7 &7 new name in chat (Colors supported)"));
	// m.setUnbreakable(true);
	// m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
	// m.setLore(l);
	// i.setItemMeta(m);
	// RewardsCore.setReward((Player) e.getPlayer(), i);
	// continue;
	// }
	// d = r.nextDouble();
	// if (d <= 0.05) {
	// RewardsCore.setReward((Player) e.getPlayer(), TokensCore.tokenStack(10000));
	// continue;
	// }
	// d = r.nextDouble();
	// if (d <= 0.025) {
	// RewardsCore.setReward((Player) e.getPlayer(), TokensCore.tokenStack(25000));
	// continue;
	// }
	// d = r.nextDouble();
	// if (d <= 0.01) {
	// RewardsCore.setReward((Player) e.getPlayer(), TokensCore.tokenStack(50000));
	// continue;
	// }
	// }
	// }
	// }
	// }
	// }
	// }

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (open.containsKey(e.getPlayer())) {
			TreasureCore.despawnChest((Player) e.getPlayer(), open.get(e.getPlayer()));
			open.remove((Player) e.getPlayer());
		}
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		if (TreasureCore.map.containsKey(e.getPlayer())) {
			TreasureCore.map.remove(e.getPlayer());
		}
	}
}
