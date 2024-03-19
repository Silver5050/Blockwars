package de.silver.blockwars.sql;

import de.silver.blockwars.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class JobSQL {

    /* Nicht für Jobs genutzt. Ist für Abfragen bei anderen Dingen wie z.B. einem /stats Command */
    public static Integer getJobExp(UUID uuid, String table) {
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM " + table + " WHERE UUID='" + uuid.toString() + "'");
            if(rs.next()) {
                return rs.getInt("EXP");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Integer getJobLevel(UUID uuid, String table) {
        try {
            ResultSet rs = Main.getInstance().getMysql().query("SELECT * FROM " + table + " WHERE UUID='" + uuid.toString() + "'");
            if(rs.next()) {
                return rs.getInt("LEVEL");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
