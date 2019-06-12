package me.askingg.mayhem.miningrewards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class Rewards implements CommandExecutor, Listener {

	private static List<Player> open = new ArrayList<Player>();
	//private static HashMap<Player, Entry<Integer, String>> map = new HashMap<Player, Entry<Integer, String>>();//Player, Slot, Code
	private static HashMap<Player, List<String>> map = new HashMap<Player, List<String>>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (!RewardsCore.hasRewards(p)) {
				Message.player("&7Sorry, but you don't have any rewards to claim", p);
				return true;
			}
			open.add(p);
			p.openInventory(menu(p));
			return true;
		}
		return false;
	}

	private static Inventory menu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, Format.color("&c&lMining Rewards"));
		Integer s = 0;
		List<String> l = new ArrayList<String>();
		for (String str : RewardsCore.getCodes(p)) {
			if (s < 45) {
				inv.setItem(s, RewardsCore.getReward(p, str));
				l.add(s.toString() + ";" + str);
				s++;
			}
		}
		map.put(p, l);
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 7);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		while (s < 45) {
			inv.setItem(s, i);
			s++;
		}
		return inv;
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		if (open.contains(e.getWhoClicked())) {
			e.setCancelled(true);
			if (e.getRawSlot() <= 44) {
				ItemStack i = e.getCurrentItem();
				if (i.getType() != Material.STAINED_GLASS_PANE) {
					Player p = (Player) e.getWhoClicked();
					if (p.getInventory().firstEmpty() == -1) {
						Message.player("Sorry, but your inventory is full", p);
						return;
					}
					ItemStack g = new ItemStack(Material.STAINED_GLASS_PANE);
					g.setDurability((byte) 7);
					ItemMeta m = g.getItemMeta();
					m.setDisplayName(Format.color("&7You already claimed this reward"));
					g.setItemMeta(m);
					e.getInventory().setItem(e.getRawSlot(), g);
					try {
						for (String str : map.get(p)) {
							if (Integer.valueOf(str.split("\\;")[0]) == e.getRawSlot()) {
								Files.data.set("Users." + p.getUniqueId() + ".MiningRewards." + str.split("\\;")[1], null);
								Files.data.save(Files.dataFile);
								break;
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					p.getInventory().addItem(i);
					p.updateInventory();
				}
			}
		}
	}

	@EventHandler
	public void close(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
			map.remove(e.getPlayer());
		}
	}
}
