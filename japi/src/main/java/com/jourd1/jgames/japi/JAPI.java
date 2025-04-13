package com.jourd1.jgames.japi;

import org.bukkit.plugin.java.JavaPlugin;

import com.jourd1.jgames.japi.jdb.JDB;

public class JAPI extends JavaPlugin {

    JDB jdb;

    public void hello() {
        getLogger().info("Hello from API !");
    }

    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");

        jdb = new JDB(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");

        jdb.disconnect();
    }
}