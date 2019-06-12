package me.askingg.mayhem.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class canirestart implements CommandExecutor {
	private List<Player> voted = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			poll("&cPOLL&b Can I restart the server? ", "&2&l[&a&l✔&2&l&m]", "&aClick to answer 'Yes'",
					"/canirestart voteyes", "&4&l[&c&l✘&4&l]", "&cClick to answer 'No'", "/canirestart voteno");
		} else {
			if (args[0].equalsIgnoreCase("voteYes")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (!voted.contains(p)) {
						voted.add(p);
					}
					Message.broadcast("&c" + p.getName() + "&7 voted for &aYes");
					Message.broadcastRaw("&cRESULTS &bCan I restart the server? &a" + voted.size() + "&8/&c"
							+ Bukkit.getOnlinePlayers().size());
				}
			}
			if (args[0].equalsIgnoreCase("voteNo")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (voted.contains(p)) {
						voted.remove(p);
					}
					Message.broadcast("&c" + p.getName() + "&7 voted for &cNo");
					Message.broadcast("&cRESULTS &bCan I restart the server? &a" + voted.size() + "&8/&c"
							+ Bukkit.getOnlinePlayers().size());
				}
			}
		}
		return false;
	}

	private void poll(String qu, String chat1, String hover1, String click1, String chat2, String hover2,
			String click2) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			Message.playerRaw("", p);
			p.spigot()
					.sendMessage(new ComponentBuilder(Format.color(qu))
							.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("").create()))
							.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "")).append(Format.color(chat1))
							.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
									new ComponentBuilder(Format.color(hover1)).create()))
							.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, click1)).append(Format.color(chat2))
							.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
									new ComponentBuilder(Format.color(hover2)).create()))
							.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, click2)).create());
			Message.playerRaw("", p);
		}
	}
}
