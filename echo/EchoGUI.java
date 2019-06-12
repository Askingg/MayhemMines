package me.askingg.mayhem.echo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class EchoGUI implements Listener {

	public static List<Player> open = new ArrayList<Player>();

	public static Inventory menu(Player p) {
		Inventory menu = Bukkit.createInventory(null, 45, Format.color("&c&l" + p.getName() + "'s Echo"));
		placeholders(menu);
		if (EchoCore.hasEquippedRelic(p)) {
			menu.setItem(10, EchoCore.equippedRelic(p));
		} else {
			ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
			i.setDurability((byte) 11);
			ItemMeta m = i.getItemMeta();
			List<String> l = new ArrayList<String>();
			m.setDisplayName(Format.color("&9&l» Empty Relic Slot «"));
			l.add(Format.color("                              "));
			l.add(Format.color("&9● &7Place a &bRelic&7 in this"));
			l.add(Format.color("&7 &7 slot to equip it"));
			m.setLore(l);
			i.setItemMeta(m);
			menu.setItem(10, i);
		}
		if (EchoCore.hasEquippedArtefact(p)) {
			menu.setItem(16, EchoCore.equippedArtefact(p));
		} else {
			ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
			ItemMeta m = i.getItemMeta();
			List<String> l = new ArrayList<String>();
			i.setDurability((byte) 10);
			l = new ArrayList<String>();
			m.setDisplayName(Format.color("&4&l» Empty Artefact Slot «"));
			l.add(Format.color("                              "));
			l.add(Format.color("&4● &7Place an &cArtefact&7 in this"));
			l.add(Format.color("&7 &7 slot to equip it"));
			m.setLore(l);
			i.setItemMeta(m);
			menu.setItem(16, i);

		}
		if (EchoCore.hasEquippedCharm(p)) {
			menu.setItem(29, EchoCore.equippedCharm(p));
		} else {
			ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
			ItemMeta m = i.getItemMeta();
			List<String> l = new ArrayList<String>();
			i.setDurability((byte) 2);
			l = new ArrayList<String>();
			m.setDisplayName(Format.color("&5&l» Empty Charm Slot «"));
			l.add(Format.color("                              "));
			l.add(Format.color("&5● &7Place a &dCharm&7 in this"));
			l.add(Format.color("&7 &7 slot to equip it"));
			m.setLore(l);
			i.setItemMeta(m);
			menu.setItem(29, i);

		}ItemStack i = new ItemStack(Material.BARRIER);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		l = new ArrayList<String>();
		m.setDisplayName(Format.color("&6&l» Empty Totem Slot «"));
		l.add(Format.color("                              "));
		l.add(Format.color("&5● &7Place a &eTotem&7 in this"));
		l.add(Format.color("&7 &7 slot to equip it"));
		l.add(Format.color("&7 &7 &cComing Soon.."));
		m.setLore(l);
		i.setItemMeta(m);
		menu.setItem(33, i);
		return menu;
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (open.contains(p)) {
			if (e.getRawSlot() < 44) {
				e.setCancelled(true);
				if (e.getRawSlot() == 10 || e.getRawSlot() == 16 || e.getRawSlot() == 29 || e.getRawSlot() == 33) {
				} else {
					return;
				}
			}
			if (e.getRawSlot() < 44) {
				if (e.getCursor().getType() == Material.AIR) {
					if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()
							&& e.getCurrentItem().getItemMeta().hasDisplayName()
							&& e.getCurrentItem().getItemMeta().getDisplayName().contains(Format.color(" Empty"))
							&& e.getCurrentItem().getItemMeta().getDisplayName().contains(Format.color("Slot "))) {
						if (e.getRawSlot() == 10) {
							Message.player("&7Place a &bRelic&7 in this slot to equip it", p);
							return;
						}
						if (e.getRawSlot() == 16) {
							Message.player("&7Place an &cArtefact&7 in this slot to equip it", p);
							return;
						}
						if (e.getRawSlot() == 29) {
							Message.player("&7Place a &dCharm&7 in this slot to equip it", p);
							return;
						}
						if (e.getRawSlot() == 33) {
							Message.player(
									"&7Do you have a suggestion for a new item? Suggest it in the discord server", p);
							return;
						}
					}
				} else {
					if (e.getRawSlot() == 10) {
						if (e.getCurrentItem().getItemMeta().getDisplayName()
								.equals(Format.color("&9&l» Empty Relic Slot «"))) {
							if (e.getCursor().getType() != Material.AIR) {
								if (EchoCore.isRelic(e.getCursor())) {
									Files.data.set("Users." + p.getUniqueId() + ".Echo.Relic.Boost",
											EchoCore.relicBoost(e.getCursor()));
									Files.data.set("Users." + p.getUniqueId() + ".Echo.Relic.Equipped.Name",
											e.getCursor().getItemMeta().getDisplayName());
									Files.data.set("Users." + p.getUniqueId() + ".Echo.Relic.Equipped.Material",
											e.getCursor().getType().toString());
									try {
										Files.data.save(Files.dataFile);
										Message.player("&7You equipped a &brelic", p);
										e.getCursor().setAmount(0);
										p.updateInventory();
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
							}
						}
					}
					if (e.getRawSlot() == 16) {
						if (e.getCurrentItem().getItemMeta().getDisplayName()
								.equals(Format.color("&4&l» Empty Artefact Slot «"))) {
							if (e.getCursor().getType() != Material.AIR) {
								if (EchoCore.isArtefact(e.getCursor())) {
									Files.data.set("Users." + p.getUniqueId() + ".Echo.Artefact.Boost",
											EchoCore.artefactBoost(e.getCursor()));
									Files.data.set("Users." + p.getUniqueId() + ".Echo.Artefact.Equipped.Name",
											e.getCursor().getItemMeta().getDisplayName());
									Files.data.set("Users." + p.getUniqueId() + ".Echo.Artefact.Equipped.Material",
											e.getCursor().getType().toString());
									Files.data.set("Users." + p.getUniqueId() + ".Echo.Artefact.Equipped.Data",
											e.getCursor().getDurability());
									try {
										Files.data.save(Files.dataFile);
										Message.player("&7You equipped an &cArtefact", p);
										e.getCursor().setAmount(0);
										p.updateInventory();
										open.add(p);
										p.openInventory(menu(p));
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
				if (e.getRawSlot() == 29) {
					if (e.getCurrentItem().getItemMeta().getDisplayName()
							.equals(Format.color("&5&l» Empty Charm Slot «"))) {
						if (e.getCursor().getType() != Material.AIR) {
							if (EchoCore.isCharm(e.getCursor())) {
								Files.data.set("Users." + p.getUniqueId() + ".Echo.Charm.Equipped.Name",
										e.getCursor().getItemMeta().getDisplayName());
								Files.data.set("Users." + p.getUniqueId() + ".Echo.Charm.Equipped.Material",
										e.getCursor().getType().toString());
								Files.data.set("Users." + p.getUniqueId() + ".Echo.Charm.Equipped.Color",
										EchoCore.getCharmColor(e.getCursor()));
								HashMap<String, Integer> map = EchoCore.charmBoosts(e.getCursor());
								for (String str : map.keySet()) {
									Files.data.set("Users." + p.getUniqueId() + ".Echo.Charm.Equipped.Boosts." + str,
											map.get(str));
								}
								try {
									Files.data.save(Files.dataFile);
									Message.player("&7You equipped a &dCharm", p);
									e.getCursor().setAmount(0);
									p.updateInventory();
									open.add(p);
									p.openInventory(menu(p));
									HashMap<String, Integer> m = EchoCore.getCharmBoosts(p);
									for (String str : m.keySet()) {
										if (str.equals("Haste")) {
											p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING,
													Integer.MAX_VALUE, (m.get(str) - 1), true, false));
											continue;
										}
										if (str.equals("Speed")) {
											p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
													Integer.MAX_VALUE, (m.get(str) - 1), true, false));
											continue;
										}
										if (str.equals("JumpBoost")) {
											p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE,
													(m.get(str) - 1), true, false));
											continue;
										}
										if (str.equals("Regeneration")) {
											p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,
													Integer.MAX_VALUE, (m.get(str) - 1), true, false));
											continue;
										}
										if (str.equals("Strength")) {
											p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,
													Integer.MAX_VALUE, (m.get(str) - 1), true, false));
											continue;
										}
										if (str.equals("Slowness")) {
											p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE,
													(m.get(str) - 1), true, false));
											continue;
										}
										if (str.equals("MiningFatigue")) {
											p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,
													Integer.MAX_VALUE, (m.get(str) - 1), true, false));
											continue;
										}
									}
									return;
								} catch (Exception ex) {
									ex.printStackTrace();
									return;
								}
							}
						}
					}
				}
				if (EchoCore.isArtefact(e.getCurrentItem())) {
					if (p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(EchoCore.equippedArtefact(p));
						p.updateInventory();
						Files.data.set("Users." + p.getUniqueId() + ".Echo.Artefact", null);
						try {
							Files.data.save(Files.dataFile);
							Message.player("&7You un-equipped an &cArtefact", p);
							p.updateInventory();
							open.add(p);
							p.openInventory(menu(p));
							return;
						} catch (Exception ex) {
							ex.printStackTrace();
							return;
						}
					} else {
						Message.player("&7You cannot un-equip an &cArtefact&7 with a full inventory", p);
						return;
					}
				}
				if (EchoCore.isRelic(e.getCurrentItem())) {
					if (p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(EchoCore.equippedRelic(p));
						p.updateInventory();
						Files.data.set("Users." + p.getUniqueId() + ".Echo.Relic", null);
						try {
							Files.data.save(Files.dataFile);
							Message.player("&7You un-equipped a &brelic", p);
							p.updateInventory();
							open.add(p);
							p.openInventory(menu(p));
							return;
						} catch (Exception ex) {
							ex.printStackTrace();
							return;
						}
					} else {
						Message.player("&7You cannot un-equip a &brelic&7 with a full inventory", p);
						return;
					}
				}
				if (EchoCore.isCharm(e.getCurrentItem())) {
					if (p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(EchoCore.equippedCharm(p));
						p.updateInventory();
						Files.data.set("Users." + p.getUniqueId() + ".Echo.Charm", null);
						try {
							Files.data.save(Files.dataFile);
							Message.player("&7You un-equipped a &dCharm", p);
							p.updateInventory();
							open.add(p);
							p.openInventory(menu(p));
							if (p.hasPotionEffect(PotionEffectType.FAST_DIGGING))
								p.removePotionEffect(PotionEffectType.FAST_DIGGING);
							if (p.hasPotionEffect(PotionEffectType.SPEED))
								p.removePotionEffect(PotionEffectType.SPEED);
							if (p.hasPotionEffect(PotionEffectType.JUMP))
								p.removePotionEffect(PotionEffectType.JUMP);
							if (p.hasPotionEffect(PotionEffectType.REGENERATION))
								p.removePotionEffect(PotionEffectType.REGENERATION);
							if (p.hasPotionEffect(PotionEffectType.INCREASE_DAMAGE))
								p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
							if (p.hasPotionEffect(PotionEffectType.SLOW))
								p.removePotionEffect(PotionEffectType.SLOW);
							if (p.hasPotionEffect(PotionEffectType.SLOW_DIGGING))
								p.removePotionEffect(PotionEffectType.SLOW_DIGGING);
							return;
						} catch (Exception ex) {
							ex.printStackTrace();
							return;
						}
					} else {
						Message.player("&7You cannot un-equip a &dCharm&7 with a full inventory", p);
						return;
					}
				}
			}
		}
	}

	@EventHandler
	public void close(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
		}
	}

	private static void placeholders(Inventory menu) {
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 7);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		menu.setItem(0, i);
		menu.setItem(1, i);
		menu.setItem(2, i);
		menu.setItem(3, i);
		menu.setItem(4, i);
		menu.setItem(5, i);
		menu.setItem(6, i);
		menu.setItem(7, i);
		menu.setItem(8, i);
		menu.setItem(12, i);
		menu.setItem(13, i);
		menu.setItem(14, i);
		menu.setItem(18, i);
		menu.setItem(19, i);
		menu.setItem(20, i);
		menu.setItem(21, i);
		menu.setItem(22, i);
		menu.setItem(23, i);
		menu.setItem(24, i);
		menu.setItem(25, i);
		menu.setItem(26, i);
		menu.setItem(27, i);
		menu.setItem(31, i);
		menu.setItem(35, i);
		menu.setItem(36, i);
		menu.setItem(37, i);
		menu.setItem(38, i);
		menu.setItem(39, i);
		menu.setItem(40, i);
		menu.setItem(41, i);
		menu.setItem(42, i);
		menu.setItem(44, i);
		menu.setItem(43, i);
		ItemStack i2r = new ItemStack(Material.STAINED_GLASS_PANE);
		i2r.setDurability((byte) 3);
		ItemMeta m2r = i2r.getItemMeta();
		m2r.setDisplayName(Format.color("&b&l« Relics «"));
		List<String> l2r = new ArrayList<String>();
		l2r.add(Format.color("                              "));
		l2r.add(Format.color("&b● &7Place a relic in the"));
		l2r.add(Format.color("&7 &7 slot to equip it"));
		m2r.setLore(l2r);
		i2r.setItemMeta(m2r);
		ItemStack i2l = new ItemStack(Material.STAINED_GLASS_PANE);
		i2l.setDurability((byte) 3);
		ItemMeta m2l = i2l.getItemMeta();
		m2l.setDisplayName(Format.color("&b&l» Relics »"));
		List<String> l2l = new ArrayList<String>();
		l2l.add(Format.color("                              "));
		l2l.add(Format.color("&b● &7Place a relic in the"));
		l2l.add(Format.color("&7 &7 slot to equip it"));
		m2l.setLore(l2l);
		i2l.setItemMeta(m2l);
		ItemStack i3r = new ItemStack(Material.STAINED_GLASS_PANE);
		i3r.setDurability((byte) 14);
		ItemMeta m3r = i3r.getItemMeta();
		m3r.setDisplayName(Format.color("&c&l« Artefacts «"));
		List<String> l3r = new ArrayList<String>();
		l3r.add(Format.color("                              "));
		l3r.add(Format.color("&c● &7Place an artefact in the"));
		l3r.add(Format.color("&7 &7 slot to equip it"));
		m3r.setLore(l3r);
		i3r.setItemMeta(m3r);
		ItemStack i3l = new ItemStack(Material.STAINED_GLASS_PANE);
		i3l.setDurability((byte) 14);
		ItemMeta m3l = i3l.getItemMeta();
		m3l.setDisplayName(Format.color("&c&l» Artefact »"));
		List<String> l3l = new ArrayList<String>();
		l3l.add(Format.color("                              "));
		l3l.add(Format.color("&c● &7Place an artefact in the"));
		l3l.add(Format.color("&7 &7 slot to equip it"));
		m3l.setLore(l3l);
		i3l.setItemMeta(m3l);

		ItemStack i4r = new ItemStack(Material.STAINED_GLASS_PANE);
		i4r.setDurability((byte) 6);
		ItemMeta m4r = i4r.getItemMeta();
		m4r.setDisplayName(Format.color("&d&l« Charms «"));
		List<String> l4r = new ArrayList<String>();
		l4r.add(Format.color("                              "));
		l4r.add(Format.color("&d● &7Place a charm in the"));
		l4r.add(Format.color("&7 &7 slot to equip it"));
		m4r.setLore(l4r);
		i4r.setItemMeta(m4r);
		ItemStack i4l = new ItemStack(Material.STAINED_GLASS_PANE);
		i4l.setDurability((byte) 6);
		ItemMeta m4l = i4l.getItemMeta();
		m4l.setDisplayName(Format.color("&d&l» Charms »"));
		List<String> l4l = new ArrayList<String>();
		l4l.add(Format.color("                              "));
		l4l.add(Format.color("&d● &7Place a charm in the"));
		l4l.add(Format.color("&7 &7 slot to equip it"));
		m4l.setLore(l4l);
		i4l.setItemMeta(m4l);

		ItemStack i5r = new ItemStack(Material.STAINED_GLASS_PANE);
		i5r.setDurability((byte) 5);
		ItemMeta m5r = i5r.getItemMeta();
		m5r.setDisplayName(Format.color("&a&l« Coming Soon «"));
		List<String> l5r = new ArrayList<String>();
		l5r.add(Format.color("                              "));
		l5r.add(Format.color("&a● &7Have a suggestion for an"));
		l5r.add(Format.color("&7 &7 item for this slot? Make"));
		l5r.add(Format.color("&7 &7 a suggestion in the"));
		l5r.add(Format.color("&7 &7 discord server"));
		m5r.setLore(l5r);
		i5r.setItemMeta(m5r);
		ItemStack i5l = new ItemStack(Material.STAINED_GLASS_PANE);
		i5l.setDurability((byte) 5);
		ItemMeta m5l = i5l.getItemMeta();
		m5l.setDisplayName(Format.color("&a&l» Coming Soon »"));
		List<String> l5l = new ArrayList<String>();
		l5l.add(Format.color("                              "));
		l5l.add(Format.color("&a● &7Have a suggestion for an"));
		l5l.add(Format.color("&7 &7 item for this slot? Make"));
		l5l.add(Format.color("&7 &7 a suggestion in the"));
		l5l.add(Format.color("&7 &7 discord server"));
		m5l.setLore(l5l);
		i5l.setItemMeta(m5l);
		menu.setItem(9, i2l);
		menu.setItem(11, i2r);
		menu.setItem(15, i3l);
		menu.setItem(17, i3r);
		menu.setItem(28, i4l);
		menu.setItem(30, i4r);
		menu.setItem(32, i5l);
		menu.setItem(34, i5r);
	}
}