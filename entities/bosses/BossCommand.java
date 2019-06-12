package me.askingg.mayhem.entities.bosses;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Message;

public class BossCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+-------------&8( &c&lBoss&b&lCommands&8 )&m------------+", sender);
			Message.senderRaw("&8 ● &b/Boss &7View the help list", sender);
			Message.senderRaw("&8 ● &b/Boss List &7View all of the bosses", sender);
			Message.footer(sender);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("list")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					BossListGUI.open.add(p);
					p.openInventory(BossListGUI.menu(p));
				}
			}
		}
		return false;
	}
}
