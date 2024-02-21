package de.silver.blockwars.commands;

import de.silver.blockwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if(player.hasPermission("blockwars.command.heal")) {
            if(args.length == 0) {
                player.sendMessage(Main.prefix + "§aDu Hast dich geheilt!");
                player.setFoodLevel(20);
                player.setHealth(20);

            } else if(args.length == 1 ) {
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null) {
                    target.setHealth(20);
                    target.setFoodLevel(20);
                    target.sendMessage(Main.prefix + "Du wurdest §bgeheilt!");
                    player.sendMessage(Main.prefix + "§aDu heilst §6" + target.getPlayerListName());
                } else {
                    player.sendMessage(Main.prefix + "§cDer spieler §6" + args[0] + " §cist nicht auf dem server!");
                }
            } else {
                player.sendMessage(Main.prefix + "§cBenutze §6/heal <Player>§c!");
            }
        } else {
            player.sendMessage(Main.prefix + "§cDazu hast du keine rechte!");
        }



        return false;
    }

}