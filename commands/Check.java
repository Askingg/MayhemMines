package me.askingg.mayhem.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class Check implements CommandExecutor, Listener {

	public static HashMap<String, Integer> individual = new HashMap<String, Integer>(); // Player : Mined
	public static HashMap<String, Integer> individual2 = new HashMap<String, Integer>(); // Player : Kills

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof ConsoleCommandSender || sender.hasPermission("mayhem.check")) {
			if (args.length == 1) {
				if (!(args[0].equalsIgnoreCase("mining") || args[0].equalsIgnoreCase("killing"))) {
					Message.sender("Check types: &bMining&7, &bKilling", sender);
					return false;
				}
				if (args[0].equalsIgnoreCase("mining")) {
					Message.sender("&7Starting a mining check on all online players", sender);
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
				}
				if (args[0].equalsIgnoreCase("killing")) {
					Message.sender("&7Starting a killing check on all online players", sender);
					for (Player pl : Bukkit.getOnlinePlayers()) {
						individual2.put(pl.getName(), 0);
					}
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
						public void run() {
							Message.sender("All results:", sender);
							for (String str : individual2.keySet()) {
								String a = Format.number((individual2.get(str)));
								if (individual2.get(str) <= 100) {
									Message.senderRaw("&8 ● &a" + str + " killed " + a + " entities", sender);
								}
								if (individual2.get(str) > 100 && individual2.get(str) <= 250) {
									Message.senderRaw("&8 ● &e" + str + " killed " + a + " entities", sender);
								}
								if (individual2.get(str) > 250 && individual2.get(str) <= 500) {
									Message.senderRaw("&8 ● &c" + str + " killed " + a + " entities", sender);
								}
								if (individual2.get(str) > 500) {
									Message.senderRaw("&8 ● &4" + str + " killed " + a + " entities", sender);
								}
							}
							individual2.clear();
						}
					}, 200L);
				}
			} else {
				Message.sender("Usage &b/Check <Mining/Killing>", sender);
			}
		} else {
			Message.sender("&7Sorry, but you don't have permission to do this", sender);
		}
		return false;
	}

	@EventHandler
	public void blockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		for (String key : Check.individual.keySet()) {
			if (key.equals(p.getName())) {
				Integer c = Check.individual.get(p.getName());
				c += 1;
				Check.individual.put(p.getName(), c);
			}
		}
	}

	@EventHandler
	public void entityDeath(EntityDeathEvent e) {
		if (e.getEntity().getKiller() instanceof Player) {
			Player p = (Player) e.getEntity().getKiller();
			for (String key : Check.individual2.keySet()) {
				if (key.equals(p.getName())) {
					Integer c = Check.individual2.get(p.getName());
					c += 1;
					Check.individual2.put(p.getName(), c);
				}
			}
		}
	}
}
