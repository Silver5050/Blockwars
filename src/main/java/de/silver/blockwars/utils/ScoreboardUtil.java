package de.silver.blockwars.utils;

import de.silver.blockwars.Main;
import de.silver.blockwars.commands.scoreboard.ScoreboardBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardUtil extends ScoreboardBuilder {

    public ScoreboardUtil(Player p) {
        super(p, "  " + "§b§lBLOCKWARS" + "  ");
    }


    @Override
    public void createScoreboard() {
        setScore("§3 ", 9);
        setScore(" §7Rang:", 8);
        setScore(" §8➥ test "  , 7);
        setScore("§c ", 6);
        setScore("§c ", 5);
        setScore(" §bMünzen:", 4);
        setScore(" §8➥ §e %coins%€", 3);
        setScore("§d ", 2);
        setScore("§bstore.blockwars.net", 1);
    }

    @Override
    public void update() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ScoreboardUtil scoreboardUtil = new ScoreboardUtil(player);
            scoreboardUtil.createScoreboard();
            scoreboardUtil.update();
        }
    }
}

