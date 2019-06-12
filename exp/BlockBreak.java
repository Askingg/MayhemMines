package me.askingg.mayhem.exp;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.askingg.mayhem.utils.Format;

public class BlockBreak implements Listener {

	@EventHandler
	public void blockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getInventory().getItemInMainHand();
		if (i.getType().toString().contains("PICKAXE")) {
			ItemMeta m = i.getItemMeta();
			if (m.hasLore()) {
				if (m.getLore().get(0).equals(Format.color("&8&m+-----------------------+"))) {
					EXPCore.addLore(p, i);
				}
				int x = -1;
				for (String str : m.getLore()) {
					x++;
					if (str.contains("Points")) {
						break;
					}
				}
				if (m.getLore().get(x - 1).contains("Prestige")) {
					return;
				}
				List<String> l = m.getLore();
				l.add(x, Format.color("&4Prestige &c0"));
				m.setLore(l);
				i.setItemMeta(m);
				p.updateInventory();
			} else {
				EXPCore.addLore(p, i);
			}
		}
	}
}
