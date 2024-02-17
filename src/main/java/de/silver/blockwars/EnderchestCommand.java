package de.silver.blockwars;

import de.silver.blockwars.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player p = (Player) sender;
        if (p.hasPermission("blockwars.command.enderchest")) {
            p.openInventory(p.getEnderChest());
            p.sendMessage(Main.prefix + "Du Hast deine Enderchest Geöffnet!");
            return false;
        }
        p.sendMessage(Main.prefix + "Dazu Hast du keine Rechte!");
        return false;

    }
}
