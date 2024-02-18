package de.silver.blockwars.listener;

import de.silver.blockwars.commands.VoteKickCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (VoteKickCommand.startban.contains(p.getName())) {
        }
    }

}