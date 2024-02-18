package de.silver.blockwars.commands;

import de.silver.blockwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;

        Player p = (Player) commandSender;

        if (!p.hasPermission("blockwars.command.invsee")){
            p.sendMessage(Main.prefix + "Dazu hast du keine Berechtigung");
            return false;
        }
        if (strings.length < 1) {
            p.sendMessage(Main.prefix + "Du musst den Command so nutzen: /invsee <Spieler>");
            return false;
        }
        Player t = Bukkit.getPlayer(strings[0]);

        if (t==null) {
            p.sendMessage(Main.prefix + "Dieser Spieler ist nicht Online!");
            return false;
        }
        if (t==p) {
            p.sendMessage(Main.prefix + "Du kannst nicht dein eigenes Inventar öffnen!");
            return false;
        }
        p.openInventory(t.getInventory());
        p.sendMessage(Main.prefix + "Du hast das Inventar von §b" + t.getName() + "§7 geöffnet!");

        return false;
    }
}
