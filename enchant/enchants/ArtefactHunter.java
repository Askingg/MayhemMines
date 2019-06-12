package me.askingg.mayhem.enchant.enchants;

import me.askingg.mayhem.echo.EchoCore;
import me.askingg.mayhem.enchant.EnchantCore;
import me.askingg.mayhem.miningrewards.RewardsCore;
import me.askingg.mayhem.utils.Message;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ArtefactHunter {

    private static boolean applyCheck(Player p, double d) {
        Random r = new Random();

        if (EnchantCore.hasCE(p, "ArtefactHunter")) {
            d = d + (EnchantCore.level(p, "ArtefactHunter") * d);
            double x = r.nextDouble();
            if (x <= d) {
                return true;
            }
        }
        return false;
    }

    public static void applyArtefactHunter(Player p){
            double d = 0.00005;
            if (applyCheck(p, d )) {
                Message.playerRaw("&8&l[&4&lArtefactHunter&8&l] &cYou found an artefact", p);
                p.getInventory().addItem(EchoCore.randomArtefact());
                p.updateInventory();
            }
    }

    public static void applyArtefactHunter(Player p, double xtra, double min, boolean r){
        Double d = 0.00005 + xtra;

        if (applyCheck(p, d)){
            Message.playerRaw("&8&l[&3&lArtefactHunter&8&l] &bYou found an artefact", p);

            ItemStack i = EchoCore.randomArtefact();
            while(EchoCore.artefactBoost(i) < min){
                i = EchoCore.randomArtefact();
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

