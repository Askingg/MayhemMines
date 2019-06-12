package me.askingg.mayhem.exp;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class EXPCommand implements CommandExecutor, Listener {

	public static HashMap<String, Integer> individual = new HashMap<String, Integer>(); // Player : Mined

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (p.hasPermission("mayhem.check")) {
			if (args.length == 0) {
				Message.sender("&7Starting a check on all online players", sender);
				for (Player pl : Bukkit.getOnlinePlayers()) {
					individual.put(pl.getName(), 0);
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
					public void run() {
						Message.sender("All results:", sender);
						for (String str : individual.keySet()) {
							String a = Format.number((individual.get(str)));
							if (individual.get(str) <= 100) {
								Message.senderRaw("&8 ● &a" + str + " mined " + a + " blocks", sender);
							}
							if (individual.get(str) > 100 && individual.get(str) <= 250) {
								Message.senderRaw("&8 ● &e" + str + " mined " + a + " blocks", sender);
							}
							if (individual.get(str) > 250 && individual.get(str) <= 500) {
								Message.senderRaw("&8 ● &c" + str + " mined " + a + " blocks", sender);
							}
							if (individual.get(str) > 500) {
								Message.senderRaw("&8 ● &4" + str + " mined " + a + " blocks", sender);
							}
						}
						individual.clear();
					}
				}, 200L);
			} else {
				Player t = Bukkit.getPlayer(args[0]);
				if (t != null) {
					Message.sender("&7Starting a check on &b" + t.getName(), sender);
					individual.put(t.getName(), 0);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
						public void run() {
							String a = Format.number((individual.get(t.getName())));
							if (individual.get(t.getName()) <= 100) {
								Message.sender("&a" + t.getName() + " mined " + a + " blocks", sender);
							}
							if (individual.get(t.getName()) > 100 && individual.get(t.getName()) <= 250) {
								Message.sender("&e" + t.getName() + " mined " + a + " blocks", sender);
							}
							if (individual.get(t.getName()) > 250 && individual.get(t.getName()) <= 500) {
								Message.sender("&c" + t.getName() + " mined " + a + " blocks", sender);
							}
							if (individual.get(t.getName()) > 500) {
								Message.sender("&4" + t.getName() + " mined " + a + " blocks", sender);
							}
							individual.remove(t.getName());
						}
					}, 200L);
				} else {
					Message.sender("&7Sorry, but &c" + args[0] + "&7 is an invalid player", sender);
				}
			}
		} else {
			Message.sender("&7Sorry, but you don't have permission to do this", sender);
		}
		return false;
	}

	@EventHandler
	public void blockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		for (String key : EXPCommand.individual.keySet()) {
			if (key.equals(p.getName())) {
				Integer c = EXPCommand.individual.get(p.getName());
				c += 1;
				EXPCommand.individual.put(p.getName(), c);
			}
		}
	}
}
