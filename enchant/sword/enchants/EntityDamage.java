package me.askingg.mayhem.enchant.sword.enchants;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import me.askingg.mayhem.enchant.sword.CECore;

public class EntityDamage implements org.bukkit.event.Listener {

	@EventHandler
	public void damage(EntityDamageByEntityEvent e) {
		Entity en = e.getEntity();
		Entity da = e.getDamager();
		if (da instanceof Player) {
			Player p = (Player) da;
			if (p.getInventory().getItemInMainHand() != null) {
				ItemStack i = p.getInventory().getItemInMainHand();
				if (i.getType().toString().contains("SWORD")) {
					if (CECore.level(i, "Splash") > 0) {
						int lvl = CECore.level(i, "Splash");
						World w = en.getWorld();
						for (Entity ent : w.getNearbyEntities(en.getLocation(), lvl, 3, lvl)) {
							if (!(ent instanceof Player)) {
								if (ent instanceof LivingEntity) {
									((LivingEntity) ent).damage(e.getDamage() / 7);
								}
							}
						}
					}
				}
				if (CECore.level(i, "Quake") > 0) {
					int lvl = CECore.level(i, "Quake");
					World w = en.getWorld();
					for (Entity ent : w.getNearbyEntities(en.getLocation(), lvl, 3, lvl)) {
						if (!(ent instanceof Player)) {
							if (ent != da) {
								ent.setVelocity(ent.getLocation().getDirection().multiply(3));
								ent.setVelocity(new Vector(ent.getVelocity().getX(), 1.0D, ent.getVelocity().getZ()));
							}
						}
					}
				}
				if (CECore.level(i, "Leach") > 0) {
					Random r = new Random();
					double d = r.nextDouble();
					if (d <= (CECore.level(i, "Leach") * 0.025)) {
						if (p.getHealth() + (e.getDamage() / 5) > 20) {
							p.setHealth(20.0);
						} else {
							p.setHealth(p.getHealth() + (e.getDamage() / 5));
						}
					}
				}
			}
		}
	}
}
