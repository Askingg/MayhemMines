package me.askingg.mayhem.echo;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Message;

public class EchoCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+-------------&8( &c&lEcho&b&lCommands&8 )&m-------------+", sender);
			Message.senderRaw("&8 ● &b/Echo &7View the help list", sender);
			Message.senderRaw("&8 ● &b/Echo Open &7Open your echo device", sender);
			Message.senderRaw("&8 ● &b/Echo Device &7Give a user an &cecho device", sender);
			Message.senderRaw("&8 ● &b/Echo Random &7Give a user a random &brelic", sender);
			Message.footer(sender);
		} else {
			if (args[0].equalsIgnoreCase("open")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					EchoGUI.open.add(p);
					p.openInventory(EchoGUI.menu(p));
				}
			}
			if (args[0].equalsIgnoreCase("random")) { // Echo Random <Type> <Player>
				if (sender.hasPermission("mayhem.echo.random") || sender instanceof ConsoleCommandSender) {
					if (args.length == 3) {
						Player p = Bukkit.getPlayer(args[2]);
						if (p != null) {
							if (args[1].equalsIgnoreCase("relic") || args[1].equalsIgnoreCase("artefact") || args[1].equalsIgnoreCase("charm")) {
								if (args[1].equalsIgnoreCase("relic")) {
									Message.player("&7You received a &brelic", p);
									p.getInventory().addItem(EchoCore.randomRelic());
									p.updateInventory();
									return true;
								}
								if (args[1].equalsIgnoreCase("artefact")) {
									Message.player("&7You received an &cArtefact", p);
									p.getInventory().addItem(EchoCore.randomArtefact());
									p.updateInventory();
									return true;
								}
								if (args[1].equalsIgnoreCase("charm")) {
									Message.player("&7You received a &dcharm", p);
									p.getInventory().addItem(EchoCore.randomCharm());
									p.updateInventory();
									return true;
								}
							} else {
								Message.sender("&7Sorry, but &c" + args[1]
										+ "&7 is an invalid type. Valid types: &bRelic&7, &cArtefact&7, &dCharm", sender);
							}
						} else {
							Message.sender("&7Sorry, but &c" + args[2] + "&7 is an invalid player", sender);
						}
					} else {
						Message.sender("&7Usage &8/ &b/Echo Random <Type> <Player>", sender);
					}
				} else {
					Message.sender("&7Sorry, but you don't have permission to do that", sender);
				}
			}
			if (args[0].equalsIgnoreCase("device")) { // Echo Device <Player>
				if (args.length == 2) {
					Player p = Bukkit.getPlayer(args[1]);
					if (p != null) {
						Message.player("&7You received an &cEcho Device", p);
						p.getInventory().addItem(EchoCore.device());
						p.updateInventory();
					} else {
						Message.sender("&7Sorry, but &c" + args[1] + "&7 is an invalid player", sender);
					}
				} else {
					Message.sender("&7Usage &8/ &b/Echo Device <Player>", sender);
				}
			}
		}
		return false;
	}
}
