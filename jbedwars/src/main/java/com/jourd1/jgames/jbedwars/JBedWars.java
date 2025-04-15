package com.jourd1.jgames.jbedwars;

import org.bukkit.plugin.java.JavaPlugin;

import com.jourd1.jgames.jbedwars.game.JBedWarsGame;

/**
 * JGames bedwars plugin
 */
public class JBedWars extends JavaPlugin {

    /**
     * Plugin startup logic
     */
    @Override
    public void onEnable() {
        // BedWars game
        JBedWarsGame jBedWarsGame = new JBedWarsGame(this);
        
        // Command handler binding
        getCommand("menu").setExecutor(jBedWarsGame);
    }

    /**
     * Plugin shutdown logic
     */
    @Override
    public void onDisable() {
    }
}