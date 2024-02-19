package de.silver.blockwars.commands;

import de.silver.blockwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TpaCommand implements CommandExecutor {

    public static HashMap<Player, Player> tp = new HashMap<>();
    public static Set<Player> cooldown = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return false;

        Player p = (Player) commandSender;

        if (args.length != 1) {
            p.sendMessage(Main.prefix + "Du musst den Command so nutzen: /tpa <Spieler/accept/deny>");
            return false;
        }
        Player t = Bukkit.getPlayer(args[0]);
        if (t == null) {
            p.sendMessage(Main.prefix + "Dieser Spieler ist nicht Online!");
            return false;
        }
        if (args[0].equalsIgnoreCase(t.getName())) {
            p.sendMessage(Main.prefix + "Du hast eine Teleport-Anfrage an §b" + t.getName() + "§7 gesendet");
            t.sendMessage(Main.prefix + "Du hast eine Teleport-Anfrage von §b" + p.getName() + "§7 erhalten!");
            tp.put(t, p);
        }

        if (args[0].equalsIgnoreCase("accept")) {
            if (tp.containsKey(p)) {
                Player tP = tp.get(p); // toPlayer
                p.sendMessage(Main.prefix + "Du hast die Teleport-Anfrage von §b" + tP + "§7 angenommen!");
                p.sendMessage(Main.prefix + "Du wirst in 3 Sekunden Teleportiert. Bewege dich solange §cnicht§7!");
                cooldown.add(p);
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                    if (cooldown.contains(p)) {
                        p.sendMessage(Main.prefix + "Du wirst in 2 Sekunden Teleportiert. Bewege dich solange §cnicht§7!");
                    }
                }, 20L);
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                    if (cooldown.contains(p)) {
                        p.sendMessage(Main.prefix + "Du wirst in 1 Sekunde Teleportiert. Bewege dich solange §cnicht§7!");
                    }
                }, 40L);
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                    if (cooldown.contains(p)) {
                        p.sendMessage(Main.prefix + "Du wurdest zu §b"+ tP.getName() + "Teleportiert");
                        p.teleport(tP.getLocation());
                        tP.playSound(tP.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, 1, 1);
                        p.playSound(tP.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, 1, 1);
                        cooldown.remove(p);
                    }
                }, 60L);
            }

        } else if (args[0].equalsIgnoreCase("deny")) {

        }


        return false;
    }
}
