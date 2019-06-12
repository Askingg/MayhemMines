package me.askingg.mayhem.dropparty;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Message;

public class DropPartyCommand implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				DropPartyGUIMain.open.add(p);
				p.openInventory(DropPartyGUIMain.menu(p));
			}
		} else {
			if (args[0].equalsIgnoreCase("help")) {
				Message.senderRaw("&8&m+----------&8( &c&lDropParty&b&lCommands&8 )&m----------+", sender);
				Message.senderRaw("&8 ● &b/DropParty &7Open the upcoming drop parties menu", sender);
				Message.senderRaw("&8 ● &b/DropParty Help &7View the help list", sender);
				Message.footer(sender);
			}
		}
		return false;
	}
}
