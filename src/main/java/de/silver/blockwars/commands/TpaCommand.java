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
    public static HashSet<Player> runout = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return false;

        Player p = (Player) commandSender;
        Player t = Bukkit.getPlayer(args[0]);

        if (args.length != 1) {
            p.sendMessage(Main.prefix + "Du musst den Command so nutzen: /tpa <Spieler/accept/deny>");
            return false;
        }
        if (args[0].equalsIgnoreCase("accept")) {
            if (tp.containsKey(p)) {
                runout.remove(p);
                Player tP = tp.get(p); // toPlayer
                p.sendMessage(Main.prefix + "Du hast die Teleport-Anfrage von §b" + p.getName() + "§7 angenommen!");
                tP.sendMessage(Main.prefix + "Du wirst in §b3 §7Sekunden Teleportiert. Bewege dich solange §cnicht§7!");
                tP.playSound(tP.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                cooldown.add(tP);
                tp.remove(p);
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                    if (cooldown.contains(tP)) {
                        tP.sendMessage(Main.prefix + "Du wirst in §b2 §7Sekunden Teleportiert. Bewege dich solange §cnicht§7!");
                        tP.playSound(tP.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                    }
                }, 20L);
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                    if (cooldown.contains(tP)) {
                        tP.sendMessage(Main.prefix + "Du wirst in §b1 §7Sekunde Teleportiert. Bewege dich solange §cnicht§7!");
                        tP.playSound(tP.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                    }
                }, 40L);
                Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                    if (cooldown.contains(tP)) {
                        tP.sendMessage(Main.prefix + "Du wurdest zu §b"+ tP.getName() + "§7 Teleportiert");
                        tP.teleport(p.getLocation());
                        tP.playSound(tP.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, 1, 1);
                        p.playSound(p.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, 1, 1);
                        cooldown.remove(tP);
                    }
                }, 60L);
                return false;
            }

        } else if (args[0].equalsIgnoreCase("deny")) {
            if (tp.containsKey(p)) {
                runout.remove(p);
                Player tP = tp.get(p);
                p.sendMessage(Main.prefix + "Du hast die Teleport-Anfrage von §b"+ tP.getName() + "§7 abgelehnt");
                tP.sendMessage(Main.prefix + "Deine Teleportationsanfrage wurde von §b" + p.getName() + "§7 abgelehnt!");
                return false;
            }
        }


        if (t == null) {
            p.sendMessage(Main.prefix + "Dieser Spieler ist nicht Online!");
            return false;
        }
        if (t == p) {
            p.sendMessage(Main.prefix + "Du darfst dir selbst keine Teleport-Anfrage senden!");
            return false;
        }
        if (args[0].equalsIgnoreCase(t.getName())) {
            if (!tp.isEmpty() && tp.get(t).equals(p) && tp.get(t) != null) {
                p.sendMessage(Main.prefix + "Du hast diesem Spieler schon eine Tpa gesendet");
                return false;
            }
            p.sendMessage(Main.prefix + "Du hast eine Teleport-Anfrage an §b" + t.getName() + "§7 gesendet!");
            t.sendMessage(Main.prefix + "Du hast eine Teleport-Anfrage von §b" + p.getName() + "§7 erhalten!");
            tp.put(t, p);
            runout.add(p);
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                if (runout.contains(p)) {
                    p.sendMessage(Main.prefix + "Deine Anfrage §b"+ t.getName() +"§7 ist ausgelaufen!");
                    t.sendMessage(Main.prefix + "Die Anfrage vov §b" + p.getName() + "§7 ist ausgelaufen!");
                    tp.remove(t);
                    runout.remove(p);
                }
            }, 4800L);
        }


        return false;
    }
}
