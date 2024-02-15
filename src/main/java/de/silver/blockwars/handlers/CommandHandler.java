package de.silver.blockwars.handlers;

import de.silver.blockwars.Blockwars;
import de.silver.blockwars.events.SuccessfulTpaEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommandHandler implements CommandExecutor {

    private final Blockwars plugin;

    public CommandHandler(Blockwars plugin) {
        this.plugin = plugin;
    }

    static HashMap<UUID, UUID> targetMap = new HashMap<>();

    private static String prefix = "§f[§bBlockWars§f] ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(prefix + ChatColor.RED + "Dieser command ist nur für Spieler!");
            return true;
        }

        if (command.getName().equals("tpa")) {
            handleTpaCommand(sender, args);
            return true;
        }

        if (command.getName().equals("tpaccept") || command.getName().equals("tpyes")) {
            handleTpaAcceptCommad(sender);
            return true;
        }

        if (command.getName().equals("tpdeny") || command.getName().equals("tpno")) {
            handleTpaDenyCommand(sender);
            return true;
        }

        return false;
    }

    private void handleTpaDenyCommand(CommandSender sender) {
        if (!sender.hasPermission("Blockwars.tpa.deny")) {
            sender.sendMessage(prefix + ChatColor.RED + "Dazu hast du keine Rechte!");
            return;
        }

        final Player senderP = (Player) sender;

        // Check if a request exists
        if (!targetMap.containsValue(senderP.getUniqueId())) {
            sender.sendMessage(prefix + ChatColor.GOLD + "Du hast keine ausstehenden Anfragen!");
            return;
        }

        for (Map.Entry<UUID, UUID> entry : targetMap.entrySet()) {
            if (entry.getValue().equals(senderP.getUniqueId())) {
                targetMap.remove(entry.getKey());
                Player originalSender = Bukkit.getPlayer(entry.getKey());
                originalSender.sendMessage(prefix + ChatColor.GOLD + "Deine TPA wurde abgelehnt!");
                sender.sendMessage(prefix + ChatColor.GOLD + "Abgelehnte TPA anfrage.");
                break;
            }
        }
    }

    private void handleTpaAcceptCommad(CommandSender sender) {
        // Check permission
        if (!sender.hasPermission("Blockwars.tpa.accept")) {
            sender.sendMessage(prefix + ChatColor.RED + "Dazu hast du keine Rechte!");
            return;
        }

        final Player senderP = (Player) sender;

        // Check if there's a pending TPA request
        if (!targetMap.containsValue(senderP.getUniqueId())) {
            sender.sendMessage(prefix + ChatColor.GOLD + "Du hast keine ausstehenden Anfragen!");
            return;
        }

        sender.sendMessage(prefix + ChatColor.GOLD + "Angenommene TPA anfrage!");

        for (Map.Entry<UUID, UUID> entry : targetMap.entrySet()) {
            if (entry.getValue().equals(senderP.getUniqueId())) {
                Player tpRequester = Bukkit.getPlayer(entry.getKey());

                // Fire the successful TPA event.
                // Used for integration with other plugins
                SuccessfulTpaEvent event = new SuccessfulTpaEvent(tpRequester, tpRequester.getLocation());
                Bukkit.getPluginManager().callEvent(event);

                // Teleport the player
                tpRequester.teleport(senderP);

                // Remove the pending request
                targetMap.remove(entry.getKey());
                break;
            }
        }
    }

    private void handleTpaCommand(CommandSender sender, String[] args) {
        // Check permissions
        if (!sender.hasPermission("Blockwars.tpa.tpa")) {
            sender.sendMessage(prefix + ChatColor.RED + "Dazu hast du keine Rechte!");
            return;
        }

        // Check if a player name was provided
        if (args.length != 1) {
            sender.sendMessage(prefix + ChatColor.RED + "ERROR!");
            return;
        }

        // Check if the target player exists
        if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
            sender.sendMessage(prefix + ChatColor.RED + "Der Spieler ist nicht online!");
            return;
        }

        Player target = Bukkit.getPlayer(args[0]);
        final Player senderP = (Player) sender;

        // Check if the target player is the requesting player
        if (target.getUniqueId().equals(senderP.getUniqueId())) {
            sender.sendMessage(prefix + ChatColor.RED + "Zu dir selber geht nich!");
            return;
        }

        // Check if there's a pending request
        if (targetMap.containsKey(senderP.getUniqueId())) {
            sender.sendMessage(prefix + ChatColor.GOLD + "Du hast eine ausschtehendende Anfrage!");
            return;
        }

        // Send the target a message informing them of the request
        target.sendMessage(prefix + ChatColor.RED + senderP.getName() + ChatColor.GOLD + " will sich zu dir Teleportieren. \nSchreibe " + ChatColor.RED + "/tpaccept" + ChatColor.GOLD + " zum annehmen.\nSchreibe " + ChatColor.RED + "/tpdeny" + ChatColor.GOLD + " zum ablehnen.\nDie anfrage verfähllt nach 5 min.");
        targetMap.put(senderP.getUniqueId(), target.getUniqueId());

        // Inform the requester that the request was sent
        sender.sendMessage(prefix + ChatColor.GOLD + "Du hast eine anfrage an " + ChatColor.RED + target.getName());

        // Remove the request after a certain time
        (new BukkitRunnable() {
            public void run() {
                CommandHandler.targetMap.remove(senderP.getUniqueId());
            }
        }).runTaskLaterAsynchronously(this.plugin, 6000L);

    }
}
