package com.jourd1.jgames.jcore.jlang;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.jourd1.jgames.jcore.JCore;

/**
 * Translation handler class
 */
public class JLang {

    // Supported languages
    private final static String[] _supportedLanguages = { "fr_FR", "en_US" };
    private final static List<String> supportedLanguages = Arrays.asList(_supportedLanguages);

    // TODO make this a YAML configuration to allow nesting in lang .yml files
    private HashMap<String, String> translationMap = new HashMap<String, String>();

    /**
     * JLang constructor
     * 
     * @param jcore Reference to the JCore plugin
     */
    public JLang(JCore jcore) {
        String defaultLangFilePath = "lang/fr_FR.yml";
        File defaultLangFile = new File(jcore.getDataFolder(), defaultLangFilePath);

        if (!defaultLangFile.exists()) {
            defaultLangFile.getParentFile().mkdirs();
            jcore.saveResource(defaultLangFilePath, true);
            jcore.getLogger()
                    .info(String.format("Created default lang file '%s', which was missing", defaultLangFilePath));
        }
        String configLang = jcore.getConfig().getString("lang");
        if (configLang == null) {
            jcore.getLogger().warning("'lang' is not set in configuration.");
            jcore.getLogger().info(String.format("Using to default lang file %s.", defaultLangFilePath));
        }

        if (!supportedLanguages.contains(configLang)) {
            jcore.getLogger().warning(String.format("'%s' lang option is not supported.", configLang));
            jcore.getLogger()
                    .info(String.format("Please use one of the following option: %s", supportedLanguages.toString()));
            jcore.getLogger().info(String.format("Using default lang file %s.", defaultLangFilePath));
        }

        String configLangFilePath = "lang/" + configLang + ".yml";

        if (configLangFilePath.equals(defaultLangFilePath) || configLang == null
                || !supportedLanguages.contains(configLang)) {
            FileConfiguration translations = YamlConfiguration.loadConfiguration(defaultLangFile);
            for (String translation : translations.getKeys(false)) {
                translationMap.put(translation, translations.getString(translation));
                jcore.getLogger().info(translation + ": " + translationMap.get(translation));
            }
        } else {
            File configLangFile = new File(jcore.getDataFolder(), configLangFilePath);
            if (!configLangFile.exists()) {
                jcore.saveResource(configLangFilePath, true);
                jcore.getLogger()
                        .info(String.format("Created lang file '%s', which was missing", configLangFilePath));

            }

            FileConfiguration translations = YamlConfiguration.loadConfiguration(configLangFile);
            for (String translation : translations.getKeys(false)) {
                translationMap.put(translation, translations.getString(translation));
                jcore.getLogger().info(translation + ": " + translationMap.get(translation));
            }
        }
    }

    /**
     * Get the desired string in the current language
     * 
     * @param path Path to the desired string
     * @return Path to the desired string if the string cant be found
     */
    public String getMessage(String path) {
        String message = translationMap.get(path);
        if (message == null) {
            return String.format("(%s)", path);
        } else {
            return message;
        }
    }

}
