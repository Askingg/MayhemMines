package me.askingg.mayhem.events;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TimeZone;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.askingg.mayhem.echo.EchoCore;
import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;
import me.askingg.mayhem.utils.Misc;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Message.playerRaw("&8&m+-------------&8( &c&lRecent&b&lUpdates&8 )&m------------+", p);
		for (String str : Files.config.getStringList("Updates")) {
			Message.playerRaw(str, p);
		}
		Message.playerRaw("&7", p);
		Message.playerRaw("&a &l●&7 Added&a &l● &8&l| &b &l●&7 Edited&b &l● &8&l|&c &l●&7 Removed&c &l●", p);
		Message.footer(p);
		Main.timeofjoin.put(p, System.currentTimeMillis());
		Main.onlinetimebroken.put(p, 0);
		if (!Main.broken.containsKey(p.getUniqueId().toString())) {
			Main.broken.put(p.getUniqueId().toString(), 0);
		}
		p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, true, false));
		if (Files.data.getString("Users." + p.getUniqueId() + ".Married") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".Married", "");
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".MarriedUUID") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".MarriedUUID", "");
		}

		Files.data.set("Users." + p.getUniqueId() + ".Username", p.getName());

		if (Files.data.getString("Users." + p.getUniqueId() + ".Scoreboard") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", Arrays.asList("Money", "Tokens", "RankUp",
					"RankupProgress", "GangName", "GangLevel", "BlocksMined", "ReactionWins", "EditScoreboard"));
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".FirstJoin") == null) {
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("GMT"));
			Files.data.set("Users." + p.getUniqueId() + ".FirstJoin", df.format(System.currentTimeMillis()));
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".Seperators") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".Seperators", Arrays.asList("12", "9", "6", "3"));
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".Prestige") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".Prestige", 0);
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankUp") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".RankUp", 0);
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankupColor") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".RankupColor", "&7");
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".Tokens") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".Tokens", 0);
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".Cryptos") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".Cryptos", 0.00000);
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".Flesh") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".Flesh", 0);
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".MinerTime") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".MinerTime", 0);
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".Kills") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".Kills", 0);
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".Gang") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".Gang", "");
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".ReactionWins") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".ReactionWins", 0);
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".ReactionTime") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".ReactionTime", 0.0);
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".Settings.Autosell") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".Settings.Autosell", true);
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".Settings.GangCreditMessages") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".Settings.GangCreditMessages", true);
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".Echo") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".Echo", Arrays.asList());
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".MiningRewards") == null) {
			Files.data.set("Users." + p.getUniqueId() + ".MiningRewards", Arrays.asList());
		}

		if (Files.data.getString("Users." + p.getUniqueId() + ".Married").equals(p.getName())) {
			Files.data.set("Users." + p.getUniqueId() + ".Married", "");
			Files.data.set("Users." + p.getUniqueId() + ".MarriedUUID", "");
		}

		if (Files.data.getConfigurationSection("Users." + p.getUniqueId() + ".MinerUpgrades") == null){
			Files.data.set("Users." + p.getUniqueId() + ".MinerUpgrades", "");
			Files.data.set("Users." + p.getUniqueId() + ".MinerUpgrades.MoreTokens", 0);
			Files.data.set("Users." + p.getUniqueId() + ".MinerUpgrades.MoreCredits", 0);

			Files.data.set("Users." + p.getUniqueId() + ".MinerUpgrades.RewardsInventory", 0);
			Files.data.set("Users." + p.getUniqueId() + ".MinerUpgrades.MinimumArtefact", 0);
			Files.data.set("Users." + p.getUniqueId() + ".MinerUpgrades.MinimumRelic", 0);
			Files.data.set("Users." + p.getUniqueId() + ".MinerUpgrades.DroprateArtefact", 0);
			Files.data.set("Users." + p.getUniqueId() + ".MinerUpgrades.DroprateRelic", 0);

			Files.data.set("Users." + p.getUniqueId() + ".MinerUpgrades.MoreMoney", 0);
			Files.data.set("Users." + p.getUniqueId() + ".MinerUpgrades.TimeReduce", 0);
			Files.data.set("Users." + p.getUniqueId() + ".MinerUpgrades.MoreBps", 0);

		}
		
		
		try {
			Files.data.save(Files.dataFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (!e.getPlayer().hasPlayedBefore()) {
			Message.player("&7You received an &cEcho Device", p);
			p.getInventory().addItem(EchoCore.device());
			p.updateInventory();
		}
		if (EchoCore.hasEquippedCharm(p)) {
			HashMap<String, Integer> m = EchoCore.getCharmBoosts(p);
			for (String str : m.keySet()) {
				if (str.equals("Haste")) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE,
							(m.get(str) - 1), true, false));
					continue;
				}
				if (str.equals("Speed")) {
					p.addPotionEffect(
							new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, (m.get(str) - 1), true, false));
					continue;
				}
				if (str.equals("JumpBoost")) {
					p.addPotionEffect(
							new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, (m.get(str) - 1), true, false));
					continue;
				}
				if (str.equals("Regeneration")) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE,
							(m.get(str) - 1), true, false));
					continue;
				}
				if (str.equals("Strength")) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE,
							(m.get(str) - 1), true, false));
					continue;
				}
				if (str.equals("Slowness")) {
					p.addPotionEffect(
							new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, (m.get(str) - 1), true, false));
					continue;
				}
				if (str.equals("MiningFatigue")) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE,
							(m.get(str) - 1), true, false));
					continue;
				}
			}
		}
		if (Main.violations.containsKey(p.getName())) {
			for (Player pl : Bukkit.getOnlinePlayers()) {
				if (pl.hasPermission("mayhem.violations.check")) {
					Message.player("&c" + p.getName() + "&7 previously had &c"
							+ Format.decimals(0, 0.0 + Main.violations.get(p.getName())) + "&7 violations", pl);
				}
			}
		}
		Main.violations.put(p.getName(), 0);
		Misc.setTablist(p);
	}
}
