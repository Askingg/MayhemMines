package me.askingg.mayhem.utils;

import java.io.File;
import java.util.Arrays;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.askingg.mayhem.main.Main;

public class Files {

	public static File configFile;
	public static FileConfiguration config;
	public static File dataFile;
	public static FileConfiguration data;
	public static File bpsFile;
	public static FileConfiguration bps;
	public static File reportsFile;
	public static FileConfiguration reports;

	public static void base() {
		Main main = Main.getPlugin(Main.class);
		if (!main.getDataFolder().exists())
			main.getDataFolder().mkdirs();
		configFile = new File(main.getDataFolder(), "config.yml");
		config = YamlConfiguration.loadConfiguration(configFile);
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				// Bosses, Dungeons, Multipliers, UniqueScoreboard, EchoDevice, AutoMiners,
				// PickaxeLevelling, PVP, PVE, CustomEnchantments, CombatEnchants, Duels
				config.set("motd", Arrays.asList(
						"&8&l|&3&ki&b AutoMiners&3 &ki&8&l|&3&ki&b CustomEnchants&3 &ki&8&l|&3&ki&b PickaxeLevelling&3 &ki&8&l|",
						"&8&l|&3&ki&b CombatEnchants&3 &ki&8&l|&3&ki&b PVP&3 &ki&8&l|&3&ki&b PVE&3 &ki&8&l|",
						"&8&l|&3&ki&b Dungeons&3 &ki&8&l|&3&ki&b Bosses&3 &ki&8&l|&3&ki&b Duels&3 &ki&8&l|",
						"&8&l|&3&ki&b UniqueScoreboard&3 &ki&8&l|&3&ki&b Multipliers&3 &ki&8&l|&3&ki&b EchoDevice&3 &ki&8&l|",
						"&8&l|&4&ki&c AutoMiners&4 &ki&8&l|&4&ki&c CustomEnchants&4 &ki&8&l|&4&ki&c PickaxeLevelling&4 &ki&8&l|",
						"&8&l|&4&ki&c CombatEnchants&4 &ki&8&l|&4&ki&c PVP&4 &ki&8&l|&4&ki&c PVE&4 &ki&8&l|",
						"&8&l|&4&ki&c Dungeons&4 &ki&8&l|&4&ki&c Bosses&4 &ki&8&l|&4&ki&c Duels&4 &ki&8&l|",
						"&8&l|&4&ki&c UniqueScoreboard&4 &ki&8&l|&4&ki&c Multipliers&4 &ki&8&l|&4&ki&c EchoDevice&4 &ki&8&l|"));
				config.set("Shops.16.STONE;3", 10000.0);
				config.set("Shops.16.STONE;0", 20000.0);
				config.set("Shops.16.CONCRETE;3", 40000.0);
				config.set("Shops.16.CONCRETE;15", 50000.0);

				config.set("Shops.15.STONE;3", 2500.0);
				config.set("Shops.15.STONE;0", 5000.0);
				config.set("Shops.15.CONCRETE;11", 10000.0);
				config.set("Shops.15.CONCRETE;5", 15000.0);
				config.set("Shops.15.CONCRETE;3", 20000.0);
				config.set("Shops.15.CONCRETE;15", 25000.0);

				config.set("Shops.14.STONE;3", 1500.0);
				config.set("Shops.14.STONE;0", 3000.0);
				config.set("Shops.14.CONCRETE;14", 4000.0);
				config.set("Shops.14.CONCRETE;11", 5000.0);
				config.set("Shops.14.CONCRETE;5", 7500.0);
				config.set("Shops.14.CONCRETE;3", 10000.0);
				config.set("Shops.14.CONCRETE;15", 15000.0);

				config.set("Shops.13.STONE;3", 1000.0);
				config.set("Shops.13.STONE;0", 2000.0);
				config.set("Shops.13.CONCRETE;14", 3000.0);
				config.set("Shops.13.CONCRETE;11", 4000.0);
				config.set("Shops.13.CONCRETE;5", 5000.0);
				config.set("Shops.13.CONCRETE;3", 7500.0);
				config.set("Shops.13.CONCRETE;15", 10000.0);

				config.set("Shops.12.STONE;3", 750.0);
				config.set("Shops.12.STONE;0", 1500.0);
				config.set("Shops.12.CONCRETE;14", 2000.0);
				config.set("Shops.12.CONCRETE;11", 3000.0);
				config.set("Shops.12.CONCRETE;5", 4000.0);
				config.set("Shops.12.CONCRETE;3", 5000.0);
				config.set("Shops.12.CONCRETE;15", 7500.0);

				config.set("Shops.12.STONE;3", 625.0);
				config.set("Shops.11.STONE;0", 1250.0);
				config.set("Shops.11.STAINED_CLAY;4", 1500.0);
				config.set("Shops.11.CONCRETE;14", 1750.0);
				config.set("Shops.11.CONCRETE;11", 2000.0);
				config.set("Shops.11.CONCRETE;5", 3000.0);
				config.set("Shops.11.CONCRETE;3", 4000.0);
				config.set("Shops.11.CONCRETE;15", 5000.0);

				config.set("Shops.10.STONE;3", 500.0);
				config.set("Shops.10.STONE;0", 1000.0);
				config.set("Shops.10.STAINED_CLAY;4", 1250.0);
				config.set("Shops.10.CONCRETE;14", 1500.0);
				config.set("Shops.10.CONCRETE;11", 1750.0);
				config.set("Shops.10.CONCRETE;5", 2000.0);
				config.set("Shops.10.CONCRETE;3", 2500.0);

				config.set("Shops.9.STONE;3", 375.0);
				config.set("Shops.9.STONE;0", 750.0);
				config.set("Shops.9.STAINED_CLAY;4", 1000.0);
				config.set("Shops.9.CONCRETE;14", 1250.0);
				config.set("Shops.9.CONCRETE;11", 1500.0);
				config.set("Shops.9.CONCRETE;5", 1750.0);
				config.set("Shops.9.CONCRETE;3", 2000.0);

				config.set("Shops.8.STONE;3", 275.0);
				config.set("Shops.8.STONE;0", 550.0);
				config.set("Shops.8.STAINED_CLAY;1", 700.0);
				config.set("Shops.8.STAINED_CLAY;4", 800.0);
				config.set("Shops.8.CONCRETE;14", 900.0);
				config.set("Shops.8.CONCRETE;11", 1000.0);
				config.set("Shops.8.CONCRETE;5", 1250.0);

				config.set("Shops.7.STONE;3", 200.0);
				config.set("Shops.7.STONE;0", 400.0);
				config.set("Shops.7.STAINED_CLAY;1", 500.0);
				config.set("Shops.7.STAINED_CLAY;4", 600.0);
				config.set("Shops.7.CONCRETE;14", 700.0);
				config.set("Shops.7.CONCRETE;11", 800.0);
				config.set("Shops.7.CONCRETE;5", 1000.0);

				config.set("Shops.6.STONE;3", 125.0);
				config.set("Shops.6.STONE;0", 250.0);
				config.set("Shops.6.STAINED_CLAY;1", 400.0);
				config.set("Shops.6.STAINED_CLAY;4", 500.0);
				config.set("Shops.6.CONCRETE;14", 400.0);
				config.set("Shops.6.CONCRETE;11", 600.0);

				config.set("Shops.5.STONE;3", 50.0);
				config.set("Shops.5.STONE;0", 100.0);
				config.set("Shops.5.STAINED_CLAY;1", 150.0);
				config.set("Shops.5.STAINED_CLAY;4", 200.0);
				config.set("Shops.5.CONCRETE;14", 300.0);
				config.set("Shops.5.CONCRETE;11", 400.0);

				config.set("Shops.4.STONE;3", 32.5);
				config.set("Shops.4.STONE;0", 75.0);
				config.set("Shops.4.STAINED_CLAY;1", 100.0);
				config.set("Shops.4.STAINED_CLAY;4", 150.0);
				config.set("Shops.4.CONCRETE;14", 200.0);

				config.set("Shops.3.STONE;3", 30.0);
				config.set("Shops.3.STONE;0", 60.0);
				config.set("Shops.3.STAINED_CLAY;1", 75.0);
				config.set("Shops.3.STAINED_CLAY;4", 100.0);
				config.set("Shops.3.CONCRETE;14", 150.0);

				config.set("Shops.2.STONE;3", 25.0);
				config.set("Shops.2.STONE;0", 50.0);
				config.set("Shops.2.STAINED_CLAY;1", 75.0);
				config.set("Shops.2.STAINED_CLAY;4", 100.0);

				config.set("Shops.1.STONE;3", 20.0);
				config.set("Shops.1.STONE;0", 40.0);
				config.set("Shops.1.STAINED_CLAY;1", 60.0);
				config.set("Shops.1.STAINED_CLAY;4", 80.0);

				config.set("Chat.Replace.shrug", "¯\\_(ツ)_/¯");
				config.set("Chat.Replace.tableflip", "(╯°□°）╯︵ ┻━┻");
				config.set("Chat.Replace.unflip", "┬─┬ ノ( ゜-゜ノ)");
				config.set("Crystals.Common.Contents.Grass.Chance", 75.0);
				config.set("Crystals.Common.Contents.Grass.Type", "Item");
				config.set("Crystals.Common.Contents.Grass.Material", "GRASS");
				config.set("Crystals.Common.Contents.Grass.DisplayName", "&a&lGrass");
				config.set("Crystals.Common.Contents.Grass.Lore",
						Arrays.asList("", "&7Some grass from", "&7the &acommon crate"));
				config.set("Crystals.Common.Contents.Grass.message", "&7You received some grass");

				config.set("Crystals.Common.Contents.Token.Chance", 25.0);
				config.set("Crystals.Common.Contents.Token.Type", "Command");
				config.set("Crystals.Common.Contents.Token.Command", "token give %player% 5");
				config.save(configFile);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		dataFile = new File(main.getDataFolder(), "data.yml");
		data = YamlConfiguration.loadConfiguration(dataFile);
		if (!dataFile.exists()) {
			try {
				dataFile.createNewFile();
				data.save(dataFile);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		bpsFile = new File(main.getDataFolder(), "bps.yml");
		bps = YamlConfiguration.loadConfiguration(bpsFile);
		if (!bpsFile.exists()) {
			try {
				bpsFile.createNewFile();
				bps.save(bpsFile);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		reportsFile = new File(main.getDataFolder(), "reports.yml");
		reports = YamlConfiguration.loadConfiguration(reportsFile);
		if (!reportsFile.exists()) {
			try {
				reportsFile.createNewFile();
				reports.set("index", 0);
				reports.save(reportsFile);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
