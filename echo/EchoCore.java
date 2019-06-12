package me.askingg.mayhem.echo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;

public class EchoCore implements Listener {

	public static Boolean isRelic(ItemStack i) {
		if (i.hasItemMeta()) {
			ItemMeta m = i.getItemMeta();
			if (m.hasLore()) {
				for (String str : m.getLore()) {
					str = ChatColor.stripColor(Format.color(str));
					if (str.contains("Item /") && str.contains("Relic")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static Boolean isArtefact(ItemStack i) {
		if (i.hasItemMeta()) {
			ItemMeta m = i.getItemMeta();
			if (m.hasLore()) {
				for (String str : m.getLore()) {
					str = ChatColor.stripColor(Format.color(str));
					if (str.contains("Item /") && str.contains("Artefact")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static Boolean isCharm(ItemStack i) {
		if (i.hasItemMeta()) {
			ItemMeta m = i.getItemMeta();
			if (m.hasLore()) {
				for (String str : m.getLore()) {
					str = ChatColor.stripColor(Format.color(str));
					if (str.contains("Item /") && str.contains("Charm")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@EventHandler
	public void onThrow(ProjectileLaunchEvent e) {
		if (e.getEntity() instanceof Player) {
			if ((isRelic(((Player) e.getEntity().getShooter()).getInventory().getItemInMainHand())
					|| isArtefact(((Player) e.getEntity().getShooter()).getInventory().getItemInMainHand())
					|| isCharm(((Player) e.getEntity().getShooter()).getInventory().getItemInMainHand()))) {

			}
		}
	}

	public static Boolean hasEquippedRelic(Player p) {
		if (Files.data.getString("Users." + p.getUniqueId() + ".Echo.Relic.Boost") != null) {
			return true;
		}
		return false;
	}

	public static Boolean hasEquippedArtefact(Player p) {
		if (Files.data.getString("Users." + p.getUniqueId() + ".Echo.Artefact.Boost") != null) {
			return true;
		}
		return false;
	}

	public static Boolean hasEquippedCharm(Player p) {
		if (Files.data.getString("Users." + p.getUniqueId() + ".Echo.Charm.Equipped.Material") != null) {
			return true;
		}
		return false;
	}

	public static Material getRelicMaterial(Player p) {
		if (hasEquippedRelic(p)) {
			return Material
					.getMaterial(Files.data.getString("Users." + p.getUniqueId() + ".Echo.Relic.Equipped.Material"));
		}
		return null;
	}

	public static Material getArtefactMaterial(Player p) {
		if (hasEquippedArtefact(p)) {
			return Material
					.getMaterial(Files.data.getString("Users." + p.getUniqueId() + ".Echo.Artefact.Equipped.Material"));
		}
		return null;
	}

	public static Material getCharmMaterial(Player p) {
		if (hasEquippedArtefact(p)) {
			return Material
					.getMaterial(Files.data.getString("Users." + p.getUniqueId() + ".Echo.Charm.Equipped.Material"));
		}
		return null;
	}

	public static int getArtefactData(Player p) {
		if (hasEquippedArtefact(p)) {
			return Files.data.getInt("Users." + p.getUniqueId() + ".Echo.Artefact.Equipped.Data");
		}
		return 0;
	}

	public static String getRelicName(Player p) {
		if (hasEquippedRelic(p)) {
			return Files.data.getString("Users." + p.getUniqueId() + ".Echo.Relic.Equipped.Name");
		}
		return null;
	}

	public static String getArtefactName(Player p) {
		if (hasEquippedArtefact(p)) {
			return Files.data.getString("Users." + p.getUniqueId() + ".Echo.Artefact.Equipped.Name");
		}
		return null;
	}

	public static String getCharmName(Player p) {
		if (hasEquippedArtefact(p)) {
			return Files.data.getString("Users." + p.getUniqueId() + ".Echo.Charm.Equipped.Name");
		}
		return null;
	}

	public static Integer relicBoost(ItemStack i) {
		if (isRelic(i)) {
			for (String str : i.getItemMeta().getLore()) {
				str = ChatColor.stripColor(Format.color(str));
				if (str.contains("Boost")) {
					String[] s = str.split(" \\/ ");
					s = s[1].split("%");
					return Integer.parseInt(s[0]);
				}
			}
		}
		return null;
	}

	public static Integer artefactBoost(ItemStack i) {
		if (isArtefact(i)) {
			for (String str : i.getItemMeta().getLore()) {
				str = ChatColor.stripColor(Format.color(str));
				if (str.contains("Boost")) {
					String[] s = str.split(" \\/ ");
					s = s[1].split("%");
					return Integer.parseInt(s[0]);
				}
			}
		}
		return null;
	}

	public static String getCharmColor(ItemStack i) {
		if (isCharm(i)) {
			return "&" + i.getItemMeta().getDisplayName().charAt(1);
		}
		return null;
	}

	public static HashMap<String, Integer> charmBoosts(ItemStack i) {
		if (isCharm(i)) {
			HashMap<String, Integer> m = new HashMap<String, Integer>();
			for (String str : i.getItemMeta().getLore()) {
				str = ChatColor.stripColor(Format.color(str));
				if (str.contains("Haste")) {
					String[] s = str.split(" \\/ ");
					m.put("Haste", Integer.parseInt(s[1]));
					continue;
				}
				if (str.contains("Speed")) {
					String[] s = str.split(" \\/ ");
					m.put("Speed", Integer.parseInt(s[1]));
					continue;
				}
				if (str.contains("Regeneration")) {
					String[] s = str.split(" \\/ ");
					m.put("Regeneration", Integer.parseInt(s[1]));
					continue;
				}
				if (str.contains("Strength")) {
					String[] s = str.split(" \\/ ");
					m.put("Strength", Integer.parseInt(s[1]));
					continue;
				}
				if (str.contains("JumpBoost")) {
					String[] s = str.split(" \\/ ");
					m.put("JumpBoost", Integer.parseInt(s[1]));
					continue;
				}
				if (str.contains("Slowness")) {
					String[] s = str.split(" \\/ ");
					m.put("Slowness", Integer.parseInt(s[1]));
					continue;
				}
				if (str.contains("MiningFatigue")) {
					String[] s = str.split(" \\/ ");
					m.put("MiningFatigue", Integer.parseInt(s[1]));
					continue;
				}
			}
			return m;
		}
		return null;
	}

	public static ItemStack device() {
		ItemStack i = new ItemStack(Material.ENDER_CHEST);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&lEcho Device"));
		l.add("");
		l.add(Format.color("&c● &7RightClick to open your echo device"));
		l.add(Format.color("&c● &7Inside of this menu you can apply"));
		l.add(Format.color("&7 &7 &bRelics&7, &cArtifacts&7 and &dCharms"));
		l.add(Format.color("&c● &7These custom items grant unique"));
		l.add(Format.color("&7 &7 effects/boosts to the player"));
		l.add(Format.color("&c● &7Alternately you can execute "));
		l.add(Format.color("&b &b /Echo Open&7 to open the echo device"));
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

	public static ItemStack equippedRelic(Player p) {
		if (hasEquippedRelic(p)) {
			ItemStack i = new ItemStack(Material
					.getMaterial(Files.data.getString("Users." + p.getUniqueId() + ".Echo.Relic.Equipped.Material")));
			ItemMeta m = i.getItemMeta();
			List<String> l = new ArrayList<String>();
			m.setDisplayName(
					Format.color(Files.data.getString("Users." + p.getUniqueId() + ".Echo.Relic.Equipped.Name")));
			Integer boost = Files.data.getInt("Users." + p.getUniqueId() + ".Echo.Relic.Boost");
			String color = "";
			if (boost < 25) {
				color = "&4";
			}
			if (boost < 50 && boost >= 25) {
				color = "&c";
			}
			if (boost < 100 && boost >= 50) {
				color = "&e";
			}
			if (boost < 150 && boost >= 100) {
				color = "&a";
			}
			if (boost < 200 && boost >= 150) {
				color = "&2";
			}
			if (boost < 215 && boost >= 200) {
				color = "&b";
			}
			if (boost == 215) {
				color = "&3";
			}
			l.add(Format.color("&7Item &8/ " + color + "Relic"));
			l.add(Format.color("&7Boost &8/ " + color + boost + "%"));
			m.setLore(l);
			i.setItemMeta(m);
			return i;
		}
		return null;
	}

	public static ItemStack equippedArtefact(Player p) {
		if (hasEquippedArtefact(p)) {
			ItemStack i = new ItemStack(Material.getMaterial(
					Files.data.getString("Users." + p.getUniqueId() + ".Echo.Artefact.Equipped.Material")));
			i.setDurability((byte) Files.data.getInt("Users." + p.getUniqueId() + ".Echo.Artefact.Equipped.Data"));
			ItemMeta m = i.getItemMeta();
			List<String> l = new ArrayList<String>();
			m.setDisplayName(
					Format.color(Files.data.getString("Users." + p.getUniqueId() + ".Echo.Artefact.Equipped.Name")));
			Integer boost = Files.data.getInt("Users." + p.getUniqueId() + ".Echo.Artefact.Boost");
			String color = "";
			if (boost < 25) {
				color = "&4";
			}
			if (boost < 50 && boost >= 25) {
				color = "&c";
			}
			if (boost < 100 && boost >= 50) {
				color = "&e";
			}
			if (boost < 150 && boost >= 100) {
				color = "&a";
			}
			if (boost < 200 && boost >= 150) {
				color = "&2";
			}
			if (boost < 215 && boost >= 200) {
				color = "&b";
			}
			if (boost == 215) {
				color = "&3";
			}
			l.add(Format.color("&7Item &8/ " + color + "Artefact"));
			l.add(Format.color("&7Boost &8/ " + color + boost + "%"));
			m.setLore(l);
			i.setItemMeta(m);
			return i;
		}
		return null;
	}

	public static ItemStack equippedCharm(Player p) {
		if (hasEquippedCharm(p)) {
			ItemStack i = new ItemStack(Material
					.getMaterial(Files.data.getString("Users." + p.getUniqueId() + ".Echo.Charm.Equipped.Material")));
			ItemMeta m = i.getItemMeta();
			List<String> l = new ArrayList<String>();
			m.setDisplayName(
					Format.color(Files.data.getString("Users." + p.getUniqueId() + ".Echo.Charm.Equipped.Name")));
			String color = Files.data.getString("Users." + p.getUniqueId() + ".Echo.Charm.Equipped.Color");
			l.add(Format.color("&7Item &8/ " + color + "Charm"));
			HashMap<String, Integer> map = getCharmBoosts(p);
			for (String str : map.keySet()) {
				l.add(Format.color("&7" + str + "&8 / " + color + map.get(str)));
			}
			m.setLore(l);
			i.setItemMeta(m);
			return i;
		}
		return null;
	}

	public static Integer getRelicBoost(Player p) {
		if (hasEquippedRelic(p)) {
			return Files.data.getInt("Users." + p.getUniqueId() + ".Echo.Relic.Boost");
		}
		return null;
	}

	public static Integer getArtefactBoost(Player p) {
		if (hasEquippedArtefact(p)) {
			return Files.data.getInt("Users." + p.getUniqueId() + ".Echo.Artefact.Boost");
		}
		return null;
	}

	public static HashMap<String, Integer> getCharmBoosts(Player p) {
		if (hasEquippedCharm(p)) {
			HashMap<String, Integer> m = new HashMap<String, Integer>();
			for (String str : Files.data
					.getConfigurationSection("Users." + p.getUniqueId() + ".Echo.Charm.Equipped.Boosts")
					.getKeys(false)) {
				m.put(str, Files.data.getInt("Users." + p.getUniqueId() + ".Echo.Charm.Equipped.Boosts." + str));
			}
			return m;
		}
		return null;
	}

	public static ItemStack randomRelic() {
		Random r = new Random();
		String name = null;
		Material mat = null;
		Integer boost = 0;
		String color = "";
		Integer x = r.nextInt(100);
		if (x <= 29) {
			name = "&7Abnormal Relic";
			boost = boost + r.nextInt((10 - 1) + 1) + 1;
		}
		if (x > 29 && x <= 49) {
			name = "&fSheriff's Badge";
			boost = boost + r.nextInt((20 - 5) + 1) + 5;
		}
		if (x > 49 && x <= 59) {
			name = "&aOfficer's Badge";
			boost = boost + r.nextInt((30 - 10) + 1) + 10;
		}
		if (x > 59 && x <= 68) {
			name = "&cWarden's Badge";
			boost = boost + r.nextInt((40 - 15) + 1) + 15;
		}
		if (x > 68 && x <= 76) {
			name = "&5&lMy&d&lste&5&lriou&d&ls Rel&5&lic";
			boost = boost + r.nextInt((50 - 20) + 1) + 20;
		}
		if (x > 76 && x <= 83) {
			name = "&6&lTen&e&lac&6&li&e&lty Re&6&llic";
			boost = boost + r.nextInt((60 - 25) + 1) + 25;
		}
		if (x > 83 && x <= 89) {
			name = "&9&lV&b&li&9&lt&b&la&9&lli&b&lt&9&ly R&b&le&9&llic";
			boost = boost + r.nextInt((70 - 30) + 1) + 30;
		}
		if (x > 89 && x <= 94) {
			name = "&4&lBl&c&loo&4&ld o&c&lf &4&lAsk&c&ling&4&lg";
			boost = boost + r.nextInt((80 - 35) + 1) + 35;
		}
		if (x > 94 && x <= 98) {
			name = "&8&lA&4&ls&b&lkin&4&lg&8&lg's &4&lS&b&lhad&4&lo&8&lw";
			boost = boost + r.nextInt((90 - 40) + 1) + 40;
		}
		if (x == 99) {
			name = "&4&lThe Af&6&lterbu&e&lrner";
			boost = boost + r.nextInt((100 - 45) + 1) + 45;
		}

		x = r.nextInt(100);
		if (x <= 29) {
			mat = Material.FIREWORK_CHARGE;
			boost = boost + r.nextInt((10 - 1) + 1) + 1;
		}
		if (x > 29 && x <= 54) {
			mat = Material.SNOW_BALL;
			boost = boost + r.nextInt((25 - 5) + 1) + 5;
		}
		if (x > 54 && x <= 74) {
			mat = Material.SLIME_BALL;
			boost = boost + r.nextInt((40 - 10) + 1) + 10;
		}
		if (x > 74 && x <= 84) {
			mat = Material.FERMENTED_SPIDER_EYE;
			boost = boost + r.nextInt((55 - 15) + 1) + 15;
		}
		if (x > 84 && x <= 92) {
			mat = Material.GLOWSTONE_DUST;
			boost = boost + r.nextInt((70 - 20) + 1) + 20;
		}
		if (x > 92 && x <= 96) {
			mat = Material.MAGMA_CREAM;
			boost = boost + r.nextInt((85 - 25) + 1) + 25;
		}
		if (x > 96 && x <= 99) {
			mat = Material.CHORUS_FRUIT_POPPED;
			boost = boost + r.nextInt((100 - 30) + 1) + 30;
		}
		if (x == 99) {
			mat = Material.NETHER_STAR;
			boost = boost + r.nextInt((115 - 35) + 1) + 35;
		}
		if (boost < 25) {
			color = "&4";
		}
		if (boost < 50 && boost >= 25) {
			color = "&c";
		}
		if (boost < 100 && boost >= 50) {
			color = "&e";
		}
		if (boost < 150 && boost >= 100) {
			color = "&a";
		}
		if (boost < 200 && boost >= 150) {
			color = "&2";
		}
		if (boost < 215 && boost >= 200) {
			color = "&b";
		}
		if (boost == 215) {
			color = "&3";
		}
		ItemStack i = new ItemStack(mat);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color(name));
		l.add(Format.color("&7Item &8/ " + color + "Relic"));
		l.add(Format.color("&7Boost &8/ " + color + boost + "%"));
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

	public static ItemStack randomArtefact() {
		Random r = new Random();
		String name = null;
		Material mat = null;
		Integer boost = 0;
		int data = 0;
		String color = "";
		Integer x = r.nextInt(100);
		if (x <= 29) {
			name = "&7Dusty Artefact";
			boost = boost + r.nextInt((10 - 1) + 1) + 1;
		}
		if (x > 29 && x <= 49) {
			name = "&7R&cus&7ty &cA&7rtef&cact";
			boost = boost + r.nextInt((20 - 5) + 1) + 5;
		}
		if (x > 49 && x <= 59) {
			name = "&4Dam&7age&4d &7Ar&4tef&7act";
			boost = boost + r.nextInt((30 - 10) + 1) + 10;
		}
		if (x > 59 && x <= 68) {
			name = "&2Din&aos&2aur&a Sku&2ll";
			boost = boost + r.nextInt((40 - 15) + 1) + 15;
		}
		if (x > 68 && x <= 76) {
			name = "&5&lAn&d&lc&5&lie&d&lnt A&5&lrt&d&lef&5&lact";
			boost = boost + r.nextInt((50 - 20) + 1) + 20;
		}
		if (x > 76 && x <= 83) {
			name = "&6&lP&e&lric&6&lel&e&less A&6&lrt&e&lefa&6&lc&e&lt";
			boost = boost + r.nextInt((60 - 25) + 1) + 25;
		}
		if (x > 83 && x <= 89) {
			name = "&8&lExtin&7&lct Art&f&lefact";
			boost = boost + r.nextInt((70 - 30) + 1) + 30;
		}
		if (x > 89 && x <= 94) {
			name = "&8&lHa&4&lunt&8&led Ar&4&ltefa&8&lct";
			boost = boost + r.nextInt((80 - 35) + 1) + 35;
		}
		if (x > 94 && x <= 98) {
			name = "&4&lPosse&6&lssed Art&e&lefact";
			boost = boost + r.nextInt((90 - 40) + 1) + 40;
		}
		if (x == 99) {
			name = "&4&lCo&c&k&lr&c&lr&4&lu&c&lpt&4&k&le&c&ld &4&lA&c&k&lr&c&lt&4&lef&ka&c&lct";
			boost = boost + r.nextInt((100 - 45) + 1) + 45;
		}

		x = r.nextInt(100);
		if (x <= 29) {
			mat = Material.INK_SACK;
			data = 8;
			boost = boost + r.nextInt((10 - 1) + 1) + 1;
		}
		if (x > 29 && x <= 54) {
			mat = Material.INK_SACK;
			data = 9;
			boost = boost + r.nextInt((25 - 5) + 1) + 5;
		}
		if (x > 54 && x <= 74) {
			mat = Material.INK_SACK;
			data = 10;
			boost = boost + r.nextInt((40 - 10) + 1) + 10;
		}
		if (x > 74 && x <= 84) {
			mat = Material.INK_SACK;
			data = 14;
			boost = boost + r.nextInt((55 - 15) + 1) + 15;
		}
		if (x > 84 && x <= 92) {
			mat = Material.INK_SACK;
			data = 5;
			boost = boost + r.nextInt((70 - 20) + 1) + 20;
		}
		if (x > 92 && x <= 96) {
			mat = Material.INK_SACK;
			data = 12;
			boost = boost + r.nextInt((85 - 25) + 1) + 25;
		}
		if (x > 96 && x <= 99) {
			mat = Material.INK_SACK;
			data = 1;
			boost = boost + r.nextInt((100 - 30) + 1) + 30;
		}
		if (x == 99) {
			mat = Material.INK_SACK;
			data = 6;
			boost = boost + r.nextInt((115 - 35) + 1) + 35;
		}
		if (boost < 25) {
			color = "&4";
		}
		if (boost < 50 && boost >= 25) {
			color = "&c";
		}
		if (boost < 100 && boost >= 50) {
			color = "&e";
		}
		if (boost < 150 && boost >= 100) {
			color = "&a";
		}
		if (boost < 200 && boost >= 150) {
			color = "&2";
		}
		if (boost < 215 && boost >= 200) {
			color = "&b";
		}
		if (boost == 215) {
			color = "&3";
		}
		ItemStack i = new ItemStack(mat);
		i.setDurability((byte) data);
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color(name));
		l.add(Format.color("&7Item &8/ " + color + "Artefact"));
		l.add(Format.color("&7Boost &8/ " + color + boost + "%"));
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

	public static ItemStack randomCharm() {
		Random r = new Random();
		String type = null;
		Material mat = null;
		String color = randomColor();
		Integer speed = 0;
		Integer haste = 0;
		Integer regeneration = 0;
		Integer strength = 0;
		Integer jumpboost = 0;
		Integer miningfatigue = 0;
		Integer slowness = 0;
		Integer x = r.nextInt(5) + 1;
		if (x == 1) {
			type = "Miner's";
		}
		if (x == 2) {
			type = "Slayer's";
		}
		if (x == 3) {
			type = "Ninja's";
		}
		if (x == 4) {
			type = "Warrior's";
		}
		if (x == 5) {
			type = "Juggernaut's";
		}
		x = r.nextInt(3) + 1;
		if (x == 1) {
			mat = Material.GHAST_TEAR;
		}
		if (x == 2) {
			mat = Material.IRON_NUGGET;
		}
		if (x == 3) {
			mat = Material.GOLD_NUGGET;
		}
		if (type.equalsIgnoreCase("Miner's")) { // HASTE, speed, jumpboost
			x = r.nextInt(100) + 1;
			if (x <= 45) {
				haste = 3;
			}
			if (x > 45 && x <= 70) {
				haste = 4;
			}
			if (x > 70 && x <= 85) {
				haste = 5;
			}
			if (x > 85 && x <= 95) {
				haste = 6;
			}
			if (x > 95) {
				haste = 7;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				speed = 0;
			}
			if (x > 50 && x <= 80) {
				speed = 1;
			}
			if (x > 80 && x <= 90) {
				speed = 2;
			}
			if (x > 90 && x <= 96) {
				speed = 3;
			}
			if (x > 96 && x <= 99) {
				speed = 4;
			}
			if (x == 100) {
				speed = 5;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				jumpboost = 0;
			}
			if (x > 50 && x <= 80) {
				jumpboost = 1;
			}
			if (x > 80 && x <= 90) {
				jumpboost = 2;
			}
			if (x > 90 && x <= 96) {
				jumpboost = 3;
			}
			if (x > 96 && x <= 99) {
				jumpboost = 4;
			}
			if (x == 100) {
				jumpboost = 5;
			}
			ItemStack i = new ItemStack(mat);
			ItemMeta m = i.getItemMeta();
			List<String> l = new ArrayList<String>();
			m.setDisplayName(Format.color(color + type + " Charm"));
			l.add(Format.color("&7Item &8/ " + color + "Charm"));
			if (haste > 0) {
				l.add(Format.color("&7Haste &8/ " + color + haste));
			}
			if (jumpboost > 0) {
				l.add(Format.color("&7JumpBoost &8/ " + color + jumpboost));
			}
			if (speed > 0) {
				l.add(Format.color("&7Speed &8/ " + color + speed));
			}
			m.setLore(l);
			i.setItemMeta(m);
			return i;
		}
		if (type.equalsIgnoreCase("Slayer's")) { // STRENGTH, speed, haste
			x = r.nextInt(100) + 1;
			if (x <= 45) {
				strength = 3;
			}
			if (x > 45 && x <= 70) {
				strength = 4;
			}
			if (x > 70 && x <= 85) {
				strength = 5;
			}
			if (x > 85 && x <= 95) {
				strength = 6;
			}
			if (x > 95) {
				strength = 7;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				haste = 0;
			}
			if (x > 50 && x <= 80) {
				haste = 1;
			}
			if (x > 80 && x <= 90) {
				haste = 2;
			}
			if (x > 90 && x <= 96) {
				haste = 3;
			}
			if (x > 96 && x <= 99) {
				haste = 4;
			}
			if (x == 100) {
				haste = 5;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				speed = 0;
			}
			if (x > 50 && x <= 80) {
				speed = 1;
			}
			if (x > 80 && x <= 90) {
				speed = 2;
			}
			if (x > 90 && x <= 96) {
				speed = 3;
			}
			if (x > 96 && x <= 99) {
				speed = 4;
			}
			if (x == 100) {
				speed = 5;
			}
			ItemStack i = new ItemStack(mat);
			ItemMeta m = i.getItemMeta();
			List<String> l = new ArrayList<String>();
			m.setDisplayName(Format.color(color + type + " Charm"));
			l.add(Format.color("&7Item &8/ " + color + "Charm"));
			if (strength > 0) {
				l.add(Format.color("&7Strength &8/ " + color + strength));
			}
			if (haste > 0) {
				l.add(Format.color("&7Haste &8/ " + color + haste));
			}
			if (speed > 0) {
				l.add(Format.color("&7Speed &8/ " + color + speed));
			}
			m.setLore(l);
			i.setItemMeta(m);
			return i;
		}
		if (type.equalsIgnoreCase("Ninja's")) { // SPEED, haste, jumpboost
			x = r.nextInt(100) + 1;
			if (x <= 45) {
				speed = 3;
			}
			if (x > 45 && x <= 70) {
				speed = 4;
			}
			if (x > 70 && x <= 85) {
				speed = 5;
			}
			if (x > 85 && x <= 95) {
				speed = 6;
			}
			if (x > 95) {
				speed = 7;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				haste = 0;
			}
			if (x > 50 && x <= 80) {
				haste = 1;
			}
			if (x > 80 && x <= 90) {
				haste = 2;
			}
			if (x > 90 && x <= 96) {
				haste = 3;
			}
			if (x > 96 && x <= 99) {
				haste = 4;
			}
			if (x == 100) {
				haste = 5;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				jumpboost = 0;
			}
			if (x > 50 && x <= 80) {
				jumpboost = 1;
			}
			if (x > 80 && x <= 90) {
				jumpboost = 2;
			}
			if (x > 90 && x <= 96) {
				jumpboost = 3;
			}
			if (x > 96 && x <= 99) {
				jumpboost = 4;
			}
			if (x == 100) {
				jumpboost = 5;
			}
			ItemStack i = new ItemStack(mat);
			ItemMeta m = i.getItemMeta();
			List<String> l = new ArrayList<String>();
			m.setDisplayName(Format.color(color + type + " Charm"));
			l.add(Format.color("&7Item &8/ " + color + "Charm"));
			if (speed > 0) {
				l.add(Format.color("&7Speed &8/ " + color + speed));
			}
			if (haste > 0) {
				l.add(Format.color("&7Haste &8/ " + color + haste));
			}
			if (jumpboost > 0) {
				l.add(Format.color("&7JumpBoost &8/ " + color + jumpboost));
			}
			m.setLore(l);
			i.setItemMeta(m);
			return i;
		}
		if (type.equalsIgnoreCase("Juggernaut's")) { // REGENERATION, Slowness, Strength, MiningFatigue
			x = r.nextInt(100) + 1;
			if (x <= 45) {
				regeneration = 3;
			}
			if (x > 45 && x <= 70) {
				regeneration = 4;
			}
			if (x > 70 && x <= 85) {
				regeneration = 5;
			}
			if (x > 85 && x <= 95) {
				regeneration = 6;
			}
			if (x > 95) {
				regeneration = 7;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				strength = 0;
			}
			if (x > 50 && x <= 80) {
				strength = 1;
			}
			if (x > 80 && x <= 90) {
				strength = 2;
			}
			if (x > 90 && x <= 96) {
				strength = 3;
			}
			if (x > 96 && x <= 99) {
				strength = 4;
			}
			if (x == 100) {
				strength = 5;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				slowness = 0;
			}
			if (x > 50 && x <= 80) {
				slowness = 1;
			}
			if (x > 80 && x <= 90) {
				slowness = 2;
			}
			if (x > 90 && x <= 96) {
				slowness = 3;
			}
			if (x > 96 && x <= 99) {
				slowness = 4;
			}
			if (x == 100) {
				slowness = 5;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				miningfatigue = 0;
			}
			if (x > 50 && x <= 80) {
				miningfatigue = 1;
			}
			if (x > 80 && x <= 90) {
				miningfatigue = 2;
			}
			if (x > 90 && x <= 96) {
				miningfatigue = 3;
			}
			if (x > 96 && x <= 99) {
				miningfatigue = 4;
			}
			if (x == 100) {
				miningfatigue = 5;
			}
			ItemStack i = new ItemStack(mat);
			ItemMeta m = i.getItemMeta();
			List<String> l = new ArrayList<String>();
			m.setDisplayName(Format.color(color + type + " Charm"));
			l.add(Format.color("&7Item &8/ " + color + "Charm"));
			if (regeneration > 0) {
				l.add(Format.color("&7Regeneration &8/ " + color + regeneration));
			}
			if (strength > 0) {
				l.add(Format.color("&7Strength &8/ " + color + strength));
			}
			if (miningfatigue > 0) {
				l.add(Format.color("&7MiningFatigue &8/ " + color + miningfatigue));
			}
			if (slowness > 0) {
				l.add(Format.color("&7Slowness &8/ " + color + slowness));
			}
			m.setLore(l);
			i.setItemMeta(m);
			return i;
		}
		if (type.equalsIgnoreCase("Warrior's")) { // Speed, Haste, Strength, JumpBoost, Regeneration, MiningFatigue,
													// Slowness
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				speed = 0;
			}
			if (x > 50 && x <= 80) {
				speed = 1;
			}
			if (x > 80 && x <= 90) {
				speed = 2;
			}
			if (x > 90 && x <= 96) {
				speed = 3;
			}
			if (x > 96 && x <= 99) {
				speed = 4;
			}
			if (x == 100) {
				speed = 5;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				haste = 0;
			}
			if (x > 50 && x <= 80) {
				haste = 1;
			}
			if (x > 80 && x <= 90) {
				haste = 2;
			}
			if (x > 90 && x <= 96) {
				haste = 3;
			}
			if (x > 96 && x <= 99) {
				haste = 4;
			}
			if (x == 100) {
				haste = 5;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				strength = 0;
			}
			if (x > 50 && x <= 80) {
				strength = 1;
			}
			if (x > 80 && x <= 90) {
				strength = 2;
			}
			if (x > 90 && x <= 96) {
				strength = 3;
			}
			if (x > 96 && x <= 99) {
				strength = 4;
			}
			if (x == 100) {
				strength = 5;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				jumpboost = 0;
			}
			if (x > 50 && x <= 80) {
				jumpboost = 1;
			}
			if (x > 80 && x <= 90) {
				jumpboost = 2;
			}
			if (x > 90 && x <= 96) {
				jumpboost = 3;
			}
			if (x > 96 && x <= 99) {
				jumpboost = 4;
			}
			if (x == 100) {
				jumpboost = 5;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				miningfatigue = 0;
			}
			if (x > 50 && x <= 80) {
				miningfatigue = 1;
			}
			if (x > 80 && x <= 90) {
				miningfatigue = 2;
			}
			if (x > 90 && x <= 96) {
				miningfatigue = 3;
			}
			if (x > 96 && x <= 99) {
				miningfatigue = 4;
			}
			if (x == 100) {
				miningfatigue = 5;
			}
			x = r.nextInt(100) + 1;
			if (x <= 50) {
				slowness = 0;
			}
			if (x > 50 && x <= 80) {
				slowness = 1;
			}
			if (x > 80 && x <= 90) {
				slowness = 2;
			}
			if (x > 90 && x <= 96) {
				slowness = 3;
			}
			if (x > 96 && x <= 99) {
				slowness = 4;
			}
			if (x == 100) {
				slowness = 5;
			}
			ItemStack i = new ItemStack(mat);
			ItemMeta m = i.getItemMeta();
			List<String> l = new ArrayList<String>();
			m.setDisplayName(Format.color(color + type + " Charm"));
			l.add(Format.color("&7Item &8/ " + color + "Charm"));
			if (haste > 0) {
				l.add(Format.color("&7Haste &8/ " + color + haste));
			}
			if (jumpboost > 0) {
				l.add(Format.color("&7JumpBoost &8/ " + color + jumpboost));
			}
			if (regeneration > 0) {
				l.add(Format.color("&7Regeneration &8/ " + color + regeneration));
			}
			if (speed > 0) {
				l.add(Format.color("&7Speed &8/ " + color + speed));
			}
			if (strength > 0) {
				l.add(Format.color("&7Strength &8/ " + color + strength));
			}
			if (miningfatigue > 0) {
				l.add(Format.color("&7MiningFatigue &8/ " + color + miningfatigue));
			}
			if (slowness > 0) {
				l.add(Format.color("&7Slowness &8/ " + color + slowness));
			}
			m.setLore(l);
			i.setItemMeta(m);
			return i;
		}
		return null;
	}

	private static String randomColor() {
		Random r = new Random();
		Integer i = r.nextInt(12) + 1;
		if (i == 1) {
			return "&3";
		}
		if (i == 2) {
			return "&4";
		}
		if (i == 3) {
			return "&5";
		}
		if (i == 4) {
			return "&6";
		}
		if (i == 5) {
			return "&7";
		}
		if (i == 6) {
			return "&9";
		}
		if (i == 7) {
			return "&a";
		}
		if (i == 8) {
			return "&b";
		}
		if (i == 9) {
			return "&c";
		}
		if (i == 10) {
			return "&d";
		}
		if (i == 11) {
			return "&e";
		}
		if (i == 12) {
			return "&f";
		}
		return null;
	}
}
