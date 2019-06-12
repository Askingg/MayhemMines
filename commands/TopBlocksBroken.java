package me.askingg.mayhem.commands;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Compare;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class TopBlocksBroken implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		HashMap<String, Double> bals = new HashMap<String, Double>();
		Compare bvc = new Compare(bals);
		TreeMap<String, Double> top = new TreeMap<String, Double>(bvc);
		for (String str : Main.broken.keySet()) {
			bals.put(str, Main.broken.get(str) + 0.0);
		}
		top.putAll(bals);
		int x = 0;
		Message.senderRaw("&8&m+-------&8( &c&lBlocksBroken&b&lLeaderboard&8 )&m-------+", sender);
		for (String str : top.keySet()) {
			x++;
			if (x < 11) {
				Message.senderRaw("&8 ● &7&l" + x + ".&7 &c" + Bukkit.getOfflinePlayer(UUID.fromString(str)).getName()
						+ " &b" + Format.number(Main.broken.get(str)), sender);
			}
		}
		Message.footer(sender);
		return false;
	}
}
