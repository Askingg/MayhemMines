package me.askingg.mayhem.crates;

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

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class CrateCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+-------------&8( &c&lCrate&b&lCommands&8 )&m------------+", sender);
			Message.senderRaw("&8 ● &b/Crate &7View the help list", sender);
			Message.senderRaw("&8 ● &b/Crate get &7Get a crate", sender);
			Message.senderRaw("&8 ● &b/Crate Give &7Give keys", sender);
			Message.senderRaw("&8 ● &b/Crate GiveAll &7Give keys to all online players", sender);
			Message.footer(sender);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("get")) { // Crate Get <Crate>
				if (sender instanceof Player && sender.hasPermission("mayhem.crate.get")) {
					if (args.length == 2) {
						if (args[1].equalsIgnoreCase("argon")
								|| args[1].equalsIgnoreCase("krypton") | args[1].equalsIgnoreCase("xenon")) {
							ItemStack i = new ItemStack(Material.CHEST);
							ItemMeta m = i.getItemMeta();
							List<String> l = new ArrayList<String>();
							m.setDisplayName(Format.color("&3&l" + args[1] + " Crate"));
							l.add(Format.color("&7Place to create a chest location"));
							m.setLore(l);
							i.setItemMeta(m);
							Player p = (Player) sender;
							p.getInventory().addItem(i);
							p.updateInventory();
							Message.player("You received a crate.", p);
							return true;
						} else {
							Message.sender("Valid crate types: &bArgon&7, &cKrypton&7, &4Xenon", sender);
							return true;
						}
					} else {
						Message.sender("Usage &b/Crate Get <Crate>", sender);
						return true;
					}
				} else {
					Message.sender("Sorry, but you don't have permission to do that", sender);
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("give")) { // crate give <player> <crate> <amount>
				if (!(sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.crates.give"))) {
					Message.sender("Sorry, but you don't have permission to do this", sender);
					return true;
				}
				if (args.length == 4) {
					Player p = Bukkit.getPlayer(args[1]);
					if (p == null) {
						Message.sender("Sorry, but &c" + args[1] + "&7 is an invalid player", sender);
						return true;
					}
					if (!(args[2].equalsIgnoreCase("argon") || args[2].equalsIgnoreCase("krypton")
							|| args[2].equalsIgnoreCase("xenon"))) {
						Message.sender("Valid crate types: &bArgon&7, &cKrypton&7, &4Xenon", sender);
						return true;
					}
					int x = 0;
					try {
						x = Integer.parseInt(args[3]);
					} catch (Exception ex) {
						Message.sender("Sorry, but &c" + args[3] + "&7 is an invalid integer", sender);
						return true;
					}
					if (x <= 0) {
						Message.sender("Sorry, but the amount must be greater than 0", sender);
						return true;
					}
					ItemStack i = new ItemStack(Material.TRIPWIRE_HOOK);
					i.setAmount(x);
					ItemMeta m = i.getItemMeta();
					List<String> l = new ArrayList<String>();
					if (args[2].equalsIgnoreCase("argon")) {
						m.setDisplayName(Format.color("&b&lArgon Key"));
						l.add(Format.color("&7"));
						l.add(Format.color("&8 ● &b&lAr"));
						l.add(Format.color("&7"));
						l.add(Format.color("&8 ● &7Crate Tier: &b1"));
						l.add(Format.color("&8 ● &7Use this key at &b/Warp Crates"));
					}
					if (args[2].equalsIgnoreCase("krypton")) {
						m.setDisplayName(Format.color("&c&lKrypton Key"));
						l.add(Format.color("&7"));
						l.add(Format.color("&8 ● &c&lKr"));
						l.add(Format.color("&7"));
						l.add(Format.color("&8 ● &7Crate Tier: &c2"));
						l.add(Format.color("&8 ● &7Use this key at &c/Warp Crates"));
					}
					if (args[2].equalsIgnoreCase("xenon")) {
						m.setDisplayName(Format.color("&4&lXenon Key"));
						l.add(Format.color("&7"));
						l.add(Format.color("&8 ● &4&lXe"));
						l.add(Format.color("&7"));
						l.add(Format.color("&8 ● &7Crate Tier: &43"));
						l.add(Format.color("&8 ● &7Use this key at &4/Warp Crates"));
					}
					m.setLore(l);
					i.setItemMeta(m);
					p.getInventory().addItem(i);
					p.updateInventory();
					Message.playerRaw("&8&l[&3&lKeys&8&l]&b You received " + x + " " + args[2].toLowerCase() + " key(s)", p);
					return true;
				} else {
					Message.sender("Usage &b/Crate Give <Player> <Crate> <Amount>", sender);
					return true;
				}
			}
		}
		return false;
	}
}
