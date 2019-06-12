package me.askingg.mayhem.main;

import org.bukkit.entity.Player;

import me.askingg.mayhem.autosell.AutosellCore;
import me.askingg.mayhem.gangs.GangCore;
import me.askingg.mayhem.marry.MarryCore;
import me.askingg.mayhem.rankup.RankupCore;
import me.askingg.mayhem.scoreboard.ScoreboardCore;
import me.askingg.mayhem.tokens.TokensCore;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class Placeholders extends PlaceholderExpansion {

	public String getIdentifier() {
		return "mayhem";
	}

	public String getPlugin() {
		return null;
	}

	public String getAuthor() {
		return "Askingg";
	}

	public String getVersion() {
		return "1.0";
	}

	public String onPlaceholderRequest(Player p, String identifier) {
		// Placeholder: %mayhem_tokenbal%
		if (identifier.equalsIgnoreCase("tokenbal")) {
			return Format.number(TokensCore.balance(p));
		}

		// Placeholder: %mayhem_flesh%
		if (identifier.equalsIgnoreCase("flesh")) {
			return Format.number(Files.data.getInt("Users." + p.getUniqueId() + ".Flesh"));
		}

		// Placeholder: %mayhem_moneymulti%
		if (identifier.equalsIgnoreCase("moneymulti")) {
			return Format.decimals(2, (1.0 + AutosellCore.multi(p))) + "x";
		}

		// Placeholder: %mayhem_tokenmulti%
		if (identifier.equalsIgnoreCase("tokenmulti")) {
			return Format.decimals(2, (1.0 + TokensCore.multi(p))) + "x";
		}

		// Placeholder: %mayhem_gangname%
		if (identifier.equalsIgnoreCase("gangname")) {
			if (GangCore.hasGang(p)) {
				return Files.data.getString("Users." + p.getUniqueId() + ".Gang");
			}
			return "N/A";
		}

		// Placeholder: %mayhem_partner%
		if (identifier.equalsIgnoreCase("partner")) {
			if (MarryCore.isMarried(p)) {
				return Files.data.getString("Users." + p.getUniqueId() + ".Married");
			} else {
				return "N/A";
			}
		}

		// Placeholder: %mayhem_marryprefix%
		if (identifier.equalsIgnoreCase("marryprefix")) {
			if (MarryCore.isMarried(p)) {
				return "&4 ❤ &8 &l|&7 ";
			} else {
				return "&7 ❤ &8 &l|&7 ";
			}
		}

		// Placeholder: %mayhem_gangprefix%
		if (identifier.equalsIgnoreCase("gangprefix")) {
			if (GangCore.hasGang(p)) {
				if (!RankupCore.prefix(p).equals("")) {
					return Files.data.getString("Users." + p.getUniqueId() + ".Gang") + "&8 &l| &f";
				} else {
					return Files.data.getString("Users." + p.getUniqueId() + ".Gang") + "&8 &l| &f";
				}
			}
			return "";
		}

		// Placeholder: %mayhem_gangcolor%
		if (identifier.equalsIgnoreCase("gangcolor")) {
			if (GangCore.hasGang(p)) {
				return Files.data.getString("Gangs." + GangCore.getGang(p) + ".Colors.Active");
			}
			return "";
		}

		// Placeholder: %mayhem_ganglevel%
		if (identifier.equalsIgnoreCase("ganglevel")) {
			if (GangCore.hasGang(p)) {
				return Files.data.getString("Gangs." + GangCore.getGang(p) + ".Level");
			}
			return "N/A";
		}

		// Placeholder: %mayhem_gangcredits%
		if (identifier.equalsIgnoreCase("gangcredits")) {
			if (GangCore.hasGang(p)) {
				return Format.number(Files.data.getDouble("Gangs." + GangCore.getGang(p) + ".Credits"));
			}
			return "N/A";
		}

		// Placeholder: %mayhem_rankupcolor%
		if (identifier.equalsIgnoreCase("rankupcolor")) {
			return Files.data.getString("Users." + p.getUniqueId() + ".RankupColor");
		}

		// Placeholder: %mayhem_prestige%
		if (identifier.equalsIgnoreCase("prestige")) {
			int i = Files.data.getInt("Users." + p.getUniqueId() + ".Prestige");
			if (i > 0) {
				return RankupCore.prestigeColor(p) + "i&7 "
						+ Files.data.getString("Users." + p.getUniqueId() + ".RankupColor") + "&lP"
						+ Files.data.getInt("Users." + p.getUniqueId() + ".Prestige") + "&7 "
						+ RankupCore.prestigeColor(p) + "i&8&l|&7";
			}
			return "";
		}

		// Placeholder: %mayhem_rankprefix%
		if (identifier.equalsIgnoreCase("rankprefix")) {
			if (Files.data.getInt("Users." + p.getUniqueId() + ".Prestige") > 0) {
				if (p.getName().equals("Synysterrr") || p.getName().equals("Askingg")
						|| p.getName().equals("Sevenfoldd")) {
					return "&9&lOwner&8 &l|&b";
				}
				if (p.getName().equals("ConfigSection")) {
					return "&3&lDev&8 &l|&f";
				}
				if (p.isOp()) {
					return "";
				}
				if (p.hasPermission("mayhem.prefix.fakeadmin")) {
					return "&b&m&lAdmin&8 &l|&f";
				}
				if (p.hasPermission("mayhem.prefix.rumination")) {
					return "&9&k&li&b&l&nManiac&9&k&li&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.admin")) {
					return "&b&lAdmin&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.dev")) {
					return "&6&lDev&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.builder")) {
					return "&5&lBuilder&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.mod")) {
					return "&c&lMod&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.helper")) {
					return "&a&lHelper&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.youtube")) {
					return "&c&lYou&f&lTube&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.twitch")) {
					return "&5&lTwitch&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.minion")) {
					return "&3&lMinion&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.yt")) {
					return "&c&lY&f&lT&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.pluto")) {
					return "&4&lPluto&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.neptune")) {
					return "&c&lNeptune&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.uranus")) {
					return "&9&lUranus&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.saturn")) {
					return "&b&lSaturn&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.jupiter")) {
					return "&3Jupiter&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.mars")) {
					return "&dMars&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.earth")) {
					return "&aEarth&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.venus")) {
					return "&eVenus&8 &l|&f";
				}
				if (p.hasPermission("mayhem.rank.mercury")) {
					return "&fMercury&8 &l|&f";
				}
				return "";
			} else {
				if (p.getName().equals("Synysterrr") || p.getName().equals("Askingg")
						|| p.getName().equals("Sevenfoldd")) {
					return "&9&lOwner&8 &l|&b ";
				}
				if (p.getName().equals("ConfigSection")) {
					return "&3&lDev&8 &l|&f ";
				}
				if (p.isOp()) {
					return "";
				}
				if (p.hasPermission("mayhem.prefix.fakeadmin")) {
					return "&b&m&lAdmin&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.prefix.rumination")) {
					return "&9&k&li&b&l&nManiac&9&k&li&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.admin")) {
					return "&b&lAdmin&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.dev")) {
					return "&6&lDev&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.builder")) {
					return "&5&lBuilder&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.mod")) {
					return "&c&lMod&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.helper")) {
					return "&a&lHelper&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.youtube")) {
					return "&c&lYou&f&lTube&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.twitch")) {
					return "&5&lTwitch&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.minion")) {
					return "&3&lMinion&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.yt")) {
					return "&c&lY&f&lT&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.pluto")) {
					return "&4&lPluto&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.neptune")) {
					return "&c&lNeptune&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.uranus")) {
					return "&9&lUranus&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.saturn")) {
					return "&b&lSaturn&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.jupiter")) {
					return "&3Jupiter&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.mars")) {
					return "&dMars&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.earth")) {
					return "&aEarth&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.venus")) {
					return "&eVenus&8 &l|&f ";
				}
				if (p.hasPermission("mayhem.rank.mercury")) {
					return "&fMercury&8 &l|&f ";
				}
				return "";
			}
		}

		// Placeholder: %mayhem_rankupprefix%
		if (identifier.equalsIgnoreCase("rankupprefix")) {
			int i = Files.data.getInt("Users." + p.getUniqueId() + ".Prestige");
			if (i > 0) {
				return RankupCore.prestigeColor(p) + "i&7 "
						+ Files.data.getString("Users." + p.getUniqueId() + ".RankupColor") + "&l"
						+ Format.decimals(0, Files.data.getDouble("Users." + p.getUniqueId() + ".RankUp")) + "&7 "
						+ RankupCore.prestigeColor(p) + "i&8&l|&7 ";
			}
			return Format.decimals(0, Files.data.getDouble("Users." + p.getUniqueId() + ".RankUp")) + "&8 &l|&f ";
		}

		// Placeholder: %mayhem_rankup%
		if (identifier.equalsIgnoreCase("rankup")) {
			return Files.data.getString("Users." + p.getUniqueId() + ".RankUp");
		}

		// Placeholder: %mayhem_rankupto%
		if (identifier.equalsIgnoreCase("rankupcost")) {
			if (RankupCore.rank(p) == 2000) {
				return "MAX";
			}
			return "$" + Format.number(RankupCore.cost(p));
		}

		// Placeholder: %mayhem_rankuppercent%
		if (identifier.equalsIgnoreCase("rankuppercent")) {
			if (RankupCore.rank(p) == 2000) {
				return "MAX";
			}
			return Format.decimals(2, (Main.eco.getBalance(p) / RankupCore.cost(p)) * 100) + "%";
		}

		// Placeholder: %mayhem_broken%
		if (identifier.equalsIgnoreCase("broken")) {
			return Format.number(Main.broken.get(p.getUniqueId().toString()) + 0.0);
		}

		// Placeholder: %mayhem_reactionwins%
		if (identifier.equalsIgnoreCase("reactionwins")) {
			return Format.decimals(0, Files.data.getDouble("Users." + p.getUniqueId() + ".ReactionWins"));
		}

		// Placeholder: %mayhem_maxram%
		if (identifier.equalsIgnoreCase("maxram")) {
			return Runtime.getRuntime().maxMemory() / 1048576L + "mb";
		}

		// Placeholder: %mayhem_allocatedram%
		if (identifier.equalsIgnoreCase("allocatedram")) {
			return Runtime.getRuntime().totalMemory() / 1048576L + "mb";
		}

		// Placeholder: %mayhem_usingram%
		if (identifier.equalsIgnoreCase("usingram")) {
			return (Runtime.getRuntime().totalMemory() / 1048576L) - (Runtime.getRuntime().freeMemory() / 1048576L)
					+ "mb";
		}

		// Placeholder: %mayhem_freeram%
		if (identifier.equalsIgnoreCase("freeram")) {
			return Runtime.getRuntime().freeMemory() / 1048576L + "mb";
		}

		// Placeholder: %mayhem_firstjoin%
		if (identifier.equalsIgnoreCase("firstjoin")) {
			return Files.data.getString("Users." + p.getUniqueId() + ".FirstJoin");
		}

		// Placeholder: %mayhem_scoreboard%
		if (identifier.equalsIgnoreCase("scoreboard")) {
			return Format.color(ScoreboardCore.scoreboardString(p));
		}

		return null;
	}
}