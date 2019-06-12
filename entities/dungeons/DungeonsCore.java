package me.askingg.mayhem.entities.dungeons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;

public class DungeonsCore {

	public static Integer countDungeon1;
	public static Integer countDungeon2;
	public static List<Location> locs1 = new ArrayList<Location>();
	public static List<Location> locs2 = new ArrayList<Location>();

	public static void setupDungeons() {
		World w = Bukkit.getWorld("world");
		locs1.add(new Location(w, 489.5, 58, -137.5));// w, (489.5 + xx), 58, (-137.5 + zz)
		locs1.add(new Location(w, 499.5, 57, -138.5));// w, (499.5 + xx), 57, (-138.5 + zz)
		locs1.add(new Location(w, 511.5, 64, -150.5));// w, (511.5 + xx), 64, (-150.5 + zz)
		locs1.add(new Location(w, 509.5, 61, -132.5));// w, (509.5 + xx), 61, (-132.5 + zz)
		locs1.add(new Location(w, 506.5, 57, -130.5));// w, (506.5 + xx), 57, (-130.5 + zz)
		locs1.add(new Location(w, 501.5, 57, -114.5));// w, (501.5 + xx), 57, (-114.5 + zz)
		locs1.add(new Location(w, 497.5, 58, -126.5));// w, (497.5 + xx), 58, (-126.5 + zz)
		locs1.add(new Location(w, 490.5, 58, -119.5));// w, (490.5 + xx), 58, (-119.5 + zz)

		locs2.add(new Location(w, -38.5, 38, -54.5));
		locs2.add(new Location(w, -18.5, 42, -1.5));
		locs2.add(new Location(w, -40.5, 49, -24.5));
		locs2.add(new Location(w, -51.5, 51, -31.5));
		locs2.add(new Location(w, -55.5, 38, 8.5));
		locs2.add(new Location(w, -78.5, 40, 26.5));
		locs2.add(new Location(w, -73.5, 39, 36.5));
		locs2.add(new Location(w, -84.5, 38, 16.5));
		locs2.add(new Location(w, -57.5, 38, 14.5));
		locs2.add(new Location(w, -91.5, 54, -2.5));
		for (LivingEntity e : w.getLivingEntities()) {
			if (!(e instanceof Player)) {
				if (e instanceof Zombie || e instanceof Skeleton) {
					if (e.getCustomName() != null && e.getCustomName().contains(Format.color("&4Tier I "))) {
						e.damage(1000);
					}
					if (e.getCustomName() != null && e.getCustomName().contains(Format.color("&4Tier II "))) {
						e.damage(1000);
					}
				}
			}
		}
		spawnMobs("Dungeon2", 2);
		spawnMobs("Dungeon2", 2);
		spawnMobs("Dungeon2", 2);
		spawnMobs("Dungeon2", 2);
		spawnMobs("Dungeon2", 2);
		spawnMobs("Dungeon2", 2);
		spawnMobs("Dungeon2", 2);
		spawnMobs("Dungeon2", 2);
		countDungeon2 = 40;
		spawnMobs("Dungeon1", 1);
		spawnMobs("Dungeon1", 1);
		spawnMobs("Dungeon1", 1);
		spawnMobs("Dungeon1", 1);
		spawnMobs("Dungeon1", 1);
		countDungeon1 = 25;
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				Integer i = 0;
				Integer ii = 0;
				for (LivingEntity e : w.getLivingEntities()) {
					if (!(e instanceof Player)) {
						if (e instanceof Zombie || e instanceof Skeleton) {
							if (e.getCustomName() != null && e.getCustomName().contains(Format.color("&4Tier I "))) {
								i++;
							}
							if (e.getCustomName() != null && e.getCustomName().contains(Format.color("&4Tier II "))) {
								ii++;
							}
						}
					}
				}
				if (i <= 5) {
					spawnMobs("Dungeon1", 1);
					spawnMobs("Dungeon1", 1);
					spawnMobs("Dungeon1", 1);
					spawnMobs("Dungeon1", 1);
					spawnMobs("Dungeon1", 1);
					countDungeon1 = countDungeon1 + 20;
				}
				if (ii <= 5) {
					spawnMobs("Dungeon2", 2);
					spawnMobs("Dungeon2", 2);
					spawnMobs("Dungeon2", 2);
					spawnMobs("Dungeon2", 2);
					spawnMobs("Dungeon2", 2);
					spawnMobs("Dungeon2", 2);
					spawnMobs("Dungeon2", 2);
					countDungeon2 = countDungeon2 + 35;
				}
			}
		}, 1000, 1000);

	}

	public static Integer getFlesh(Player p) {
		return Files.data.getInt("Users." + p.getUniqueId() + ".Flesh");
	}

	public static void removeFlesh(Player p, Integer x) {
		Files.data.set("Users." + p.getUniqueId() + ".Flesh",
				Files.data.getInt("Users." + p.getUniqueId() + ".Flesh") - x);
		try {
			Files.data.save(Files.dataFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void spawnMobs(String str, Integer i) {
		Random r = new Random();
		World w = Bukkit.getWorld("world");
		if (str.equalsIgnoreCase("Dungeon1")) {
			Double xx;
			Double zz;
			Integer count = 0;
			while (count < 5) {
				Integer x = r.nextInt(locs1.size());
				Integer y = r.nextInt(8) + 1;
				count++;
				xx = (Math.random() * 4) - 2;
				zz = (Math.random() * 4) - 2;
				Location l = new Location(Bukkit.getWorld("w"), (locs1.get(x).getX() + xx), locs1.get(x).getY(),
						(locs1.get(x).getZ() + zz));
				if (y <= 7) {
					Zombie z = (Zombie) w.spawnEntity(l, EntityType.ZOMBIE);
					z.setCustomName(Format.color("&4Tier I &cZombie"));
					z.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
					z.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
					z.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
					z.getEquipment().setItemInMainHandDropChance(0);
					z.getEquipment().setLeggingsDropChance(0);
					z.getEquipment().setBootsDropChance(0);
				}
				if (y == 8) {
					Skeleton s = (Skeleton) w.spawnEntity(l, EntityType.SKELETON);
					s.setCustomName(Format.color("&4Tier I &cSkeleton"));
					ItemStack bow = new ItemStack(Material.BOW);
					ItemMeta m = bow.getItemMeta();
					m.addEnchant(Enchantment.ARROW_DAMAGE, 2, true);
					bow.setItemMeta(m);
					s.getEquipment().setItemInMainHand(new ItemStack(bow));
					s.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
					s.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
					s.getEquipment().setItemInMainHandDropChance(0);
					s.getEquipment().setLeggingsDropChance(0);
					s.getEquipment().setBootsDropChance(0);
				}
			}
		}
		if (str.equalsIgnoreCase("Dungeon2")) {
			Double xx;
			Double zz;
			Integer count = 0;
			while (count < 5) {
				Integer x = r.nextInt(locs2.size());
				Integer y = r.nextInt(8) + 1;
				count++;
				xx = (Math.random() * 4) - 2;
				zz = (Math.random() * 4) - 2;
				Location l = new Location(Bukkit.getWorld("w"), (locs2.get(x).getX() + xx), locs2.get(x).getY(),
						(locs2.get(x).getZ() + zz));
				if (y <= 7) {
					Zombie z = (Zombie) w.spawnEntity(l, EntityType.ZOMBIE);
					z.setCustomName(Format.color("&4Tier II &cZombie"));
					ItemStack sw = new ItemStack(Material.IRON_SWORD);
					ItemMeta swm = sw.getItemMeta();
					swm.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
					sw.setItemMeta(swm);
					z.getEquipment().setItemInMainHand(new ItemStack(sw));
					ItemStack le = new ItemStack(Material.DIAMOND_LEGGINGS);
					ItemMeta lem = le.getItemMeta();
					lem.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
					le.setItemMeta(lem);
					z.getEquipment().setLeggings(new ItemStack(le));
					ItemStack bo = new ItemStack(Material.GOLD_BOOTS);
					ItemMeta bom = bo.getItemMeta();
					bom.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
					bo.setItemMeta(bom);
					z.getEquipment().setBoots(bo);
					z.getEquipment().setItemInMainHandDropChance(0);
					z.getEquipment().setLeggingsDropChance(0);
					z.getEquipment().setBootsDropChance(0);
				}
				if (y == 8) {
					Skeleton s = (Skeleton) w.spawnEntity(l, EntityType.SKELETON);
					s.setCustomName(Format.color("&4Tier II &cSkeleton"));
					ItemStack bow = new ItemStack(Material.BOW);
					ItemMeta m = bow.getItemMeta();
					m.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
					bow.setItemMeta(m);
					s.getEquipment().setItemInMainHand(new ItemStack(bow));
					ItemStack le = new ItemStack(Material.GOLD_LEGGINGS);
					ItemMeta lem = le.getItemMeta();
					lem.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
					le.setItemMeta(lem);
					s.getEquipment().setLeggings(new ItemStack(le));
					ItemStack bo = new ItemStack(Material.DIAMOND_BOOTS);
					ItemMeta bom = bo.getItemMeta();
					bom.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
					bo.setItemMeta(bom);
					s.getEquipment().setBoots(new ItemStack(bo));
					s.getEquipment().setItemInMainHandDropChance(0);
					s.getEquipment().setLeggingsDropChance(0);
					s.getEquipment().setBootsDropChance(0);
				}
			}
		}
	}
	// Zombie z = (Zombie) w.spawnEntity(l, EntityType.ZOMBIE);
	// z.setCustomName(Format.color("&4Tier II &cZombie"));
	// ItemStack sw = new ItemStack(Material.IRON_SWORD);
	// ItemMeta swm = sw.getItemMeta();
	// swm.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
	// sw.setItemMeta(swm);
	// z.getEquipment().setItemInMainHand(new ItemStack(sw));
	// ItemStack le = new ItemStack(Material.DIAMOND_LEGGINGS);
	// ItemMeta lem = le.getItemMeta();
	// lem.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
	// le.setItemMeta(lem);
	// z.getEquipment().setLeggings(new ItemStack(le));
	// ItemStack bo = new ItemStack(Material.GOLD_BOOTS);
	// ItemMeta bom = bo.getItemMeta();
	// bom.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
	// bo.setItemMeta(bom);
	// z.getEquipment().setBoots(bo);
	// z.getEquipment().setItemInMainHandDropChance(0);
	// z.getEquipment().setLeggingsDropChance(0);
	// z.getEquipment().setBootsDropChance(0);
}
