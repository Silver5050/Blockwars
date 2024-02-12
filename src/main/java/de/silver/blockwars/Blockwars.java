package de.silver.blockwars;

import MySQL.MySQL;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Blockwars extends JavaPlugin {

    public static MySQL mysql;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gm").setExecutor(new gmCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("workbench").setExecutor(new WorkBenchCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("enderchest").setExecutor(new EnderchestCommand());
        getCommand("day").setExecutor(new DayCommand());
        getCommand("night").setExecutor(new NightCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadMySQLFile() {
        File MySQL = new File("plugins/blockwars/MySQL.yml");
        YamlConfiguration yMySQL = YamlConfiguration.loadConfiguration(MySQL);
        yMySQL.addDefault("MySQL.Host", (Object) "localhost");
        yMySQL.addDefault("MySQL.Port", (Object)"3306");
        yMySQL.addDefault("MySQL.Database", (Object)"gg");
        yMySQL.addDefault("MySQL.User", (Object)"root");
        yMySQL.addDefault("MySQL.Password", (Object)"123");
        yMySQL.options().copyDefaults(true);
        try {
            yMySQL.save(MySQL);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
