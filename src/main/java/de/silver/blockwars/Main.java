package de.silver.blockwars;

import de.silver.blockwars.commands.*;
import de.silver.blockwars.listener.*;
import de.silver.blockwars.sql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;



public final class Main extends JavaPlugin {

    private static Main instance;

    public static String prefix = "§f[§b§lBlockWars§f] §7";
    private MySQL mysql;

    @Override
    public void onEnable() {

        instance = this;
        setupMySQL();

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

        Bukkit.getConsoleSender().sendMessage("§7======================================");
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bStatus: §adisabled");
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bVersion: §6" + this.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§bBlock§3Wars §7| §bDeveloper: §6" + this.getDescription().getAuthors());
        Bukkit.getConsoleSender().sendMessage("§7======================================");


    }

    private void setupMySQL() {
        mysql = new MySQL();
        mysql.update("CREATE TABLE IF NOT EXISTS player(UUID varchar(64), NAME varchar(32), COINS bigint, PLAYTIME bigint, CLAN varchar(4), IP varchar(32), GEMS bigint)");
        mysql.update("CREATE TABLE IF NOT EXISTS clans(NAME varchar(32), TAG varchar(4), MODS varchar(999), TRUSTED varchar(999), HOME varchar(999))");
        mysql.update("CREATE TABLE IF NOT EXISTS friends(UUID varchar(64), FRIENDS varchar(9999))");
    }

    public static Main getInstance() {
        return instance;
    }


    public MySQL getMysql() {
        return mysql;
    }

}
