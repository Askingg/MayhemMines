package me.askingg.mayhem.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.echo.EchoCore;
import me.askingg.mayhem.miningrewards.RewardsCore;
import me.askingg.mayhem.tokens.TokensCore;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class Treasure implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.treasure.command")) { // Treasure
																											// <Player>
																											// <Amount>
			if (args.length == 2) {
				Player p = Bukkit.getPlayer(args[0]);
				if (p == null) {
					Message.sender("Sorry, but &c" + args[0] + "&7 is an invalid player", sender);
					return true;
				}
				Integer x = 0;
				try {
					x = Integer.parseInt(args[1]);
				} catch (Exception ex) {
					Message.sender("Sorry, but &c" + args[1] + "&7 is an invalid integer", sender);
					return true;
				}
				if (x < 1) {
					Message.sender("Sorry, but the amount must be greater than 0", sender);
					return true;
				}
				Message.player("You were given the contents of &b" + x
						+ "&7 buried treasures: Claim your rewards with &b/Rewards", p);
				Random r = new Random();
				Integer c = -1;
				while (x > 0) {
					x--;
					while (c < 26) {
						c++;
						Double d = r.nextDouble();
						if (d <= 0.0025) {
							RewardsCore.setReward((Player) p, TokensCore.tokenStack(100000));
							continue;
						}
						if (d <= 0.0025) {
							RewardsCore.setReward((Player) p, EchoCore.randomCharm());
							continue;
						}
						d = r.nextDouble();
						if (d <= 0.0025) {
							RewardsCore.setReward((Player) p, EchoCore.randomRelic());
							continue;
						}
						d = r.nextDouble();
						if (d <= 0.0025) {
							RewardsCore.setReward((Player) p, EchoCore.randomCharm());
							continue;
						}
						d = r.nextDouble();
						if (d <= 0.0025) {
							ItemStack i = new ItemStack(Material.NAME_TAG, 1);
							ItemMeta m = i.getItemMeta();
							List<String> l = new ArrayList<String>();
							m.setDisplayName(Format.color(
									"&8&m&l«&8&m-<&c&k&li&8&m[--|&c&k&li&b&l Rename Token &c&k&li&8&m|--]&c&k&li&8&m>-&8&l&m»"));
							m.addEnchant(Enchantment.LUCK, 0, true);
							m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							l.add(Format.color("&7"));
							l.add(Format.color("&8● &7Drag and drop onto the item you wish"));
							l.add(Format.color("&7 &7 &7 to rename, then type your item's"));
							l.add(Format.color("&7 &7 &7 new name in chat (Colors supported)"));
							m.setUnbreakable(true);
							m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
							m.setLore(l);
							i.setItemMeta(m);
							RewardsCore.setReward((Player) p, i);
							continue;
						}
						d = r.nextDouble();
						if (d <= 0.05) {
							RewardsCore.setReward((Player) p, TokensCore.tokenStack(10000));
							continue;
						}
						d = r.nextDouble();
						if (d <= 0.025) {
							RewardsCore.setReward((Player) p, TokensCore.tokenStack(25000));
							continue;
						}
						d = r.nextDouble();
						if (d <= 0.01) {
							RewardsCore.setReward((Player) p, TokensCore.tokenStack(50000));
							continue;
						}
					}
				}
			} else {
				Message.sender("Usage &b/Treasure <Player> <Amount>", sender);
				return true;
			}
		}
		return false;
	}
}
