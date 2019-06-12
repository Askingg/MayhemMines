package me.askingg.mayhem.autosell;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.multiplier.MultiplierCore;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class AutosellCommand implements org.bukkit.command.CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+------------&8( &c&lAutosell&b&lCommands&8 )&m-----------+", sender);
			Message.senderRaw("&8 ● &b/AutoSell &7View the help list", sender);
			Message.senderRaw("&8 ● &b/AutoSell Toggle &7Toggle your autosell", sender);
			Message.senderRaw("&8 ● &b/AutoSell Multi &7View your total multiplier", sender);
			Message.footer(sender);
		} else {
			if (args[0].equalsIgnoreCase("toggle")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					boolean b = true;
					if (Files.data.getBoolean("Users." + p.getUniqueId() + ".Settings.Autosell")) {
						b = false;
					}
					try {
						Files.data.set("Users." + p.getUniqueId() + ".Settings.Autosell", b);
						Files.data.save(Files.dataFile);
					} catch (Exception ex) {

					}
					if (b) {
						Message.player("&7You &aEnabled&7 AutoSell", p);
					} else {
						Message.player("&7You &cDisabled&7 AutoSell", p);
					}
				} else {
					Message.sender("&7Sorry, but you must be a player to use this command", sender);
				}
			}
			if (args[0].equalsIgnoreCase("multiplier") || args[0].equalsIgnoreCase("multi")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					Message.playerRaw("&8&m+------------&8( &c&lAutosell&b&lMultiplier&8 )&m-----------+", p);
					Message.playerRaw("&8 ● &7Total &c" + Format.decimals(3, AutosellCore.multi(p)) + " &8(&b"
							+ Format.decimals(1, (AutosellCore.multi(p) * 100)) + "%&8)", p);
					Message.playerRaw("", p);
					if (MultiplierCore.moneyMulti.containsKey(p)) {
						Message.playerRaw("&8 ● &7Personal &c" + Format.decimals(3, AutosellCore.personalMulti(p))
								+ " &8(&b" + Format.decimals(1, (AutosellCore.personalMulti(p) * 100)) + "%&8) &a"
								+ Format.time((int) (MultiplierCore.moneyTime.get(p) / 1000)), p);
					} else {
						Message.playerRaw("&8 ● &7Personal &c" + Format.decimals(3, AutosellCore.personalMulti(p))
								+ " &8(&b" + Format.decimals(1, (AutosellCore.personalMulti(p) * 100)) + "%&8) &a", p);
					
					}
					Message.playerRaw("&8 ● &7Permission &c" + Format.decimals(3, AutosellCore.permissionMulti(p))
					+ " &8(&b" + Format.decimals(1, (AutosellCore.permissionMulti(p) * 100)) + "%&8)", p);
					Message.playerRaw("&8 ● &7RankUp &c" + Format.decimals(3, AutosellCore.rankupMulti(p)) + " &8(&b"
							+ Format.decimals(1, (AutosellCore.rankupMulti(p) * 100)) + "%&8)", p);
					Message.playerRaw("&8 ● &7Relic &c" + AutosellCore.relicMulti(p) + " &8(&b"
							+ Format.decimals(1, ((AutosellCore.relicMulti(p) * 100))) + "%&8)", p);
					Message.playerRaw("&8 ● &7Gang &c" + AutosellCore.gangMulti(p) + " &8(&b"
							+ Format.decimals(1, ((AutosellCore.gangMulti(p) * 100))) + "%&8)", p);
					Message.playerRaw("&8 ● &7PickaxeBoost &c" + AutosellCore.pickaxeBoostMulti(p) + " &8(&b"
							+ Format.decimals(1, ((AutosellCore.pickaxeBoostMulti(p) * 100))) + "%&8)", p);
					Message.playerRaw("&8&m+-------------------------------------------+", p);
				}
			}
		}
		return false;
	}
}
