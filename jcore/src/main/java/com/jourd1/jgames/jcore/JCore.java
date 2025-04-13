package com.jourd1.jgames.jcore;

import org.bukkit.plugin.java.JavaPlugin;

import com.jourd1.jgames.jcore.events.JEvents;

public class JCore extends JavaPlugin {


    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");

        JEvents jEvents = new JEvents(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}