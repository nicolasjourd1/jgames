package com.jourd1.jgames.jbedwars;

import org.bukkit.plugin.java.JavaPlugin;

import com.jourd1.jgames.jbedwars.game.JBedWarsGame;

public class JBedWars extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");

        JBedWarsGame jBedWarsGame = new JBedWarsGame(this);
        
        // Commande de l'ouverture du menu
        getCommand("menu").setExecutor(jBedWarsGame);
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");

    }
}