package de.silver.blockwars;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {

    private static String prefix = "§bBlockWars §f| ";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender.hasPermission("Blockwars.Discord")) {
            sender.sendMessage(prefix + "Discord : https://discord.gg/UattFRRVcY");
        }
        return false;
    }
}
