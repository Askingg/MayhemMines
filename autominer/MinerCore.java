package me.askingg.mayhem.autominer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.autosell.AutosellCore;
import me.askingg.mayhem.enchant.EnchantCore;
import me.askingg.mayhem.enchant.enchants.Gatherer;
import me.askingg.mayhem.main.Main;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.askingg.mayhem.utils.Message;

public class MinerCore implements Listener {

	public static List<Player> mining = new ArrayList<Player>();
	public static HashMap<Player, Long> times = new HashMap<Player, Long>(); // Player, JoinTimeLong
	public static HashMap<String, Player> miners = new HashMap<String, Player>(); // AutoMinerID, Player
	public static HashMap<String, Player> dminers = new HashMap<String, Player>(); // AutoMinerID, Player
	public static HashMap<String, Location> minersLocs = new HashMap<String, Location>(); // AutoMinerID, Location
	public static HashMap<String, Location> dminersLocs = new HashMap<String, Location>(); // AutoMinerID, Location

	@EventHandler
	public void disableCMD(PlayerCommandPreprocessEvent e) {
		if (mining.contains(e.getPlayer())) {
			String cmd = e.getMessage();
			if (!(cmd.startsWith("/msg") || cmd.startsWith("/r") || cmd.startsWith("/ce") || cmd.startsWith("/miner")
					|| cmd.startsWith("/show") || cmd.startsWith("/sb"))) {
				Message.player("You cannot use this command while in an autominer: &c/Miner Leave", e.getPlayer());
				e.setCancelled(true);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static ItemStack timeItem(long milis) {
		ItemStack i = new ItemStack(Material.getMaterial(2266));
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&c&lAutoMining Chip: &b&l" + Format.time((int) milis / 1000)));
		l.add(Format.color("&7"));
		l.add(Format.color("&bRightClick&7 to redeem time"));
		l.add(Format.color("&7"));
		l.add(Format.color("&7Milis &b" + milis));
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

	public static void joinMiner(Player p, String id) {
		if (mining.contains(p)) {
			Message.player("Sorry, but you're already in an autominer", p);
			return;
		}
		if (Files.data.getLong("Users." + p.getUniqueId().toString() + ".MinerTime") < 10000) {
			Message.player("Sorry, but you need atleast &c10s&7 of automining time", p);
			return;
		}
		if (id.contains("Public")) {
			if (miners.get(id) == null) {
				Message.player("You joined " + id, p);
				mining.add(p);
				miners.put(id, p);
				p.teleport(minersLocs.get(id));
				return;
			} else {
				Message.player("Sorry, but that autominer is already taken by &c" + miners.get(id), p);
				return;
			}
		} else {
			if (dminers.get(id) == null) {
				Message.player("You joined " + id, p);
				mining.add(p);
				dminers.put(id, p);
				p.teleport(dminersLocs.get(id));
				return;
			} else {
				Message.player("Sorry, but that autominer is already taken by &c" + dminers.get(id), p);
				return;
			}
		}
	}

	public static void joinMiner(Player p) {
		if (mining.contains(p)) {
			Message.player("Sorry, but you're already in an autominer", p);
			return;
		}
		if (Files.data.getLong("Users." + p.getUniqueId().toString() + ".MinerTime") < 10000) {
			Message.player("Sorry, but you need atleast &c10s&7 of automining time", p);
			return;
		}
		String id = null;
		for (String str : miners.keySet()) {
			if (miners.get(str) == null) {
				id = str;
				break;
			}
		}
		if (id == null) {
			for (String str : dminers.keySet()) {
				if (dminers.get(str) == null) {
					id = str;
					break;
				}
			}
		}
		if (id == null) {
			Message.player("Sorry, but all autominers are currently taken", p);
			return;
		}
		Message.player("You joined " + id, p);
		if (id.contains("Public")) {
			p.teleport(minersLocs.get(id));
			miners.put(id, p);
		} else {
			dminers.put(id, p);
			p.teleport(dminersLocs.get(id));
		}
		mining.add(p);
		return;
	}

	public static void leaveMiner(Player p) {
		if (!mining.contains(p)) {
			Message.player("Sorry, but you're not in an autominer", p);
			return;
		}
		mining.remove(p);
		for (String str : miners.keySet()) {
			if (miners.get(str) != null) {
				if (miners.get(str).equals(p)) {
					Message.player("You left your autominer", p);
					miners.put(str, null);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + p.getName());
					return;
				}
			}
		}
		for (String str : dminers.keySet()) {
			if (dminers.get(str) != null) {
				if (dminers.get(str).equals(p)) {
					Message.player("You left your autominer", p);
					dminers.put(str, null);
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spawn " + p.getName());
					return;
				}
			}
		}
	}

	public static void setupMiners() {
		miners.put("Public 1", null);
		miners.put("Public 2", null);
		miners.put("Public 3", null);
		miners.put("Public 4", null);
		miners.put("Public 5", null);
		miners.put("Public 6", null);
		dminers.put("Donator 1", null);
		dminers.put("Donator 2", null);
		dminers.put("Donator 3", null);
		dminers.put("Donator 4", null);
		dminers.put("Donator 5", null);
		dminers.put("Donator 6", null);
		minersLocs.put("Public 1",
				new Location(Bukkit.getWorld("world"), -890.5, 173.0, -783.5, (long) 360.0, (long) -8.5));
		minersLocs.put("Public 2",
				new Location(Bukkit.getWorld("world"), -851.5, 172.0, -798.5, (long) 90.0, (long) -8.5));
		minersLocs.put("Public 3",
				new Location(Bukkit.getWorld("world"), -779.5, 174.0, -808.5, (long) 270.0, (long) -8.5));
		minersLocs.put("Public 4",
				new Location(Bukkit.getWorld("world"), -888.5, 172.0, -764.5, (long) 180., (long) -8.5));
		minersLocs.put("Public 5",
				new Location(Bukkit.getWorld("world"), -832.5, 173.0, -743.5, (long) 270.0, (long) -8.5));
		minersLocs.put("Public 6",
				new Location(Bukkit.getWorld("world"), -793.5, 173.0, -769.5, (long) 180.0, (long) -8.5));
		dminersLocs.put("Donator 1",
				new Location(Bukkit.getWorld("world"), -809.5, 174.0, -715.5, (long) 270.0, (long) -8.5));
		dminersLocs.put("Donator 2",
				new Location(Bukkit.getWorld("world"), -826.5, 174.0, -716.5, (long) 270.0, (long) -8.5));
		dminersLocs.put("Donator 3",
				new Location(Bukkit.getWorld("world"), -892.5, 173.0, -694.5, (long) 360.0, (long) -8.5));
		dminersLocs.put("Donator 4",
				new Location(Bukkit.getWorld("world"), -802.5, 173.0, -674.5, (long) 180.0, (long) -8.5));
		dminersLocs.put("Donator 5",
				new Location(Bukkit.getWorld("world"), -839.5, 172.0, -655.5, (long) 360.0, (long) -8.5));
		dminersLocs.put("Donator 6",
				new Location(Bukkit.getWorld("world"), -881.5, 172.0, -661.5, (long) 270.0, (long) -8.5));

		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				if (mining.size() > 0) {
					for (Player p : mining) {
						double noTime = MinerUpgradeCore.getDoubleResult(p, "TimeReduce");
						double moneyBoost = MinerUpgradeCore.getDoubleResult(p, "MoreMoney");
						double bpsTemp = MinerUpgradeCore.getDoubleResult(p,  "MoreBps");

						int bps = (int) Math.floor(bpsTemp);
						double bpsOdds = bpsTemp - bps;



						long time = Files.data.getLong("Users." + p.getUniqueId().toString() + ".MinerTime");
						if (time < 2000) {
							Message.player("Sorry, but your automining time expired", p);
							leaveMiner(p);
							return;
						}


						Random r = new Random();

						if (r.nextDouble() > noTime){
						Files.data.set("Users." + p.getUniqueId().toString() + ".MinerTime", time - 1000);}

						if (p.getInventory().getItemInMainHand() != null
								&& p.getInventory().getItemInMainHand().getType().toString().contains("PICKAXE")) {
							String u = p.getUniqueId().toString();

							Main.broken.put(u.toString(), (Main.broken.get(u.toString()) + 1));

							Double d = 1 + AutosellCore.multi(p);
							Main.eco.depositPlayer(p, (AutosellCore.worth(p, Material.STONE, (int) 3) * d * (1+moneyBoost)));

							int count = 4 + bps;
							if(r.nextDouble() < bpsOdds) {
								count++;
							}

							while (count > 0) {
								count--;
								p.setLevel(p.getLevel() + Gatherer.blockXP(p)); //TODO replace deprecated blockXP(p) with blockXP(p, location)
								p.updateInventory();
								d = 0.001;
								EnchantCore.applyMinerEnchants(p);

							}
						}
					}
				}
			}
		}, 0, 20);

	}
}
