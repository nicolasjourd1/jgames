package com.jourd1.jgames.jcore.jdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;

import com.jourd1.jgames.jcore.JCore;

/**
 * Handles database connection
 */
public class JDB {

    private JCore jcore;

    static Connection connection;

    private String user;
    private String password;
    private String url;

    /**
     * Constructeur de la classe JDB
     * @param plugin
     */
    public JDB(JCore plugin) {
        this.jcore = plugin;

        FileConfiguration config = plugin.getConfig();

        url = config.getString("db.url");
        password = config.getString("db.password");
        user = config.getString("db.user");

        plugin.getLogger().info("Connecting to the database...");

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            plugin.getLogger().info("Successfully connected to the database !");
        }
    }

    /**
     * Déconnexion de la base de données
     */
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
