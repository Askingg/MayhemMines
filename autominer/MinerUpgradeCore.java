package me.askingg.mayhem.autominer;

import me.askingg.mayhem.utils.Files;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MinerUpgradeCore {

    private static HashMap<String, Integer> maxLevels = new HashMap<String, Integer>();
    private static HashMap<String, Integer> baseCost = new HashMap<String, Integer>();
    private static HashMap<String, Integer> costUpgrade = new HashMap<String, Integer>();
    private static HashMap<String, String> currency = new HashMap<String, String>();
    
    public MinerUpgradeCore(){
        maxLevels.put("TimeReduce", 10);
        maxLevels.put("MoreMoney", 10);
        maxLevels.put("MoreBps", 8);
        maxLevels.put("MoreTokens", 25);
        maxLevels.put("MoreCredits", 10);
        maxLevels.put("DroprateRelic", 10);
        maxLevels.put("DroprateArtefact", 10);
        maxLevels.put("MinimumRelic", 15);
        maxLevels.put("MinimumArtefact", 15);
        maxLevels.put("RewardsInventory", 1);

        baseCost.put("TimeReduce", 1 );
        baseCost.put("MoreMoney", 2);
        baseCost.put("MoreBps", 3);
        baseCost.put("MoreTokens", 4);
        baseCost.put("MoreCredits", 1);
        baseCost.put("DroprateRelic", 2);
        baseCost.put("DroprateArtefact", 3);
        baseCost.put("MinimumRelic", 4);
        baseCost.put("MinimumArtefact", 1);
        baseCost.put("RewardsInventory", 2);

        costUpgrade.put("TimeReduce", 1 );
        costUpgrade.put("MoreMoney", 2);
        costUpgrade.put("MoreBps", 3);
        costUpgrade.put("MoreTokens", 4);
        costUpgrade.put("MoreCredits", 1);
        costUpgrade.put("DroprateRelic", 2);
        costUpgrade.put("DroprateArtefact", 3);
        costUpgrade.put("MinimumRelic", 4);
        costUpgrade.put("MinimumArtefact", 1);
        costUpgrade.put("RewardsInventory", 2);

        currency.put("TimeReduce", "Money" );
        currency.put("MoreMoney", "Money");
        currency.put("MoreBps", "Money");
        currency.put("MoreTokens", "Money");
        currency.put("MoreCredits", "Tokens");
        currency.put("DroprateRelic","Tokens");
        currency.put("DroprateArtefact","Tokens");
        currency.put("MinimumRelic", "Tokens");
        currency.put("MinimumArtefact", "Flesh");
        currency.put("RewardsInventory", "Flesh");



    }

    public static int getLevel(Player p, String name){
        return(Files.data.getInt("Users." + p.getUniqueId() + ".MinerUpgrades." + name));
    }

    public static double getDoubleResult(Player p, String name){
        double x = getLevel(p, name);
        if( name.equalsIgnoreCase("TimeReduce")){
            x = x*0.05; //max level 10
        }
        if (name.equalsIgnoreCase("MoreMoney")){
            x = x*0.1; //max level 10
        }
        if (name.equalsIgnoreCase("MoreBps")){
            x = x*0.25; //max level 8
        }
        if (name.equalsIgnoreCase("MoreTokens")){
            x = x*0.1; //max level 25
        }
        if(name.equalsIgnoreCase("MoreCredits")){
            x = x*0.05; //max level 10
        }
        if(name.equalsIgnoreCase("DroprateRelic") || name.equalsIgnoreCase("DroprateArtefact")){
            x = x*0.000005; //max level 10
        }
        if(name.equalsIgnoreCase("MinimumRelic") || name.equalsIgnoreCase("MinimumArtefact")){
            x = x*5; //max level 15
        }
        return x;
    }

    public static boolean getBooleanResult(Player p, String name){
        if(name.equalsIgnoreCase("RewardsInventory")){
            return ( getLevel(p, name) == 1);
        }
        return false;
    }


    public static int getMaxLevel(String name){
        return maxLevels.get(name);
    }

    public static int getBaseCost(String name){
        return baseCost.get(name);
    }

    public static int getCostUpgrade(String name){
        return costUpgrade.get(name);
    }

    public static String getCurrency(String name){
        return currency.get(name);
    }

    public static void upgradeLevel(Player p, String name){
        Files.data.set("Users." + p.getUniqueId() + ".MinerUpgrades." + name, getLevel(p, name) +1);
    }



}
