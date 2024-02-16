package de.silver.blockwars;


import de.silver.blockwars.handlers.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;



public final class Blockwars extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage("§7======================================");
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bStatus: §aenabled");
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bVersion: §6" + this.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bDeveloper: §6" + this.getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage("§7======================================");

        getCommand("abfall").setExecutor(new AbfallCommand());
        getCommand("tpa").setExecutor(new CommandHandler(this));
        getCommand("tpaccept").setExecutor(new CommandHandler(this));
        getCommand("tpdeny").setExecutor(new CommandHandler(this));
        getCommand("tpyes").setExecutor(new CommandHandler(this));
        getCommand("tpno").setExecutor(new CommandHandler(this));
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
        Bukkit.getConsoleSender().sendMessage("§7======================================");
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bStatus: §adisabled");
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bVersion: §6" + this.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bDeveloper: §6" + this.getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage("§7======================================");


    }
}
