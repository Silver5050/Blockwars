package de.silver.blockwars.commands;

import de.silver.blockwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AbfallCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
                if (p.hasPermission("blockwars.command.abfall")) {
                    p.openInventory(Bukkit.createInventory(null, 18, "Abfall"));
                    p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 2.0F, 1.0F);

                }else {
                    p.sendMessage(Main.prefix + "Dazu hast du keine Rechte!");
                }

        }

        return false;
    }
}
