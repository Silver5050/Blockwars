package de.silver.blockwars.jobs;

import de.silver.blockwars.Main;
import de.silver.blockwars.sql.PlayerSQL;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class MinerJob implements Listener {
    private HashMap<Material, Double> blockList;

    public MinerJob() {
        this.blockList = new HashMap<>();
        registerBlocks();
    }

    private void registerBlocks() {
        blockList.put(Material.STONE, 1.0);
        blockList.put(Material.GRANITE, 1.0);
        blockList.put(Material.ANDESITE, 1.0);
        blockList.put(Material.DIORITE, 1.0);
        blockList.put(Material.NETHER_QUARTZ_ORE, 3.0);
        blockList.put(Material.NETHER_GOLD_ORE, 3.0);
        blockList.put(Material.COBBLESTONE, 1.0);
        blockList.put(Material.DEEPSLATE, 1.0);
        blockList.put(Material.COBBLED_DEEPSLATE, 1.0);
        blockList.put(Material.DIAMOND_ORE, 10.0);
        blockList.put(Material.DEEPSLATE_DIAMOND_ORE, 10.0);
        blockList.put(Material.IRON_ORE, 3.0);
        blockList.put(Material.DEEPSLATE_IRON_ORE, 3.0);
        blockList.put(Material.DEEPSLATE_GOLD_ORE, 3.0);
        blockList.put(Material.GOLD_ORE, 3.0);
        blockList.put(Material.DEEPSLATE_REDSTONE_ORE, 3.0);
        blockList.put(Material.REDSTONE_ORE, 3.0);
        blockList.put(Material.LAPIS_ORE, 3.0);
        blockList.put(Material.DEEPSLATE_LAPIS_ORE, 3.0);
        blockList.put(Material.DEEPSLATE_COAL_ORE, 2.0);
        blockList.put(Material.COAL_ORE, 2.0);
        blockList.put(Material.COPPER_ORE, 2.0);
        blockList.put(Material.DEEPSLATE_COPPER_ORE, 2.0);
        blockList.put(Material.EMERALD_ORE, 15.0);
        blockList.put(Material.DEEPSLATE_EMERALD_ORE, 15.0);

        /* Blöcke die als sich selbst droppen */
        blockList.put(Material.OBSIDIAN, 5.0);
        blockList.put(Material.ANCIENT_DEBRIS, 10.0);

    }
    public static boolean playerExist(UUID uuid) {
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM miner WHERE UUID='" + uuid.toString() + "'");
            return rs.next() && rs.getString("UUID") != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(UUID uuid) {
        if(!playerExist(uuid)) {
            Main.getInstance().getMysql().update("INSERT INTO miner(UUID, EXP, LEVEL, MULTIPLIER) VALUES ('" + uuid.toString() + "', '0', '0', '1')");
        }
    }

    public static Double getExp(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM miner WHERE UUID='" + uuid.toString() + "'");
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
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM miner WHERE UUID='" + uuid.toString() + "'");
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
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM miner WHERE UUID='" + uuid.toString() + "'");
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
            Main.getInstance().getMysql().update("UPDATE miner SET LEVEL='" + (getLevel(uuid) + level) + "' WHERE UUID='" + uuid.toString() + "'");
        } else {
            createPlayer(uuid);
            updateLevel(uuid, level);
        }
    }

    public static void updateExp(UUID uuid, Double exp) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE miner SET EXP='" + getExp(uuid) + exp + "' WHERE UUID='" + uuid.toString() + "'");
        } else {
            createPlayer(uuid);
            updateExp(uuid, exp);
        }
    }

    public static void updateMultiplier(UUID uuid, float multiplier) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE miner SET EXP='" + (getMultiplier(uuid) + multiplier) + "' WHERE UUID='" + uuid.toString() + "'");
        } else {
            createPlayer(uuid);
            updateMultiplier(uuid, multiplier);
        }
    }

    public static void removeMultiplier(UUID uuid, float multiplier) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE miner SET EXP='" + (getMultiplier(uuid) - multiplier) + "' WHERE UUID='" + uuid.toString() + "'");
        }
    }

    public static void removeExp(UUID uuid, Double exp) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE miner SET EXP='" + (getExp(uuid) - exp) + "' WHERE UUID='" + uuid.toString() + "'");
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

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (p.getWorld().getName().equals("citybuild")) return;
        if (!PlayerSQL.getJob(p.getUniqueId()).equals("miner")) return;
        ItemStack hand = p.getInventory().getItemInMainHand();
        if(hand == null) return;
        if(hand.hasItemMeta()) {
            ItemMeta meta = hand.getItemMeta();
            if(meta.getEnchants().containsKey(Enchantment.SILK_TOUCH)) return;
        }
        if(e.getBlock().getDrops().contains(new ItemStack(e.getBlock().getType()))) return;
        if (blockList.containsKey(e.getBlock().getType())) {

            // Levelup
            if ((getExp(p.getUniqueId()) + blockList.get(e.getBlock().getType())*getMultiplier(p.getUniqueId())) >= calculateLevel(getLevel(p.getUniqueId()))) {
                removeExp(p.getUniqueId(), calculateLevel(getLevel(p.getUniqueId())));
                updateLevel(p.getUniqueId(), 1);
                RewardMessage(p);
            }

            // OnBreak (Jedes mal)
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2.0f, 2.0f);
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§3+" + blockList.get(e.getBlock().getType())*getMultiplier(p.getUniqueId()) + " Miner (" + getExp(p.getUniqueId()) + "/" + calculateLevel(getLevel(p.getUniqueId())) + ") Exp"));
            updateExp(p.getUniqueId(), blockList.get(e.getBlock().getType())*getMultiplier(p.getUniqueId()));
        }
    }


    public void RewardMessage(Player p) {
        p.sendMessage("");
        p.sendMessage("§8§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        p.sendMessage("§7 LEVELUP" + MinerJob.getLevel(p.getUniqueId()));
        p.sendMessage("§8§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
        p.sendMessage("");
        p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1, 1);
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }
}
