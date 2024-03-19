package de.silver.blockwars.utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemCreator {

    private Material mat;
    private boolean glow;
    private Integer amount;
    private String display;
    private List<String> lore;
    private Map<Enchantment, Integer> enchantments;

    public ItemCreator() {
        this.enchantments = new HashMap<>();
    }

    public ItemCreator(ItemStack item) {
        this.enchantments = new HashMap<>();
        this.mat = item.getType();
        this.amount = item.getAmount();
        if (item.hasItemMeta()) {
            if (item.getItemMeta().hasDisplayName()) {
                this.display = item.getItemMeta().getDisplayName();
            }
            if (item.getItemMeta().hasLore()) {
                this.lore = item.getItemMeta().getLore();
            }
            if (item.getItemMeta().hasEnchants()) {
                this.enchantments = item.getItemMeta().getEnchants();
            }
        }
    }

    public ItemCreator material(Material mat) {
        this.mat = mat;
        return this;
    }

    public ItemCreator amount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemCreator displayName(String display) {
        this.display = display;
        return this;
    }

    public ItemCreator lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemCreator withGlow(boolean glows) {
        this.glow = glows;
        return this;
    }

    public ItemCreator addEnchant(Enchantment ench, int level) {
        this.enchantments.put(ench, level);
        return this;
    }

    public ItemCreator removeEnchant(Enchantment ench) {
        if (!this.enchantments.containsKey(ench)) {
            return this;
        }
        this.enchantments.remove(ench);
        return this;
    }

    public Material getMaterial() {
        return this.mat;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public String getDisplayName() {
        return this.display;
    }

    public List<String> getLore() {
        return this.lore;
    }

    public Map<Enchantment, Integer> getEnchantments() {
        return this.enchantments;
    }

    public ItemStack build() {
        ItemStack item = null;
        if (this.mat == null) {
            return item;
        }
        if (this.amount == null) {
            this.amount = 1;
        }
        if (!this.enchantments.isEmpty()) {
            item.addUnsafeEnchantments(this.enchantments);
        }
        item = new ItemStack(this.mat, this.amount);
        if (this.display != null || this.lore != null) {
            ItemMeta meta = item.getItemMeta();
            if (this.display != null) {
                meta.setDisplayName(this.display);
            }
            if (this.lore != null) {
                meta.setLore(this.lore);
            }
            if (this.glow == true) {
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
            item.setItemMeta(meta);
        }
        return item;
    }

}
