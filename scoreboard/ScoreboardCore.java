package me.askingg.mayhem.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import me.askingg.mayhem.blockspersecond.BPS;
import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.zombies.ZombiesCore;
import me.clip.placeholderapi.PlaceholderAPI;

public class ScoreboardCore implements Listener {

	public static int cnt = 1;
	static Main pl;

	public ScoreboardCore(Main instance) {
		pl = instance;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.setScoreboard(scoreboard(p));
	}

	@SuppressWarnings("deprecation")
	public static Scoreboard scoreboard(Player p) {
		Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective o = sb.registerNewObjective("sb", "");
		Team t = sb.registerNewTeam("team");
		if (p.getWorld().getName().equals("zombies")) {
			o.setDisplayName(Format.color("&c&lMayhem&b&lMines&4&lZombies"));
			o.setDisplaySlot(DisplaySlot.SIDEBAR);
			Score top = o.getScore(Format.color("&7&m+----------------------------+&0"));
			top.setScore(15);
			Integer i = 14;
			if (!ZombiesCore.gameRunning) {
				t.addEntry(Format.color("&7 &7 Kills &c&lComing Soon.."));
				Score s = o.getScore(Format.color("&7 &7 Kills &c&lComing Soon.."));
				s.setScore(i);
				i--;
				t.addEntry(Format.color("&7 &7 HighestWave &c&lComing Soon.."));
				s = o.getScore(Format.color("&7 &7 HighestWave &c&lComing Soon.."));
				s.setScore(i);
				i--;
				t.addEntry(Format.color("&7 &7 GamesPlayed &c&lComing Soon.."));
				s = o.getScore(Format.color("&7 &7 GamesPlayed &c&lComing Soon.."));
				s.setScore(i);
				i--;
			} else {
				t.addEntry(Format.color("&7 &7 Wave &c&lComing Soon.."));
				Score s = o.getScore(Format.color("&7 &7 Wave &c&l" + ZombiesCore.wave));
				s.setScore(i);
				i--;
				t.addEntry(Format.color("&7 &7 Alive &c&lComing Soon.."));
				s = o.getScore(Format.color("&7 &7 Alive &c&l" + ZombiesCore.remaining));
				s.setScore(i);
				i--;
				t.addEntry(Format.color("&1 &2 "));
				s = o.getScore(Format.color("&1 &2 "));
				s.setScore(i);
				i--;
				if (cnt <= 4) {
					t.addEntry(Format.color("&c Points:"));
					s = o.getScore(Format.color("&c Points:"));
					s.setScore(i);
					i--;
					for (String str : ZombiesCore.points.keySet()) {
						t.addEntry(Format.color("&7 &7 " + str + " &c&l" + ZombiesCore.points.get(str)));
						s = o.getScore(Format.color("&7 &7 " + str + " &c&l" + ZombiesCore.points.get(str)));
						s.setScore(i);
						i--;
					}
					cnt++;
					;
				} else {
					t.addEntry(Format.color("&c Kills:"));
					s = o.getScore(Format.color("&c Kills:"));
					s.setScore(i);
					i--;
					for (String str : ZombiesCore.kills.keySet()) {
						t.addEntry(Format.color("&7 &7 " + str + " &c&l" + ZombiesCore.kills.get(str)));
						s = o.getScore(Format.color("&7 &7 " + str + " &c&l" + ZombiesCore.kills.get(str)));
						s.setScore(i);
						i--;
					}
					if (cnt == 8) {
						cnt = 1;
					} else {
						cnt++;
					}
				}
			}
			Score bottom = o.getScore(Format.color("&7&m+----------------------------+&1"));
			bottom.setScore(i);
			return sb;
		}
		if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").size() <= 0) {
			return sb;
		}
		o.setDisplayName(Format.color("&c&lMayhem&b&lMines&3&lBeta"));
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		Score top = o.getScore(Format.color("&7&m+----------------------------+&0"));
		top.setScore(15);
		Integer i = 14;
		Integer i2 = 0;
		for (String str : Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard")) {
			if (i == 1) {
				break;
			} else {
				if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains(i.toString())) {
					t.addEntry(Format.color("&7 &m----------------------------&" + i2));
					Score s = o.getScore(Format.color("&7 &m----------------------------&" + i2));
					s.setScore(i);
					i2++;
					i--;
				}
				if (str.equalsIgnoreCase("PlayerName")) {
					t.addEntry(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Player &b&l" + p.getName())));
					Score s = o.getScore(
							Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Player &b&l" + p.getName())));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("Online")) {
					t.addEntry(Format.color(
							PlaceholderAPI.setPlaceholders(p, "&7 &7 Online &b&l" + Bukkit.getOnlinePlayers().size())));
					Score s = o.getScore(Format.color(
							PlaceholderAPI.setPlaceholders(p, "&7 &7 Online &b&l" + Bukkit.getOnlinePlayers().size())));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("Money")) {
					t.addEntry(Format
							.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Money &b&l%vault_eco_balance_formatted%")));
					Score s = o.getScore(Format
							.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Money &b&l%vault_eco_balance_formatted%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("MoneyMulti")) {
					t.addEntry(Format
							.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 MoneyMulti &b&l%mayhem_moneymulti%")));
					Score s = o.getScore(Format
							.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 MoneyMulti &b&l%mayhem_moneymulti%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("Tokens")) {
					t.addEntry(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Tokens &b&l%mayhem_tokenbal%")));
					Score s = o.getScore(
							Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Tokens &b&l%mayhem_tokenbal%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("TokenMulti")) {
					t.addEntry(Format
							.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 TokenMulti &b&l%mayhem_tokenmulti%")));
					Score s = o.getScore(Format
							.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 TokenMulti &b&l%mayhem_tokenmulti%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("RankUp")) {
					t.addEntry(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 RankUp &b&l%mayhem_rankup%")));
					Score s = o.getScore(
							Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 RankUp &b&l%mayhem_rankup%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("RankUpProgress")) {
					t.addEntry(Format
							.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Progress &b&l%mayhem_rankuppercent%")));
					Score s = o.getScore(Format
							.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Progress &b&l%mayhem_rankuppercent%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("RankUpCost")) {
					t.addEntry(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Cost &b&l%mayhem_rankupcost%")));
					Score s = o.getScore(
							Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Cost &b&l%mayhem_rankupcost%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("GangName")) {
					t.addEntry(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Gang &b&l%mayhem_gangname%")));
					Score s = o.getScore(
							Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Gang &b&l%mayhem_gangname%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("GangLevel")) {
					t.addEntry(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Level &b&l%mayhem_ganglevel%")));
					Score s = o.getScore(
							Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Level &b&l%mayhem_ganglevel%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("GangCredits")) {
					t.addEntry(
							Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Credits &b&l%mayhem_gangcredits%")));
					Score s = o.getScore(
							Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Credits &b&l%mayhem_gangcredits%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("BlocksBroken")) {
					t.addEntry(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Broken &b&l%mayhem_broken%")));
					Score s = o.getScore(
							Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Broken &b&l%mayhem_broken%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("BPS")) {
					if (BPS.bpssb.containsKey(p)) {
						t.addEntry(Format.color("&7 &7 AvgBPS &b&l" + BPS.bpssb.get(p)));
						Score s = o.getScore(Format.color("&7 &7 AvgBPS &b&l" + BPS.bpssb.get(p)));
						s.setScore(i);
					} else {
						t.addEntry(Format.color("&7 &7 AvgBPS &b&lN/A"));
						Score s = o.getScore(Format.color("&7 &7 AvgBPS &b&lN/A"));
						s.setScore(i);
					}
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("ReactionWins")) {
					t.addEntry(Format
							.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 ReactionWins &b&l%mayhem_reactionwins%")));
					Score s = o.getScore(Format
							.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 ReactionWins &b&l%mayhem_reactionwins%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("Flesh")) {
					t.addEntry(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Flesh &b&l%mayhem_flesh%")));
					Score s = o.getScore(
							Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Flesh &b&l%mayhem_flesh%")));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("DungeonKills")) {
					t.addEntry(Format.color("&7 &7 DungeonKills &b&l"
							+ Format.number(Files.data.getInt("Users." + p.getUniqueId() + ".Kills"))));
					Score s = o.getScore(Format.color("&7 &7 DungeonKills &b&l"
							+ Format.number(Files.data.getInt("Users." + p.getUniqueId() + ".Kills"))));
					s.setScore(i);
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("EditScoreboard")) {
					t.addEntry(Format.color(PlaceholderAPI.setPlaceholders(p, "&c &c &lEdit Your Board &b&l/sb edit")));
					Score s = o.getScore(
							Format.color(PlaceholderAPI.setPlaceholders(p, "&c &c &lEdit Your Board &b&l/sb edit")));
					s.setScore(i);
					i--;
					continue;
				}
			}
		}
		Score bottom = o.getScore(Format.color("&7&m+----------------------------+&1"));
		bottom.setScore(i);
		return sb;
	}

	@SuppressWarnings("deprecation")
	public static String scoreboardString(Player p) {
		if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").size() <= 0) {
			return "";
		}
		String s = "&7&m+----------------------------+";
		Integer i = 14;
		for (String str : Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard")) {
			if (i == 1) {
				break;
			} else {
				if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains(i.toString())) {
					s = s + "\n" + "&7 &m----------------------------";
				}
				if (str.equalsIgnoreCase("PlayerName")) {
					s = s + "\n" + "&7 &7 Player &b&l" + p.getName();
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("Online")) {
					s = s + "\n" + "&7 &7 Online &b&l" + Bukkit.getOnlinePlayers().size();
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("Money")) {
					s = s + "\n" + "&7 &7 Money &b&l%vault_eco_balance_formatted%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("MoneyMulti")) {
					s = s + "\n" + "&7 &7 MoneyMulti &b&l%mayhem_moneymulti%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("Tokens")) {
					s = s + "\n" + "&7 &7 Tokens &b&l%mayhem_tokenbal%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("TokenMulti")) {
					s = s + "\n" + "&7 &7 TokenMulti &b&l%mayhem_tokenmulti%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("RankUp")) {
					s = s + "\n" + "&7 &7 RankUp &b&l%mayhem_rankup%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("RankUpProgress")) {
					s = s + "\n" + "&7 &7 Progress &b&l%mayhem_rankuppercent%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("RankUpCost")) {
					s = s + "\n" + "&7 &7 Cost &b&l%mayhem_rankupcost%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("GangName")) {
					s = s + "\n" + "&7 &7 Gang &b&l%mayhem_gangname%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("GangLevel")) {
					s = s + "\n" + "&7 &7 Level &b&l%mayhem_ganglevel%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("GangCredits")) {
					s = s + "\n" + "&7 &7 Credits &b&l%mayhem_gangcredits%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("BlocksBroken")) {
					s = s + "\n" + "&7 &7 Broken &b&l%mayhem_broken%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("BPS")) {
					if (BPS.bpssb.containsKey(p)) {
						s = s + "\n" + "&7 &7 AvgBPS &b&l" + BPS.bpssb.get(p);
					} else {
						s = s + "\n" + "&7 &7 AvgBPS &b&lN/A";
					}
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("ReactionWins")) {
					s = s + "\n" + "&7 &7 ReactionWins &b&l%mayhem_reactionwins%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("DungeonKills")) {
					s = s + "\n" + "&7 &7 DungeonKills &b&l"
							+ Format.number(Files.data.getInt("Users." + p.getUniqueId() + ".Kills"));
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("Flesh")) {
					s = s + "\n" + "&7 &7 Flesh &b&l%mayhem_flesh%";
					i--;
					continue;
				}
				if (str.equalsIgnoreCase("EditScoreboard")) {
					s = s + "\n" + "&c &c &lEdit Your Board &b&l/sb edit";
					i--;
					continue;
				}
			}
		}
		s = s + "\n&7&m+----------------------------+";
		return PlaceholderAPI.setPlaceholders(p, s);
	}

	public static void startTimer() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			@Override
			public void run() {
				if (Bukkit.getOnlinePlayers().size() > 0) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						p.setScoreboard(scoreboard(p));
					}
				}
			}
		}, 30, 30);
	}
}
