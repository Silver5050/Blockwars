package de.silver.blockwars.commands;

import de.silver.blockwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VoteKickCommand implements CommandExecutor {

    public static ArrayList<String> yesvote = new ArrayList<>();
    public static ArrayList<String> novote = new ArrayList<>();
    public static ArrayList<String> startban = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player unbanplayer = StartKickCommand.targetPlayer;
        if (!(commandSender instanceof Player)) return false;

        Player p  = (Player) commandSender;

        if (StartKickCommand.StartTimer == false) {
            p.sendMessage(Main.prefix + "Es gibt inmoment noch kein Votekick!");
            return false;
        }

        if (args.length < 1) {
            p.sendMessage(Main.prefix + "Du musst den Command so nutzen: /votekick nein/ja");
            return false;
        }

        if (yesvote.contains(p.getName()) || novote.contains(p.getName())) {
            p.sendMessage(Main.prefix + "Du hast schon abgestimmt!");
            return false;
        }

        if (args[0].equalsIgnoreCase("nein")) {
            novote.add(p.getName());
            p.sendMessage(Main.prefix + "Du hast Erfolgreich für §cNein §7abgestimmt!");
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
        } else if (args[0].equalsIgnoreCase("ja")) {
            yesvote.add(p.getName());
            p.sendMessage(Main.prefix + "Du hast Erfolgreich für §aJa §7abgestimmt!");
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_YES, 1, 1);
        }

        Bukkit.getScheduler().runTaskLater(Main.getInstance(), ()-> {
            if (yesvote.size()>novote.size()) {
                p.sendMessage(Main.prefix + "Der Spieler" + unbanplayer.getName() + " wurde gekickt!");
                Bukkit.broadcastMessage(Main.prefix + "Der VoteKick wurde entschieden §a" + yesvote.size() + "§7/§c" + novote.size());
                unbanplayer.kickPlayer(Main.prefix + "Du wurdest aufgrund eines VoteKicks gekickt!");
                yesvote.clear();
                novote.clear();
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "ban " + unbanplayer.getName() + " " +  Main.prefix + "Du wurdest ausgesperrt :/ Versuche es in 5 Minuten wieder"); // Das hier so anpassen, dass es auf das Bansystem passt!
                StartKickCommand.StartTimer = false;
            } else {
                Bukkit.broadcastMessage(Main.prefix + "Der VoteKick wurde entschieden §a" + yesvote.size() + "§7/§c" + novote.size());
                p.sendMessage(Main.prefix + "Der Spieler" + unbanplayer.getName() + " wurde nicht gekickt!");
                yesvote.clear();
                novote.clear();
                StartKickCommand.StartTimer = false;
            }
        }, 600L);
        return false;
    }
}
