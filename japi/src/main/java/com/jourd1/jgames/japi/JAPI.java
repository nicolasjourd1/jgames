package com.jourd1.jgames.japi;

import org.bukkit.plugin.java.JavaPlugin;

public class JAPI extends JavaPlugin {

    public void hello() {
        getLogger().info("Hello from API !");
    }

    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}