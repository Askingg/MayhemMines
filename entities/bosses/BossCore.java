package me.askingg.mayhem.entities.bosses;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class BossCore {

	public static boolean magmaAlive = false;
	public static long magmaSpawnTime = 0;
	public static int id1 = 0;

	public static void healthAlert(MagmaCube m) {
		id1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(me.askingg.mayhem.main.Main.instance, new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				if (magmaAlive) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (p.getWorld().getName().equals(m.getWorld().getName())) {
							if (p.getLocation().distance(m.getLocation()) <= 250) {
								Message.player("&4&lMagma&f&lLord &8- [&b" + Format.decimals(0, m.getHealth()) + "&8/&b"
										+ Format.decimals(0, m.getMaxHealth()) + "&8]", p);
							}
						}
					}
				} else {
					Bukkit.getScheduler().cancelTask(id1);
				}
			}
		}, 0, 100);
	}

	@SuppressWarnings("deprecation")
	public static void magmaLord(Location l) {
		magmaAlive = true;
		World w = l.getWorld();
		MagmaCube b = (MagmaCube) w.spawnEntity(l, EntityType.MAGMA_CUBE);
		b.setCustomName(Format.color("&c&l&ki&4&lMagma &f&lLord&c&l&ki"));
		b.setCustomNameVisible(true);
		b.setSize(5);
		ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta m = i.getItemMeta();
		m.addEnchant(Enchantment.DAMAGE_ALL, 50, true);
		m.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
		b.getEquipment().setItemInHand(i);
		b.getEquipment().setItemInHandDropChance((float) 0);
		i = new ItemStack(Material.DIAMOND_HELMET);
		m = i.getItemMeta();
		m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		m.setUnbreakable(true);
		i.setItemMeta(m);
		b.getEquipment().setHelmet(i);
		b.getEquipment().setHelmetDropChance((float) 0);
		i = new ItemStack(Material.DIAMOND_CHESTPLATE);
		m = i.getItemMeta();
		m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		m.setUnbreakable(true);
		i.setItemMeta(m);
		b.getEquipment().setChestplate(i);
		b.getEquipment().setChestplateDropChance((float) 0);
		i = new ItemStack(Material.DIAMOND_LEGGINGS);
		m = i.getItemMeta();
		m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		m.setUnbreakable(true);
		i.setItemMeta(m);
		b.getEquipment().setLeggings(i);
		b.getEquipment().setLeggingsDropChance((float) 0);
		i = new ItemStack(Material.DIAMOND_BOOTS);
		m = i.getItemMeta();
		m.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		m.setUnbreakable(true);
		i.setItemMeta(m);
		m.setUnbreakable(true);
		i.setItemMeta(m);
		b.getEquipment().setBoots(i);
		b.getEquipment().setBootsDropChance((float) 0);
		((Damageable) b).setMaxHealth(2000);
		b.setHealth(2000);
		Message.broadcast("&7The &4&lMagma&f&lLord&7 has awoken. Watch out");
		healthAlert(b);
	}
}
