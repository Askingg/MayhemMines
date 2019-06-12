package me.askingg.mayhem.entities.bosses;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.askingg.mayhem.utils.Format;

public class BossListGUI implements Listener {

	public static List<Player> open = new ArrayList<Player>();

	@SuppressWarnings("deprecation")
	public static Inventory menu(Player p) {
		p.getEyeLocation().getDirection();
		Inventory inv = Bukkit.createInventory(null, 27, Format.color("&c&lBosses"));
		borders(inv);
		// 8 = 4 5 = c 3 = a else b
		ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta m = (SkullMeta) i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setOwner(Bukkit.getOfflinePlayer("MHF_LavaSlime").getName());
		m.setDisplayName(Format.color("&c&l&ki&4&lMagma &f&lLord&c&l&ki"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &7Difficulty &4|||||||||&7|"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &7Damage &4|||||||||&7|"));
		l.add(Format.color("&8 ● &7Health &4||||||||||"));
		l.add(Format.color("&8 ● &7Resistance &4||||||||&7||"));
		l.add(Format.color("&7"));
		if (BossCore.magmaAlive) {
			l.add(Format.color("&8 ● &7Cooldown &cAlive"));
		} else if (BossCore.magmaSpawnTime == 0) {
			l.add(Format.color("&8 ● &7Cooldown &bSpawnable"));
		} else if (System.currentTimeMillis() <= (BossCore.magmaSpawnTime + (1800 * 1000))) {
			l.add(Format.color("&8 ● &7Cooldown &b"
					+ Format.time((int) Math.round(
							(BossCore.magmaSpawnTime + (1800 * 1000)) -System.currentTimeMillis() ) / 1000)));
		} else {
			l.add(Format.color("&8 ● &7Cooldown &bSpawnable"));
		}
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &cAbility:"));
		l.add(Format.color("&8 &8 &8 ● &7When attacked, chance to spawn &c&lMagma &7&lMinions"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.addItem(i);
		i = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		m = (SkullMeta) i.getItemMeta();
		l.clear();
		m.setOwner(Bukkit.getOfflinePlayer("MHF_Golem").getName());
		m.setDisplayName(Format.color("&3&l&ki&b&lGolem &f&lTyrant&3&l&ki"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &7Difficulty &c|||||||&7|||"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &7Damage &c|||||||&7|||"));
		l.add(Format.color("&8 ● &7Health &a||||&7||||||"));
		l.add(Format.color("&8 ● &7Resistance &4||||||||||&7"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &cAbility:"));
		l.add(Format.color("&8 &8 &8 ● &7When attacked, chance to launch you away"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.addItem(i);
		i = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.SKELETON.ordinal());
		m = (SkullMeta) i.getItemMeta();
		l.clear();
		m.setDisplayName(Format.color("&e&l&ki&f&lSkeletal &7&lChamp&e&l&ki"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &7Difficulty &c||||||&7||||"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &7Damage &4||||||||||"));
		l.add(Format.color("&8 ● &7Health &c||||||&7||||"));
		l.add(Format.color("&8 ● &7Resistance &b||&7||||||||"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &cAbility:"));
		l.add(Format.color("&8 &8 &8 ● &7When attacked, chance to launch away from you"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.addItem(i);
		i = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		m = (SkullMeta) i.getItemMeta();
		l.clear();
		m.setOwner(Bukkit.getOfflinePlayer("MHF_PigZombie").getName());
		m.setDisplayName(Format.color("&f&l&ki&e&lZombie &6&lEmperor&f&k&li"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &7Difficulty &c|||||&7|||||"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &7Difficulty &c|||&7|||||||"));
		l.add(Format.color("&8 ● &7Damage &b||&7||||||||"));
		l.add(Format.color("&8 ● &7Health &c||||&7||||||"));
		l.add(Format.color("&8 ● &7Resistance &4|||||||||&7|"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &cAbility:"));
		l.add(Format.color("&8 &8 &8 ● &7When attacking, absorbs your health"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.addItem(i);
		i = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.ZOMBIE.ordinal());
		m = (SkullMeta) i.getItemMeta();
		l.clear();
		m.setDisplayName(Format.color("&4&l&ki&c&lElite &b&lZombie&9&l&ki"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &7Difficulty &a|||&7|||||||"));
		l.add(Format.color("&8 ● &7Damage &a|||&7|||||||"));
		l.add(Format.color("&8 ● &cHealth &a|||||&7|||||"));
		l.add(Format.color("&8 ● &7Resistance &b|&7|||||||||"));
		l.add(Format.color("&7"));
		l.add(Format.color("&8 ● &cAbility:"));
		l.add(Format.color("&8 &8 &8 ● &cNONE"));
		m.setLore(l);
		i.setItemMeta(m);
		inv.addItem(i);
		return inv;
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		if (open.contains(e.getWhoClicked())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void close(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
		}
	}

	private static void borders(Inventory inv) {
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 7);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(Format.color(""));
		i.setItemMeta(m);
		inv.setItem(0, i);
		inv.setItem(1, i);
		inv.setItem(2, i);
		inv.setItem(3, i);
		inv.setItem(4, i);
		inv.setItem(5, i);
		inv.setItem(6, i);
		inv.setItem(7, i);
		inv.setItem(8, i);
		inv.setItem(9, i);
		inv.setItem(17, i);
		inv.setItem(18, i);
		inv.setItem(19, i);
		inv.setItem(20, i);
		inv.setItem(21, i);
		inv.setItem(22, i);
		inv.setItem(23, i);
		inv.setItem(24, i);
		inv.setItem(25, i);
		inv.setItem(26, i);
	}
}
