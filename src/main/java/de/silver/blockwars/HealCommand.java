package de.silver.blockwars;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    private static String prefix = "§bBLOCKWARS | ";


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("Blockwars.heal")) {
                if(args.length == 0) {
                    player.sendMessage(prefix + "§aDu Hast dich geheilt!");
                    player.setFoodLevel(20);
                    player.setHealth(20);


                } else if(args.length == 1 ) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null) {
                        target.setHealth(20);
                        target.setFoodLevel(20);
                        target.sendMessage(prefix + "§aDu wurdest geheilt!");
                        player.sendMessage(prefix + "§aDu heilst §6" + target.getPlayerListName());
                    } else {
                        player.sendMessage(prefix + "§cDer spieler §6" + args[0] + " §cist nicht auf dem server!");
                    }
                } else {
                    player.sendMessage(prefix + "§cBenutze §6/heal <Player>§c!");
                }


            } else {
                player.sendMessage(prefix + "§cDazu hast du keine rechte!");
            }

        } else {
            sender.sendMessage(prefix + "§cnur spieler dürfen diesen command benutzen!");
        }



        return false;
    }

}