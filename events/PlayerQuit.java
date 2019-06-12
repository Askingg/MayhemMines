package me.askingg.mayhem.events;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.askingg.mayhem.autominer.MinerCore;
import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.miningrewards.RewardsCore;
import me.askingg.mayhem.multiplier.MultiplierCore;
import me.askingg.mayhem.utils.Message;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if (MinerCore.mining.contains(p)) {
			MinerCore.leaveMiner(p);
		}
		if (MultiplierCore.moneyTime.containsKey(p)) {
			RewardsCore.setReward(p, MultiplierCore.multiItem("Money", MultiplierCore.moneyMulti.get(p),
					(int) (MultiplierCore.moneyTime.get(p) / 1000)));
			MultiplierCore.moneyMulti.remove(p);
			MultiplierCore.moneyTime.remove(p);
		}
		if (MultiplierCore.tokenTime.containsKey(p)) {
			RewardsCore.setReward(p, MultiplierCore.multiItem("Token", MultiplierCore.tokenMulti.get(p),
					(int) (MultiplierCore.tokenTime.get(p) / 1000)));
			MultiplierCore.tokenMulti.remove(p);
			MultiplierCore.tokenTime.remove(p);
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yy|HH;mm;ss");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			Main.timeofjoin.remove(p);
			Main.onlinetimebroken.remove(p);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@EventHandler
	public void onKick(PlayerKickEvent e) {
		Player p = e.getPlayer();
		Message.console("&7Player &c" + p.getName() + "&7 was kicked form the server for: '&c"
				+ e.getReason() + "&7'");
		if (e.getReason().contains("packets")) {
			for (Player pl : Bukkit.getOnlinePlayers()) {
				if (pl.hasPermission("mayhem.kickwarning")) {
					Message.playerRaw("&8&l[&4&lKickWarning&8&l]&c " + p.getName() + "&7 was just kicked for sending too many packets &8(&7Possible Nuker&8)", pl);
				}
			}
		}
	}
}