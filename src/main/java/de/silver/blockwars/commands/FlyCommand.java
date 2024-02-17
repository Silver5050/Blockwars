package de.silver.blockwars.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import de.silver.blockwars.Main;


public class FlyCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {

       if (sender.hasPermission("blockwars.command.fly")) {
           Player p = (Player) sender;
           if (!p.getAllowFlight()) {
               p.setAllowFlight(true);
               p.setFlying(true);
               p.sendMessage(Main.prefix + "Du kannst Jetzt fliegen!");
           }else {
               p.setAllowFlight(false);
               p.setFlying(false);
               p.sendMessage(Main.prefix + "§4Du kannst jetzt nicht mehr fliegen!");
           }
       }else {
           sender.sendMessage(Main.prefix + "§4Dazu hast du keine rechte");
       }
        return false;
    }
}
