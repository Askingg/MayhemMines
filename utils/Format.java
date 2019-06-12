package me.askingg.mayhem.utils;

import java.text.NumberFormat;
import java.util.Locale;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.minecraft.server.v1_12_R1.NBTTagCompound;

public class Format {

	public static String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	public static String decimals(Integer decimalSpaces, Double number) {
		return String.format("%1$,." + decimalSpaces + "f", number);
	}
	
	public static String papi(Player p, String s) {
		return PlaceholderAPI.setPlaceholders((OfflinePlayer) p, s);
	}

	public static String number(double paramDouble) {
		if (paramDouble < 1000.0D) {
			return format(paramDouble);
		}
		if (paramDouble < 1000000.0D) {
			return format(paramDouble / 1000.0D) + "k";
		}
		if (paramDouble < 1.0E9D) {
			return format(paramDouble / 1000000.0D) + "M";
		}
		if (paramDouble < 1.0E12D) {
			return format(paramDouble / 1.0E9D) + "B";
		}
		if (paramDouble < 1.0E15D) {
			return format(paramDouble / 1.0E12D) + "T";
		}
		if (paramDouble < 1.0E18D) {
			return format(paramDouble / 1.0E15D) + "Q";
		}

		long l = (long) paramDouble;
		return String.valueOf(l);
	}
	
	public static String time(int seconds) {
	    if (seconds < 60) {
	        return seconds + "s";
	      }
	      int minutes = seconds / 60;
	      int s = 60 * minutes;
	      int secondsLeft = seconds - s;
	      if (minutes < 60) {
	        if (secondsLeft > 0) {
	          return String.valueOf(minutes + "m " + secondsLeft + "s");
	        }
	        return String.valueOf(minutes + "m");
	      }
	      if (minutes < 1440) {
	        String time = "";
	        int hours = minutes / 60;
	        time = hours + "h";
	        int inMins = 60 * hours;
	        int leftOver = minutes - inMins;
	        if (leftOver >= 1) {
	          time = time + " " + leftOver + "m";
	        }
	        if (secondsLeft > 0) {
	          time = time + " " + secondsLeft + "s";
	        }
	        return time;
	      }
	      String time = "";
	      int days = minutes / 1440;
	      time = days + "d";
	      int inMins = 1440 * days;
	      int leftOver = minutes - inMins;
	      if (leftOver >= 1) {
	        if (leftOver < 60) {
	          time = time + " " + leftOver + "m";
	        } else {
	          int hours = leftOver / 60;
	          time = time + " " + hours + "h";
	          int hoursInMins = 60 * hours;
	          int minsLeft = leftOver - hoursInMins;
	          if (leftOver >= 1) {
	            time = time + " " + minsLeft + "m";
	          }
	        }
	      }
	      if (secondsLeft > 0) {
	        time = time + " " + secondsLeft + "s";
	      }
	      return time;
	    }

	public static void hover(Player p, String chat, String click, String hover) {
		p.spigot()
				.sendMessage(new ComponentBuilder(color(chat))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(color(hover)).create()))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, click)).create());
	}

	public static void item(Player p, ItemStack i, String chat) {
		net.minecraft.server.v1_12_R1.ItemStack stack = CraftItemStack.asNMSCopy(i);
		NBTTagCompound tag = new NBTTagCompound();
		stack.save(tag);
		p.spigot()
				.sendMessage(new ComponentBuilder(color(chat)).event(
						new HoverEvent(HoverEvent.Action.SHOW_ITEM, new ComponentBuilder(tag.toString()).create()))
						.create());
	}

	public static void inventory(Player p, String chat, String click, String hover) {
		p.spigot()
				.sendMessage(new ComponentBuilder(color(chat))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(color(hover)).create()))
						.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, click)).create());
	}

	public static void poll(Player p, String qu, String chat1, String hover1, String click1, String chat2,
			String hover2, String click2) {
		Message.playerRaw("", p);
		p.spigot().sendMessage(new ComponentBuilder(color(qu))
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()))
				.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "")).append(color(chat1))
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(color(hover1)).create()))
				.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, click1)).append(color(chat2))
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(color(hover2)).create()))
				.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, click2)).create());
		Message.playerRaw("", p);
	}

	public static String format(double paramDouble) {
		NumberFormat localNumberFormat = NumberFormat.getInstance(Locale.ENGLISH);

		localNumberFormat.setMaximumFractionDigits(2);

		localNumberFormat.setMinimumFractionDigits(0);

		return localNumberFormat.format(paramDouble);
	}
}
