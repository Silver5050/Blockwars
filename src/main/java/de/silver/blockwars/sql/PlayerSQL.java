package de.silver.blockwars.sql;

import de.silver.blockwars.Main;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerSQL {

    public static boolean existsByName(String name) {
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM player WHERE NAME='" + name + "'");
            return rs.next() && rs.getString("NAME") != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static UUID getUUIDByName(String name) {
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM player WHERE NAME'" + name + "'");
            if (rs.next()) {
                return UUID.fromString(rs.getString("UUID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean playerExist(UUID uuid) {
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM player WHERE UUID='" + uuid.toString() + "'");
            return rs.next() && rs.getString("UUID") != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(UUID uuid) {
        if (!playerExist(uuid)) {
            Main.getInstance().getMysql().update("INSERT INTO player(UUID, NAME, COINS, PLAYTIME, CLAN, IP, GEMS, JOB)" +
                    " VALUES ('" + uuid.toString() + "', '" + Bukkit.getPlayer(uuid).getName() + "','1000', '0', '', '" + Bukkit.getPlayer(uuid).getAddress().getAddress().toString().replace("/", "") + "', '0', '')");
        }
    }

    public static Long getCoins(UUID uuid) {
        if (playerExist(uuid)){
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM player WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    return rs.getLong("COINS");
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getCoins(uuid);
        }
        return null;
    }

    public static String getJob(UUID uuid) {
        if (playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM player WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    return rs.getString("JOB");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getJob(uuid);
        }
        return null;
    }

    public static Long getPlaytime(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM player WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    return rs.getLong("PLAYTIME");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getPlaytime(uuid);
        }
        return null;
    }

    public static String getClan(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM player WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    return rs.getString("CLAN");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getClan(uuid);
        }
        return null;
    }

    public static String getIp(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM player WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    return rs.getString("IP");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getIp(uuid);
        }
        return null;
    }

    public static Long getGems(UUID uuid) {
        if(playerExist(uuid)) {
            try {
                ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM player WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    return rs.getLong("GEMS");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getGems(uuid);
        }
        return null;
    }
    public static void setCoins(UUID uuid, long coins) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE player SET COINS='" + coins + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setCoins(uuid, coins);
        }
    }
    public static void setJob(UUID uuid, String job) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE player SET JOB='" + job + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setJob(uuid, job);
        }
    }

    public static void addCoins(UUID uuid, long coins) {
        setCoins(uuid, getCoins(uuid) + coins);
    }

    public static void removeCoins(UUID uuid, long coins) {
        setCoins(uuid, getCoins(uuid) - coins);
    }

    public static void setPlaytime(UUID uuid, long time) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE player SET PLAYTIME='" + time + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setPlaytime(uuid, time);
        }
    }

    public static void addPlaytime(UUID uuid, long time) {
        setPlaytime(uuid, getPlaytime(uuid) + time);
    }

    public static void removePlaytime(UUID uuid, long time) {
        setPlaytime(uuid, getPlaytime(uuid) - time);
    }

    public static void setClan(UUID uuid, String clan) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE player SET CLAN='" + clan + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setClan(uuid, clan);
        }
    }

    public static void removeClan(UUID uuid) {
        setClan(uuid, "");
    }

    public static void setGems(UUID uuid, long gems) {
        if(playerExist(uuid)) {
            Main.getInstance().getMysql().update("UPDATE player SET GEMS='" + gems + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setGems(uuid, gems);
        }
    }

    public static void addGems(UUID uuid, long gems) {
        setGems(uuid, getGems(uuid) + gems);
    }

    public static void removeGems(UUID uuid, long gems) {
        setGems(uuid, getGems(uuid) - gems);
    }


}
