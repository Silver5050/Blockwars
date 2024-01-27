package de.silver.blockwars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCommand implements CommandExecutor {

    private static String prefix = "§bBlockWars §f| ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player p = (Player) sender;
        if (p.hasPermission("Blockwars.Enderchest")) {
            p.openInventory(p.getEnderChest());
            p.sendMessage(prefix + "Du Hast deine Enderchest Geöffnet!");
            return false;
        }
        p.sendMessage(prefix + "Dazu Hast du keine Rechte!");
        return false;

    }
}
