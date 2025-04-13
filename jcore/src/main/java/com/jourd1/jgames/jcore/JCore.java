package com.jourd1.jgames.jcore;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.jourd1.jgames.jcore.events.JEvents;
import com.jourd1.jgames.jcore.jdb.JDB;

public class JCore extends JavaPlugin {

    private FileConfiguration config;
    private File configFile;

    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");

        init();

        JEvents jEvents = new JEvents(this);
        JDB jdb = new JDB(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }

    public void init() {
        setupConfig();
        loadConfig();
    }

    public void setupConfig() {

        configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", true);
            getLogger().info("Created config.yml file, which was missing");
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void loadConfig() {
        // TODO
    }

}