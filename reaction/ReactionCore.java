package me.askingg.mayhem.reaction;

import java.util.Random;

import org.bukkit.Bukkit;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class ReactionCore {

	private static Main main;
	public static long startTime = 0;
	public static Boolean reaction = false;
	public static String reactionWord = null;
	public static long startTime;

	public static void reactionInstance(Main m) {
		main = m;
	}

	public static void beginReactions() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
			@Override
			public void run() {
				reaction = true;
				Random r = new Random();
				int x = r.nextInt(2);
				if (x == 0) {
					reactionWord = randomWord(15);
				}
				if (x == 1) {
					reactionWord = randomWord2();
				}
				Message.broadcast("&7First person to type &b" + reactionWord + "&7 wins");
				startTime = System.currentTimeMillis();
			}
		}, 12000, 12000);
	}

	public static String getTime(long time) {
		if (startTime < 0L)
			return "0";
		double t = (time - startTime) / 1000.0D;
		return Format.decimals(2, t);
	}

	public static String randomWord2() {
		Random r = new Random();
		int x = r.nextInt(26);
		if (x == 0) {
			return "Discord";
		}
		if (x == 1) {
			return "Minecraft";
		}
		if (x == 2) {
			return "Relic";
		}
		if (x == 3) {
			return "Artefact";
		}
		if (x == 4) {
			return "Charm";
		}
		if (x == 5) {
			return "EchoDevice";
		}
		if (x == 6) {
			return "OP-Prison";
		}
		if (x == 7) {
			return "MayhemMines";
		}
		if (x == 8) {
			return "DecayedFlesh";
		}
		if (x == 9) {
			return "Gangs";
		}
		if (x == 10) {
			return "Tokens";
		}
		if (x == 11) {
			return "Askingg";
		}
		if (x == 12) {
			return "Sevenfoldd";
		}
		if (x == 13) {
			return "Askingg";
		}
		if (x == 14) {
			return "Mercury";
		}
		if (x == 15) {
			return "Venus";
		}
		if (x == 16) {
			return "Earth";
		}
		if (x == 17) {
			return "Mars";
		}
		if (x == 18) {
			return "Jupiter";
		}
		if (x == 19) {
			return "Saturn";
		}
		if (x == 20) {
			return "Uranus";
		}
		if (x == 21) {
			return "Neptune";
		}
		if (x == 22) {
			return "pluto";
		}
		if (x == 23) {
			return "Argon";
		}
		if (x == 24) {
			return "Krypton";
		}
		if (x == 25) {
			return "Xenon";
		}
		return "MayhemMines";
	}

	public static String randomWord(Integer i) {
		String word = "";
		while (i >= 0) {
			i--;
			Random r = new Random();
			Integer x = r.nextInt(62);
			if (x == 0) {
				word = word + x;
			}
			if (x == 1) {
				word = word + x;
			}
			if (x == 2) {
				word = word + x;
			}
			if (x == 3) {
				word = word + x;
			}
			if (x == 4) {
				word = word + x;
			}
			if (x == 5) {
				word = word + x;
			}
			if (x == 6) {
				word = word + x;
			}
			if (x == 7) {
				word = word + x;
			}
			if (x == 8) {
				word = word + x;
			}
			if (x == 9) {
				word = word + x;
			}
			if (x == 10) {
				word = word + "A";
			}
			if (x == 11) {
				word = word + "B";
			}
			if (x == 12) {
				word = word + "C";
			}
			if (x == 13) {
				word = word + "D";
			}
			if (x == 14) {
				word = word + "E";
			}
			if (x == 15) {
				word = word + "F";
			}
			if (x == 16) {
				word = word + "G";
			}
			if (x == 17) {
				word = word + "H";
			}
			if (x == 18) {
				word = word + "I";
			}
			if (x == 19) {
				word = word + "J";
			}
			if (x == 20) {
				word = word + "K";
			}
			if (x == 21) {
				word = word + "L";
			}
			if (x == 22) {
				word = word + "M";
			}
			if (x == 23) {
				word = word + "N";
			}
			if (x == 24) {
				word = word + "O";
			}
			if (x == 25) {
				word = word + "P";
			}
			if (x == 26) {
				word = word + "Q";
			}
			if (x == 27) {
				word = word + "R";
			}
			if (x == 28) {
				word = word + "S";
			}
			if (x == 29) {
				word = word + "T";
			}
			if (x == 30) {
				word = word + "U";
			}
			if (x == 31) {
				word = word + "V";
			}
			if (x == 32) {
				word = word + "W";
			}
			if (x == 33) {
				word = word + "X";
			}
			if (x == 34) {
				word = word + "Y";
			}
			if (x == 35) {
				word = word + "Z";
			}
			if (x == 36) {
				word = word + "a";
			}
			if (x == 37) {
				word = word + "b";
			}
			if (x == 38) {
				word = word + "c";
			}
			if (x == 39) {
				word = word + "d";
			}
			if (x == 40) {
				word = word + "e";
			}
			if (x == 41) {
				word = word + "f";
			}
			if (x == 42) {
				word = word + "g";
			}
			if (x == 43) {
				word = word + "h";
			}
			if (x == 44) {
				word = word + "i";
			}
			if (x == 45) {
				word = word + "j";
			}
			if (x == 46) {
				word = word + "k";
			}
			if (x == 47) {
				word = word + "l";
			}
			if (x == 48) {
				word = word + "m";
			}
			if (x == 49) {
				word = word + "n";
			}
			if (x == 50) {
				word = word + "o";
			}
			if (x == 51) {
				word = word + "p";
			}
			if (x == 52) {
				word = word + "q";
			}
			if (x == 53) {
				word = word + "r";
			}
			if (x == 54) {
				word = word + "s";
			}
			if (x == 55) {
				word = word + "t";
			}
			if (x == 56) {
				word = word + "u";
			}
			if (x == 57) {
				word = word + "v";
			}
			if (x == 58) {
				word = word + "w";
			}
			if (x == 59) {
				word = word + "x";
			}
			if (x == 60) {
				word = word + "y";
			}
			if (x == 61) {
				word = word + "z";
			}
		}
		return word;
	}
}
