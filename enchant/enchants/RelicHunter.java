package me.askingg.mayhem.enchant.enchants;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.askingg.mayhem.echo.EchoCore;
import me.askingg.mayhem.enchant.EnchantCore;
import me.askingg.mayhem.miningrewards.RewardsCore;
import me.askingg.mayhem.utils.Message;

public class RelicHunter {

    private static boolean applyCheck(Player p, double d) {
        Random r = new Random();

        if (EnchantCore.hasCE(p, "RelicHunter")) {
            d = d + (EnchantCore.level(p, "RelicHunter") * d);
            double x = r.nextDouble();
            if (x <= d) {
                return true;
            }
        }
            return false;
    }


    public static void applyRelichunter(Player p){
        double d = 0.00005;
        if(applyCheck(p, d)){
            Message.playerRaw("&8&l[&3&lRelicHunter&8&l] &bYou found a relic", p);
            p.getInventory().addItem(EchoCore.randomRelic());
            p.updateInventory();
        }
    }




    public static void applyRelichunter(Player p, double xtra, double min, boolean r){
        Double d = 0.00005 + xtra;

            if (applyCheck(p, d)){
                Message.playerRaw("&8&l[&3&lRelicHunter&8&l] &bYou found a relic", p);

                ItemStack i = EchoCore.randomRelic();
                while(EchoCore.relicBoost(i) < min){
                    i = EchoCore.randomRelic();
                }

                if(!r){
                p.getInventory().addItem(i);
                p.updateInventory();
                }

                else{
                    RewardsCore.setReward(p, i);
                }

            }
        }


    }

