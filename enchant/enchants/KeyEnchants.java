package me.askingg.mayhem.enchant.enchants;

import me.askingg.mayhem.enchant.EnchantCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class KeyEnchants {
    public static void applyKeyEnchants(Player p){
        Random r = new Random();
        double d ;
        d = r.nextDouble();
        if (d <= 0.001) {
            int a = 1;
            int k = r.nextInt(100) + 1;
            String key = "";
            if (EnchantCore.hasCE(p, "KeyQuality")) {
                if (EnchantCore.level(p, "KeyQuality") == 20) {
                    if (k <= 33)
                        key = "Argon";
                    if (k > 33 && k < 91)
                        key = "Krypton";
                    if (k >= 91)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 19) {
                    if (k <= 33)
                        key = "Argon";
                    if (k > 33 && k < 91)
                        key = "Krypton";
                    if (k >= 91)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 18) {
                    if (k <= 35)
                        key = "Argon";
                    if (k > 35 && k < 91)
                        key = "Krypton";
                    if (k >= 91)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 17) {
                    if (k <= 37)
                        key = "Argon";
                    if (k > 37 && k < 92)
                        key = "Krypton";
                    if (k >= 92)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 16) {
                    if (k <= 40)
                        key = "Argon";
                    if (k > 40 && k < 92)
                        key = "Krypton";
                    if (k >= 92)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 15) {
                    if (k <= 40)
                        key = "Argon";
                    if (k > 40 && k < 92)
                        key = "Krypton";
                    if (k >= 92)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 14) {
                    if (k <= 40)
                        key = "Argon";
                    if (k > 40 && k < 92)
                        key = "Krypton";
                    if (k >= 92)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 13) {
                    if (k <= 40)
                        key = "Argon";
                    if (k > 40 && k < 92)
                        key = "Krypton";
                    if (k >= 92)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 12) {
                    if (k <= 40)
                        key = "Argon";
                    if (k > 40 && k < 93)
                        key = "Krypton";
                    if (k >= 93)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 11) {
                    if (k <= 45)
                        key = "Argon";
                    if (k > 45 && k < 93)
                        key = "Krypton";
                    if (k >= 93)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 10) {
                    if (k <= 45)
                        key = "Argon";
                    if (k > 45 && k < 94)
                        key = "Krypton";
                    if (k >= 94)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 9) {
                    if (k <= 45)
                        key = "Argon";
                    if (k > 45 && k < 95)
                        key = "Krypton";
                    if (k >= 95)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 8) {
                    if (k <= 45)
                        key = "Argon";
                    if (k > 45 && k < 95)
                        key = "Krypton";
                    if (k >= 95)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 7) {
                    if (k <= 45)
                        key = "Argon";
                    if (k > 45 && k < 95)
                        key = "Krypton";
                    if (k >= 95)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 6) {
                    if (k <= 50)
                        key = "Argon";
                    if (k > 50 && k < 96)
                        key = "Krypton";
                    if (k >= 96)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 5) {
                    if (k <= 50)
                        key = "Argon";
                    if (k > 50 && k < 96)
                        key = "Krypton";
                    if (k >= 96)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 4) {
                    if (k <= 50)
                        key = "Argon";
                    if (k > 50 && k < 96)
                        key = "Krypton";
                    if (k >= 96)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 3) {
                    if (k <= 55)
                        key = "Argon";
                    if (k > 55 && k < 96)
                        key = "Krypton";
                    if (k >= 96)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 2) {
                    if (k <= 55)
                        key = "Argon";
                    if (k > 55 && k < 97)
                        key = "Krypton";
                    if (k >= 97)
                        key = "Xenon";
                }
                if (EnchantCore.level(p, "KeyQuality") == 1) {
                    if (k <= 55)
                        key = "Argon";
                    if (k > 55 && k < 98)
                        key = "Krypton";
                    if (k >= 98)
                        key = "Xenon";
                }
            } else {
                if (k <= 60)
                    key = "Argon";
                if (k > 60 && k < 98)
                    key = "Krypton";
                if (k >= 98)
                    key = "Xenon";
            }
            if (EnchantCore.hasCE(p, "KeyHunter")) {
                a += EnchantCore.level(p, "KeyHunter");
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                    "crate give " + p.getName() + " " + key + " " + a);
        }
    }
}

