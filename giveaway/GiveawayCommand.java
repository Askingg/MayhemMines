package me.askingg.mayhem.giveaway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.askingg.mayhem.miningrewards.RewardsCore;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class GiveawayCommand implements CommandExecutor {

	public static HashMap<Player, ItemStack> handConfirm = new HashMap<Player, ItemStack>();

	@Override
	public boolean onCommand(CommandSender sender, Command cnd, String label, String[] args) {
		if (args.length == 0) {
			Message.senderRaw("&8&m+------------&8( &c&lGiveaway&b&lCommands&8 )&m-----------+", sender);
			Message.senderRaw("&8 ● &b/GA &7View the help list", sender);
			Message.senderRaw("&8 ● &b/GA Hand &7Giveaway the item in your hand", sender);
			Message.footer(sender);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("hand")) { // GA Hand
				if (sender instanceof Player && sender.hasPermission("mayhem.giveaway.hand")) {
					Player p = (Player) sender;
					if (GiveawayCore.give) {
						Message.player("Sorry, but there is currently a giveaway active", p);
						return true;
					}
					if (Bukkit.getOnlinePlayers().size() <= 3) {
						Message.player("Sorry, but there must be atleast &c4&7 people online to run a giveaway", p);
					}
					ItemStack i = p.getInventory().getItemInMainHand();
					if (handConfirm.containsKey(p)) {
						if (handConfirm.get(p).equals(i)) {
							Random r = new Random();
							List<Player> online = new ArrayList<Player>();
							for (Player pl : Bukkit.getOnlinePlayers()) {
								online.add(pl);
							}
							online.remove(p);
							int x = r.nextInt(Bukkit.getOnlinePlayers().size());
							Player pl = online.get(x);
							if (pl.getInventory().firstEmpty() != -1) {
								pl.getInventory().addItem(i);
								pl.updateInventory();
							} else {
								RewardsCore.setReward(pl, i);
								Message.player("Your inventory was full so the item was added to your &b/Rewards", p);
							}
							for (Player pla : Bukkit.getOnlinePlayers()) {
								Format.item(pla, i, "&8&l[&9&lGiveaway&8&l]&b " + p.getName() + "&7 is hosting a giveaway &8(&7hover&8) &ax" + i.getAmount());
								Format.item(pla, i, "&8&l[&9&lGiveaway&8&l]&b " + pl.getName() + "&7 won the giveaway &8(&7hover&8) &ax" + i.getAmount());
							}
							p.getInventory().getItemInMainHand().setAmount(0);
							p.updateInventory();
							return true;
						} else {
							Message.player("Sorry, but you are now holding a different item than you were previously.",
									p);
							handConfirm.remove(p);
							return true;
						}
					} else {
						if (i != null && i.getType() != Material.AIR) {
							handConfirm.put(p, i);
							Message.player("Are you sure that you want to giveaway your &b"
									+ i.getType().toString().toLowerCase()
									+ "&7 to a random online player: execute &b/ga hand&7 once more", p);
							return true;
						}
					}
				} else {
					Message.sender("Sorry, but you don't have permission to do this", sender);
					return true;
				}
			}
		}
		return false;
	}
}
