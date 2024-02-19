package de.silver.blockwars.listener;

import de.silver.blockwars.Main;
import de.silver.blockwars.commands.TpaCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class onMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (TpaCommand.cooldown.contains(p)) {
            p.sendMessage(Main.prefix + "Der Teleport wurde abgebrochen, da du dich Bewegt hast");
            TpaCommand.cooldown.remove(p);
        }
    }
}
