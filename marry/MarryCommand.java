package me.askingg.mayhem.marry;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Message;

public class MarryCommand implements org.bukkit.command.CommandExecutor {

	public static HashMap<Player, Player> request = new HashMap<Player, Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+------------&8( &c&lMarriage&b&lCommands&8 )&m-----------+", sender);
			Message.senderRaw("&8 ● &b/Marry &7View the help list", sender);
			Message.senderRaw("&8 ● &b/Marry <Name> &7Marry another player", sender);
			Message.senderRaw("&8 ● &b/Marry Accept &7Accept a marriage request", sender);
			Message.senderRaw("&8 ● &b/Marry Divorce &7Split from your partner", sender);
			Message.senderRaw("&8 ● &b/Marry List &7View all marriages", sender);
			Message.footer(sender);
		} else {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args[0].equalsIgnoreCase("Accept") || args[0].equalsIgnoreCase("Divorce")
						|| args[0].equalsIgnoreCase("list")) {
					if (args[0].equalsIgnoreCase("list")) {
						Message.player("Coming Soon..", p);
						return true;
					}
					if (args[0].equalsIgnoreCase("accept")) {
						if (request.containsValue(p)) {
							if (args.length == 2) {
								Player pl = Bukkit.getPlayer(args[1]);
								if (pl == null) {
									Message.player("Sorry, but &c" + args[1] + "&7 is an invalid player", p);
									return true;
								}
								if (MarryCore.isMarried(p)) {
									Message.player("Sorry, but you must be loyal to &c" + MarryCore.getPartner(p), p);
									return true;
								}
								if (MarryCore.isMarried(pl)) {
									Message.player("Sorry, but &c" + args[0] + " must be loyal to &c" + MarryCore.getPartner(pl),
											p);
									return true;
								}
								if (p.equals(pl)) {
									Message.player("Sorry, but you cannot marry yourself", p);
									return true;
								}
								if (request.containsKey(pl) && request.get(pl).equals(p)) {
									Message.player("You accepted the marriage request from &b" + args[1], p);
									Message.player("&b" + p.getName() + "&7 accepted your marriage request", pl);
									Message.broadcastRaw("&8&l[&c&lMarriage&8&l] &b" + pl.getName() + "&7 and &b" + p.getName() + "&7 are now married");
									Files.data.set("Users." + p.getUniqueId() + ".Married", pl.getName());
									Files.data.set("Users." + pl.getUniqueId() + ".Married", p.getName());
									Files.data.set("Users." + p.getUniqueId() + ".MarriedUUID", pl.getUniqueId().toString());
									Files.data.set("Users." + pl.getUniqueId() + ".MarriedUUID", p.getUniqueId().toString());
									try {
										request.remove(pl);
										Files.data.save(Files.dataFile);
										return true;
									} catch (Exception ex) {
										ex.printStackTrace();
										return false;
									}
								} else {
									Message.player("Sorry, but you don't have any marriage requests from &c" + args[1],
											p);
									return true;
								}
							} else {
								Message.player("Usage &b/Marry Accept <Player>", p);
								return true;
							}
						} else {
							Message.player("Sorry, but you don't have any incoming marriage requests", p);
							return true;
						}
					}
					if (args[0].equalsIgnoreCase("Divorce")) {
						if (MarryCore.isMarried(p)) {
							Message.broadcastRaw("&8&l[&c&lMarriage&8&l] &c" + p.getName() + "&7 just divorced &c" + MarryCore.getPartner(p));
							Files.data.set("Users." + UUID.fromString(MarryCore.getPartnerUUID(p)) + ".Married", "");
							Files.data.set("Users." + UUID.fromString(MarryCore.getPartnerUUID(p)) + ".MarriedUUID", "");
							Files.data.set("Users." + p.getUniqueId() + ".Married", "");
							Files.data.set("Users." + p.getUniqueId() + ".MarriedUUID", "");
							try {
								Files.data.save(Files.dataFile);
								return true;
							} catch (Exception ex) {
								ex.printStackTrace();
								return false;
							}
						} else {
							Message.player("Sorry, but you're not married", p);
							return true;
						}
					}
				} else {
					Player pl = Bukkit.getPlayer(args[0]);
					if (pl == null) {
						Message.player("Sorry, but &c" + args[0] + "&7 is an invalid player", p);
						return true;
					}
					if (MarryCore.isMarried(p)) {
						Message.player("Sorry, but you must be loyal to &c" + MarryCore.getPartner(p), p);
						return true;
					}
					if (MarryCore.isMarried(pl)) {
						Message.player("Sorry, but &c" + args[0] + " must be loyal to &c" + MarryCore.getPartner(pl),
								p);
						return true;
					}
					request.put(p, pl);
					Message.player("You sent a marriage request to &b" + pl.getName(), p);
					Message.player("&b" + p.getName() + "&7 is requesting to marry you: &b/Marry Accept " + p.getName(),
							pl);
					return true;
				}
			}
		}
		return false;
	}
}
