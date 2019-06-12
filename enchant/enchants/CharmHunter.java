package me.askingg.mayhem.enchant.enchants;

import me.askingg.mayhem.echo.EchoCore;
import me.askingg.mayhem.enchant.EnchantCore;
import me.askingg.mayhem.miningrewards.RewardsCore;
import me.askingg.mayhem.utils.Message;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CharmHunter {
    public static void applyCharmHunter(Player p, boolean re){
        Random r = new Random();
        Double d ;
        Double x ;
        if (EnchantCore.hasCE(p, "CharmHunter")) {
            d = 0.00005;
            d = d + (EnchantCore.level(p, "CharmHunter") * 0.000005);
            x = r.nextDouble();
            if (x <= d) {
                Message.playerRaw("&8&l[&5&lCharmHunter&8&l] &dYou found a charm", p);
                ItemStack i = EchoCore.randomCharm();
                if (!re) {
                    p.getInventory().addItem(i);
                    p.updateInventory();
                }
                else{
                    RewardsCore.setReward(p,i);
                }
            }
        }
    }
}
