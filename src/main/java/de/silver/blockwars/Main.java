package de.silver.blockwars;

import de.silver.blockwars.commands.*;
import de.silver.blockwars.listener.*;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;



public final class Main extends JavaPlugin {

    private static Main instance;

    public static String prefix = "§f[§b§lBlockWars§f] §7";


    @Override
    public void onEnable() {

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        LuckPerms api = LuckPermsProvider.get();


        instance = this;

        Bukkit.getConsoleSender().sendMessage("§7======================================");
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bStatus: §aenabled");
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bVersion: §6" + this.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bDeveloper: §6" + this.getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage("§7======================================");

        getCommand("abfall").setExecutor(new AbfallCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gm").setExecutor(new GameModeCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("workbench").setExecutor(new WorkBenchCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("enderchest").setExecutor(new EnderchestCommand());
        getCommand("day").setExecutor(new DayCommand());
        getCommand("night").setExecutor(new NightCommand());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("startkick").setExecutor(new StartKickCommand());
        getCommand("votekick").setExecutor(new VoteKickCommand());
        getCommand("tpa").setExecutor(new TpaCommand());
        getCommand("tpahere").setExecutor(new TpaHereCommand());
        getCommand("feed").setExecutor(new TpaHereCommand());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new onJoinListener(), this);
        pm.registerEvents(new onQuitListener(), this);
        pm.registerEvents(new onBlockBreakListener(), this);
        pm.registerEvents(new onMoveListener(), this);
    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage("§7=========================================");
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bStatus: §adisabled");
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bVersion: §6" + this.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bDeveloper: §6" + this.getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage("§7=========================================");


    }

    public static Main getInstance() {
        return instance;
    }

}
