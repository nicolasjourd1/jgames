package com.jourd1.jgames.jcore.jdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;

import com.jourd1.jgames.jcore.JCore;

/**
 * Database connection handler class
 */
public class JDB {

    /**
     * Reference to the JCore plugin
     */
    private JCore jcore;

    /**
     * SQL Connection
     */
    static Connection connection;

    // The three variables below are to be set in the config.yml of JCore

    /**
     * Database username
     */
    private String user;
    /**
     * Database password
     */
    private String password;
    /**
     * Database url
     */
    private String url;

    /**
     * JDB constructor
     * 
     * @param jcore Reference to the JCore plugin
     */
    public JDB(JCore jcore) {
        // Reference to the JCore plugin
        this.jcore = jcore;

        // Retrieve JCore config
        FileConfiguration config = jcore.getConfig();

        // Get config database fields
        url = config.getString("db.url");
        password = config.getString("db.password");
        user = config.getString("db.user");

        this.jcore.getLogger().info("Connecting to the database...");

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            jcore.getLogger().severe("Could not connect to the database ! Did you configure it ?");
            jcore.getLogger().severe("Add or verify your database credentials in plugins/JCore/config.yml");
            e.printStackTrace();
        }

        if (connection != null) {
            this.jcore.getLogger().info("Successfully connected to the database !");
        }
    }

    /**
     * Disconnect the database
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
