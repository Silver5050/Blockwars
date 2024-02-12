package MySQL;

import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL {

    private String HOST;
    private String PORT;
    private String DATABASE;
    private String USER;
    private String PASSWORD;
    public static Connection con;

    public MySQL(String host, String port, String database, String user, String password) {
        super();
        this.HOST = "";
        this.PORT = "";
        this.DATABASE = "";
        this.USER = "";
        this.PASSWORD = "";
        this.HOST = host;
        this.PORT = port;
        this.DATABASE = database;
        this.USER = user;
        this.PASSWORD = password;
        this.connect();
    }

    public void connect() {
        String prefix = "§f[§bBlockWars§f] ";
        try {
            MySQL.con = DriverManager.getConnection("jdbc:mysql://" + this.HOST + ":" + this.PORT + "/" + this.DATABASE, this.USER, this.PASSWORD);
            Bukkit.getConsoleSender().sendMessage(String.valueOf(prefix) + "Die Verbindung mit MySQL wurde hergestellt!");
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(prefix) + "Die Verbindung ist fehlgeschlagen!" + e.getMessage());
        }
    }
    public void close() {
        String prefix = "§f[§bBlockWars§f] ";
        try {
            if (MySQL.con != null) {
                MySQL.con.close();
                Bukkit.getConsoleSender().sendMessage(String.valueOf(prefix) + "Die Verbindung mit MySQL wurde beendet!");
            }
        }
        catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(prefix) + "Die Verbindung mit MySQL konnte nicht beendet werden! §4Fehler: " + e.getMessage());
        }
    }
    public void update(String qre) {
        if (MySQL.con !=null) {
            try {
                Statement st = (Statement) MySQL.con.createStatement();
                st.executeUpdate(qre);
                st.close();
            }
            catch (SQLException e) {
                this.connect();
                System.err.print(e);
            }
        }
    }
    public ResultSet quary(String qre) {
        if (MySQL.con != null) {
            ResultSet rs = null;
            try {
                Statement st = (Statement) MySQL.con.createStatement();
                rs = st.executeQuery(qre);
            }
            catch (SQLException e) {
                this.connect();
                System.err.print(e);
            }
            return rs;
        }
        return null;
    }
}
