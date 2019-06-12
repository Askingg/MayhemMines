package me.askingg.mayhem.events;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import com.shampaggon.crackshot.events.WeaponDamageEntityEvent;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Format;

public class EntityDamage implements Listener {

	@EventHandler
	public void damage(EntityDamageEvent e) {
		if ((e.getEntity() instanceof Player) && e.getCause() == DamageCause.FALL || e.getCause() == DamageCause.LAVA
				|| e.getCause() == DamageCause.SUFFOCATION || e.getCause() == DamageCause.HOT_FLOOR
				|| e.getCause() == DamageCause.FIRE || e.getCause() == DamageCause.FIRE_TICK) {
			e.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if (event.getEntityType() != EntityType.PLAYER)
			return;
		event.setCancelled(true);
	}

	@EventHandler
	public void damage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Location l = e.getEntity().getLocation();
			l.setY(l.getY() + 2);
			ArmorStand as = (ArmorStand) l.getWorld().spawn(l, ArmorStand.class);
			as.setVisible(false);
			as.setSmall(true);
			as.setMarker(true);
			as.setGravity(false);
			as.setCustomName(Format.color("&c- " + Format.decimals(1, e.getFinalDamage())));
			as.setCustomNameVisible(true);
			Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
				public void run() {
					as.setGravity(true);
				}
			}, 20);
			Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
				public void run() {
					as.remove();
				}
			}, 30);
		}
	}

	@EventHandler
	public void damageByGun(WeaponDamageEntityEvent e) {
		Location l = e.getVictim().getLocation();
		l.setY(l.getY() + 2);
		ArmorStand as = (ArmorStand) l.getWorld().spawn(l, ArmorStand.class);
		as.setVisible(false);
		as.setSmall(true);
		as.setMarker(true);
		as.setGravity(false);
		if (e.isHeadshot()) {
			as.setCustomName(Format.color("&6- " + Format.decimals(1, e.getDamage())));
		} else {
			as.setCustomName(Format.color("&f- " + Format.decimals(1, e.getDamage())));
		}
		as.setCustomNameVisible(true);
		Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				as.setGravity(true);
			}
		}, 20);
		Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				as.remove();
			}
		}, 30);

	}
}
