package me.askingg.mayhem.utils;

import java.util.Random;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;

public class Misc {

	@SuppressWarnings("deprecation")
	public static void setTablist(Player p) {
		p.setPlayerListName(Format.color(PlaceholderAPI.setPlaceholders(p,
				"%mayhem_gangprefix%%mayhem_rankprefix%%mayhem_rankupcolor%%mayhem_prestige%%mayhem_rankupcolor%%mayhem_rankupprefix%%player_displayname%")));
	}

	public static String randomCode() {
		return randomString(4) + "-" + randomString(3);
	}

	public static String randomString(Integer i) {
		String c = "";
		while (i >= 0) {
			i--;
			Random r = new Random();
			Integer x = r.nextInt(62);
			if (x == 0) {
				c = c + x;
			}
			if (x == 1) {
				c = c + x;
			}
			if (x == 2) {
				c = c + x;
			}
			if (x == 3) {
				c = c + x;
			}
			if (x == 4) {
				c = c + x;
			}
			if (x == 5) {
				c = c + x;
			}
			if (x == 6) {
				c = c + x;
			}
			if (x == 7) {
				c = c + x;
			}
			if (x == 8) {
				c = c + x;
			}
			if (x == 9) {
				c = c + x;
			}
			if (x == 10) {
				c = c + "A";
			}
			if (x == 11) {
				c = c + "B";
			}
			if (x == 12) {
				c = c + "C";
			}
			if (x == 13) {
				c = c + "D";
			}
			if (x == 14) {
				c = c + "E";
			}
			if (x == 15) {
				c = c + "F";
			}
			if (x == 16) {
				c = c + "G";
			}
			if (x == 17) {
				c = c + "H";
			}
			if (x == 18) {
				c = c + "I";
			}
			if (x == 19) {
				c = c + "J";
			}
			if (x == 20) {
				c = c + "K";
			}
			if (x == 21) {
				c = c + "L";
			}
			if (x == 22) {
				c = c + "M";
			}
			if (x == 23) {
				c = c + "N";
			}
			if (x == 24) {
				c = c + "O";
			}
			if (x == 25) {
				c = c + "P";
			}
			if (x == 26) {
				c = c + "Q";
			}
			if (x == 27) {
				c = c + "R";
			}
			if (x == 28) {
				c = c + "S";
			}
			if (x == 29) {
				c = c + "T";
			}
			if (x == 30) {
				c = c + "U";
			}
			if (x == 31) {
				c = c + "V";
			}
			if (x == 32) {
				c = c + "W";
			}
			if (x == 33) {
				c = c + "X";
			}
			if (x == 34) {
				c = c + "Y";
			}
			if (x == 35) {
				c = c + "Z";
			}
			if (x == 36) {
				c = c + "a";
			}
			if (x == 37) {
				c = c + "b";
			}
			if (x == 38) {
				c = c + "c";
			}
			if (x == 39) {
				c = c + "d";
			}
			if (x == 40) {
				c = c + "e";
			}
			if (x == 41) {
				c = c + "f";
			}
			if (x == 42) {
				c = c + "g";
			}
			if (x == 43) {
				c = c + "h";
			}
			if (x == 44) {
				c = c + "i";
			}
			if (x == 45) {
				c = c + "j";
			}
			if (x == 46) {
				c = c + "k";
			}
			if (x == 47) {
				c = c + "l";
			}
			if (x == 48) {
				c = c + "m";
			}
			if (x == 49) {
				c = c + "n";
			}
			if (x == 50) {
				c = c + "o";
			}
			if (x == 51) {
				c = c + "p";
			}
			if (x == 52) {
				c = c + "q";
			}
			if (x == 53) {
				c = c + "r";
			}
			if (x == 54) {
				c = c + "s";
			}
			if (x == 55) {
				c = c + "t";
			}
			if (x == 56) {
				c = c + "u";
			}
			if (x == 57) {
				c = c + "v";
			}
			if (x == 58) {
				c = c + "w";
			}
			if (x == 59) {
				c = c + "x";
			}
			if (x == 60) {
				c = c + "y";
			}
			if (x == 61) {
				c = c + "z";
			}
		}
		return c;
	}
}
