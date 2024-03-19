package de.silver.blockwars.listener;

import de.silver.blockwars.Main;
import de.silver.blockwars.commands.VoteKickCommand;
import de.silver.blockwars.jobs.FighterJob;
import de.silver.blockwars.jobs.ForagerJob;
import de.silver.blockwars.jobs.MinerJob;
import de.silver.blockwars.sql.PlayerSQL;
import de.silver.blockwars.utils.ScoreboardUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        Player p = e.getPlayer();

        new ScoreboardUtil(p);

        if (VoteKickCommand.startban.contains(p.getName())) {
        }

        if (!p.hasPlayedBefore()) {
            e.setJoinMessage(Main.prefix + "Der Spieler §b" + p.getName() + "§7 ist das erste Mal dem Server beigetreten!");
        }

        if (!PlayerSQL.playerExist(p.getUniqueId()) || !p.hasPlayedBefore()) {
            PlayerSQL.createPlayer(p.getUniqueId());
            FighterJob.createPlayer(p.getUniqueId());
            ForagerJob.createPlayer(p.getUniqueId());
            MinerJob.createPlayer(p.getUniqueId());
        }
    }
}
