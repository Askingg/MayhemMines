package me.askingg.mayhem.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.entities.dungeons.DungeonsCore;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class Flesh implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+-------------&8( &c&lFlesh&b&lCommands&8 )&m-------------+", sender);
			Message.senderRaw("&8 ● &b/Flesh &7View the help list", sender);
			Message.senderRaw("&8 ● &b/Flesh Grinder &7Open the flesh grinder", sender);
			Message.senderRaw("&8 ● &b/Flesh Withdraw &7Withdraw your flesh", sender);
			Message.senderRaw("&8 ● &b/Flesh Give &7Give a player flesh", sender);
			Message.senderRaw("&8 ● &b/Flesh Givev &7Give a player virtual flesh", sender);
			Message.footer(sender);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("grinder")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					Grinder.open.add(p);
					p.openInventory(Grinder.grinder(p));
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("Withdraw")) { // Flesh WIthdraw <Amount>
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (args.length == 2) {
						int a = 0;
						try {
							a = Integer.parseInt(args[1]);
						} catch (Exception ex) {
							Message.sender("Sorry, buc &c" + args[1] + "&7 is an invalid integer", sender);
							return true;
						}
						if (a <= 0) {
							Message.player("Sorry, but you must withdraw atleast 1 flesh", p);
							return true;
						}
						if (DungeonsCore.getFlesh(p) >= a) {
							if (p.getInventory().firstEmpty() == -1) {
								Message.sender("Sorry, but your inventory is full", sender);
								return true;
							}
							ItemStack i = new ItemStack(Material.ROTTEN_FLESH);
							i.setAmount(a);
							ItemMeta m = i.getItemMeta();
							List<String> l = new ArrayList<String>();
							m.setDisplayName(Format.color("&cDecayed Flesh"));
							l.add(Format.color("&7"));
							l.add(Format.color("&7Used to obtain unique enchantments"));
							m.setLore(l);
							i.setItemMeta(m);
							p.getInventory().addItem(i);
							p.updateInventory();
							DungeonsCore.removeFlesh(p, a);
							Message.player("You withdrew &b" + Format.decimals(0, a + 0.0) + " flesh", p);
							return true;
						}
					} else {
						Message.sender("Usage &b/Flesh Withdraw <Amount>", sender);
						return true;
					}
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("give")) { // Flesh give <player> <amount>
				if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.flesh.give")) {
					if (args.length == 3) {
						Player p = Bukkit.getPlayer(args[1]);
						if (p == null) {
							Message.sender("Sorry, but &c" + args[1] + "&7 is an invalid player", sender);
							return true;
						}
						int a = 0;
						try {
							a = Integer.parseInt(args[2]);
						} catch (Exception ex) {
							Message.sender("Sorry, buc &c" + args[2] + "&7 is an invalid integer", sender);
							return true;
						}
						if (p.getInventory().firstEmpty() == -1) {
							Message.sender("Sorry, but &c" + args[1] + "'s&7 inventory is full", sender);
							return true;
						}
						ItemStack i = new ItemStack(Material.ROTTEN_FLESH);
						i.setAmount(a);
						ItemMeta m = i.getItemMeta();
						List<String> l = new ArrayList<String>();
						m.setDisplayName(Format.color("&cDecayed Flesh"));
						l.add(Format.color("&7"));
						l.add(Format.color("&7Used to obtain unique enchantments"));
						m.setLore(l);
						i.setItemMeta(m);
						p.getInventory().addItem(i);
						p.updateInventory();
						Message.sender("You gave &b" + args[1] + " " + Format.decimals(0, a + 0.0) + " flesh", sender);
						Message.player("&a + " + Format.decimals(0, a + 0.0) + " flesh", p);
						return true;
					} else {
						Message.sender("Usage &b/Flesh Give <Player> <Amount>", sender);
						return true;
					}
				} else {
					Message.sender("Sorry, but you don't have permission to do this", sender);
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("givev")) { // Flesh give <player> <amount>
				if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.flesh.give")) {
					if (args.length == 3) {
						Player p = Bukkit.getPlayer(args[1]);
						if (p == null) {
							Message.sender("Sorry, but &c" + args[1] + "&7 is an invalid player", sender);
							return true;
						}
						int a = 0;
						try {
							a = Integer.parseInt(args[2]);
						} catch (Exception ex) {
							Message.sender("Sorry, buc &c" + args[2] + "&7 is an invalid integer", sender);
							return true;
						}
						try {
							Files.data.set("Users." + p.getUniqueId() + ".Flesh", (Files.data.getInt("Users." + p.getUniqueId() + ".Flesh") + a));
							Files.data.save(Files.dataFile);
							Message.player("&a + " + Format.decimals(0, a + 0.0) + " flesh (virtual)", p);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						return true;
					} else {
						Message.sender("Usage &b/Flesh Givev <Player> <Amount>", sender);
						return true;
					}
				} else {
					Message.sender("Sorry, but you don't have permission to do this", sender);
					return true;
				}
			}
		}
		return false;
	}
}
