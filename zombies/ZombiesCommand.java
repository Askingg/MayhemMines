package me.askingg.mayhem.zombies;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.askingg.mayhem.utils.Message;

public class ZombiesCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.getName().equals("Askingg")) {
			if (args.length == 0) {
				Message.senderRaw("&8&m+----------&8( &3&lCoD&c&lZombies&b&lCommands&8 )&m---------+", sender);
				Message.senderRaw("&8 ● &b/Zombies &7View the help list", sender);
				Message.senderRaw("&8 ● &b/Zombies Test &7Development command", sender);
				Message.footer(sender);
			} else {
				if (args[0].equalsIgnoreCase("test")) {

					// START GAME TESTING
					if (sender instanceof Player) {
						// Player p = (Player) sender;
						List<Player> l = new ArrayList<Player>();
						for (Player pl : Bukkit.getOnlinePlayers()) {
							l.add(pl);
						}
						if (!ZombiesCore.gameRunning) {
							ZombiesCore.startGame(l, "testing");
						} else {
							// END GAME
						}
					}

					// WORLDEDIT SET BLOCKS
					// if (sender instanceof Player) {
					// Player p = (Player) sender;
					// Selection s = Main.getWE().getSelection(p);
					// if (s == null) {
					// Message.sender("Make a selection first", sender);
					// return true;
					// }
					// CuboidRegion c = new CuboidRegion(BukkitUtil.getLocalWorld(s.getWorld()),
					// s.getNativeMaximumPoint(), s.getNativeMinimumPoint());
					// EditSession sesh = new EditSession(BukkitUtil.getLocalWorld(s.getWorld()),
					// c.getArea());
					// try {
					// sesh.setBlocks(c, new BaseBlock(0));
					// sesh.flushQueue();
					// Message.player("Set all of the blocks", p);
					// } catch (MaxChangedBlocksException ex) {
					// ex.printStackTrace();
					// }
					// }

				}
			}
		} else {
			Message.sender("Sorry, but this feature is currently under development", sender);
			return true;
		}
		return false;
	}

}
