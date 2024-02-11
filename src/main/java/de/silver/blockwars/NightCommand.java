package de.silver.blockwars;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.World;

public class NightCommand implements CommandExecutor {

    private static String prefix = "§f[§bBlockWars§f] ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;
        if (p.hasPermission("Blockwars.night")) {
            for (World world : Bukkit.getServer().getWorlds()) {
                world.setTime(18000L);
            }
            p.sendMessage(prefix + "Du hast die Zeit auf Nacht gestellt!");
            return false;
        }
        p.sendMessage(prefix + "§4Dazu hast du keine Rechte!");
        return false;
    }
}
