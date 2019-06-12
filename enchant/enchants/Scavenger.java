package me.askingg.mayhem.enchant.enchants;

import java.util.Random;

import me.askingg.mayhem.gangs.GangCore;
import me.askingg.mayhem.tokens.TokensCore;
import me.askingg.mayhem.utils.Format;
import org.bukkit.entity.Player;

import me.askingg.mayhem.enchant.EnchantCore;

public class Scavenger {

    private static double getTokens(Player p){
        Double t = TokenHunter.tokens(p);
        double tt = (TokensCore.multi(p) + 1) * t;
        TokensCore.addNoMsg(p, t);
        return tt;
    }

    private static double getCredits(Player p){
        return GangCredits.credits(p);
    }

    private static void rewardPlayer(Player p, double tt, double t2){
        Double t = TokenHunter.tokens(p);
        String m = "&bTokens\n&8● &7Max &b&l" + Format.number(TokenHunter.max(p)) + "\n&8● &7Min &b&l"
                + Format.number(TokenHunter.min(p)) + "\n&8● &7Base &b&l" + Format.number(t)
                + "\n&8● &7Multiplier &b&l" + Format.decimals(1, ((TokensCore.multi(p) + 1) * 100))
                + "%\n&8● &7Total &b&l" + Format.number(tt) + "\n\n&cCredits\n";
        if (GangCore.hasGang(p)) {
            GangCore.addCredits(t2, GangCore.getGang(p));
            m = m + "&8● &7Max &c&l" + Format.number(GangCredits.max(p)) + "\n&8● &7Min &c&l"
                    + Format.number(GangCredits.min(p));
        } else {
            m = m + "&8● &cNot in a gang";
        }
        Format.hover(p, "&8&l[&4&lScavenger&8&l] &cYou found " + Format.number(tt) + " tokens and "
                + Format.number(t2) + " credits", "", m);

    }

    public static void applyScavenger(Player p) {
        Random r = new Random();
        Double d = 0.001;
        if (EnchantCore.hasCE(p, "Scavenger")) {
            d = d + (EnchantCore.level(p, "Scavenger") * 0.00009);
        }
        Double x = r.nextDouble();
        if (x <= d){
            Double tt = getTokens(p);
            Double t2 = getCredits(p);
            rewardPlayer(p, tt, t2);
        }
    }
    public static void applyScavenger(Player p, double tokenMulti, double creditsMulti){
        Random r = new Random();
        Double d = 0.001;
        if (EnchantCore.hasCE(p, "Scavenger")) {
            d = d + (EnchantCore.level(p, "Scavenger") * 0.00009);
        }
        Double x = r.nextDouble();
        if (x <= d){
            Double tt = getTokens(p)*tokenMulti;
            Double t2 = getCredits(p)*creditsMulti;
            rewardPlayer(p, tt, t2);
        }

    }

}
