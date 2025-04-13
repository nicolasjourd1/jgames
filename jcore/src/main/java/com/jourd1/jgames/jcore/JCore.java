package com.jourd1.jgames.jcore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.jourd1.jgames.japi.JAPI;
import com.jourd1.jgames.jbedwars.JBedWars;
import com.jourd1.jgames.jcore.events.JEvents;

public class JCore extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");

        JAPI japi = (JAPI) Bukkit.getPluginManager().getPlugin("JAPI");
        if (japi == null) {
            getLogger().severe("Could not load JAPI !");
        } else {
            japi.hello();
        }

        JEvents jEvents = new JEvents(this);
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}