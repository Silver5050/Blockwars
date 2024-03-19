package de.silver.blockwars.jobs;

import de.silver.blockwars.Main;
import de.silver.blockwars.sql.PlayerSQL;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class FighterJob implements Listener {


    private HashMap<EntityType, Double> mobList;
    public FighterJob() {
        mobList = new HashMap<>();
        registerMobs();
    }

    private void registerMobs() {
        mobList.put(EntityType.ZOMBIE, 10.0);
        mobList.put(EntityType.COW, 10.0);
        mobList.put(EntityType.SHEEP, 10.0);
        mobList.put(EntityType.PIG, 10.0);
        mobList.put(EntityType.SKELETON, 10.0);
        mobList.put(EntityType.WITHER, 120.0);
        mobList.put(EntityType.DROWNED, 10.0);
        mobList.put(EntityType.STRAY, 10.0);
        mobList.put(EntityType.SPIDER, 10.0);
        mobList.put(EntityType.BLAZE, 12.0);
        mobList.put(EntityType.CAVE_SPIDER, 10.0);
        mobList.put(EntityType.CHICKEN, 6.0);
        mobList.put(EntityType.CREEPER, 10.0);
        mobList.put(EntityType.ENDERMAN, 13.0);
        mobList.put(EntityType.EVOKER, 15.0);
        mobList.put(EntityType.GHAST, 13.0);
        mobList.put(EntityType.GUARDIAN, 10.0);
        mobList.put(EntityType.HOGLIN, 10.0);
        mobList.put(EntityType.HUSK, 10.0);
        mobList.put(EntityType.PILLAGER, 10.0);
        mobList.put(EntityType.PIGLIN, 10.0);
        mobList.put(EntityType.RABBIT, 5.0);
        mobList.put(EntityType.RAVAGER, 30.0);
        mobList.put(EntityType.SHULKER, 20.0);
        mobList.put(EntityType.SLIME, 3.0);
        mobList.put(EntityType.STRIDER, 10.0);
        mobList.put(EntityType.TURTLE, 10.0);
        mobList.put(EntityType.VINDICATOR, 10.0);
        mobList.put(EntityType.WITCH, 20.0);
        mobList.put(EntityType.WITHER_SKELETON, 10.0);
        mobList.put(EntityType.ZOMBIE_VILLAGER, 15.0);
        mobList.put(EntityType.ZOGLIN, 40.0);
        mobList.put(EntityType.WARDEN, 500.0);
        mobList.put(EntityType.PHANTOM, 20.0);
        mobList.put(EntityType.OCELOT, 69.0);
        mobList.put(EntityType.IRON_GOLEM, 20.0);
        mobList.put(EntityType.MAGMA_CUBE, 3.0);

        mobList.put(EntityType.ELDER_GUARDIAN, 100.0);
    }

    public static boolean playerExist(UUID uuid) {
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM fighter WHERE UUID='" + uuid.toString() + "'");
            return rs.next() && rs.getString("UUID") != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(UUID uuid) {
        if(!playerExist(uuid)) {
            Main.getInstance().getMysql().update("INSERT INTO fighter(UUID, EXP, LEVEL, MULTIPLIER) VALUES ('" + uuid.toString() + "', '0', '0', '1')");
        }
    }

    public static Double getExp(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM fighter WHERE UUID='" + uuid.toString() + "'");
                if(rs.next()) {
                    return rs.getDouble("EXP");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getExp(uuid);
        }
        return 0.0;
    }

    public static Float getMultiplier(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM fighter WHERE UUID='" + uuid.toString() + "'");
                if(rs.next()) {
                    return rs.getFloat("MULTIPLIER");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getMultiplier(uuid);
        }
        return 0F;
    }

    public static Integer getLevel(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM fighter WHERE UUID='" + uuid.toString() + "'");
                if(rs.next()) {
                    return rs.getInt("LEVEL");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getLevel(uuid);
        }
        return 0;
    }

    public static void updateLevel(UUID uuid, int level) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE fighter SET LEVEL='" + (getLevel(uuid) + level) + "' WHERE UUID='" + uuid.toString() + "'");
        } else {
            createPlayer(uuid);
            updateLevel(uuid, level);
        }
    }

    public static void updateExp(UUID uuid, Double exp) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE fighter SET EXP='" + (getExp(uuid) + exp) + "' WHERE UUID='" + uuid.toString() + "'");
        } else {
            createPlayer(uuid);
            updateExp(uuid, exp);
        }
    }

    public static void updateMultiplier(UUID uuid, float multiplier) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE fighter SET EXP='" + (getMultiplier(uuid) + multiplier) + "' WHERE UUID='" + uuid.toString() + "'");
        } else {
            createPlayer(uuid);
            updateMultiplier(uuid, multiplier);
        }
    }

    public static void removeMultiplier(UUID uuid, float multiplier) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE fighter SET EXP='" + (getMultiplier(uuid) - multiplier) + "' WHERE UUID='" + uuid.toString() + "'");
        }
    }

    public static void removeExp(UUID uuid, Double exp) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE fighter SET EXP='" + (getExp(uuid) - exp) + "' WHERE UUID='" + uuid.toString() + "'");
        }
    }

    public Double calculateLevel(int level) {
        double neededExp = 100;
        if(level > 0) {
            for(int i = 0; i < level; i++) {
                neededExp = (int) (neededExp * 1.5);
            }
        }
        return neededExp;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSlay(EntityDeathEvent e) {
        LivingEntity et = e.getEntity();
        if(et.getKiller() == null) return;
        if(et.getKiller() instanceof Player) {
            Player p = et.getKiller();
            if (!PlayerSQL.getJob(p.getUniqueId()).equals("fighter")) return;
            if(mobList.containsKey(et.getType())) {
                if ((getExp(p.getUniqueId()) + mobList.get(et.getType())) >= calculateLevel(getLevel(p.getUniqueId()))) {
                    removeExp(p.getUniqueId(), calculateLevel(getLevel(p.getUniqueId())));
                    updateLevel(p.getUniqueId(), 1);
                    RewardMessage(p);
                }
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0f, 2.0f);
                updateExp(p.getUniqueId(), (mobList.get(e.getEntity().getType())*getMultiplier(p.getUniqueId())));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§3+" + mobList.get(et.getType()) + " Kämpfer (" + getExp(p.getUniqueId()) + "/" + calculateLevel(getLevel(p.getUniqueId())) + ") Exp"));
                    }
                }.runTaskLater(Main.getInstance(), 5L);
            }
        } else if(et.getKiller() instanceof Arrow) {
            Arrow a = (Arrow) et.getKiller();
            if(a.getShooter() instanceof Player) {
                Player p = (Player) a.getShooter();
                if(mobList.containsKey(et.getType())) {
                    if ((getExp(p.getUniqueId()) + mobList.get(et.getType())) >= calculateLevel(getLevel(p.getUniqueId()))) {
                        removeExp(p.getUniqueId(), calculateLevel(getLevel(p.getUniqueId())));
                        updateLevel(p.getUniqueId(), 1);
                        RewardMessage(p);
                    }
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0f, 2.0f);
                    updateExp(p.getUniqueId(), (mobList.get(e.getEntity().getType())*getMultiplier(p.getUniqueId())));
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§3+" + mobList.get(et.getType()) + " Kämpfer (" + getExp(p.getUniqueId()) + "/" + calculateLevel(getLevel(p.getUniqueId())) + ") Exp"));
                        }
                    }.runTaskLater(Main.getInstance(), 5L);
                }
            }
        }
    }
    public void RewardMessage(Player p) {
        p.sendMessage("");
        p.sendMessage("§8§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        p.sendMessage("§7 LEVELUP" + FighterJob.getLevel(p.getUniqueId()));
        p.sendMessage("§8§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        p.sendMessage("");
        p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1, 1);
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }
}
