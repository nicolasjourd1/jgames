package com.jourd1.jgames.jbedwars.game;

import org.bukkit.plugin.java.JavaPlugin;

import com.jourd1.jgames.japi.jgame.JGame;

public class JBedWarsGame extends JGame {

    JavaPlugin plugin;

    public JBedWarsGame(JavaPlugin plugin) {
        super("JBedWars", 4);
        this.plugin = plugin;

        plugin.getLogger().info(this.toString());
    }

}
