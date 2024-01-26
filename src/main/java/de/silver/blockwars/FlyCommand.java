package de.silver.blockwars;

import jdk.tools.jmod.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {

       if (sender.hasPermission("Blockwars.Fly")) {
           Player p = (Player) sender;
           if (!p.getAllowFlight()) {
               p.setAllowFlight(true);
               p.setFlying(true);
               p.sendMessage("§4Du Kannst Jetzt fliegen!");
           }else {
               p.setAllowFlight(false);
               p.setFlying(false);
               p.sendMessage("§4Du kannst jetzt nicht mehr fliegen!");
           }
       }else {
           sender.sendMessage("§4Dazu hast du keine rechte");
       }
        return false;
    }
}
