package me.askingg.mayhem.rankup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.Player;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class RankupCore {

	public static List<Player> pconfirm = new ArrayList<Player>();

	public static double baseCost() {
		return 100000.0;
	}

	public static double baseIncrease() {
		return 100000;
	}

	public static Integer rank(Player p) {
		return Files.data.getInt("Users." + p.getUniqueId() + ".RankUp");
	}

	public static Boolean prestige(Player p) {
		if (Main.eco.getBalance(p) >= 10000000000000.0) {
			if (pconfirm.contains(p)) {
				try {
					pconfirm.remove(p);
					int i = 1;
					while (i <= 16) {
						i++;
						Main.perm.playerRemove(p, "mayhem.shops." + i);
						Main.perm.playerRemove(p, "essentials.warps.mine" + i);
					}
					Main.eco.withdrawPlayer(p, (Main.eco.getBalance(p) - 1));
					Message.broadcastRaw("&7");
					Message.broadcastRaw("&7");
					Message.broadcastRaw("&4&lPRESTIGE &8// &b&l" + p.getName() + "&7 just prestiged!");
					Message.broadcastRaw("&7");
					Message.player(
							"&7All of your enchantment's max levels have been slightly improved: &cPurchasing enchantments for another player who cannot access the levels is forbidden",
							p);
					Message.broadcastRaw("&7");
					Message.playerRaw("&7", p);
					p.chat("/spawn");
					p.chat("/spawn");
					p.chat("/spawn");
					Files.data.set("Users." + p.getUniqueId() + ".RankUp", 0);
					Files.data.set("Users." + p.getUniqueId() + ".Prestige",
							(Files.data.getInt("Users." + p.getUniqueId() + ".Prestige") + 1));
					Files.data.save(Files.dataFile);
					return true;
				} catch (Exception ex) {
					ex.printStackTrace();
					return false;
				}
			} else {
				pconfirm.add(p);
				Message.playerRaw("&7", p);
				Message.playerRaw(
						"&4&lWARNING &cYou will lose your money when you prestige: Trading away your money before you prestige is forbidden",
						p);
				Message.playerRaw(
						"&4&lWARNING &cAfter prestiging, people may not pay you to rankup: This will result in both of you being punished",
						p);
				Message.playerRaw(
						"&4&lWARNING &cYou are not permitted to purchase enchantments for another player who cannot access the increased enchantment levels; You will be punished for doing so",
						p);
				Message.playerRaw("&7", p);
				Message.playerRaw(
						"&7By prestiging, you agree that you have read and accept the above, and accept that this action cannot be undone: If you stil wish to prestige execute &b/Prestige&7 once more.",
						p);
				Message.playerRaw("&7", p);
				return true;
			}
		} else {
			Message.player("Sorry, but you need &c$10T&7 to prestige", p);
			return true;
		}
	}

	public static Boolean rankUp(Player p) {
		if (Main.eco.getBalance(p) >= RankupCore.cost(p)) {
			if (rank(p) == 2000) {
				Message.player("&7Sorry, but &b2,000&7 is the max... &b/Prestige", p);
				return false;
			}
			try {
				Main.eco.withdrawPlayer(p, RankupCore.cost(p));
				if (((RankupCore.rank(p) + 1) % 5) == 0) {
					Message.broadcast("&b" + p.getName() + "&7 ranked up to &b" + (RankupCore.rank(p) + 1));
				}
				if (((rank(p) + 1) % 200) == 0) {
					randomColor(p);
				}
				if ((rank(p) + 1) == 10) {
					Main.perm.playerAdd(p, "essentials.warps.mine2");
					Main.perm.playerAdd(p, "mayhem.shops.2");
					Message.player("&7You unlocked the second mine: &b/warp mine2", p);
				}
				if ((rank(p) + 1) == 25) {
					Main.perm.playerAdd(p, "essentials.warps.mine3");
					Main.perm.playerAdd(p, "mayhem.shops.3");
					Message.player("&7You unlocked the third mine: &b/warp mine3", p);
				}
				if ((rank(p) + 1) == 50) {
					Main.perm.playerAdd(p, "essentials.warps.mine4");
					Main.perm.playerAdd(p, "mayhem.shops.4");
					Message.player("&7You unlocked the fourth mine: &b/warp mine4", p);
				}
				if ((rank(p) + 1) == 75) {
					Main.perm.playerAdd(p, "essentials.warps.mine5");
					Main.perm.playerAdd(p, "mayhem.shops.5");
					Message.player("&7You unlocked the fifth mine: &b/warp mine5", p);
				}
				if ((rank(p) + 1) == 100) {
					Main.perm.playerAdd(p, "essentials.warps.mine6");
					Main.perm.playerAdd(p, "mayhem.shops.6");
					Message.player("&7You unlocked the sixth mine: &b/warp mine6", p);
				}
				if ((rank(p) + 1) == 200) {
					Main.perm.playerAdd(p, "essentials.warps.mine7");
					Main.perm.playerAdd(p, "mayhem.shops.7");
					Message.player("&7You unlocked the seventh mine: &b/warp mine7", p);
				}
				if ((rank(p) + 1) == 400) {
					Main.perm.playerAdd(p, "essentials.warps.mine8");
					Main.perm.playerAdd(p, "mayhem.shops.8");
					Message.player("&7You unlocked the eighth mine: &b/warp mine8", p);
				}
				if ((rank(p) + 1) == 600) {
					Main.perm.playerAdd(p, "essentials.warps.mine9");
					Main.perm.playerAdd(p, "mayhem.shops.9");
					Message.player("&7You unlocked the nineth mine: &b/warp mine9", p);
				}
				if ((rank(p) + 1) == 800) {
					Main.perm.playerAdd(p, "essentials.warps.mine10");
					Main.perm.playerAdd(p, "mayhem.shops.10");
					Message.player("&7You unlocked the tenth mine: &b/warp mine10", p);
				}
				if ((rank(p) + 1) == 1000) {
					Main.perm.playerAdd(p, "essentials.warps.mine11");
					Main.perm.playerAdd(p, "mayhem.shops.11");
					Message.player("&7You unlocked the eleventh mine: &b/warp mine11", p);
				}
				if ((rank(p) + 1) == 1200) {
					Main.perm.playerAdd(p, "essentials.warps.mine12");
					Main.perm.playerAdd(p, "mayhem.shops.12");
					Message.player("&7You unlocked the twelveth mine: &b/warp mine12", p);
				}
				if ((rank(p) + 1) == 1400) {
					Main.perm.playerAdd(p, "essentials.warps.mine13");
					Main.perm.playerAdd(p, "mayhem.shops.13");
					Message.player("&7You unlocked the thirteenth mine: &b/warp mine13", p);
				}
				if ((rank(p) + 1) == 1600) {
					Main.perm.playerAdd(p, "essentials.warps.mine14");
					Main.perm.playerAdd(p, "mayhem.shops.14");
					Message.player("&7You unlocked the fourteenth mine: &b/warp mine14", p);
				}
				if ((rank(p) + 1) == 1800) {
					Main.perm.playerAdd(p, "essentials.warps.mine15");
					Main.perm.playerAdd(p, "mayhem.shops.15");
					Message.player("&7You unlocked the fifteenth mine: &b/warp mine15", p);
				}
				Files.data.set("Users." + p.getUniqueId() + ".RankUp", (rank(p) + 1));
				Files.data.save(Files.dataFile);
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}
		} else {
			Message.player("&7Sorry, but you don't have the &c$" + Format.decimals(2, RankupCore.cost(p))
					+ "&7 required to rankup", p);
			return false;
		}
	}

	public static String prefix(Player p) {
		if (p.getName().equals("Synysterrr") || p.getName().equals("Askingg") || p.getName().equals("Sevenfoldd")) {
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

	public static String prestigeColor(Player p) {
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankupColor").contains("&7")) {
			return "&f&k";
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankupColor").contains("&e")) {
			return "&6&k";
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankupColor").contains("&c")) {
			return "&4&k";
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankupColor").contains("&a")) {
			return "&2&k";
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankupColor").contains("&3")) {
			return "&b&k";
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankupColor").contains("&b")) {
			return "&3&k";
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankupColor").contains("&2")) {
			return "&a&k";
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankupColor").contains("&d")) {
			return "&5&k";
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankupColor").contains("&5")) {
			return "&d&k";
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankupColor").contains("&f")) {
			return "&7&k";
		}
		if (Files.data.getString("Users." + p.getUniqueId() + ".RankupColor").contains("&6")) {
			return "&e&k";
		}
		return null;
	}

	public static void randomColor(Player p) {
		Random r = new Random();
		Boolean b = false;
		if (p.isOp() || (p.hasPermission("mayhem.rankcolor.yellow") && p.hasPermission("mayhem.rankcolor.red")
				&& p.hasPermission("mayhem.rankcolor.lime") && p.hasPermission("mayhem.rankcolor.cyan")
				&& p.hasPermission("mayhem.rankcolor.blue") && p.hasPermission("mayhem.rankcolor.green")
				&& p.hasPermission("mayhem.rankcolor.pink") && p.hasPermission("mayhem.rankcolor.purple")
				&& p.hasPermission("mayhem.rankcolor.white") && p.hasPermission("mayhem.rankcolor.orange"))) {
			return;
		}
		while (!b) {
			Integer x = r.nextInt(10) + 1;
			if (x == 1) {
				if (!p.hasPermission("mayhem.rankcolor.yellow")) {
					Message.player("&7You unlocked the &eYellow&7 rank prefix color", p);
					Main.perm.playerAdd(p, "mayhem.rankcolor.yellow");
					b = true;
				}
			}
			if (x == 2) {
				if (!p.hasPermission("mayhem.rankcolor.red")) {
					Message.player("&7You unlocked the &cRed&7 rank prefix color", p);
					Main.perm.playerAdd(p, "mayhem.rankcolor.red");
					b = true;
				}
			}
			if (x == 3) {
				if (!p.hasPermission("mayhem.rankcolor.lime")) {
					Message.player("&7You unlocked the &aLime&7 rank prefix color", p);
					Main.perm.playerAdd(p, "mayhem.rankcolor.lime");
					b = true;
				}
			}
			if (x == 4) {
				if (!p.hasPermission("mayhem.rankcolor.cyan")) {
					Message.player("&7You unlocked the &3Cyan&7 rank prefix color", p);
					Main.perm.playerAdd(p, "mayhem.rankcolor.cyan");
					b = true;
				}
			}
			if (x == 5) {
				if (!p.hasPermission("mayhem.rankcolor.blue")) {
					Message.player("&7You unlocked the &bBlue&7 rank prefix color", p);
					Main.perm.playerAdd(p, "mayhem.rankcolor.blue");
					b = true;
				}
			}
			if (x == 6) {
				if (!p.hasPermission("mayhem.rankcolor.green")) {
					Message.player("&7You unlocked the &2Green&7 rank prefix color", p);
					Main.perm.playerAdd(p, "mayhem.rankcolor.green");
					b = true;
				}
			}
			if (x == 7) {
				if (!p.hasPermission("mayhem.rankcolor.pink")) {
					Message.player("&7You unlocked the &dPink&7 rank prefix color", p);
					Main.perm.playerAdd(p, "mayhem.rankcolor.pink");
					b = true;
				}
			}
			if (x == 8) {
				if (!p.hasPermission("mayhem.rankcolor.purple")) {
					Message.player("&7You unlocked the &5Purple&7 rank prefix color", p);
					Main.perm.playerAdd(p, "mayhem.rankcolor.purple");
					b = true;
				}
			}
			if (x == 9) {
				if (!p.hasPermission("mayhem.rankcolor.white")) {
					Message.player("&7You unlocked the &fWhite&7 rank prefix color", p);
					Main.perm.playerAdd(p, "mayhem.rankcolor.white");
					b = true;
				}
			}
			if (x == 10) {
				if (!p.hasPermission("mayhem.rankcolor.orange")) {
					Message.player("&7You unlocked the &6Orange&7 rank prefix color", p);
					Main.perm.playerAdd(p, "mayhem.rankcolor.orange");
					b = true;
				}
			}
		}
	}

	public static double prestigeIncrease(Player p) {
		int prestige = Files.data.getInt("Users." + p.getUniqueId() + ".Prestige");
		if (Files.data.getInt("Users." + p.getUniqueId() + ".Prestige") > 0) {
			return 1 + (0.25 * prestige);
		}
		return 1;
	}

	public static double cost(Player p) {
		Double x = 0.0;
		double pm = prestigeIncrease(p);
		if (rank(p) < 50) {
			x = x + (baseIncrease() * rank(p)) + 100000;
			return x * pm;
		}
		if (rank(p) < 100 && rank(p) >= 50) {
			x = x + (baseIncrease() * (rank(p) * 2));
			return x * pm;
		}
		if (rank(p) < 150 && rank(p) >= 100) {
			x = x + (baseIncrease() * (rank(p) * 3));
			return x * pm;
		}
		if (rank(p) < 200 && rank(p) >= 150) {
			x = x + (baseIncrease() * (rank(p) * 4));
			return x * pm;
		}
		if (rank(p) < 250 && rank(p) >= 200) {
			x = x + (baseIncrease() * (rank(p) * 5));
			return x * pm;
		}
		if (rank(p) < 300 && rank(p) >= 250) {
			x = x + (baseIncrease() * (rank(p) * 7.5));
			return x * pm;
		}
		if (rank(p) < 350 && rank(p) >= 300) {
			x = x + (baseIncrease() * (rank(p) * 10));
			return x * pm;
		}
		if (rank(p) < 400 && rank(p) >= 350) {
			x = x + (baseIncrease() * (rank(p) * 12.5));
			return x * pm;
		}
		if (rank(p) < 450 && rank(p) >= 400) {
			x = x + (baseIncrease() * (rank(p) * 15));
			return x * pm;
		}
		if (rank(p) < 500 && rank(p) >= 450) {
			x = x + (baseIncrease() * (rank(p) * 20));
			return x * pm;
		}
		if (rank(p) < 550 && rank(p) >= 500) {
			x = x + (baseIncrease() * (rank(p) * 25));
			return x * pm;
		}
		if (rank(p) < 600 && rank(p) >= 550) {
			x = x + (baseIncrease() * (rank(p) * 30));
			return x * pm;
		}
		if (rank(p) < 650 && rank(p) >= 600) {
			x = x + (baseIncrease() * (rank(p) * 35));
			return x * pm;
		}
		if (rank(p) < 700 && rank(p) >= 650) {
			x = x + (baseIncrease() * (rank(p) * 40));
			return x * pm;
		}
		if (rank(p) < 750 && rank(p) >= 700) {
			x = x + (baseIncrease() * (rank(p) * 45));
			return x * pm;
		}
		if (rank(p) < 800 && rank(p) >= 750) {
			x = x + (baseIncrease() * (rank(p) * 50));
			return x * pm;
		}
		if (rank(p) < 850 && rank(p) >= 800) {
			x = x + (baseIncrease() * (rank(p) * 60));
			return x * pm;
		}
		if (rank(p) < 900 && rank(p) >= 850) {
			x = x + (baseIncrease() * (rank(p) * 70));
			return x * pm;
		}
		if (rank(p) < 950 && rank(p) >= 900) {
			x = x + (baseIncrease() * (rank(p) * 80));
			return x * pm;
		}
		if (rank(p) < 1000 && rank(p) >= 950) {
			x = x + (baseIncrease() * (rank(p) * 90));
			return x * pm;
		}
		if (rank(p) < 1050 && rank(p) >= 1000) {
			x = x + (baseIncrease() * (rank(p) * 100));
			return x * pm;
		}
		if (rank(p) < 1100 && rank(p) >= 1050) {
			x = x + (baseIncrease() * (rank(p) * 125));
			return x * pm;
		}
		if (rank(p) < 1150 && rank(p) >= 1100) {
			x = x + (baseIncrease() * (rank(p) * 150));
			return x * pm;
		}
		if (rank(p) < 1200 && rank(p) >= 1150) {
			x = x + (baseIncrease() * (rank(p) * 200));
			return x * pm;
		}
		if (rank(p) < 1250 && rank(p) >= 1200) {
			x = x + (baseIncrease() * (rank(p) * 250));
			return x * pm;
		}
		if (rank(p) < 1300 && rank(p) >= 1250) {
			x = x + (baseIncrease() * (rank(p) * 300));
			return x * pm;
		}
		if (rank(p) < 1350 && rank(p) >= 1300) {
			x = x + (baseIncrease() * (rank(p) * 350));
			return x * pm;
		}
		if (rank(p) < 1400 && rank(p) >= 1350) {
			x = x + (baseIncrease() * (rank(p) * 400));
			return x * pm;
		}
		if (rank(p) < 1450 && rank(p) >= 1400) {
			x = x + (baseIncrease() * (rank(p) * 500));
			return x * pm;
		}
		if (rank(p) < 1500 && rank(p) >= 1450) {
			x = x + (baseIncrease() * (rank(p) * 600));
			return x * pm;
		}
		if (rank(p) < 1550 && rank(p) >= 1500) {
			x = x + (baseIncrease() * (rank(p) * 700));
			return x * pm;
		}
		if (rank(p) < 1600 && rank(p) >= 1550) {
			x = x + (baseIncrease() * (rank(p) * 800));
			return x * pm;
		}
		if (rank(p) < 1650 && rank(p) >= 1600) {
			x = x + (baseIncrease() * (rank(p) * 900));
			return x * pm;
		}
		if (rank(p) < 1700 && rank(p) >= 1650) {
			x = x + (baseIncrease() * (rank(p) * 1000));
			return x * pm;
		}
		if (rank(p) < 1750 && rank(p) >= 1700) {
			x = x + (baseIncrease() * (rank(p) * 1250));
			return x * pm;
		}
		if (rank(p) < 1800 && rank(p) >= 1750) {
			x = x + (baseIncrease() * (rank(p) * 1500));
			return x * pm;
		}
		if (rank(p) < 1850 && rank(p) >= 1800) {
			x = x + (baseIncrease() * (rank(p) * 1750));
			return x * pm;
		}
		if (rank(p) < 1900 && rank(p) >= 1850) {
			x = x + (baseIncrease() * (rank(p) * 2000));
			return x * pm;
		}
		if (rank(p) < 1950 && rank(p) >= 1900) {
			x = x + (baseIncrease() * (rank(p) * 2500));
			return x * pm;
		}
		if (rank(p) < 2000 && rank(p) >= 1950) {
			x = x + (baseIncrease() * (rank(p) * 3000));
			return x * pm;
		}
		// x = x + (RankupCore.rank(p) * ((RankupCore.rank(p) * 0.25) *
		// baseIncrease())); // 5 * 2 * 100,000

		// if (rank(p) < 50) {
		// x = x + (baseIncrease() * rank(p));
		// return x;
		// }
		// if (rank(p) < 100 && rank(p) >= 50) {
		// x = x + (baseIncrease() * 49);
		// Integer i = rank(p) - 49;
		// x = x + ((baseIncrease() * 5) * i);
		// return x;
		// }
		// if (rank(p) < 150 && rank(p) >= 100) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// Integer i = rank(p) - 99;
		// x = x + ((baseIncrease() * 10) * i);
		// return x;
		// }
		// if (rank(p) < 200 && rank(p) >= 150) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// Integer i = rank(p) - 149;
		// x = x + ((baseIncrease() * 15) * i);
		// return x;
		// }
		// if (rank(p) < 250 && rank(p) >= 200) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// Integer i = rank(p) - 199;
		// x = x + ((baseIncrease() * 20) * i);
		// return x;
		// }
		// if (rank(p) < 300 && rank(p) >= 250) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// Integer i = rank(p) - 249;
		// x = x + ((baseIncrease() * 25) * i);
		// return x;
		// }
		// if (rank(p) < 350 && rank(p) >= 300) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// Integer i = rank(p) - 299;
		// x = x + ((baseIncrease() * 30) * i);
		// return x;
		// }
		// if (rank(p) < 400 && rank(p) >= 350) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// Integer i = rank(p) - 349;
		// x = x + ((baseIncrease() * 35) * i);
		// return x;
		// }
		// if (rank(p) < 450 && rank(p) >= 400) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// x = x + ((baseIncrease() * 35) * 49);
		// Integer i = rank(p) - 399;
		// x = x + ((baseIncrease() * 40) * i);
		// return x;
		// }
		// if (rank(p) < 500 && rank(p) >= 450) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// x = x + ((baseIncrease() * 35) * 49);
		// x = x + ((baseIncrease() * 40) * 49);
		// Integer i = rank(p) - 449;
		// x = x + ((baseIncrease() * 45) * i);
		// return x;
		// }
		// if (rank(p) < 550 && rank(p) >= 500) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// x = x + ((baseIncrease() * 35) * 49);
		// x = x + ((baseIncrease() * 40) * 49);
		// x = x + ((baseIncrease() * 45) * 49);
		// Integer i = rank(p) - 449;
		// x = x + ((baseIncrease() * 50) * i);
		// return x;
		// }
		// if (rank(p) < 600 && rank(p) >= 550) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// x = x + ((baseIncrease() * 35) * 49);
		// x = x + ((baseIncrease() * 40) * 49);
		// x = x + ((baseIncrease() * 45) * 49);
		// x = x + ((baseIncrease() * 50) * 49);
		// Integer i = rank(p) - 499;
		// x = x + ((baseIncrease() * 60) * i);
		// return x;
		// }
		// if (rank(p) < 650 && rank(p) >= 600) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// x = x + ((baseIncrease() * 35) * 49);
		// x = x + ((baseIncrease() * 40) * 49);
		// x = x + ((baseIncrease() * 45) * 49);
		// x = x + ((baseIncrease() * 50) * 49);
		// x = x + ((baseIncrease() * 60) * 49);
		// Integer i = rank(p) - 549;
		// x = x + ((baseIncrease() * 70) * i);
		// return x;
		// }
		// if (rank(p) < 700 && rank(p) >= 650) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// x = x + ((baseIncrease() * 35) * 49);
		// x = x + ((baseIncrease() * 40) * 49);
		// x = x + ((baseIncrease() * 45) * 49);
		// x = x + ((baseIncrease() * 50) * 49);
		// x = x + ((baseIncrease() * 60) * 49);
		// x = x + ((baseIncrease() * 70) * 49);
		// Integer i = rank(p) - 599;
		// x = x + ((baseIncrease() * 80) * i);
		// return x;
		// }
		// if (rank(p) < 750 && rank(p) >= 700) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// x = x + ((baseIncrease() * 35) * 49);
		// x = x + ((baseIncrease() * 40) * 49);
		// x = x + ((baseIncrease() * 45) * 49);
		// x = x + ((baseIncrease() * 50) * 49);
		// x = x + ((baseIncrease() * 60) * 49);
		// x = x + ((baseIncrease() * 70) * 49);
		// x = x + ((baseIncrease() * 80) * 49);
		// Integer i = rank(p) - 749;
		// x = x + ((baseIncrease() * 90) * i);
		// return x;
		// }
		// if (rank(p) < 800 && rank(p) >= 750) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// x = x + ((baseIncrease() * 35) * 49);
		// x = x + ((baseIncrease() * 40) * 49);
		// x = x + ((baseIncrease() * 45) * 49);
		// x = x + ((baseIncrease() * 50) * 49);
		// x = x + ((baseIncrease() * 60) * 49);
		// x = x + ((baseIncrease() * 70) * 49);
		// x = x + ((baseIncrease() * 80) * 49);
		// x = x + ((baseIncrease() * 90) * 49);
		// Integer i = rank(p) - 799;
		// x = x + ((baseIncrease() * 100) * i);
		// return x;
		// }
		// if (rank(p) < 850 && rank(p) >= 800) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// x = x + ((baseIncrease() * 35) * 49);
		// x = x + ((baseIncrease() * 40) * 49);
		// x = x + ((baseIncrease() * 45) * 49);
		// x = x + ((baseIncrease() * 50) * 49);
		// x = x + ((baseIncrease() * 60) * 49);
		// x = x + ((baseIncrease() * 70) * 49);
		// x = x + ((baseIncrease() * 80) * 49);
		// x = x + ((baseIncrease() * 90) * 49);
		// x = x + ((baseIncrease() * 100) * 49);
		// Integer i = rank(p) - 849;
		// x = x + ((baseIncrease() * 125) * i);
		// return x;
		// }
		// if (rank(p) < 900 && rank(p) >= 850) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// x = x + ((baseIncrease() * 35) * 49);
		// x = x + ((baseIncrease() * 40) * 49);
		// x = x + ((baseIncrease() * 45) * 49);
		// x = x + ((baseIncrease() * 50) * 49);
		// x = x + ((baseIncrease() * 60) * 49);
		// x = x + ((baseIncrease() * 70) * 49);
		// x = x + ((baseIncrease() * 80) * 49);
		// x = x + ((baseIncrease() * 90) * 49);
		// x = x + ((baseIncrease() * 100) * 49);
		// x = x + ((baseIncrease() * 125) * 49);
		// Integer i = rank(p) - 899;
		// x = x + ((baseIncrease() * 150) * i);
		// return x;
		// }
		// if (rank(p) < 950 && rank(p) >= 900) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// x = x + ((baseIncrease() * 35) * 49);
		// x = x + ((baseIncrease() * 40) * 49);
		// x = x + ((baseIncrease() * 45) * 49);
		// x = x + ((baseIncrease() * 50) * 49);
		// x = x + ((baseIncrease() * 60) * 49);
		// x = x + ((baseIncrease() * 70) * 49);
		// x = x + ((baseIncrease() * 80) * 49);
		// x = x + ((baseIncrease() * 90) * 49);
		// x = x + ((baseIncrease() * 100) * 49);
		// x = x + ((baseIncrease() * 125) * 49);
		// x = x + ((baseIncrease() * 150) * 49);
		// Integer i = rank(p) - 949;
		// x = x + ((baseIncrease() * 175) * i);
		// return x;
		// }
		// if (rank(p) < 1000 && rank(p) >= 950) {
		// x = x + (baseIncrease() * 49);
		// x = x + ((baseIncrease() * 5) * 49);
		// x = x + ((baseIncrease() * 10) * 49);
		// x = x + ((baseIncrease() * 15) * 49);
		// x = x + ((baseIncrease() * 20) * 49);
		// x = x + ((baseIncrease() * 25) * 49);
		// x = x + ((baseIncrease() * 30) * 49);
		// x = x + ((baseIncrease() * 35) * 49);
		// x = x + ((baseIncrease() * 40) * 49);
		// x = x + ((baseIncrease() * 45) * 49);
		// x = x + ((baseIncrease() * 50) * 49);
		// x = x + ((baseIncrease() * 60) * 49);
		// x = x + ((baseIncrease() * 70) * 49);
		// x = x + ((baseIncrease() * 80) * 49);
		// x = x + ((baseIncrease() * 90) * 49);
		// x = x + ((baseIncrease() * 100) * 49);
		// x = x + ((baseIncrease() * 125) * 49);
		// x = x + ((baseIncrease() * 150) * 49);
		// x = x + ((baseIncrease() * 175) * 49);
		// Integer i = rank(p) - 999;
		// x = x + ((baseIncrease() * 200) * i);
		// return x;
		// }
		return x;
	}

}
