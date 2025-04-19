package com.jourd1.jgames.jcore;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.jourd1.jgames.jcore.commands.JCommands;
import com.jourd1.jgames.jcore.events.JEvents;
import com.jourd1.jgames.jcore.jdb.JDB;
import com.jourd1.jgames.jcore.jlang.JLang;

/**
 * Main class
 */
public class JCore extends JavaPlugin {

    private FileConfiguration config;
    private File configFile;
    private JLang jlang;

    /**
     * Plugin startup logic
     */
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");

        init();

        new JEvents(this);
        new JDB(this);
        new JCommands(this);
        jlang = new JLang(this);
    }

    /**
     * Plugin shutdown logic
     */
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }

    /**
     * Initialisation du plugin et chargement des configurations et langues
     */
    public void init() {
        setupConfig();
        loadConfig();
    }

    /**
     * Initialisation du fichier de configuration
     */
    public void setupConfig() {

        configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", true);
            getLogger().info("Created config.yml file, which was missing");
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    /**
     * Chargement des configurations et langues
     */
    public void loadConfig() {
        // TODO
    }

    /**
     * Récupération de la configuration courante
     */
    public FileConfiguration getConfig() {
        return this.config;
    }

    /**
     * Récupération de la langue courante
     */
    public JLang getLang() {
        return jlang;
    }

}