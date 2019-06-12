package me.askingg.mayhem.utils;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.main.Main;

public class Message {
	
	public static void console(String msg) {
		Bukkit.getConsoleSender().sendMessage(Format.color(Main.prefix + msg));
	}

	public static void broadcast(String msg) {
		Bukkit.broadcastMessage(Format.color(Main.prefix + msg));
	}

	public static void broadcastRaw(String msg) {
		Bukkit.broadcastMessage(Format.color(msg));
	}

	public static void player(String msg, Player player) {
		player.sendMessage(Format.color(Main.prefix + msg));
	}

	public static void playerRaw(String msg, Player player) {
		player.sendMessage(Format.color(msg));
	}

	public static void sender(String msg, CommandSender sender) {
		sender.sendMessage(Format.color(Main.prefix + msg));
	}

	public static void senderRaw(String msg, CommandSender sender) {
		sender.sendMessage(Format.color(msg));
	}

	public static void debug(String msg) {
		Bukkit.broadcastMessage(Format.color("&4&lDEBUG &8// &c" + msg));
	}

	public static void footer(Player p) {
		Random r = new Random();
		playerRaw("&8&m+-------------------------------------------+", p);
		if (r.nextInt(10) == 0) {
			playerRaw("&4 ● &bThis feature was developed by &c&lAskingg", p);
			playerRaw("&8&m+-------------------------------------------+", p);
		}
	}

	public static void footer(CommandSender sender) {
		Random r = new Random();
		senderRaw("&8&m+-------------------------------------------+", sender);
		if (r.nextInt(10) == 0) {
			if (r.nextInt(2) == 0) {
				senderRaw("&4 ● &bThis feature was developed by &c&lAskingg", sender);
			} else {
				senderRaw("&3 ● &cThis feature was developed by &b&lAskingg", sender);
			}
			senderRaw("&8&m+-------------------------------------------+", sender);
		}
	}
}
