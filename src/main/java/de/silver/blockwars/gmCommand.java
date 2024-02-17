package de.silver.blockwars;

import de.silver.blockwars.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class gmCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String S, String[] args) {
        if (s instanceof Player) {
            if (s.hasPermission("blockwars.command.gm")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("0")) {
                        ((Player) s).setGameMode(GameMode.SURVIVAL);
                        s.sendMessage(Main.prefix + "§fDu bist jetzt im Überlebensmodus");
                    } else if (args[0].equalsIgnoreCase("1")) {
                        ((Player) s).setGameMode((GameMode.CREATIVE));
                        s.sendMessage(Main.prefix + "§fDu bist jetzt im Kreativmodus");
                    } else if (args[0].equalsIgnoreCase("2")) {
                        ((Player) s).setGameMode(GameMode.ADVENTURE);
                        s.sendMessage(Main.prefix + "§fDu bist jetzt im Abenteuermodus");
                    } else if (args[0].equalsIgnoreCase("3")) {
                        ((Player) s).setGameMode(GameMode.SPECTATOR);
                        s.sendMessage(Main.prefix + "§fDu bist jetzt im spectatormodus");
                    }else {
                        s.sendMessage(Main.prefix + "§fBitte verwende /gm 0-3 (name)");
                    }
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("0")) {
                        if (Bukkit.getPlayer(args[1]) != null) {

                        }
                    }
                }
            }
        }
        return false;
    }
}