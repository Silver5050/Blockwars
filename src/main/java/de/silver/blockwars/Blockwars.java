package de.silver.blockwars;

import org.bukkit.plugin.java.JavaPlugin;

public final class Blockwars extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gm").setExecutor(new gmCommand());
        getCommand("heal").setExecutor(new HealCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
