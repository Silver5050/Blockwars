package de.silver.blockwars.sql;

import java.sql.*;

public class MySQL {


    private Connection con;

    public boolean isConnected() {
        return  con != null;
    }

    public MySQL() {
        connect();
    }


    public void connect(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://");
            System.out.println("[SQL] Verbindung zur SQL aufgebaut!");
        } catch (SQLException e) {
            System.out.println("[SQL] Verbindung zur SQL ist fehlgeschlagen! Fehler: " + e.getMessage());
        }
    }


    public void close() {
        try {
            con.close();
            System.out.println("[SQL] Verbindung zur SQL beendet.");
        }catch (SQLException e) {
            System.out.println("[SQL] Verbindungsabbruch zur SQL ist fehlgeschlagen! Fehler: " + e.getMessage());
        }
    }


    public void update(String qry) {
        try {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery(qry);
        } catch (SQLException e) {
            connect();
            System.err.println(e);
        }
        return rs;
    }

}
