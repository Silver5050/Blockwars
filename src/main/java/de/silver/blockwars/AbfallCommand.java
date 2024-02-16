package de.silver.blockwars;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

public class AbfallCommand implements CommandExecutor {

    private static String prefix = "§f[§bBlockWars§f] ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
                if (p.hasPermission("Blockwars.abfall")) {
                    p.openInventory(Bukkit.createInventory((InventoryHolder)null, 18, "Abfall"));
                    p.playSound(p.getLocation(), Sound.BLOCK_LAVA_POP, 2.0F, 1.0F);

                }else {
                    p.sendMessage(prefix + "Dazu hast du keine Rechte!");
                }

        }

        return false;
    }
}
