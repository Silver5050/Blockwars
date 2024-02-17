package de.silver.blockwars;

import de.silver.blockwars.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiscordCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender.hasPermission("blockwars.command.discord")) {
            sender.sendMessage(Main.prefix + "Discord : https://discord.gg/UattFRRVcY");
        }
        return false;
    }
}
