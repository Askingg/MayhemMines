package me.askingg.mayhem.commands;

import java.util.ArrayList;
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

public class Grinder implements CommandExecutor, Listener {

	public static List<Player> open = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			open.add(p);
			p.openInventory(grinder(p));
		}
		return false;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (open.contains(e.getWhoClicked())) {
			if (e.getRawSlot() == 4) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if (open.contains(p)) {
			Integer c = 0;
			for (ItemStack i : e.getInventory()) {
				if (i != null) {
					if (i.getType() != Material.SIGN) {
						if (i.getType() == Material.ROTTEN_FLESH) {
							if (i.hasItemMeta()) {
								ItemMeta m = i.getItemMeta();
								if (m.hasDisplayName() && m.getDisplayName().equals(Format.color("&cDecayed Flesh"))) {
									if (m.hasLore() && m.getLore().get(1)
											.equals(Format.color("&7Used to obtain unique enchantments"))) {
										c+=i.getAmount();
										continue;
									}
								}
							}
						}
						p.getInventory().addItem(i);
					}
				}
			}
			try {
				Files.data.set("Users." + p.getUniqueId() + ".Flesh", (Files.data.getInt("Users." + p.getUniqueId() + ".Flesh") + c));
				Files.data.save(Files.dataFile);
				Message.player("You claimed &b" + c + "&7 Flesh", p);
				p.updateInventory();
				open.remove(p);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static Inventory grinder(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, Format.color("&c&lGrinder"));
		ItemStack i = new ItemStack(Material.SIGN);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&lGrinder Information"));
		l.add(Format.color("&7"));
		l.add(Format.color("&b &nHow to use the grinder&b:"));
		l.add(Format.color("&8 ● &7Simply put all of your flesh into"));
		l.add(Format.color("&7 &7 &7 this menu, and close it. It will"));
		l.add(Format.color("&7 &7 &7 then automatically give you all"));
		l.add(Format.color("&7 &7 &7 of your flesh points"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(4, i);
		return inv;
	}
}
