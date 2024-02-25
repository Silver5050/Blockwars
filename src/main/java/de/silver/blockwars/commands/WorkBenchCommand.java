package de.silver.blockwars.commands;

import de.silver.blockwars.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorkBenchCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (s.hasPermission("blockwars.command.workebench"))
        if((s instanceof Player)) {
            Player p = (Player) s;
            p.openWorkbench(null, true);
            p.sendMessage(Main.prefix + "Du hast deine Werkbank geöffnet!");
        }
        return false;
    }
}