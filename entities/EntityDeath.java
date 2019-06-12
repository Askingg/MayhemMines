package me.askingg.mayhem.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.SlimeSplitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.enchant.sword.CECore;
import me.askingg.mayhem.entities.bosses.BossCore;
import me.askingg.mayhem.entities.dungeons.DungeonsCore;
import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.tokens.TokensCore;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class EntityDeath implements Listener {

	@EventHandler
	public void death(EntityDeathEvent e) {
		LivingEntity ent = e.getEntity();
		if (!(ent instanceof Player)) {
			if (ent.getName().contains(Format.color("&4Tier I "))) {
				Random r = new Random();
				e.getDrops().clear();
				World w = Bukkit.getWorld("world");
				Integer x = 0;
				for (LivingEntity enti : w.getLivingEntities()) {
					if (!(enti instanceof Player)) {
						if (enti instanceof Zombie || enti instanceof Skeleton) {
							if (enti.getCustomName() != null
									&& enti.getCustomName().contains(Format.color("&4Tier I "))) {
								x++;
							}
						}
					}
				}
				if (x <= 5) {
					DungeonsCore.spawnMobs("Dungeon1", 1);
					DungeonsCore.spawnMobs("Dungeon1", 1);
					DungeonsCore.spawnMobs("Dungeon1", 1);
					DungeonsCore.spawnMobs("Dungeon1", 1);
					DungeonsCore.spawnMobs("Dungeon1", 1);
					DungeonsCore.countDungeon1 = DungeonsCore.countDungeon1 + 20;
				}
				ItemStack i = new ItemStack(Material.ROTTEN_FLESH);
				i.setAmount(r.nextInt(3));
				ItemMeta m = i.getItemMeta();
				List<String> l = new ArrayList<String>();
				m.setDisplayName(Format.color("&cDecayed Flesh"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Used to obtain unique enchantments"));
				m.setLore(l);
				i.setItemMeta(m);
				if (ent.getKiller() != null) {
					Player p = (Player) ent.getKiller();
					if (CECore.level(p.getInventory().getItemInMainHand(), "Vaccum") > 0) {
						if (p.getInventory().firstEmpty() != -1) {
							p.getInventory().addItem(i);
							p.updateInventory();
						}
					} else {
						ent.getWorld().dropItemNaturally(ent.getLocation(), i);
					}
					Files.data.set("Users." + p.getUniqueId() + ".Kills",
							Files.data.getInt("Users." + p.getUniqueId() + ".Kills") + 1);
					try {
						Files.data.save(Files.dataFile);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					if (Files.data.getInt("Users." + p.getUniqueId() + ".Kills") > 500
							&& !p.hasPermission("essentials.warps.dungeon2")) {
						Message.player("You unlocked the second dungeon: &b/Warp Dungeon2", p);
						Main.perm.playerAdd(p, "essentials.warps.dungeon2");
					}
				}
				return;
			}
			if (ent.getName().contains(Format.color("&4Tier II "))) {
				Random r = new Random();
				e.getDrops().clear();
				World w = Bukkit.getWorld("world");
				Integer x = 0;
				for (LivingEntity enti : w.getLivingEntities()) {
					if (!(enti instanceof Player)) {
						if (enti instanceof Zombie || enti instanceof Skeleton) {
							if (enti.getCustomName() != null
									&& enti.getCustomName().contains(Format.color("&4Tier II "))) {
								x++;
							}
						}
					}
				}
				if (x <= 5) {
					DungeonsCore.spawnMobs("Dungeon2", 2);
					DungeonsCore.spawnMobs("Dungeon2", 2);
					DungeonsCore.spawnMobs("Dungeon2", 2);
					DungeonsCore.spawnMobs("Dungeon2", 2);
					DungeonsCore.spawnMobs("Dungeon2", 2);
					DungeonsCore.countDungeon2 = DungeonsCore.countDungeon2 + 20;
				}
				ItemStack i = new ItemStack(Material.ROTTEN_FLESH);
				i.setAmount(r.nextInt(6));
				ItemMeta m = i.getItemMeta();
				List<String> l = new ArrayList<String>();
				m.setDisplayName(Format.color("&cDecayed Flesh"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Used to obtain unique enchantments"));
				m.setLore(l);
				i.setItemMeta(m);
				if (ent.getKiller() != null) {
					Player p = (Player) ent.getKiller();
					if (CECore.level(p.getInventory().getItemInMainHand(), "Vaccum") > 0) {
						if (p.getInventory().firstEmpty() != -1) {
							p.getInventory().addItem(i);
							p.updateInventory();
						}
					} else {
						ent.getWorld().dropItemNaturally(ent.getLocation(), i);
					}
					Files.data.set("Users." + p.getUniqueId() + ".Kills",
							Files.data.getInt("Users." + p.getUniqueId() + ".Kills") + 1);
					try {
						Files.data.save(Files.dataFile);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					if (Files.data.getInt("Users." + p.getUniqueId() + ".Kills") > 500
							&& !p.hasPermission("essentials.warps.dungeon2")) {
						Message.player("You unlocked the second dungeon: &b/Warp Dungeon2", p);
						Main.perm.playerAdd(p, "essentials.warps.dungeon2");
					}
				}
				return;
			}
			if (ent.getName().equals(Format.color("&c&l&ki&4&lMagma &f&lLord&c&l&ki"))) {
				BossCore.magmaSpawnTime = System.currentTimeMillis();
				BossCore.magmaAlive = false;
				Random r = new Random();
				Message.broadcast("&7The &4&lMagma&f&lLord&7 has been eliminated by &b" + ent.getKiller().getName());
				e.getDrops().clear();
				double x = r.nextDouble();
				int t = (r.nextInt(25000000 - 2500000) + 2500000);
				if (x < 0.05) {
					t = (r.nextInt(250000000 - 50000000) + 50000000);
				}
				if (x < 0.075) {
					t = (r.nextInt(150000000 - 10000000) + 10000000);
				}
				if (x < 0.1) {
					t = (r.nextInt(50000000 - 5000000) + 5000000);
				}
				ent.getLocation().getWorld().dropItemNaturally(ent.getLocation(), TokensCore.tokenStack(t));
				ItemStack i = new ItemStack(Material.AIR);
				ItemMeta m = i.getItemMeta();
				List<String> l = new ArrayList<String>();
				x = r.nextDouble();
				if (x < 0.05) {
					i = new ItemStack(Material.MAGMA_CREAM);
					m = i.getItemMeta();
					m.addEnchant(Enchantment.DAMAGE_ALL, 55, true);
					m.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
					m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					m.setDisplayName(Format.color("&cThe &4&lMagma&f&lLord's &cheart"));
					l.add(Format.color("&7"));
					l.add(Format.color("&8 ● &cA legendary item, dropped by"));
					l.add(Format.color("&c &c &c the &4&lMagma&f&lLord"));
					l.add(Format.color("&8 ● &4Sharpness &8/ &455"));
					l.add(Format.color("&8 ● &4FireAspect &8/ &45"));
					m.setLore(l);
					i.setItemMeta(m);
					ent.getLocation().getWorld().dropItemNaturally(ent.getLocation(), i);
				}
				x = r.nextDouble();
				if (x < 0.7) { // Grade 1 token
					i = new ItemStack(Material.FIREWORK_CHARGE);
					m = i.getItemMeta();
					m.setDisplayName(Format.color("&8-=[&7&ki&f Grade 1 Boss Token &7&ki&8]=-"));
					m.addEnchant(Enchantment.LUCK, 0, true);
					m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					m.setLore(Arrays.asList(Format.color("&7A token of the bosses")));
					i.setItemMeta(m);
					Integer a = r.nextInt(7) + 1;
					i.setAmount(a);
					e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), i);
				}
				x = r.nextDouble();
				if (x < 0.3) { // Grade 2 token
					i = new ItemStack(Material.EYE_OF_ENDER);
					m = i.getItemMeta();
					m.setDisplayName(Format.color("&8-=[&b&ki&b Grade 2 Boss Token &3&ki&8]=-"));
					m.addEnchant(Enchantment.LUCK, 0, true);
					m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					m.setLore(Arrays.asList(Format.color("&7A token of the bosses")));
					i.setItemMeta(m);
					Integer a = r.nextInt(4) + 1;
					i.setAmount(a);
					e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), i);
				}
				x = r.nextDouble();
				if (x < 0.1) { // Grade 3 token
					i = new ItemStack(Material.FIREBALL);
					m = i.getItemMeta();
					m.setDisplayName(Format.color("&8-=[&4&ki&c Grade 3 Boss Token &4&ki&8]=-"));
					m.addEnchant(Enchantment.LUCK, 0, true);
					m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					m.setLore(Arrays.asList(Format.color("&7A token of the bosses")));
					i.setItemMeta(m);
					Integer a = r.nextInt(2) + 1;
					i.setAmount(a);
					e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), i);
				}
				Integer ax = (r.nextInt(20)) + 1;
				World w = ent.getWorld();
				Location loc = ent.getLocation();
				Message.broadcast("&b" + ax + "&3&l Loot &f&lMinions&7 have emerged");
				while (ax > 0) {
					int rl = r.nextInt(11) - 5;
					int rl2 = r.nextInt(11) - 5;
					double ep = r.nextDouble();
					Location locat = new Location(w, (loc.getX() + rl), loc.getY(), (loc.getZ() + rl2));
					if (ep < 0.35) {
						PigZombie zo = (PigZombie) w.spawnEntity(locat, EntityType.PIG_ZOMBIE);
						zo.setCustomName(Format.color("&c&ki&c&lEpic Loot &f&lMinion&b&ki"));
						zo.setCustomNameVisible(true);
						zo.setBaby(true);
						zo.getEquipment().setHelmet(new ItemStack(Material.ENDER_CHEST));
						zo.getEquipment().setHelmetDropChance((float) 0);
						zo.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
					} else {
						PigZombie zo = (PigZombie) w.spawnEntity(locat, EntityType.PIG_ZOMBIE);
						zo.setCustomName(Format.color("&b&ki&3&lLoot &f&lMinion&b&ki"));
						zo.setCustomNameVisible(true);
						zo.setBaby(true);
						zo.getEquipment().setHelmet(new ItemStack(Material.CHEST));
						zo.getEquipment().setHelmetDropChance((float) 0);
						zo.getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
					}
					ax--;
				}
			}
			if (ent.getCustomName().equals(Format.color("&b&ki&3&lLoot &f&lMinion&b&ki"))) {
				e.getDrops().clear();
				Random r = new Random();
				double x = r.nextDouble();
				if (x <= 0.75) {
					int t = (r.nextInt(250000 - 25000) + 25000);
					if (x < 0.05) {
						t = (r.nextInt(2500000 - 500000) + 500000);
					}
					if (x < 0.075) {
						t = (r.nextInt(1500000 - 100000) + 100000);
					}
					if (x < 0.1) {
						t = (r.nextInt(500000 - 50000) + 50000);
					}
					ent.getLocation().getWorld().dropItemNaturally(ent.getLocation(), TokensCore.tokenStack(t));
				}
			}
			if (ent.getCustomName().equals(Format.color("&c&ki&c&lEpic Loot &f&lMinion&b&ki"))) {
				e.getDrops().clear();
				Random r = new Random();
				double x = r.nextDouble();
				if (x <= 0.5) {
					int t = (r.nextInt(500000 - 50000) + 50000);
					if (x < 0.05) {
						t = (r.nextInt(5000000 - 1000000) + 1000000);
					}
					if (x < 0.075) {
						t = (r.nextInt(3000000 - 200000) + 200000);
					}
					if (x < 0.1) {
						t = (r.nextInt(1000000 - 100000) + 100000);
					}
					ent.getLocation().getWorld().dropItemNaturally(ent.getLocation(), TokensCore.tokenStack(t));
				}
			}
			if (ent.getCustomName().equals(Format.color("&c&lMagma &7&lMinion"))) {
				e.getDrops().clear();
				Random r = new Random();
				double x = r.nextDouble();
				if (x <= 0.5) {
					int t = (r.nextInt(50000 - 5000) + 5000);
					if (x < 0.05) {
						t = (r.nextInt(500000 - 100000) + 100000);
					}
					if (x < 0.075) {
						t = (r.nextInt(300000 - 20000) + 20000);
					}
					if (x < 0.1) {
						t = (r.nextInt(100000 - 10000) + 10000);
					}
					ent.getLocation().getWorld().dropItemNaturally(ent.getLocation(), TokensCore.tokenStack(t));
				}
			}
		} else {
			if (ent.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
				if (ent.getLastDamageCause().getEntity().getName()
						.equals(Format.color("&c&l&ki&4&lMagma &f&lLord&c&l&ki"))) {
					Message.broadcast("&c" + ent.getName() + "&7 was burnt to smithereens by the &4&lMagma&f&lLord");
				}
			}
		}
	}

	@EventHandler
	public void target(EntityTargetLivingEntityEvent e) {
		if (!(e.getTarget() instanceof Player)) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void split(SlimeSplitEvent e) {
		if (e.getEntity().getName().equals(Format.color("&c&l&ki&4&lMagma &f&lLord&c&l&ki"))
				|| e.getEntity().getName().equals(Format.color("&c&lMagma &7&lMinion"))) {
			e.setCancelled(true);
		}
	}
}
