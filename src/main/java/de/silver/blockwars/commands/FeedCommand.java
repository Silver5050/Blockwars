package de.silver.blockwars.commands;

import de.silver.blockwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return false;

        Player p = (Player) commandSender;

        if (!p.hasPermission("blockwars.command.feed")) {
            p.sendMessage(Main.prefix + "Dazu hast du keine Berechtigung!");
            return false;
        }

        if (args.length > 1) {
            p.sendMessage(Main.prefix + "Du musst den Command so nutzen: /feed");
            return false;
        }

        if (args.length == 0) {
            p.sendMessage(Main.prefix + "Du hast deinen Hunger gestillt!");
            p.setFoodLevel(20);
            p.setSaturation(10);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
        } else if (args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            p.sendMessage(Main.prefix + "Du hast den Hunger von§b " + t.getName() + "§7 gestillt");
            t.sendMessage(Main.prefix + "Du hast deinen Hunger gestillt!");
            t.setFoodLevel(20);
            t.setSaturation(10);
            t.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
        }


        return false;
    }
}
