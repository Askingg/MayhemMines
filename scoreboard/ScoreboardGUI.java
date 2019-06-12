package me.askingg.mayhem.scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Files;
import me.askingg.mayhem.utils.Format;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;

public class ScoreboardGUI implements Listener {

	public static List<Player> open = new ArrayList<Player>();
	private static HashMap<Player, String> mode = new HashMap<Player, String>();

	@SuppressWarnings("deprecation")
	public static Inventory menu(Player p) {
		if (!mode.containsKey(p)) {
			mode.put(p, "modules");
		}
		Inventory menu = Bukkit.createInventory(null, 45, Format.color("&c&lScoreboard Editor"));
		borders(p, menu);
		if (mode.get(p).equalsIgnoreCase("modules")) {
			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("PlayerName")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lPlayer Name"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Player &b&l" + p.getName())));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lPlayer Name"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Player &b&l" + p.getName())));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("Online")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lOnline Player Count"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(
						PlaceholderAPI.setPlaceholders(p, "&7 &7 Online &b&l" + Bukkit.getOnlinePlayers().size())));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lOnline Player Count"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(
						PlaceholderAPI.setPlaceholders(p, "&7 &7 Online &b&l" + Bukkit.getOnlinePlayers().size())));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("Money")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lMoney"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Money &b&l%vault_eco_balance_formatted%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lMoney"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Money &b&l%vault_eco_balance_formatted%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("MoneyMulti")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lMoney Multiplier"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 MoneyMulti &b&l%mayhem_moneymulti%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lMoney Multiplier"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 MoneyMulti &b&l%mayhem_moneymulti%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("Tokens")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lTokens"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Tokens &b&l%mayhem_tokenbal%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lTokens"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Tokens &b&l%mayhem_tokenbal%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("TokenMulti")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lToken Multiplier"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 TokenMulti &b&l%mayhem_tokenmulti%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lToken Multiplier"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 TokenMulti &b&l%mayhem_tokenmulti%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("Flesh")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lFlesh"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Flesh &b&l%mayhem_flesh%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lFlesh"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Flesh &b&l%mayhem_flesh%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("DungeonKills")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lDungeonKills"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 DungeonKills &b&l" + Format.number(Files.data.getInt("Users." + p.getUniqueId() + ".Kills")))));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lDungeonKills"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 DungeonKills &b&l" + Format.number(Files.data.getInt("Users." + p.getUniqueId() + ".Kills")))));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("RankUp")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lRank"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 RankUp &b&l%mayhem_rankup%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lRank"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 RankUp &b&l%mayhem_rankup%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("RankupProgress")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lRankup Progress"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Progress &b&l%mayhem_rankuppercent%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lRankup Progress"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Progress &b&l%mayhem_rankuppercent%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("RankupCost")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lRankup Cost"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Progress &b&l%mayhem_rankupcost%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lRankup Cost"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Progress &b&l%mayhem_rankupcost%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("GangName")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lGang Name"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Gang &b&l%mayhem_gangname%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lGang Name"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Gang &b&l%mayhem_gangname%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("GangLevel")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lGang Level"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 GangLevel &b&l%mayhem_ganglevel%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lGang Level"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 GangLevel &b&l%mayhem_ganglevel%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("GangCredits")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lGang Credits"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 GangCredits &b&l%mayhem_gangcredits%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lGang Credits"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 GangCredits &b&l%mayhem_gangcredits%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("BlocksBroken")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lBlocks Broken"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Broken &b&l%mayhem_broken%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lBlocks Broken"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 Broken &b&l%mayhem_broken%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("BPS")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lAverage BlocksPerSecond"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 AvgBPS &b&l%mayhem_broken%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lAverage BlocksPerSecond"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 AvgBPS &b&l%mayhem_broken%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("ReactionWins")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lReaction Wins"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 ReactionWins &b&l%mayhem_reactionwins%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lReaction Wins"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&7 &7 ReactionWins &b&l%mayhem_reactionwins%")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Scoreboard").contains("EditScoreboard")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lEdit Your Scoreboard"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&c &c &lEdit Your Board &b&l/sb edit")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lEdit Your Scoreboard"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				l.add(Format.color("&7"));
				l.add(Format.color("&7Preview: "));
				l.add(Format.color(PlaceholderAPI.setPlaceholders(p, "&c&lEdit Your Scoreboard &b&l/sb edit")));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}
		} else {
			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("14")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 14"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 14"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("13")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 13"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 13"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("12")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 12"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 12"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("11")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 11"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 11"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("10")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 10"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 10"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("9")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 9"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 9"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("8")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 8"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 8"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("7")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 7"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 7"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("6")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 6"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 6"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("5")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 5"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 5"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("4")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 4"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 4"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("3")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 3"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 3"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}

			if (Files.data.getStringList("Users." + p.getUniqueId() + ".Seperators").contains("2")) {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 3);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&b&lSeperator at line 2"));
				l.add(Format.color("&bEnabled"));
				l.add(Format.color("&7Click to &cdisable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			} else {
				ItemStack i = new ItemStack(Material.WOOL);
				List<String> l = new ArrayList<String>();
				l.add(Format.color("&7"));
				i.setDurability((byte) 14);
				ItemMeta m = i.getItemMeta();
				m.setDisplayName(Format.color("&c&lSeperator at line 2"));
				l.add(Format.color("&cDisabled"));
				l.add(Format.color("&7Click to &benable"));
				m.setLore(l);
				i.setItemMeta(m);
				menu.addItem(i);
			}
		}
		return menu;
	}

	@EventHandler
	public void click(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (open.contains(p)) {
			e.setCancelled(true);
			ItemStack i = e.getCurrentItem();
			if (mode.get(p).equals("modules")) {
				if (i != null) {
					if (i.hasItemMeta()) {
						ItemMeta m = i.getItemMeta();
						if (m.hasDisplayName()) {
							if (i.getType() == Material.WOOL) {
								if (m.getDisplayName().contains(Format.color("Player Name"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("PlayerName")) {
											l.remove("PlayerName");
										} else {
											l.add("PlayerName");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Online Player Count"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("Online")) {
											l.remove("Online");
										} else {
											l.add("Online");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Money Multiplier"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("MoneyMulti")) {
											l.remove("MoneyMulti");
										} else {
											l.add("MoneyMulti");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Money"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("Money")) {
											l.remove("Money");
										} else {
											l.add("Money");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Tokens"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("Tokens")) {
											l.remove("Tokens");
										} else {
											l.add("Tokens");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Token Multiplier"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("TokenMulti")) {
											l.remove("TokenMulti");
										} else {
											l.add("TokenMulti");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Flesh"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("Flesh")) {
											l.remove("Flesh");
										} else {
											l.add("Flesh");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("DungeonKills"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("DungeonKills")) {
											l.remove("DungeonKills");
										} else {
											l.add("DungeonKills");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Rankup Progress"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("RankupProgress")) {
											l.remove("RankupProgress");
										} else {
											l.add("RankupProgress");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Rankup Cost"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("RankupCost")) {
											l.remove("RankupCost");
										} else {
											l.add("RankupCost");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Rank"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("RankUp")) {
											l.remove("RankUp");
										} else {
											l.add("RankUp");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Gang Name"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("GangName")) {
											l.remove("GangName");
										} else {
											l.add("GangName");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Gang Level"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("GangLevel")) {
											l.remove("GangLevel");
										} else {
											l.add("GangLevel");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Gang Credits"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("GangCredits")) {
											l.remove("GangCredits");
										} else {
											l.add("GangCredits");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Blocks Broken"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("BlocksBroken")) {
											l.remove("BlocksBroken");
										} else {
											l.add("BlocksBroken");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Average BlocksPerSecond"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("BPS")) {
											l.remove("BPS");
										} else {
											l.add("BPS");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Reaction Wins"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("ReactionWins")) {
											l.remove("ReactionWins");
										} else {
											l.add("ReactionWins");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
								if (m.getDisplayName().contains(Format.color("Edit Your Scoreboard"))) {
									try {
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Scoreboard");
										if (l.contains("EditScoreboard")) {
											l.remove("EditScoreboard");
										} else {
											l.add("EditScoreboard");
										}
										Files.data.set("Users." + p.getUniqueId() + ".Scoreboard", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
							}
							if (m.getDisplayName().equals(Format.color("&b&lModules"))) {
								mode.put(p, "seperators");
								open.add(p);
								p.openInventory(menu(p));
							}
							if (m.getDisplayName().equals(Format.color("&b&lSeperators"))) {
								mode.put(p, "modules");
								open.add(p);
								p.openInventory(menu(p));
							}
						}
					}
				}
			} else {
				if (i != null) {
					if (i.hasItemMeta()) {
						ItemMeta m = i.getItemMeta();
						if (m.hasDisplayName()) {
							if (i.getType() == Material.WOOL) {
								if (m.getDisplayName().contains(Format.color("Seperator at line "))) {
									try {
										String[] s = ChatColor.stripColor(Format.color(m.getDisplayName())).split("line ");
										List<String> l = Files.data
												.getStringList("Users." + p.getUniqueId() + ".Seperators");
										if (l.contains(s[1])) {
											l.remove(s[1]);
										} else {
											l.add(s[1]);
										}
										Files.data.set("Users." + p.getUniqueId() + ".Seperators", l);
										Files.data.save(Files.dataFile);
										open.add(p);
										p.openInventory(menu(p));
										return;
									} catch (Exception ex) {
										ex.printStackTrace();
										return;
									}
								}
							}
							if (m.getDisplayName().equals(Format.color("&b&lModules"))) {
								mode.put(p, "seperators");
								open.add(p);
								p.openInventory(menu(p));
							}
							if (m.getDisplayName().equals(Format.color("&b&lSeperators"))) {
								mode.put(p, "modules");
								open.add(p);
								p.openInventory(menu(p));
							}
						}
					}
				}
			}
		}
	}

	private static void borders(Player p, Inventory inv) {
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 7);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		ItemStack i2 = new ItemStack(Material.STAINED_GLASS_PANE);
		i2.setDurability((byte) 11);
		ItemMeta m2 = i2.getItemMeta();
		List<String> l2 = new ArrayList<String>();
		if (mode.get(p).equals("modules")) {
			m2.setDisplayName(Format.color("&b&lModules"));
			l2.add(Format.color("&7"));
			l2.add(Format.color("&7Currently editing &bmodules"));
			l2.add(Format.color("&7Click to edit &bseperators"));
		} else {
			m2.setDisplayName(Format.color("&b&lSeperators"));
			l2.add(Format.color("&7"));
			l2.add(Format.color("&7Currently editing &bseperators"));
			l2.add(Format.color("&7Click to edit &bmodules"));
		}
		m2.setLore(l2);
		i2.setItemMeta(m2);
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
		inv.setItem(26, i);
		inv.setItem(27, i);
		inv.setItem(35, i);
		inv.setItem(36, i);
		inv.setItem(37, i);
		inv.setItem(38, i);
		inv.setItem(39, i);
		inv.setItem(40, i2);
		inv.setItem(41, i);
		inv.setItem(42, i);
		inv.setItem(43, i);
		inv.setItem(44, i);
	}

	@EventHandler
	public void close(InventoryCloseEvent e) {
		if (open.contains(e.getPlayer())) {
			open.remove(e.getPlayer());
		}
	}

}
