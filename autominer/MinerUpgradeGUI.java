package me.askingg.mayhem.autominer;

import java.util.ArrayList;
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

import me.askingg.mayhem.entities.dungeons.DungeonsCore;
import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.tokens.TokensCore;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;

public class MinerUpgradeGUI implements Listener {

	public static ArrayList<Player> open = new ArrayList<Player>();

	public static Inventory menu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, Format.color("&c&lAutoMinerUpgrades"));
		List<String> li = new ArrayList<String>();
		li.add("TimeReduce");
		li.add("MoreMoney");
		li.add("MoreBps");
		li.add("MoreTokens");
		li.add("MoreCredits");
		li.add("DroprateRelic");
		li.add("DroprateArtefact");
		li.add("MinimumRelic");
		li.add("MinimumArtefact");
		li.add("RewardsInventory");
		for (String str : li) {
			ItemStack i = new ItemStack(Material.STONE);
			ItemMeta m = i.getItemMeta();
			m.setDisplayName(Format.color("&c&l" + str));
			List<String> l = new ArrayList<String>();
			double cost = MinerUpgradeCore.getBaseCost(str)
					+ MinerUpgradeCore.getCostUpgrade(str) * MinerUpgradeCore.getLevel(p, str);
			l.add(Format.color("&7Cost &b" + cost));
			m.setLore(l);
			i.setItemMeta(m);
			inv.addItem(i);
		}
		return inv;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (open.contains(e.getWhoClicked())) {
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			if (e.getSlot() <= 35) {
				if (e.getCurrentItem().getType() != Material.AIR) {
					ItemStack i = e.getCurrentItem();
					ItemMeta im = i.getItemMeta();
					String displayName = im.getDisplayName();
					List<String> enchants = new ArrayList<String>();
					for (String str : Files.data.getConfigurationSection("Users." + p.getUniqueId() + ".MinerUpgrades")
							.getKeys(false)) {
						enchants.add(str);
					}
					for (String s : enchants) {
						if (displayName.contains(s)) {
							checkBuy(p, s);
						}
					}
				}
			}
		}
	}

	private boolean checkBuy(Player p, String name) {
		if (MinerUpgradeCore.getLevel(p, name) != MinerUpgradeCore.getMaxLevel(name)) {
			double cost = MinerUpgradeCore.getBaseCost(name)
					+ MinerUpgradeCore.getCostUpgrade(name) * MinerUpgradeCore.getLevel(p, name);

			if (MinerUpgradeCore.getCurrency(name).equalsIgnoreCase("Tokens")) {
				if (TokensCore.hasTokens(p, cost)) {
					TokensCore.remove(p, cost);
					MinerUpgradeCore.upgradeLevel(p, name);
					return true;
				}
			}

			if (MinerUpgradeCore.getCurrency(name).equalsIgnoreCase("Flesh")) {
				if (DungeonsCore.getFlesh(p) >= cost) {
					DungeonsCore.removeFlesh(p, (int) cost);
					MinerUpgradeCore.upgradeLevel(p, name);
					return true;
				}
			}

			if (MinerUpgradeCore.getCurrency(name).equalsIgnoreCase("Money")) {
				if (Main.eco.getBalance(p) >= cost) {
					Main.eco.withdrawPlayer(p, cost);
					MinerUpgradeCore.upgradeLevel(p, name);
					return true;
				}
			}

			p.sendMessage("You don't have enough " + MinerUpgradeCore.getCurrency(name) + " to upgrade " + name + ".");
		}

		p.sendMessage("You have already maxed out " + name + ".");
		return false;

	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
		}
	}
}
