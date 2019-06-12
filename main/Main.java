package me.askingg.mayhem.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.TimeZone;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import me.askingg.mayhem.autominer.MinerCommand;
import me.askingg.mayhem.autominer.MinerCore;
import me.askingg.mayhem.autominer.MinerListGUI;
import me.askingg.mayhem.autominer.MinerUpgradeCore;
import me.askingg.mayhem.autominer.MinerUpgradeGUI;
import me.askingg.mayhem.autosell.AutosellCommand;
import me.askingg.mayhem.autosell.BlockBreak;
import me.askingg.mayhem.blockspersecond.BPS;
import me.askingg.mayhem.burriedtreasure.OpenContainer;
import me.askingg.mayhem.burriedtreasure.TreasureCore;
import me.askingg.mayhem.commands.Check;
import me.askingg.mayhem.commands.ClearChat;
import me.askingg.mayhem.commands.Fix;
import me.askingg.mayhem.commands.Flesh;
import me.askingg.mayhem.commands.Grinder;
import me.askingg.mayhem.commands.Help;
import me.askingg.mayhem.commands.Links;
import me.askingg.mayhem.commands.MayhemReload;
import me.askingg.mayhem.commands.MuteChat;
import me.askingg.mayhem.commands.New;
import me.askingg.mayhem.commands.PlayTime;
import me.askingg.mayhem.commands.Profile;
import me.askingg.mayhem.commands.ReferredBy;
import me.askingg.mayhem.commands.Rename;
import me.askingg.mayhem.commands.RenameTokens;
import me.askingg.mayhem.commands.Replace;
import me.askingg.mayhem.commands.Showcase;
import me.askingg.mayhem.commands.StaffChat;
import me.askingg.mayhem.commands.TopBlocksBroken;
import me.askingg.mayhem.commands.Treasure;
import me.askingg.mayhem.commands.Updates;
import me.askingg.mayhem.commands.Vote;
import me.askingg.mayhem.commands.canirestart;
import me.askingg.mayhem.commands.restartcount;
import me.askingg.mayhem.commands.violations;
import me.askingg.mayhem.crates.CrateCommands;
import me.askingg.mayhem.crates.CratesCore;
import me.askingg.mayhem.crates.HopperCore;
import me.askingg.mayhem.crates.PreviewGUI;
import me.askingg.mayhem.dropparty.DropPartyCommand;
import me.askingg.mayhem.dropparty.DropPartyGUIMain;
import me.askingg.mayhem.echo.EchoCommand;
import me.askingg.mayhem.echo.EchoCore;
import me.askingg.mayhem.echo.EchoGUI;
import me.askingg.mayhem.echo.EchoUpgradeGUI;
import me.askingg.mayhem.enchant.CustomEnchantCommand;
import me.askingg.mayhem.enchant.EnchantGUI;
import me.askingg.mayhem.enchant.enchants.Flight;
import me.askingg.mayhem.enchant.enchants.Gatherer;
import me.askingg.mayhem.entities.EntityDeath;
import me.askingg.mayhem.entities.bosses.BossCommand;
import me.askingg.mayhem.entities.bosses.BossListGUI;
import me.askingg.mayhem.entities.dungeons.DungeonsCore;
import me.askingg.mayhem.events.BlockPlace;
import me.askingg.mayhem.events.EntityDamage;
import me.askingg.mayhem.events.PlayerInteract;
import me.askingg.mayhem.events.PlayerJoin;
import me.askingg.mayhem.events.PlayerQuit;
import me.askingg.mayhem.events.ServerListPing;
import me.askingg.mayhem.exp.ExpGUI;
import me.askingg.mayhem.gangs.GangCommand;
import me.askingg.mayhem.gangs.gui.PanelGUI;
import me.askingg.mayhem.giveaway.GiveawayCommand;
import me.askingg.mayhem.marry.MarryCommand;
import me.askingg.mayhem.miningrewards.Rewards;
import me.askingg.mayhem.miningrewards.RewardsCore;
import me.askingg.mayhem.multiplier.MultiplierCommand;
import me.askingg.mayhem.multiplier.MultiplierCore;
import me.askingg.mayhem.pickaxe.PickaxeGUI;
import me.askingg.mayhem.rainbow.RainbowCommands;
import me.askingg.mayhem.rainbow.RainbowLeather;
import me.askingg.mayhem.rankup.ColorGUI;
import me.askingg.mayhem.rankup.PrestigeCommand;
import me.askingg.mayhem.rankup.RankupCommand;
import me.askingg.mayhem.reaction.PlayerChat;
import me.askingg.mayhem.reaction.ReactionCommand;
import me.askingg.mayhem.reaction.ReactionCore;
import me.askingg.mayhem.scoreboard.ScoreboardCommand;
import me.askingg.mayhem.scoreboard.ScoreboardCore;
import me.askingg.mayhem.scoreboard.ScoreboardGUI;
import me.askingg.mayhem.tokens.TokenCommand;
import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Message;
import me.askingg.mayhem.zombies.MiscEvents;
import me.askingg.mayhem.zombies.SIgnEvents;
import me.askingg.mayhem.zombies.ZombieDeath;
import me.askingg.mayhem.zombies.ZombiesCommand;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin {

	public static Main instance;
	public static HashMap<Player, Integer> bps = new HashMap<Player, Integer>();
	public static HashMap<Player, Long> timeofjoin = new HashMap<Player, Long>();
	public static HashMap<Player, Integer> onlinetimebroken = new HashMap<Player, Integer>();
	public static HashMap<String, Integer> broken = new HashMap<String, Integer>(); // Player, Amount
	public static HashMap<String, Integer> gangBroken = new HashMap<String, Integer>(); // Gang, Amount
	public static HashMap<String, Integer> violations = new HashMap<String, Integer>(); // Player, Amount
	public static Economy eco = null;
	public static Permission perm = null;
	public static String prefix = "&c&lMayhem&b&lMines&3&lBeta &8// &7";

	public void onEnable() {
		instance = this;
		if (setupEconomy() && setupPermissions()) {
			Files.base();
			if (Files.data.getConfigurationSection("Users") != null) {
				for (String str : Files.data.getConfigurationSection("Users").getKeys(false)) {
					broken.put(str, Files.data.getInt("Users." + str + ".Broken"));
				}
			}
			if (Files.data.getConfigurationSection("Gangs") != null) {
				for (String str : Files.data.getConfigurationSection("Gangs").getKeys(false)) {
					gangBroken.put(str, Files.data.getInt("Gangs." + str + ".Broken"));
				}
			}
			ReactionCore.reactionInstance(this);
			getServer().getPluginManager().registerEvents(new PlayerChat(), this);
			getServer().getPluginManager().registerEvents(new BlockBreak(), this);
			getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
			getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
			getServer().getPluginManager().registerEvents(new EnchantGUI(), this);
			getServer().getPluginManager().registerEvents(new BlockBreak(), this);
			getServer().getPluginManager().registerEvents(new EchoCore(), this);
			getServer().getPluginManager().registerEvents(new EchoGUI(), this);
			getServer().getPluginManager().registerEvents(new BlockPlace(), this);
			getServer().getPluginManager().registerEvents(new Flight(), this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.exp.BlockBreak(), this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.enchant.enchants.BlockBreak(), this);
			getServer().getPluginManager().registerEvents(new Gatherer(), this);
			getServer().getPluginManager().registerEvents(new PickaxeGUI(), this);
			getServer().getPluginManager().registerEvents(new ExpGUI(), this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.events.PlayerChat(), this);
			getServer().getPluginManager().registerEvents(new EntityDamage(), this);
			getServer().getPluginManager().registerEvents(new ColorGUI(), this);
			getServer().getPluginManager().registerEvents(new DropPartyGUIMain(), this);
			getServer().getPluginManager().registerEvents(new ScoreboardCore(this), this);
			getServer().getPluginManager().registerEvents(new ScoreboardGUI(), this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.events.BlockBreak(), this);
			getServer().getPluginManager().registerEvents(new Help(this), this);
			getServer().getPluginManager().registerEvents(new Showcase(), this);
			getServer().getPluginManager().registerEvents(new PanelGUI(), this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.entities.PlayerInteract(), this);
			getServer().getPluginManager().registerEvents(new EntityDeath(), this);
			getServer().getPluginManager().registerEvents(new Rewards(), this);
			getServer().getPluginManager().registerEvents(new Check(), this);
			getServer().getPluginManager().registerEvents(new OpenContainer(), this);
			getServer().getPluginManager().registerEvents(new BossListGUI(), this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.chatbot.PlayerChat(), this);
			getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
			getServer().getPluginManager().registerEvents(new violations(), this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.entities.EntityDamage(), this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.enchant.sword.CEGui(), this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.enchant.sword.enchants.EntityDamage(),
					this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.enchant.sword.enchants.PlayerInteract(),
					this);
			getServer().getPluginManager().registerEvents(new Grinder(), this);
			getServer().getPluginManager().registerEvents(new SIgnEvents(), this);
			getServer().getPluginManager().registerEvents(new MiscEvents(), this);
			getServer().getPluginManager().registerEvents(new ZombieDeath(), this);
			getServer().getPluginManager().registerEvents(new EchoUpgradeGUI(), this);
			getServer().getPluginManager().registerEvents(new MinerListGUI(), this);
			getServer().getPluginManager().registerEvents(new MinerCore(), this);
			getServer().getPluginManager().registerEvents(new Profile(), this);
			getServer().getPluginManager().registerEvents(new ServerListPing(), this);
			getServer().getPluginManager().registerEvents(new BPS(), this);
			getServer().getPluginManager().registerEvents(new MuteChat(), this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.crates.PlayerInteract(), this);
			getServer().getPluginManager().registerEvents(new PreviewGUI(), this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.crates.BlockPlace(), this);
			getServer().getPluginManager().registerEvents(new StaffChat(), this);
			getServer().getPluginManager().registerEvents(new me.askingg.mayhem.gangs.gui.ColorGUI(), this);
			getServer().getPluginManager().registerEvents(new HopperCore(), this);
			getServer().getPluginManager().registerEvents(new MinerUpgradeGUI(), this);
			getCommand("updates").setExecutor(new Updates());
			getCommand("mayhemreload").setExecutor(new MayhemReload());
			getCommand("rewards").setExecutor(new Rewards());
			getCommand("autosell").setExecutor(new AutosellCommand());
			getCommand("tokens").setExecutor(new TokenCommand());
			getCommand("echo").setExecutor(new EchoCommand());
			getCommand("gang").setExecutor(new GangCommand());
			getCommand("rankup").setExecutor(new RankupCommand());
			getCommand("canirestart").setExecutor(new canirestart());
			getCommand("dropparty").setExecutor(new DropPartyCommand());
			getCommand("scoreboard").setExecutor(new ScoreboardCommand());
			getCommand("topblocksbroken").setExecutor(new TopBlocksBroken());
			getCommand("reaction").setExecutor(new ReactionCommand());
			getCommand("replace").setExecutor(new Replace());
			getCommand("showcase").setExecutor(new Showcase());
			getCommand("referredby").setExecutor(new ReferredBy());
			getCommand("playtime").setExecutor(new PlayTime());
			getCommand("rewards").setExecutor(new Rewards());
			getCommand("check").setExecutor(new Check());
			getCommand("mvote").setExecutor(new Vote());
			getCommand("links").setExecutor(new Links());
			getCommand("boss").setExecutor(new BossCommand());
			getCommand("violations").setExecutor(new violations());
			getCommand("new").setExecutor(new New());
			getCommand("customenchant").setExecutor(new CustomEnchantCommand());
			getCommand("rainbow").setExecutor(new RainbowCommands());
			getCommand("grinder").setExecutor(new Grinder());
			getCommand("fix").setExecutor(new Fix());
			getCommand("zombies").setExecutor(new ZombiesCommand());
			getCommand("miner").setExecutor(new MinerCommand());
			getCommand("restartcount").setExecutor(new restartcount());
			getCommand("flesh").setExecutor(new Flesh());
			getCommand("profile").setExecutor(new Profile());
			getCommand("multiplier").setExecutor(new MultiplierCommand());
			getCommand("treasure").setExecutor(new Treasure());
			getCommand("renametokens").setExecutor(new RenameTokens());
			getCommand("rename").setExecutor(new Rename());
			getCommand("clearchat").setExecutor(new ClearChat());
			getCommand("mutechat").setExecutor(new MuteChat());
			getCommand("crate").setExecutor(new CrateCommands());
			getCommand("prestige").setExecutor(new PrestigeCommand());
			getCommand("giveaway").setExecutor(new GiveawayCommand());
			getCommand("staffchat").setExecutor(new StaffChat());
			getCommand("marry").setExecutor(new MarryCommand());
			ReactionCore.beginReactions();
			if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
				new Placeholders().register();
			}
			ScoreboardCore.startTimer();
			DungeonsCore.setupDungeons();
			MinerCore.setupMiners();
			MultiplierCore.setupMultipliers();
			BPS.setupBPS();
			CratesCore.setupCrates();
			new MinerUpgradeCore();
		} else {
			Message.console("&cThe plugin was disabled: Vault was not found");
			getServer().getPluginManager().disablePlugin(this);
		}
		RainbowLeather.start();
	}

	public static WorldEditPlugin getWE() {
		return (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
	}

	public void onDisable() {
		try {
			for (String str : broken.keySet()) {
				Files.data.set("Users." + str + ".Broken", broken.get(str));
			}
			Files.data.save(Files.dataFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (MinerCore.mining.contains(p)) {
				MinerCore.leaveMiner(p);
			}
			if (MultiplierCore.moneyTime.containsKey(p)) {
				RewardsCore.setReward(p, MultiplierCore.multiItem("Money", MultiplierCore.moneyMulti.get(p),
						(int) (MultiplierCore.moneyTime.get(p) / 1000)));
				MultiplierCore.moneyMulti.remove(p);
				MultiplierCore.moneyTime.remove(p);
			}
			if (MultiplierCore.tokenTime.containsKey(p)) {
				RewardsCore.setReward(p, MultiplierCore.multiItem("Token", MultiplierCore.tokenMulti.get(p),
						(int) (MultiplierCore.tokenTime.get(p) / 1000)));
				MultiplierCore.tokenMulti.remove(p);
				MultiplierCore.tokenTime.remove(p);
			}
			DateFormat df = new SimpleDateFormat("dd/MM/yy|HH;mm;ss");
			df.setTimeZone(TimeZone.getTimeZone("GMT"));

		}
		TreasureCore.shutDown();
	}

	public static void restart() {
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
						"tm bc &4&lRESTART\\n&cServer restarting in: 10");
			}
		}, 20);
		Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
						"tm bc &4&lRESTART\\n&cServer restarting in: 9");
			}
		}, 40);
		Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
						"tm bc &4&lRESTART\\n&cServer restarting in: 8");
			}
		}, 60);
		Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
						"tm bc &4&lRESTART\\n&cServer restarting in: 7");
			}
		}, 80);
		Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
						"tm bc &4&lRESTART\\n&cServer restarting in: 6");
			}
		}, 100);
		Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
						"tm bc &4&lRESTART\\n&cServer restarting in: 5");
			}
		}, 120);
		Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
						"tm bc &4&lRESTART\\n&cServer restarting in: 4");
			}
		}, 140);
		Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
						"tm bc &4&lRESTART\\n&cServer restarting in: 3");
			}
		}, 160);
		Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
						"tm bc &4&lRESTART\\n&cServer restarting in: 2");
			}
		}, 180);
		Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
						"tm bc &4&lRESTART\\n&cServer restarting in: 1");
			}
		}, 200);
		Main.instance.getServer().getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(),
						"tm bc &4&lRESTART\\n&cServer restarting in: NOW");
			}
		}, 220);

	}

	public boolean setupEconomy() {
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		eco = rsp.getProvider();
		return eco != null;
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			perm = permissionProvider.getProvider();
		}
		return (perm != null);
	}
}
