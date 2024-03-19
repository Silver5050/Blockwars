package de.silver.blockwars.listener;

import de.silver.blockwars.Main;
import de.silver.blockwars.sql.MySQL;
import de.silver.blockwars.sql.PlayerSQL;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

public class JobListener implements Listener {


    @EventHandler
    public void onClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().contains("Jobs")) {
            e.setCancelled(true);
            if (e.getRawSlot() == 11) {
                if (!PlayerSQL.getJob(p.getUniqueId()).equals("fighter")) {
                    PlayerSQL.setJob(p.getUniqueId(), "fighter");
                    p.sendMessage(Main.prefix + "Du hast deinen Job zu §4§lKÄMPFER §7gewechselt!");
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                }
            }
            if (e.getRawSlot() == 13) {
                if (!PlayerSQL.getJob(p.getUniqueId()).equals("miner")) {
                    PlayerSQL.setJob(p.getUniqueId(), "miner");
                    p.sendMessage(Main.prefix + "Du hast deinen Job zu §7§MINEN-ARBEITER §7gewechselt!");
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                }
            }
            if (e.getRawSlot() == 15) {
                if (!PlayerSQL.getJob(p.getUniqueId()).equals("forager")) {
                    PlayerSQL.setJob(p.getUniqueId(), "forager");
                    p.sendMessage(Main.prefix + "Du hast deinen Job zu §2§LBAUM-FÄLLER §7gewechselt!");
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                }
            }
        }

    }

}
