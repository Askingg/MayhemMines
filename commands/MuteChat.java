package me.askingg.mayhem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.askingg.mayhem.utils.Message;

public class MuteChat implements CommandExecutor, Listener {

	public static boolean muted = false;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.mutechat")) {
			if (!muted) {
				muted = true;
				Message.broadcast("&7The chat has been muted by &c" + sender.getName());
				return true;
			}
			if (muted) {
				muted = false;
				Message.broadcast("&7The chat has been unmuted by &b" + sender.getName());
				return true;
			}
		} else {
			Message.sender("&7Sorry, but you don't have permission to do this", sender);
			return true;
		}
		return false;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		if (muted && !e.getPlayer().hasPermission("mayhem.mutechat.bypass")) {
			e.setCancelled(true);
		}
	}
}
