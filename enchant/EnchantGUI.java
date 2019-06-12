package me.askingg.mayhem.enchant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.entities.dungeons.DungeonsCore;
import me.askingg.mayhem.exp.EXPCore;
import me.askingg.mayhem.tokens.TokensCore;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class EnchantGUI implements Listener {

	public static List<Player> open = new ArrayList<Player>();

	public static Inventory menu(Player p) {
		Inventory menu = Bukkit.createInventory(null, 54, Format.color("&c&lEnchanting"));
		borders(menu);
		menu.addItem(vanillaEnchantItem(p, Material.SUGAR, Enchantment.DIG_SPEED, "Efficiency"));
		menu.addItem(vanillaEnchantItem(p, Material.DIAMOND, Enchantment.LOOT_BONUS_BLOCKS, "Fortune"));
		menu.addItem(customEnchantItem(p, Material.NETHER_STAR, "Scavenger",
				Arrays.asList("&7Start 'Scavenger' Events: Finding tokens", "&7and gang credits")));
		menu.addItem(customEnchantItem(p, Material.SLIME_BALL, "TokenHunter",
				Arrays.asList("&7Increase the amount of tokens you get", "&7from scavenger events")));
		menu.addItem(customEnchantItem(p, Material.MAGMA_CREAM, "GangCredits",
				Arrays.asList("&7Increase the amount of gang credits you", "&7get from scavenger events")));
		menu.addItem(customEnchantItem(p, Material.FEATHER, "Flight",
				Arrays.asList("&7Allows you to fly while holding your", "&7pickaxe")));
		menu.addItem(customEnchantItem(p, Material.EXP_BOTTLE, "Gatherer",
				Arrays.asList("&7Get more EXP when mining blocks")));
		menu.addItem(customEnchantItem(p, Material.GLASS_BOTTLE, "Lazy", Arrays.asList("&7Reduces the levelup cost")));
		menu.addItem(customEnchantItem(p, Material.TRIPWIRE_HOOK, "KeyHunter",
				Arrays.asList("&7Get more keys while mining")));
		menu.addItem(customEnchantItem(p, Material.TRIPWIRE_HOOK, "KeyQuality",
				Arrays.asList("&7Get better keys while mining")));
		ItemStack i = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&4&lBlood &c&lEnchantment"));
		l.add(Format.color("&7"));
		if (EnchantCore.hasCE(p, "RelicHunter")) {
			if (EnchantCore.level(p, "RelicHunter") == EnchantCore.maxLevelBlood("RelicHunter")) {
				l.add(Format.color("&7RelicHunter &8/ &c" + EnchantCore.level(p, "RelicHunter") + " &8(&7Maxed&8)"));
			} else {
				l.add(Format.color("&7RelicHunter &8/ &c" + EnchantCore.level(p, "RelicHunter")));
			}
		} else {
			l.add(Format.color("&7RelicHunter &8/ &c0"));
		}
		if (EnchantCore.hasCE(p, "ArtefactHunter")) {
			if (EnchantCore.level(p, "ArtefactHunter") == EnchantCore.maxLevelBlood("ArtefactHunter")) {
				l.add(Format
						.color("&7ArtefactHunter &8/ &c" + EnchantCore.level(p, "ArtefactHunter") + " &8(&7Maxed&8)"));
			} else {
				l.add(Format.color("&7ArtefactHunter &8/ &c" + EnchantCore.level(p, "ArtefactHunter")));
			}
		} else {
			l.add(Format.color("&7ArtefactHunter &8/ &c0"));
		}
		if (EnchantCore.hasCE(p, "CharmHunter")) {
			if (EnchantCore.level(p, "CharmHunter") == EnchantCore.maxLevelBlood("CharmHunter")) {
				l.add(Format.color("&7CharmHunter &8/ &c" + EnchantCore.level(p, "CharmHunter") + " &8(&7Maxed&8)"));
			} else {
				l.add(Format.color("&7CharmHunter &8/ &c" + EnchantCore.level(p, "CharmHunter")));
			}
		} else {
			l.add(Format.color("&7CharmHunter &8/ &c0"));
		}
		l.add(Format.color("&7"));
		l.add(Format.color("&7Click to purchase a random &4Blood &cEnchantment"));
		l.add(Format.color("&7Cost &c" + Format.decimals(0, EnchantCore.bloodCost(p)) + " Decayed Flesh"));
		m.setLore(l);
		i.setItemMeta(m);
		menu.setItem(40, i);
		return menu;
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (open.contains(p)) {
			e.setCancelled(true);
			ItemStack i = e.getCurrentItem();
			if (i != null) {
				if (i.hasItemMeta() && i.getItemMeta().hasDisplayName()) {
					if (i.getItemMeta().getDisplayName().equals(Format.color("&4&lBlood &c&lEnchantment"))) {
						if (i.getType() == Material.EYE_OF_ENDER) {
							ItemStack h = p.getInventory().getItemInMainHand();
							if (h != null) {
								if (h.getType().toString().contains("PICKAXE")) {
									if (!EnchantCore.hasMaxedBloodEnchants(p)) {
										String s = EnchantCore.randomBloodEnchant(p);
										bloodEnchantApplication(p, s);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} else {
										Message.player("Sorry, but all blood enchants are maxed on this pickaxe", p);
										return;
									}
								}
							}
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lFortune"))) {
						if (i.getType() == Material.DIAMOND) {
							vanillaEnchantApplication(p, e.getClick(), Enchantment.LOOT_BONUS_BLOCKS, "Fortune");
							open.add(p);
							p.openInventory(menu(p));
							return;
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lEfficiency"))) {
						if (i.getType() == Material.SUGAR) {
							vanillaEnchantApplication(p, e.getClick(), Enchantment.DIG_SPEED, "Efficiency");
							open.add(p);
							p.openInventory(menu(p));
							return;
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lScavenger"))) {
						if (i.getType() == Material.NETHER_STAR) {
							customEnchantApplication(p, e.getClick(), "Scavenger");
							open.add(p);
							p.openInventory(menu(p));
							return;
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lTokenHunter"))) {
						if (i.getType() == Material.SLIME_BALL) {
							customEnchantApplication(p, e.getClick(), "TokenHunter");
							open.add(p);
							p.openInventory(menu(p));
							return;
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lGangCredits"))) {
						if (i.getType() == Material.MAGMA_CREAM) {
							customEnchantApplication(p, e.getClick(), "GangCredits");
							open.add(p);
							p.openInventory(menu(p));
							return;
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lFlight"))) {
						if (i.getType() == Material.FEATHER) {
							customEnchantApplication(p, e.getClick(), "Flight");
							open.add(p);
							p.openInventory(menu(p));
							return;
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lGatherer"))) {
						if (i.getType() == Material.EXP_BOTTLE) {
							customEnchantApplication(p, e.getClick(), "Gatherer");
							open.add(p);
							p.openInventory(menu(p));
							return;
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lLazy"))) {
						if (i.getType() == Material.GLASS_BOTTLE) {
							customEnchantApplication(p, e.getClick(), "Lazy");
							open.add(p);
							p.openInventory(menu(p));
							return;
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lKeyHunter"))) {
						if (i.getType() == Material.TRIPWIRE_HOOK) {
							customEnchantApplication(p, e.getClick(), "KeyHunter");
							open.add(p);
							p.openInventory(menu(p));
							return;
						}
					}
					if (i.getItemMeta().getDisplayName().equals(Format.color("&c&lKeyQuality"))) {
						if (i.getType() == Material.TRIPWIRE_HOOK) {
							customEnchantApplication(p, e.getClick(), "KeyQuality");
							open.add(p);
							p.openInventory(menu(p));
							return;
						}
					}
				}
			}
		}
	}

	private static void borders(Inventory inv) {
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 7);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		ItemStack i2 = new ItemStack(Material.PAPER);
		ItemMeta m2 = i2.getItemMeta();
		List<String> l2 = new ArrayList<String>();
		m2.setDisplayName(Format.color("&c&lInformation"));
		l2.add(Format.color("&7"));
		l2.add(Format.color("&bRightClick &8/ &7Enchant 1 level"));
		l2.add(Format.color("&bLeftClick &8/ &7Enchant 10 levels"));
		m2.setLore(l2);
		i2.setItemMeta(m2);
		inv.setItem(0, i);
		inv.setItem(1, i);
		inv.setItem(2, i);
		inv.setItem(3, i);
		inv.setItem(4, i2);
		inv.setItem(5, i);
		inv.setItem(6, i);
		inv.setItem(7, i);
		inv.setItem(8, i);
		inv.setItem(9, i);
		inv.setItem(17, i);
		inv.setItem(18, i);
		inv.setItem(26, i);
		inv.setItem(27, i);
		inv.setItem(28, i);
		inv.setItem(29, i);
		inv.setItem(30, i);
		inv.setItem(31, i);
		inv.setItem(32, i);
		inv.setItem(33, i);
		inv.setItem(34, i);
		inv.setItem(35, i);
		inv.setItem(36, i);
		inv.setItem(37, i);
		inv.setItem(38, i);
		inv.setItem(42, i);
		inv.setItem(43, i);
		inv.setItem(44, i);
		inv.setItem(45, i);
		inv.setItem(46, i);
		inv.setItem(47, i);
		inv.setItem(48, i);
		inv.setItem(49, i);
		inv.setItem(50, i);
		inv.setItem(51, i);
		inv.setItem(52, i);
		inv.setItem(53, i);
		i.setDurability((byte) 14);
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		inv.setItem(39, i);
		inv.setItem(41, i);
	}

	@EventHandler
	public void close(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
		}
	}

	private static ItemStack customEnchantItem(Player p, Material mat, String str, List<String> desc) {
		ItemStack i = new ItemStack(mat);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&l" + str));
		l.add("");
		ItemStack h = p.getInventory().getItemInMainHand();
		if (h != null) {
			for (String list : desc) {
				l.add(Format.color(list));
			}
			l.add(Format.color("&7"));
			l.add(Format.color("&7Max Level &8/ &b" + EnchantCore.maxLevel(p, str)));
			if (EnchantCore.hasCE(p, str)) {
				if (EnchantCore.level(p, str) < EnchantCore.maxLevel(p, str)) {
					l.add(Format.color("&7Level &8/ &b" + EnchantCore.level(p, str)));
				} else {
					l.add(Format.color("&7Level &8/ &b" + EnchantCore.level(p, str) + " &8(&7Maxed&8)"));
				}
				l.add(Format.color("&7Cost &8/ &b" + Format.decimals(0,
						EnchantCore.cost(str) + (EnchantCore.level(p, str) * EnchantCore.costIncrease(str)))));
				if (EnchantCore.maxLevel(p, str) >= 10) {
					Double cost = 0.0;
					for (int x = 0; x < 10; x++) {
						cost = cost + (EnchantCore.cost(str)
								+ (EnchantCore.costIncrease(str) * (EnchantCore.level(p, str) + x)));
					}
					l.add(Format.color("&7Cost x10 &8/ &b" + Format.decimals(0, cost)));
				}
			} else {
				l.add(Format.color("&7Level &8/ &b0"));
				l.add(Format.color("&7Cost &8/ &b" + Format.decimals(0, EnchantCore.cost(str))));
				if (EnchantCore.maxLevel(p, str) >= 10) {
					Double cost = 0.0;
					for (int x = 0; x < 10; x++) {
						cost = cost + (EnchantCore.cost(str) + (EnchantCore.costIncrease(str) * (0 + x)));
					}
					l.add(Format.color("&7Cost x10 &8/ &b" + Format.decimals(0, cost)));
				}
			}
		}
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

	private static void customEnchantApplication(Player p, ClickType click, String str) {
		ItemStack i = p.getInventory().getItemInMainHand();
		if (click == ClickType.LEFT) {
			Double cost = (EnchantCore.cost(str) + (EnchantCore.level(p, str) * EnchantCore.costIncrease(str)));
			if (TokensCore.hasTokens(p, cost)) {
				String enchColor = EnchantCore.randomColor();
				if (EnchantCore.level(p, str) < EnchantCore.maxLevel(p, str)) {
					ItemMeta m = i.getItemMeta();
					List<String> l = new ArrayList<String>();
					if (m.hasLore()) {
						l = m.getLore();
					}
					if (EnchantCore.hasCE(p, str)) {
						if (m.hasLore()) {
							Integer x = -1;
							for (String line : i.getItemMeta().getLore()) {
								x++;
								if (line.contains(str)) {
									break;
								}
							}
							l.set(x, Format
									.color(enchColor + str + " &8/ " + enchColor + (EnchantCore.level(p, str) + 1)));
						} else {
							EXPCore.addLore(p, i);
							l.add(l.size() - 7, Format
									.color(enchColor + str + " &8/ " + enchColor + (EnchantCore.level(p, str) + 1)));
						}
					} else {
						l.add(l.size() - 7,
								Format.color(enchColor + str + " &8/ " + enchColor + (EnchantCore.level(p, str) + 1)));
					}
					m.setLore(l);
					i.setItemMeta(m);
					p.updateInventory();
					Message.player("&b+ 1 " + str + " level", p);
					TokensCore.remove(p, cost);
				} else {
					Message.player("&7Sorry, but your &c" + str + "&7 is already the max level", p);
				}
			} else {
				Message.player("&7Sorry, but you don't have the &c" + Format.decimals(0, cost)
						+ " tokens&7 required to purchase &c" + str, p);
			}
		} else if (click == ClickType.RIGHT) {
			Integer lvl = EnchantCore.level(p, str);
			Double cost = 0.0;
			for (int x = 0; x < 10; x++) {
				cost = cost + (EnchantCore.cost(str) + (EnchantCore.costIncrease(str) * (lvl + x)));
			}
			if ((lvl + 9) < EnchantCore.maxLevel(p, str)) {
				if (TokensCore.hasTokens(p, cost)) {
					String enchColor = EnchantCore.randomColor();
					if (EnchantCore.level(p, str) < EnchantCore.maxLevel(p, str)) {
						ItemMeta m = i.getItemMeta();
						List<String> l = new ArrayList<String>();
						if (m.hasLore()) {
							l = m.getLore();
						}
						if (EnchantCore.hasCE(p, str)) {
							if (m.hasLore()) {
								Boolean hasEnchant = false;
								Integer x = -1;
								for (String line : i.getItemMeta().getLore()) {
									x++;
									if (line.contains(str)) {
										hasEnchant = true;
										break;
									}
								}
								if (hasEnchant) {
									l.set(x, Format.color(
											enchColor + str + " &8/ " + enchColor + (EnchantCore.level(p, str) + 10)));
								}
							} else {
								EXPCore.addLore(p, i);
								l.add(l.size() - 7, Format.color(
										enchColor + str + " &8/ " + enchColor + (EnchantCore.level(p, str) + 10)));
							}
						} else {
							l.add(l.size() - 7, Format
									.color(enchColor + str + " &8/ " + enchColor + (EnchantCore.level(p, str) + 10)));
						}
						m.setLore(l);
						i.setItemMeta(m);
						p.updateInventory();
						Message.player("&b+ 10 " + str + " levels", p);
						TokensCore.remove(p, cost);
					} else {
						Message.player("&7Sorry, but your &c" + str + "&7 is already the max level", p);
					}
				} else {
					Message.player("&7Sorry, but you don't have the &c" + Format.decimals(0, cost)
							+ " tokens&7 required to purchase &c" + str, p);
				}
			} else {
				Message.player("&7Sorry, but this would exceed the max level for &c" + str, p);
			}
		}
	}

	private static void bloodEnchantApplication(Player p, String str) {
		ItemStack i = p.getInventory().getItemInMainHand();
		String enchColor = EnchantCore.randomColor2();
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		if (m.hasLore()) {
			l = m.getLore();
		}
		if (DungeonsCore.getFlesh(p) >= EnchantCore.bloodCost(p)) {
			if (EnchantCore.hasCE(p, str)) {
				if (m.hasLore()) {
					Integer x = -1;
					for (String line : i.getItemMeta().getLore()) {
						x++;
						if (line.contains(str)) {
							break;
						}
					}
					l.set(x, Format.color(enchColor + str + " &8/ " + enchColor + (EnchantCore.level(p, str) + 1)));
				} else {
					EXPCore.addLore(p, i);
					l.add(l.size() - 7,
							Format.color(enchColor + str + " &8/ " + enchColor + (EnchantCore.level(p, str) + 1)));
				}
			} else {
				l.add(l.size() - 7,
						Format.color(enchColor + str + " &8/ " + enchColor + (EnchantCore.level(p, str) + 1)));
			}
		} else {
			Message.player("Sorry, but you don't have the &c" + Format.decimals(0, EnchantCore.bloodCost(p))
					+ " Decaying Flesh &7required to purchase a random &4Blood&c Enchantment", p);
			return;
		}
		DungeonsCore.removeFlesh(p, (int) Math.round(EnchantCore.bloodCost(p)));
		m.setLore(l);
		i.setItemMeta(m);
		p.updateInventory();
		Message.player("&b+ 1 " + str + " level", p);
	}

	private static void vanillaEnchantApplication(Player p, ClickType click, Enchantment ench, String str) {
		ItemStack i = p.getInventory().getItemInMainHand();
		Integer lvl = i.getItemMeta().getEnchantLevel(ench);
		if (click == ClickType.LEFT) {
			Double cost = (EnchantCore.cost(str) + (lvl * EnchantCore.costIncrease(str)));
			if (TokensCore.hasTokens(p, cost)) {
				String enchColor = EnchantCore.randomColor();
				if (lvl < EnchantCore.maxLevel(p, str)) {
					ItemMeta m = i.getItemMeta();
					if (m.hasEnchant(ench)) {
						m.addEnchant(ench, (m.getEnchantLevel(ench) + 1), true);
					} else {
						m.addEnchant(ench, 1, true);
					}
					i.setItemMeta(m);
					p.updateInventory();
					List<String> l = new ArrayList<String>();
					if (i.getItemMeta().hasLore()) {
						l.addAll(m.getLore());
						Boolean hasEnchant = false;
						Integer x = -1;
						for (String line : i.getItemMeta().getLore()) {
							x++;
							if (line.contains(str)) {
								hasEnchant = true;
								break;
							}
						}
						if (hasEnchant) {
							l.set(x, Format.color(enchColor + str + "&8 / " + enchColor + i.getEnchantmentLevel(ench)));
						} else {
							l.add(l.size() - 7,
									Format.color(enchColor + str + "&8 / " + enchColor + i.getEnchantmentLevel(ench)));
						}
					} else {
						l.add(l.size() - 7, Format.color(enchColor + str + " &8/ " + enchColor));
					}
					m.setLore(l);
					m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					i.setItemMeta(m);
					p.updateInventory();
					Message.player("&b+ 1 " + str + " level", p);
					TokensCore.remove(p, cost);
					return;
				} else {
					Message.player("&7Sorry, but your &c" + str + "&7 is already the max level", p);
				}
			} else {
				Message.player("&7Sorry, but you don't have the &c" + Format.decimals(0, cost)
						+ " tokens&7 required to purchase &c" + str, p);
			}
		} else if (click == ClickType.RIGHT) {
			Double cost = 0.0;
			for (int x = 0; x < 10; x++) {
				cost = cost + (EnchantCore.cost(str) + (EnchantCore.costIncrease(str) * (lvl + x)));
			}
			if (EnchantCore.maxLevel(p, str) >= 10) {
				if (TokensCore.hasTokens(p, cost)) {
					String enchColor = EnchantCore.randomColor();
					if ((lvl + 9) < EnchantCore.maxLevel(p, str)) {
						ItemMeta m = i.getItemMeta();
						if (m.hasEnchant(ench)) {
							m.addEnchant(ench, (m.getEnchantLevel(ench) + 10), true);
						} else {
							m.addEnchant(ench, 10, true);
						}
						i.setItemMeta(m);
						p.updateInventory();
						List<String> l = new ArrayList<String>();
						if (i.getItemMeta().hasLore()) {
							l.addAll(m.getLore());
							Boolean hasEnchant = false;
							Integer x = -1;
							for (String line : i.getItemMeta().getLore()) {
								x++;
								if (line.contains(str)) {
									hasEnchant = true;
									break;
								}
							}
							if (hasEnchant) {
								l.set(x, Format
										.color(enchColor + str + "&8 / " + enchColor + i.getEnchantmentLevel(ench)));
							} else {
								l.add(l.size() - 7, Format
										.color(enchColor + str + "&8 / " + enchColor + i.getEnchantmentLevel(ench)));
							}
						} else {
							l.add(l.size() - 7, Format.color(enchColor + str + " &8/ " + enchColor));
						}
						m.setLore(l);
						m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						i.setItemMeta(m);
						p.updateInventory();
						Message.player("&b+ 10 " + str + " levels", p);
						TokensCore.remove(p, cost);
						return;
					} else {
						Message.player("&7Sorry, but this would exceed the max level for &c" + str, p);
					}
				} else {
					Message.player("&7Sorry, but you don't have the &c" + Format.decimals(0, cost)
							+ " tokens&7 required to purchase &c" + str, p);
				}
			} else {
				Message.player("&7This enchantment's max level is less than 10", p);
			}
		} else {
			return;
		}
	}

	private static ItemStack vanillaEnchantItem(Player p, Material mat, Enchantment ench, String str) {
		ItemStack i = new ItemStack(mat);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&l" + str));
		l.add("");
		ItemStack h = p.getInventory().getItemInMainHand();
		if (h != null) {
			l.add(Format.color("&7Max Level &8/ &b" + EnchantCore.maxLevel(p, str)));
			if (h.getItemMeta().hasEnchant(ench)) {
				Integer lvl = h.getItemMeta().getEnchantLevel(ench);
				if (lvl < EnchantCore.maxLevel(p, str)) {
					l.add(Format.color("&7Level &8/ &b" + h.getItemMeta().getEnchantLevel(ench)));
				} else {
					l.add(Format.color("&7Level &8/ &b" + h.getItemMeta().getEnchantLevel(ench) + " &8(&7Maxed&8)"));
				}
				l.add(Format.color("&7Cost &8/ &b"
						+ Format.decimals(0, (EnchantCore.cost(str) + (lvl * EnchantCore.costIncrease(str))))));
				Double cost = 0.0;
				for (int x = 0; x < 10; x++) {
					cost = cost + (EnchantCore.cost(str) + (EnchantCore.costIncrease(str) * (lvl + x)));
				}
				l.add(Format.color("&7Cost x10 &8/ &b" + Format.decimals(0, cost)));
			} else {
				l.add(Format.color("&7Level &8/ &b0"));
				l.add(Format.color("&7Cost &8/ &b" + EnchantCore.cost(str)));
				Double cost = 0.0;
				for (int x = 0; x < 10; x++) {
					cost = cost + (EnchantCore.cost(str) + (EnchantCore.costIncrease(str) * (0 + x)));
				}
				l.add(Format.color("&7Cost x10 &8/ &b" + Format.decimals(0, cost)));
			}
		}
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}
}
