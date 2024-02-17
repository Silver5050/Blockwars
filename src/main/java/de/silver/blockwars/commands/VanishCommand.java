package de.silver.blockwars.commands;

import de.silver.blockwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class VanishCommand implements CommandExecutor {

    private final Set<UUID> vanishedPlayers = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player targetPlayer = null;
        if (sender.hasPermission("blockwars.command.vanish"))

        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Main.prefix + "Bitte definire einen Spieler wenn du es schon von der console ausfürst!");

                return true;
            }

            targetPlayer = (Player) sender;

        } else {
            // /vanish <player> -> toggle status of other player
            targetPlayer = Bukkit.getPlayer(args[0]);

            if (targetPlayer == null) {
                sender.sendMessage(Main.prefix + "Spieler " + args[0] + " wurde nicht gefunden.");

                return true;
            }
        }

        UUID uniqueId = targetPlayer.getUniqueId();
        boolean isVanished = vanishedPlayers.contains(uniqueId);

        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
            if (otherPlayer.equals(targetPlayer))
                continue;

            if (isVanished)
                otherPlayer.showPlayer(targetPlayer);

            else
                otherPlayer.hidePlayer(targetPlayer);
        }

        sender.sendMessage(Main.prefix + "Spieler " + targetPlayer.getName() + " ist jetzt " + (isVanished ? "Sichtbar" : "vanished") + ".");

        if (isVanished)
            vanishedPlayers.remove(uniqueId);
        else
            vanishedPlayers.add(uniqueId);

        return true;
    }
}