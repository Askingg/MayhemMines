package me.askingg.mayhem.enchant.sword.enchants;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.askingg.mayhem.enchant.sword.CECore;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class PlayerInteract implements Listener {

	public static HashMap<Player, Long> leap = new HashMap<Player, Long>();

	@EventHandler
	public void interact(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getInventory().getItemInMainHand() != null
					&& p.getInventory().getItemInMainHand().getType().toString().contains("SWORD")) {
				ItemStack i = p.getInventory().getItemInMainHand();
				if (CECore.level(i, "Leap") > 0) {
					if (leap.containsKey(p)) {
						if ((System.currentTimeMillis() >= (leap.get(p) + 5000))) {
							p.setVelocity(p.getLocation().getDirection().multiply(CECore.level(i, "Leap")));
							p.setVelocity(new Vector(p.getVelocity().getX(), 1.0D, p.getVelocity().getZ()));
							leap.put(p, System.currentTimeMillis());
							return;
						} else {
							Message.player(
									"You cannot use Leap for another &c" + Format.time(
											(int) Math.round((leap.get(p) + 5000) - System.currentTimeMillis()) / 1000),
									p);
							return;
						}
					}
					p.setVelocity(p.getLocation().getDirection().multiply(CECore.level(i, "Leap")));
					p.setVelocity(new Vector(p.getVelocity().getX(), 1.0D, p.getVelocity().getZ()));
					leap.put(p, System.currentTimeMillis());
					return;
				}
			}
		}
	}
}
