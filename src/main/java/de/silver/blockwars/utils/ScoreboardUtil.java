package de.silver.blockwars.utils;

import de.silver.blockwars.Main;
import de.silver.blockwars.commands.scoreboard.ScoreboardBuilder;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
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
        User user = Main.getInstance().getLuckPerms().getUserManager().getUser(player.getUniqueId());
        Group group = Main.getInstance().getLuckPerms().getGroupManager().getGroup(user.getPrimaryGroup());
        setScore("§3 ", 9);
        setScore(" §7Rang:", 8);
        setScore(" §8➥ " + user  , 7);
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

