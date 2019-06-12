package me.askingg.mayhem.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.askingg.mayhem.utils.Message;

public class StaffChat implements CommandExecutor, Listener {

	public static List<Player> staffchat = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.staffchat")) {
			if (args.length == 0) {
				Message.sender("Usage &b/SC <Toggle/<Message>>", sender);
			}
			if (args[0].equalsIgnoreCase("toggle")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (staffchat.contains(p)) {
						staffchat.remove(p);
						Message.playerRaw("&8&l[&9&lStaffchat&8&l]&7 You &cdisabled&7 staff chat", p);
						return true;
					} else {
						staffchat.add(p);
						Message.playerRaw("&8&l[&9&lStaffchat&8&l]&7 You &aenabled&7 staff chat", p);
						return true;
					}
				}
			} else {
				String m = "";
				for (int i = 0; i < args.length; i++) {
					m += args[i] + " ";
				}
				for (Player pl : Bukkit.getOnlinePlayers()) {
					if (pl.hasPermission("mayhem.staffchat")) {
						Message.playerRaw("&8&l[&9&lStaffchat&8&l]&3 " + sender.getName() + "&8 // &b" + m, pl);
					}
				}
			}
		} else {
			Message.sender("Sorry, but you don't have permission to do this", sender);
			return true;
		}
		return false;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (staffchat.contains(e.getPlayer())) {
			e.setCancelled(true);
			for (Player pl : Bukkit.getOnlinePlayers()) {
				if (pl.hasPermission("mayhem.staffchat")) {
					Message.playerRaw(
							"&8&l[&9&lStaffchat&8&l]&3 " + e.getPlayer().getName() + "&8 // &b" + e.getMessage(), pl);
				}
			}
		}
	}
}
