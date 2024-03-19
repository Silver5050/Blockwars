package de.silver.blockwars.commands;

import de.silver.blockwars.manager.JobInventoryManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JobCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;

        Player p = (Player) commandSender;

        p.openInventory(JobInventoryManager.JobInventory());
        p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);


        return false;
    }
}
