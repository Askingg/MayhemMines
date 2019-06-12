package me.askingg.mayhem.giveaway;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.miningrewards.RewardsCore;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class GiveawayCore {

	public static Boolean give = false;
	
	public static void giveaway(Player p, ItemStack i) {
		for (Player pl : Bukkit.getOnlinePlayers()) {
			Format.item(pl, i, "&8&l[&9&lGiveaway&8&l]&b " + p.getName() + "&7 is giving away an item &8(&7hover&8)");
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				Random r = new Random();
				List<Player> online = new ArrayList<Player>();
				for (Player pl : Bukkit.getOnlinePlayers()) {
					online.add(pl);
				}
				int x = r.nextInt(Bukkit.getOnlinePlayers().size());
				Player pl = online.get(x);
				if (pl.getInventory().firstEmpty() != -1) {
					pl.getInventory().addItem(i);
					pl.updateInventory();
				} else {
					RewardsCore.setReward(p, i);
					Message.player("Your inventory was full so the item was added to your &b/Rewards", p);
				}
				for (Player pla : Bukkit.getOnlinePlayers()) {
					Format.item(pla, i, "&8&l[&9&lGiveaway&8&l]&b " + pl.getName() + "&7 just won the giveaway &8(&7hover&8)");
					GiveawayCommand.handConfirm.remove(p);
				}
			}
		}, 100);
	}
}
