package me.askingg.mayhem.entities;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class EntityDamage implements org.bukkit.event.Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void damage(EntityDamageByEntityEvent e) {
		Entity ent = e.getEntity();
		if (ent instanceof Player) {
			Entity ent2 = e.getDamager();
			if (ent2.getCustomName() != null) {
				if (ent2.getCustomName().equals(Format.color("&c&l&ki&4&lMagma &f&lLord&c&l&ki"))) {
					e.setDamage(e.getDamage() * 8);
				}
				if (ent2.getCustomName().equals(Format.color("&c&lMagma &7&lMinion"))) {
					e.setDamage(e.getDamage() * 4);
				}
			}
		}
		if (ent.getCustomName() != null) {
			if (ent.getCustomName().equals(Format.color("&c&l&ki&4&lMagma &f&lLord&c&l&ki"))) {
				Random r = new Random();
				double d = r.nextDouble();
				if (d <= 0.075) {
					int ax = (r.nextInt(10)) + 1;
					World w = ent.getWorld();
					Location loc = ent.getLocation();
					Message.broadcast("&b" + ax + "&c&l Magma &7&lMinions&7 have emerged");
					while (ax > 0) {
						int rl = r.nextInt(11) - 5;
						int rl2 = r.nextInt(11) - 5;
						Location locat = new Location(w, (loc.getX() + rl), loc.getY(), (loc.getZ() + rl2));
						MagmaCube ma = (MagmaCube) w.spawnEntity(locat, EntityType.MAGMA_CUBE);
						ma.setSize(2);
						ma.setCustomName(Format.color("&c&lMagma &7&lMinion"));
						ma.setCustomNameVisible(true);
						ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
						ItemMeta m = i.getItemMeta();
						m.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
						m.addEnchant(Enchantment.KNOCKBACK, 5, true);
						m.addEnchant(Enchantment.FIRE_ASPECT, 3, true);
						ma.getEquipment().setItemInMainHand(i);
						ma.getEquipment().setItemInMainHandDropChance((float) 0);
						((Damageable) ma).setMaxHealth(100);
						ma.setHealth(100);
						ax--;
					}
				}
			}
		}
	}
}
