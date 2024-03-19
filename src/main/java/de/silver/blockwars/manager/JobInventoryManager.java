package de.silver.blockwars.manager;

import de.silver.blockwars.Main;
import de.silver.blockwars.utils.ItemCreator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class JobInventoryManager{


    public static Inventory JobInventory() {
        Inventory inv = Bukkit.createInventory(null, 9*3, Main.prefix + "Jobs");

        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, new ItemCreator().material(Material.GRAY_STAINED_GLASS_PANE).displayName("§9 ").build());
        }

        inv.setItem(11, new ItemCreator().material(Material.WOODEN_SWORD).displayName("§4§lKÄMPFER-JOB ").build());
        inv.setItem(13, new ItemCreator().material(Material.WOODEN_PICKAXE).displayName("§7§lMINER-JOB ").build());
        inv.setItem(15, new ItemCreator().material(Material.WOODEN_AXE).displayName("§§2§lBAUM-FÄLLER ").build());

        return inv;
    }

}
