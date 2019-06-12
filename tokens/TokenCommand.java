package me.askingg.mayhem.tokens;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.multiplier.MultiplierCore;
import me.askingg.mayhem.utils.Compare;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class TokenCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+------------&8( &c&lToken&b&lCommands&8 )&m------------+", sender);
			Message.senderRaw("&8 ● &b/Tokens &7View the help list", sender);
			Message.senderRaw("&8 ● &b/Tokens Balance &7View how many tokens someone has", sender);
			Message.senderRaw("&8 ● &b/Tokens Withdraw &7Withdraw tokens into a token stack", sender);
			Message.senderRaw("&8 ● &b/Tokens Baltop &7View the top 10 token balances", sender);
			Message.senderRaw("&8 ● &b/Tokens Give &7Give a user tokens", sender);
			Message.senderRaw("&8 ● &b/Tokens Remove &7Remove tokens from a user", sender);
			Message.senderRaw("&8 ● &b/Tokens Multi &7View your token multiplier", sender);
			Message.footer(sender);
		} else {
			if (args[0].equalsIgnoreCase("test")) {
				Message.debug("Allocated - &b" + (Runtime.getRuntime().totalMemory() / 1048576L));
				Message.debug("Using - &b" + ((Runtime.getRuntime().totalMemory() / 1048576L)
						- (Runtime.getRuntime().freeMemory() / 1048576L)));
				Message.debug("Free - &b" + (Runtime.getRuntime().freeMemory() / 1048576L));
				Message.debug("Max - &b" + (Runtime.getRuntime().maxMemory() / 1048576L));
			}
			if (args[0].equalsIgnoreCase("balance") || args[0].equalsIgnoreCase("bal")) { // Token Balance <Player>
				if (args.length == 1 || args.length == 2) {
					if (args.length == 1) {
						if (sender instanceof Player) {
							Player p = (Player) sender;
							Message.sender("&7You have &b" + Format.decimals(0, TokensCore.balance(p)) + " tokens",
									sender);
							return true;
						} else {
							Message.sender("&7Sorry, but only players can use this command", sender);
							return false;
						}
					} else {
						Player p = Bukkit.getPlayer(args[1]);
						if (p != null) {
							Message.sender("&b" + args[1] + "&7 has &b" + Format.decimals(0, TokensCore.balance(p))
									+ " tokens", sender);
							return true;
						} else {
							Message.sender("&7Sorry, but &c" + args[1] + "&7 is an inalid target", sender);
							return false;
						}
					}
				} else {
					Message.sender("&7Usage &8/ &b/Token Balance <Player>", sender);
					return false;
				}
			}
			if (args[0].equalsIgnoreCase("withdraw")) { // Token Withdraw <Amount>
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (args.length == 2) {
						try {
							Integer.parseInt(args[1]);
						} catch (Exception ex) {
							Message.player("Sorry, but &c" + args[1] + "&7 is an invalid integer", p);
						}
						if (Integer.parseInt(args[1]) > 0) {
							if (TokensCore.hasTokens(p, Double.valueOf(args[1]))) {
								p.getInventory().addItem(TokensCore.tokenStack(Integer.valueOf(args[1])));
								p.updateInventory();
								Message.player("You withdrew a &b" + Format.number(Double.valueOf(args[1]))
										+ " tokens &7 token stack", p);
								TokensCore.remove(p, Double.valueOf(args[1]));
							} else {
								Message.player("Sorry, but you don't have &c" + Format.number(Double.valueOf(args[1]))
										+ "&7 tokens", p);
							}

						} else {
							Message.sender("Sorry, but you must withdraw atleast 1 token", sender);
							return true;
						}
					} else {
						Message.sender("Usage &b/Token Withdraw <Amount>", sender);
					}
				}
			}
			if (args[0].equalsIgnoreCase("balancetop") || args[0].equalsIgnoreCase("baltop")) { // Token baltop
				HashMap<String, Double> bals = new HashMap<String, Double>();
				Compare bvc = new Compare(bals);
				TreeMap<String, Double> top = new TreeMap<String, Double>(bvc);
				for (String str : Files.data.getConfigurationSection("Users").getKeys(false)) {
					bals.put(str, Files.data.getDouble("Users." + str + ".Tokens"));
				}
				top.putAll(bals);
				int x = 0;
				Message.senderRaw("&8&m+-------&8( &c&lTokenBalance&b&lLeaderboard&8 )&m-------+", sender);
				for (String str : top.keySet()) {
					x++;
					if (x < 11) {
						Message.senderRaw(
								"&8 ● &7&l" + x + ".&7 &c" + Bukkit.getOfflinePlayer(UUID.fromString(str)).getName()
										+ " &b" + Format.number(Files.data.getDouble("Users." + str + ".Tokens")),
								sender);
					}
				}
				Message.footer(sender);
				return true;
			}
			if (args[0].equalsIgnoreCase("give")) { // Tokens Give <Player> <Amount>
				if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.tokens.give")) {
					if (args.length == 3) {
						Player p = Bukkit.getPlayer(args[1]);
						if (p != null) {
							Double d;
							try {
								d = Double.parseDouble(args[2]);
							} catch (Exception ex) {
								Message.sender("&7Sorry, but &c" + args[2] + "&7 is an invalid number", sender);
								return false;
							}
							try {
								TokensCore.addNoMulti(p, d);
								Message.sender(
										"&7You gave &b" + Format.decimals(0, d) + " tokens&7 to &b" + p.getName(),
										sender);
								return true;
							} catch (Exception ex) {
								ex.printStackTrace();
								return false;
							}
						} else {
							Message.sender("&7Sorry, but &c" + args[1] + "&7 is an inalid target", sender);
							return false;
						}
					} else {
						Message.sender("&7Usage &8/ &b/Tokens Give <Player> <Amount>", sender);
						return false;
					}
				}
			}
			if (args[0].equalsIgnoreCase("remove")) { // Tokens Remove <Player> <Amount>
				if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.tokens.remove")) {
					if (args.length == 3) {
						Player p = Bukkit.getPlayer(args[1]);
						if (p != null) {
							Double d;
							try {
								d = Double.parseDouble(args[2]);
							} catch (Exception ex) {
								Message.sender("&7Sorry, but &c" + args[2] + "&7 is an invalid number", sender);
								return false;
							}
							if (TokensCore.hasTokens(p, d)) {
								try {
									TokensCore.remove(p, d);
									Message.sender(
											"&7You took &b" + Format.decimals(0, d) + " tokens&7 from &b" + p.getName(),
											sender);
									return true;
								} catch (Exception ex) {
									ex.printStackTrace();
									return false;
								}
							} else {
								Message.sender("&7Sorry, but &c" + p.getName() + "&7 Doesn't have enough tokens",
										sender);
								return false;
							}
						} else {
							Message.sender("&7Sorry, but &c" + args[1] + "&7 is an inalid target", sender);
							return false;
						}
					} else {
						Message.sender("&7Usage &8/ &b/Tokens Remove <Player> <Amount>", sender);
						return false;
					}
				}
			}
			if (args[0].equalsIgnoreCase("multiplier") || args[0].equalsIgnoreCase("multi")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					Message.playerRaw("&8&m+-------------&8( &c&lToken&b&lMultiplier&8 )&m------------+", p);
					Message.playerRaw("&8 ● &7Total &c" + Format.decimals(3, TokensCore.multi(p)) + " &8(&b"
							+ Format.decimals(1, (TokensCore.multi(p) * 100)) + "%&8)", p);
					Message.playerRaw("", p);
					if (MultiplierCore.tokenMulti.containsKey(p)) {
						Message.playerRaw("&8 ● &7Personal &c" + Format.decimals(3, TokensCore.personalMulti(p))
								+ " &8(&b" + Format.decimals(1, (TokensCore.personalMulti(p) * 100)) + "%&8) &a"
								+ Format.time((int) (MultiplierCore.tokenTime.get(p) / 1000)), p);
					} else {
						Message.playerRaw("&8 ● &7Personal &c" + Format.decimals(3, TokensCore.personalMulti(p))
								+ " &8(&b" + Format.decimals(1, (TokensCore.personalMulti(p) * 100)) + "%&8) &a", p);
					
					}
					Message.playerRaw("&8 ● &7Permission &c" + TokensCore.permissionMulti(p) + " &8(&b"
							+ Format.decimals(1, ((TokensCore.permissionMulti(p) * 100))) + "%&8)", p);
					Message.playerRaw("&8 ● &7Gang &c" + TokensCore.gangMulti(p) + " &8(&b"
							+ Format.decimals(1, ((TokensCore.gangMulti(p) * 100))) + "%&8)", p);
					Message.playerRaw("&8 ● &7Artefact &c" + TokensCore.artefactMulti(p) + " &8(&b"
							+ Format.decimals(1, ((TokensCore.artefactMulti(p) * 100))) + "%&8)", p);
					Message.playerRaw("&8 ● &7PickaxeBoost &c" + TokensCore.pickaxeBoostMulti(p) + " &8(&b"
							+ Format.decimals(1, ((TokensCore.pickaxeBoostMulti(p) * 100))) + "%&8)", p);
					Message.playerRaw("&8&m+-------------------------------------------+", p);
				}
			}
		}
		return false;
	}
}
