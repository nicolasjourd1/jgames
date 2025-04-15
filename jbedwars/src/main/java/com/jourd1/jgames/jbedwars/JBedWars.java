package com.jourd1.jgames.jbedwars;

import org.bukkit.plugin.java.JavaPlugin;

import com.jourd1.jgames.jbedwars.game.JBedWarsGame;

/**
 * Plugin BedWars pour le jeu de bedwars
 */
public class JBedWars extends JavaPlugin {

    /**
     * Méthode appelée lors de l'activation du plugin
     */
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");

        JBedWarsGame jBedWarsGame = new JBedWarsGame(this);
        
        // Commande de l'ouverture du menu
        getCommand("menu").setExecutor(jBedWarsGame);
    }

    /**
     * Méthode appelée lors du désactivation du plugin
     */
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");

    }
}