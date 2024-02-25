package de.silver.blockwars.commands;

import de.silver.blockwars.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartKickCommand implements CommandExecutor {

    public static boolean StartTimer = false;
    public static Player targetPlayer;


    @Override
    public boolean onCommand(CommandSender s, Command cmd, String string, String[] args) {
        if(!(s instanceof Player)) return false;

        Player p = (Player) s;
        if (!p.hasPermission("blockwars.command.votekick")) {
            p.sendMessage(Main.prefix + "Dazu hast du keine Berechtigung");
            return false;
        }

        if (StartTimer == true) {
            p.sendMessage(Main.prefix + "Es läuft schon ein Startkick!");
            return false;
        }

        if (args.length <= 1) {
            p.sendMessage(Main.prefix + "Du musst den Command so nutzen: /startkick <Name> [Grund]");
            return false;
        }

        String message = "";
        for(int i = 1; i != args.length; i++)
            message += args[i] + " ";

        Player t = Bukkit.getPlayer(args[0]);
        if (t==null) {
            p.sendMessage(Main.prefix + "Dieser Spieler ist nicht Online");
        } else if(t==p){
            p.sendMessage(Main.prefix + "Du kannst dich nicht selbst voten");
        }

        TextComponent yes = new TextComponent(Main.prefix + "§aStimme für [ja] ab");
        TextComponent no = new TextComponent(Main.prefix + "§cStimme für [nein] ab");
        yes.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/votekick ja"));
        yes.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aStimme für [Ja] ab").create()));
        no.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/votekick nein"));
        no.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cStimme für [Nein] ab").create()));
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage("§8-------------------[§bVoteKick§8]---------------------");
            all.sendMessage(Main.prefix + "");
            all.sendMessage(Main.prefix + "Soll der Spieler " + t.getName() + " für 5min");
            all.sendMessage(Main.prefix + "rausgeworfen werden?");
            all.sendMessage(Main.prefix + "");
            all.sendMessage(Main.prefix + "Grund: " + message);
            all.sendMessage(Main.prefix + "Dauer: 30sek");
            all.sendMessage(Main.prefix + "");
            all.spigot().sendMessage(no);
            all.spigot().sendMessage(yes);
            all.sendMessage(Main.prefix + "");
            all.sendMessage("§8-------------------[§bVoteKick§8]---------------------");
            all.playSound(all.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 1);
        }
        targetPlayer = t;
        StartTimer = true;


        return false;
    }
}