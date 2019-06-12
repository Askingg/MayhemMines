package me.askingg.mayhem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.askingg.mayhem.utils.Message;

public class Vote implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Message.sender("&7Voting websites: ", sender);
		Message.senderRaw("&8 ● &7&l1. &bhttps://minecraft-server-list.com/server/437980/vote/", sender);
		Message.senderRaw("&8 ● &7&l2. &bhttps://minecraft-mp.com/server/218405/vote/", sender);
		Message.senderRaw("&8 ● &7&l3. &bhttps://minecraft-server.net/vote/Askingg/", sender);
		Message.senderRaw("&8 ● &7&l4. &bhttps://www.trackyserver.com/server/237906", sender);
		Message.senderRaw("&8 ● &7&l5. &bhttps://minecraftservers.biz/servers/143829/#vote_now", sender);
		Message.senderRaw("&7Voting greatly helps the server, and also gives you some nice rewards.", sender);
		return true;
	}
}
