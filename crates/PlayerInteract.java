package me.askingg.mayhem.crates;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class PlayerInteract implements Listener {

	int id = 0;

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if (e.getClickedBlock().getType() == Material.CHEST) {
				for (String str : Files.config.getConfigurationSection("Crates.Locations").getKeys(false)) {
					if (e.getClickedBlock().getLocation().getX() == Files.config
							.getDouble("Crates.Locations." + str + ".X")) {
						Location l = new Location(
								Bukkit.getWorld(Files.config.getString("Crates.Locations." + str + ".World")),
								Files.config.getDouble("Crates.Locations." + str + ".X"),
								Files.config.getDouble("Crates.Locations." + str + ".Y"),
								Files.config.getDouble("Crates.Locations." + str + ".Z"));
						String type = Files.config.getString("Crates.Locations." + str + ".Type");
						List<String> crateTypes = new ArrayList<String>();
						for (String key : Files.config.getConfigurationSection("Crates").getKeys(false)) {
							crateTypes.add(key);
						}
						if (e.getClickedBlock().getLocation().equals(l)) {
							e.setCancelled(true);
							if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
								PreviewGUI.open.add(p);
								p.openInventory(PreviewGUI.menu(p, type));
								return;
							}
							ItemStack i = p.getInventory().getItemInMainHand();
							if (i != null && i.getType() != Material.AIR) {
								if (i.hasItemMeta()) {
									ItemMeta m = i.getItemMeta();
									if (m.hasDisplayName() && m.hasLore()) {
										if (m.getDisplayName().contains("Key")) {
											if (ChatColor.stripColor(Format.color(m.getLore().get(4)))
													.equals(" ● Use this key at /Warp Crates")) {
												if (!p.isSneaking()) {
													if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
														for (String crate : crateTypes) {

															if (m.getDisplayName().contains(crate)) {
																CratesCore.openCrate(p, crate);
																i.setAmount(i.getAmount() - 1);
																p.getInventory().setItemInMainHand(i);
																p.updateInventory();
																return;
															}
														}
													}
												} else {
													if (CratesCore.canOpen) {
														CratesCore.canOpen = false;
														if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
															for (String crate : crateTypes) {
																if (m.getDisplayName().contains(crate)) {
																	id = Bukkit.getServer().getScheduler()
																			.scheduleAsyncRepeatingTask(Main.instance,
																					new Runnable() {
																						int x = i.getAmount();

																						public void run() {
																							if (x <= 0) {
																								Bukkit.getScheduler()
																										.cancelTask(id);
																								CratesCore.canOpen = true;
																							}
																							x--;
																							CratesCore.openCrate(p,
																									crate);
																						}
																					}, 2, 2);
																	i.setAmount(0);
																	p.getInventory().setItemInMainHand(i);
																	return;
																}
															}
														}
														return;
													} else {
														Message.player(
																"Sorry, another user is currently opening a crate, please wait",
																p);
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
				}
			}
		}
	}
}