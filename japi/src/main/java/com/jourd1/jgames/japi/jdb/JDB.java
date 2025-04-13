package com.jourd1.jgames.japi.jdb;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;

import com.jourd1.jgames.japi.JAPI;

public class JDB {

    private JAPI japi;

    static Connection connection;

    private String user;
    private String password;
    private String url;

    public JDB(JAPI japi) {
        this.japi = japi;

        FileConfiguration config = japi.getConfig();

        url = config.getString("db.url");
        password = config.getString("db.password");
        user = config.getString("db.user");

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            japi.getLogger().info("Successfully connected to the database !");
        }

        

    }

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
