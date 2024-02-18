package de.silver.blockwars.listener;

import de.silver.blockwars.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Random;

public class onBlockBreakListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getItemMeta().getEnchants().containsKey(Enchantment.SILK_TOUCH) && e.getBlock().getType().equals(Material.SPAWNER)) {
            if (p.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_PICKAXE) || p.getInventory().getItemInMainHand().getType().equals(Material.NETHERITE_PICKAXE)) {
                Random random = new Random();
                int randomNumber = random.nextInt(1000) + 1;
                ItemStack spawner = new ItemStack(Material.SPAWNER);
                if (randomNumber == 696) {
                    Item dropItem = p.getWorld().dropItem(p.getLocation(), spawner);
                    dropItem.setPickupDelay(0);
                    Bukkit.broadcastMessage("§8-------------------[§bSpawner-DROP§8]-------------------");
                    Bukkit.broadcastMessage(Main.prefix + " ");
                    Bukkit.broadcastMessage(Main.prefix + "Der Spieler §b" + p.getName() + "§7 hat einen Spawner gedroppt");
                    Bukkit.broadcastMessage(Main.prefix + "§7Chance: 1/1000 §8§o(0,01%)");
                    Bukkit.broadcastMessage(Main.prefix + " ");
                    Bukkit.broadcastMessage("§8-------------------[§bSpawner-DROP§8]-------------------");
                    for (Player all : Bukkit.getOnlinePlayers()) {
                        all.playSound(all.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1);
                    }
                }
            }
        }

    }
}
