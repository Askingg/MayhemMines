package me.askingg.mayhem.autominer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class MinerCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+-------------&8( &c&lMiner&b&lCommands&8 )&m-------------+", sender);
			Message.senderRaw("&8 ● &b/Miner &7View the help list", sender);
			Message.senderRaw("&8 ● &b/Miner Time &7View how much automining time you have available", sender);
			Message.senderRaw("&8 ● &b/Miner Join &7Join an autominer", sender);
			Message.senderRaw("&8 ● &b/Miner Leave &7Leave the autominer", sender);
			Message.senderRaw("&8 ● &b/Miner List &7View the list of autominers", sender);
			Message.senderRaw("&8 ● &b/Miner Give &7Give a time chip to a player", sender);
			Message.footer(sender);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("dev")) {
				if( sender.hasPermission("Miner.Dev")) {
					Message.senderRaw("mining:", sender);
					for (Player p : MinerCore.mining) {
						Message.senderRaw("- " + p.getName(), sender);
					}
					Message.senderRaw("miners:", sender);
					for (String str : MinerCore.miners.keySet()) {
						if (MinerCore.miners.get(str) == null) {
							Message.senderRaw(str + " null", sender);
							continue;
						}
						Message.senderRaw(str + " " + MinerCore.miners.get(str), sender);
					}
					Message.senderRaw("dminers:", sender);
					for (String str : MinerCore.dminers.keySet()) {
						if (MinerCore.dminers.get(str) == null) {
							Message.senderRaw(str + " null", sender);
							continue;
						}
						Message.senderRaw(str + " " + MinerCore.dminers.get(str), sender);
					}
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("join")) { // Miner Join
				if (sender instanceof Player) {
					MinerCore.joinMiner((Player) sender);
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("time")) { // Miner Time <Player>
				if (args.length == 1) {
					if (sender instanceof Player) {
						Player p = (Player) sender;
						Message.sender("You currently have &b"
								+ Format.time(
										Files.data.getInt("Users." + p.getUniqueId().toString() + ".MinerTime") / 1000)
								+ "&7 available automining time", sender);
						return true;
					}
				}
				if (args.length == 2) {
					Player p = Bukkit.getPlayer(args[1]);
					if (p == null) {
						Message.sender("Sorry, but &c" + args[1] + "&7 is an invalid player", sender);
						return true;
					}
					Message.sender("&b" + p.getName() + "&7 currently has &b"
							+ Format.time(
									Files.data.getInt("Users." + p.getUniqueId().toString() + ".MinerTime") / 1000)
							+ "&7 available automining time", sender);
					return true;
				}
				Message.sender("Usage &b/Miner Time <Player>", sender);
				return true;
			}
			if (args[0].equalsIgnoreCase("leave")) { // Miner Leave
				if (sender instanceof Player) {
					MinerCore.leaveMiner((Player) sender);
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("list")) { // Miner List
				if (sender instanceof Player) {
					Player p = (Player) sender;
					MinerListGUI.open.add(p);
					p.openInventory(MinerListGUI.menu(p));
					return true;
				}
			}
			if (args[0].equalsIgnoreCase("upgrade")){ // Miner Upgrade
				if (sender.hasPermission("Miner.Upgrade")) {
					if (sender instanceof Player) {
						Player p = (Player) sender;
						MinerUpgradeGUI.open.add(p);
						p.openInventory(MinerUpgradeGUI.menu(p));
						return true;
					}
				}
			}
			if (args[0].equalsIgnoreCase("give")) { // Miner Give <Player> <Seconds>
				if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.miner.give")) {
					if (args.length == 3) {
						Player p = Bukkit.getPlayer(args[1]);
						if (p == null) {
							Message.sender("Sorry, but &c" + args[1] + "&7 is an invalid player", sender);
							return true;
						}
						int t = 0;
						try {
							t = Integer.parseInt(args[2]);
						} catch (Exception ex) {
							Message.sender("Sorry, but &c" + args[2] + "&7 is an invalid integer", sender);
							return true;
						}
						if (t > 0) {
							p.getInventory().addItem(MinerCore.timeItem(t * 1000));
							p.updateInventory();
							Message.player("You received an autominer chip: &b" + Format.time(t), p);
							return true;
						} else {
							Message.sender("Sorry, but the time must be greater than 0", sender);
							return true;
						}
					} else {
						Message.sender("Usage &b/Miner Give <Player> <Time(Seconds)>", sender);
						return true;
					}
				}
			}
		}
		return false;
	}

}
