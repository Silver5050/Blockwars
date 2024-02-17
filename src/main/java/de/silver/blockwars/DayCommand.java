package de.silver.blockwars;

import de.silver.blockwars.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Player p = (Player) sender;
        if (p.hasPermission("blockwars.command.day")) {
            for (World world : Bukkit.getServer().getWorlds()) {
                world.setTime(48000L);
            }
            p.sendMessage(Main.prefix + "Du hast die zeit auf Tag gestellt!");
            return false;
        }
        p.sendMessage(Main.prefix + "§4Dazu hast du keine Rechte!");

        return false;
    }
}
