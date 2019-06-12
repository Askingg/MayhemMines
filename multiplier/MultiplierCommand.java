package me.askingg.mayhem.multiplier;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Message;

public class MultiplierCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+-----------&8( &c&lMultiplier&b&lCommands&8 )&m-----------+", sender);
			Message.senderRaw("&8 ● &b/Multi &7View the help list", sender);
			Message.senderRaw("&8 ● &b/Multi Check &7View your current multipliers", sender);
			Message.senderRaw("&8 ● &b/Multi end &7View your current multipliers", sender);
			Message.senderRaw("&8 ● &b/Multi Give &7Give a player a multiplier", sender);
			Message.footer(sender);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("check")) {

			}
			if (args[0].equalsIgnoreCase("end")) { // Multi end <money/token>
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (args.length == 2) {
						if (!(args[1].equalsIgnoreCase("money") || args[1].equalsIgnoreCase("token"))) {
							Message.sender("Usage &b/Multi End <Money/Token>", sender);
							return true;
						}
						if (args[1].equalsIgnoreCase("money")) {
							if (MultiplierCore.moneyMulti.containsKey(p)) {
								if (p.getInventory().firstEmpty() == -1) {
									Message.sender("Sorry, but your inventory is full", sender);
									return true;
								}
								Message.player("You ended your &b" + MultiplierCore.moneyMulti.get(p) + "&7 money multiplier", p);
								p.getInventory()
										.addItem(MultiplierCore.multiItem("Money", MultiplierCore.moneyMulti.get(p),
												(int) (MultiplierCore.moneyTime.get(p) / 1000)));
								p.updateInventory();
								MultiplierCore.moneyMulti.remove(p);
								MultiplierCore.moneyTime.remove(p);
								return true;
							} else {
								Message.player("You don't have an active money multiplier", p);
								return true;
							}
						}
						if (args[1].equalsIgnoreCase("token")) {
							if (MultiplierCore.tokenMulti.containsKey(p)) {
								if (p.getInventory().firstEmpty() == -1) {
									Message.sender("Sorry, but your inventory is full", sender);
									return true;
								}
								Message.player("You ended your &b" + MultiplierCore.tokenMulti.get(p) + "&7 token multiplier", p);
								p.getInventory()
										.addItem(MultiplierCore.multiItem("Token", MultiplierCore.tokenMulti.get(p),
												(int) (MultiplierCore.tokenTime.get(p) / 1000)));
								p.updateInventory();
								MultiplierCore.tokenMulti.remove(p);
								MultiplierCore.tokenTime.remove(p);
								return true;
							} else {
								Message.player("You don't have an active token multiplier", p);
								return true;
							}
						}
					} else {
						Message.sender("Usage &b/Multi End <Money/Token>", sender);
						return true;
					}
				}
			}
			if (args[0].equalsIgnoreCase("give")) { // Multi give <player> <multi> <time(seconds)> <Type>
				if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.flesh.give")) {
					if (args.length == 5) {
						Player p = Bukkit.getPlayer(args[1]);
						if (p == null) {
							Message.sender("Sorry, but &c" + args[1] + "&7 is an invalid player", sender);
							return true;
						}
						double multi = 0.0;
						try {
							multi = Double.parseDouble(args[2]);
						} catch (Exception ex) {
							Message.sender("Sorry, buc &c" + args[2] + "&7 is an invalid double", sender);
							return true;
						}
						int time = 0;
						try {
							time = Integer.parseInt(args[3]);
						} catch (Exception ex) {
							Message.sender("Sorry, buc &c" + args[3] + "&7 is an invalid integer", sender);
							return true;
						}
						if (p.getInventory().firstEmpty() == -1) {
							Message.sender("Sorry, but &c" + args[1] + "'s&7 inventory is full", sender);
							return true;
						}
						if (!(args[4].equalsIgnoreCase("token") || args[4].equalsIgnoreCase("money"))) {
							Message.sender("Sorry, but &c" + args[4] + "&7Is an invalid multiplier type: &bToken/Money",
									sender);
							return true;
						}
						p.getInventory().addItem(MultiplierCore.multiItem(args[4], multi, time));
						p.updateInventory();
						return true;
					} else {
						Message.sender("Usage &b/Multi Give <Player> <Multiplier> <Time(Seconds)> <Money/Token>",
								sender);
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
