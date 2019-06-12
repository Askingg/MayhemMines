package me.askingg.mayhem.zombies;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitUtil;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldedit.regions.CuboidRegion;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;
import me.askingg.mayhem.utils.Misc;

public class SIgnEvents implements Listener {

	/////////////////
	// SIGN FORMAT:
	/////////////////
	// [zombies]
	//
	// door
	// <cost>
	/////////////////

	@EventHandler
	public void blockBreak(BlockBreakEvent e) {
		if (e.getBlock() != null || !e.getBlock().getType().equals(Material.AIR)) {
			if (e.getBlock().getType() == Material.SIGN || e.getBlock().getType() == Material.SIGN_POST
					|| e.getBlock().getType() == Material.WALL_SIGN) {
				Player p = e.getPlayer();
				Sign s = (Sign) e.getBlock().getState();
				if (s.getLine(0).equals(Format.color("[&4&lZombies&0]"))) {
					if (p.hasPermission("mayhem.zombies.removesign")) {
						if (p.isSneaking()) {
							for (String c : Files.config.getConfigurationSection("Zombies.Doors").getKeys(false)) {
								Location l = new Location(
										Bukkit.getWorld(
												Files.config.getString("Zombies.Doors." + c + ".Location.World")),
										Files.config.getDouble("Zombies.Doors." + c + ".Location.X"),
										Files.config.getDouble("Zombies.Doors." + c + ".Location.Y"),
										Files.config.getDouble("Zombies.Doors." + c + ".Location.Z"));
								if (s.getLocation().equals(l)) {
									Files.config.set("Zombies.Doors." + c, null);
									try {
										Files.config.save(Files.configFile);
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
									}
								}
							}
						}
					}
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void signChange(SignChangeEvent e) {
		if (e.getLine(0).equalsIgnoreCase("[zombies]")) {
			Player p = e.getPlayer();
			if (p.hasPermission("mayhem.zombies.createsign")) {
				if (e.getLine(2).equalsIgnoreCase("door")) {
					if (!e.getLine(3).equals("")) {
						try {
							Integer.parseInt(e.getLine(3));
						} catch (Exception ex) {
							Message.player("Line 4 is an invalid integer", p);
							return;
						}
						e.setLine(0, Format.color("[&4&lZombies&0]"));
						e.setLine(2, Format.color("&fDoor"));
						e.setLine(3, Format.color("&fCost &b" + e.getLine(3)));
						String c = Misc.randomCode();
						while (Files.config.getString("Zombies.Doors." + c) != null) {
							c = Misc.randomCode();
						}
						Location l = e.getBlock().getLocation();
						Files.config.set("Zombies.Doors." + c + ".Location.World", l.getWorld().getName());
						Files.config.set("Zombies.Doors." + c + ".Location.X", l.getX());
						Files.config.set("Zombies.Doors." + c + ".Location.Y", l.getY());
						Files.config.set("Zombies.Doors." + c + ".Location.Z", l.getZ());
						Files.config.set("Zombies.Doors." + c + ".Door", null);
						try {
							Files.config.save(Files.configFile);
							Message.player("Created a door sign", p);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			} else {
				Message.player("Sorry, but you don't have permission to setup &4&lZombies&7 signs", p);
				return;
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void signInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock() != null || !e.getClickedBlock().getType().equals(Material.AIR)) {
				if (e.getClickedBlock().getType() == Material.SIGN
						|| e.getClickedBlock().getType() == Material.SIGN_POST
						|| e.getClickedBlock().getType() == Material.WALL_SIGN) {
					Player p = e.getPlayer();
					Sign s = (Sign) e.getClickedBlock().getState();
					if (s.getLine(0).equals(Format.color("[&4&lZombies&0]"))) {
						ItemStack i = p.getInventory().getItemInMainHand();
						if (i != null) {
							Selection sel = Main.getWE().getSelection(p);
							if (Files.config.getConfigurationSection("Zombies.Doors") != null) {
								for (String c : Files.config.getConfigurationSection("Zombies.Doors").getKeys(false)) {
									Location l = new Location(
											Bukkit.getWorld(
													Files.config.getString("Zombies.Doors." + c + ".Location.World")),
											Files.config.getDouble("Zombies.Doors." + c + ".Location.X"),
											Files.config.getDouble("Zombies.Doors." + c + ".Location.Y"),
											Files.config.getDouble("Zombies.Doors." + c + ".Location.Z"));
									if (s.getLocation().equals(l)) {
										if (i.getType() == Material.BARRIER) {
											if (Main.getWE().getSelection(p) == null) {
												Message.player("Please make a selection first", p);
												return;
											}
											Files.config.set("Zombies.Doors." + c + ".Door.World",
													sel.getWorld().getName());
											Files.config.set("Zombies.Doors." + c + ".Door.MaxPoint.X",
													sel.getMaximumPoint().getX());
											Files.config.set("Zombies.Doors." + c + ".Door.MaxPoint.Y",
													sel.getMaximumPoint().getY());
											Files.config.set("Zombies.Doors." + c + ".Door.MaxPoint.Z",
													sel.getMaximumPoint().getZ());
											Files.config.set("Zombies.Doors." + c + ".Door.MinPoint.X",
													sel.getMinimumPoint().getX());
											Files.config.set("Zombies.Doors." + c + ".Door.MinPoint.Y",
													sel.getMinimumPoint().getY());
											Files.config.set("Zombies.Doors." + c + ".Door.MinPoint.Z",
													sel.getMinimumPoint().getZ());
											try {
												Files.config.save(Files.configFile);
												Message.player("Setup the door location for that sign", p);
												return;
											} catch (Exception ex) {
												ex.printStackTrace();
												return;
											}
										} else {
											CuboidSelection cs = new CuboidSelection(
													Bukkit.getWorld(Files.config
															.getString("Zombies.Doors." + c + ".Door.World")),
													new Location(
															Bukkit.getWorld(Files.config
																	.getString("Zombies.Doors." + c + ".Door.World")),
															Files.config.getDouble(
																	"Zombies.Doors." + c + ".Door.MaxPoint.X"),
															Files.config.getDouble(
																	"Zombies.Doors." + c + ".Door.MaxPoint.Y"),
															Files.config.getDouble(
																	"Zombies.Doors." + c + ".Door.MaxPoint.Z")),
													new Location(
															Bukkit.getWorld(Files.config
																	.getString("Zombies.Doors." + c + ".Door.World")),
															Files.config.getDouble(
																	"Zombies.Doors." + c + ".Door.MinPoint.X"),
															Files.config.getDouble(
																	"Zombies.Doors." + c + ".Door.MinPoint.Y"),
															Files.config.getDouble(
																	"Zombies.Doors." + c + ".Door.MinPoint.Z")));
											CuboidRegion cr = new CuboidRegion(BukkitUtil.getLocalWorld(s.getWorld()),
													cs.getNativeMaximumPoint(), cs.getNativeMinimumPoint());
											EditSession sesh = new EditSession(BukkitUtil.getLocalWorld(s.getWorld()),
													cr.getArea());
											try {
												sesh.setBlocks(cr, new BaseBlock(0));
												sesh.flushQueue();
											} catch (MaxChangedBlocksException ex) {
												ex.printStackTrace();
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
