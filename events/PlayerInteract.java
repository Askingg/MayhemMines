package me.askingg.mayhem.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.echo.EchoCore;
import me.askingg.mayhem.echo.EchoGUI;
import me.askingg.mayhem.echo.EchoUpgradeGUI;
import me.askingg.mayhem.multiplier.MultiplierCore;
import me.askingg.mayhem.pickaxe.PickaxeGUI;
import me.askingg.mayhem.tokens.TokensCore;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class PlayerInteract implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void click(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			ItemStack h = p.getInventory().getItemInMainHand();
			if (h != null) {
				if (h.getType().toString().contains("PICKAXE")) {
					if (p.isSneaking()) {
						Message.player("&7You must have a &6Totem&7 equipped to use this &8(&7Coming soon... &8)", p);
						return;
					} else {
						PickaxeGUI.open.add(p);
						p.openInventory(PickaxeGUI.menu(p));
						return;
					}
				}
				if (h.getType() == Material.ENDER_CHEST) {
					if (h.hasItemMeta()) {
						if (h.getItemMeta().hasDisplayName() && h.getItemMeta().hasLore()) {
							if (h.getItemMeta().getDisplayName().equals(Format.color("&c&lEcho Device"))) {
								EchoGUI.open.add(p);
								p.openInventory(EchoGUI.menu(p));
								return;
							}
						}
					}
				}
				if (h.getType() == Material.getMaterial(2266)) {
					if (h.hasItemMeta()) {
						ItemMeta hm = h.getItemMeta();
						if (hm.hasDisplayName() && hm.hasLore()) {
							if (hm.getDisplayName().contains(Format.color("&c&lAutoMining Chip: &b&l"))) {
								if (hm.getLore().size() == 4) {
									if (hm.getLore().get(1).equals(Format.color("&bRightClick&7 to redeem time"))) {
										long time = Long.valueOf(
												ChatColor.stripColor(Format.color(hm.getLore().get(3))).split(" ")[1]);
										try {
											p.getInventory().getItemInMainHand()
													.setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
											p.updateInventory();
											Message.player(
													"You claimed &b" + Format.time((int) time / 1000)
															+ "&7 AutoMining time, new total: &b"
															+ Format.time((int) (Files.data.getLong("Users."
																	+ p.getUniqueId().toString() + ".MinerTime") + time)
																	/ 1000),
													p);
											Files.data.set("Users." + p.getUniqueId().toString() + ".MinerTime",
													Files.data.getLong(
															"Users." + p.getUniqueId().toString() + ".MinerTime")
															+ time);
											Files.data.save(Files.dataFile);
											return;
										} catch (Exception ex) {
											ex.printStackTrace();
											return;
										}
									}
								}
							}
						}
					}
				}
				if (MultiplierCore.isMultiplier(h)) {
					if (MultiplierCore.getType(h).equalsIgnoreCase("Money")) {
						if (MultiplierCore.moneyMulti.containsKey(p)) {
							double multi = MultiplierCore.getMulti(h);
							if (MultiplierCore.moneyMulti.get(p).equals(multi)) {
								MultiplierCore.moneyTime.put(p,
										MultiplierCore.moneyTime.get(p) + MultiplierCore.getTime(h));
								Message.player(
										"You added &b" + Format.time((int) (MultiplierCore.getTime(h) / 1000))
												+ "&7 to your &b" + MultiplierCore.moneyMulti.get(p) + "x &7token multiplier",
										p);
								p.getInventory().getItemInMainHand()
										.setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
								p.updateInventory();
								return;
							} else {
								Message.player("Sorry, but you cannot apply a &b" + multi
										+ "&7x multiplier while you have a &c" + MultiplierCore.moneyMulti.get(p)
										+ "&7 active", p);
								return;
							}
						} else {
							MultiplierCore.moneyMulti.put(p, MultiplierCore.getMulti(h));
							MultiplierCore.moneyTime.put(p, MultiplierCore.getTime(h));
							Message.player("You redeemed a &b" + MultiplierCore.getMulti(h) + "x &7 money multiplier for &b"
									+ Format.time((int) (MultiplierCore.moneyTime.get(p) / 1000)), p);
							p.getInventory().getItemInMainHand()
									.setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
							p.updateInventory();
							return;
						}
					}
					if (MultiplierCore.getType(h).equalsIgnoreCase("Token")) {
						if (MultiplierCore.tokenMulti.containsKey(p)) {
							double multi = MultiplierCore.getMulti(h);
							if (MultiplierCore.tokenMulti.get(p).equals(multi)) {
								MultiplierCore.tokenTime.put(p,
										MultiplierCore.tokenTime.get(p) + MultiplierCore.getTime(h));
								Message.player(
										"You added &b" + Format.time((int) (MultiplierCore.getTime(h) / 1000))
												+ "&7 to your &b" + MultiplierCore.tokenMulti.get(p) + "x &7token multiplier",
										p);
								p.getInventory().getItemInMainHand()
										.setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
								p.updateInventory();
								return;
							} else {
								Message.player("Sorry, but you cannot apply a &b" + multi
										+ "&7x multiplier while you have a &c" + MultiplierCore.tokenMulti.get(p)
										+ "&7 active", p);
								return;
							}
						} else {
							MultiplierCore.tokenMulti.put(p, MultiplierCore.getMulti(h));
							MultiplierCore.tokenTime.put(p, MultiplierCore.getTime(h));
							Message.player("You redeemed a &b" + MultiplierCore.getMulti(h) + "x &7 token multiplier for &b"
									+ Format.time((int) (MultiplierCore.tokenTime.get(p) / 1000)), p);
							p.getInventory().getItemInMainHand()
									.setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
							p.updateInventory();
							return;
						}
					}
					return;
				}
				if (EchoCore.isRelic(h)) {
					e.setCancelled(true);
					EchoUpgradeGUI.open.add(p);
					p.openInventory(EchoUpgradeGUI.menu(p, "Relic"));
					return;
				}
				if (EchoCore.isArtefact(h)) {
					e.setCancelled(true);
					EchoUpgradeGUI.open.add(p);
					p.openInventory(EchoUpgradeGUI.menu(p, "Artefact"));
					return;
				}
				if (TokensCore.isTokenStack(h)) {
					if (h.getAmount() == 1) {
						TokensCore.addNoMulti(p, (TokensCore.getTokenStackWorth(h) + 0.0));
						p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
						return;
					} else {
						TokensCore.addNoMulti(p, ((TokensCore.getTokenStackWorth(h) + 0.0)) * h.getAmount());
						p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
						return;
					}
				}
			}
		}
	}
}
