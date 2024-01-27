package de.silver.blockwars;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;

public class WorkBenchCommand implements CommandExecutor {

    private static String prefix = "§bBlockWars §f| ";

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (s.hasPermission("Blockwars.WorkeBench"))
        if((s instanceof Player)) {
            Player p = (Player) s;
            p.openWorkbench(null, true);
            p.sendMessage(prefix + "Du hast deine Werkbank geöffnet!");
        }
        return false;
    }
}