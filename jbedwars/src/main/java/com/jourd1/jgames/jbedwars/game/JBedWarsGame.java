package com.jourd1.jgames.jbedwars.game;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jourd1.jgames.jbedwars.JBedWars;
import com.jourd1.jgames.jcore.jgame.JGame;
import com.jourd1.jgames.jcore.jmenu.JItemBuilder;
import com.jourd1.jgames.jcore.jmenu.JMenu;

/**
 * JBedWarsGame class which manages a single game at once. Main plugin class
 * manages all the games.
 */
public class JBedWarsGame extends JGame implements CommandExecutor {

    /**
     * Reference to the JBedWars Plugin
     */
    JBedWars jbedwars;

    /*
     * BedWars game menu
     */
    private JMenu menu;

    /**
     * BedWars game configuration menu
     */
    private JMenu configMenu;

    /**
     * JBedWarsGame constructor
     * 
     * @param jbedwars Reference to the JBedWars plugin
     */
    public JBedWarsGame(JBedWars jbedwars) {
        // Calling JGame super constructor
        super("JBedWars", 4);

        // Reference to the JBedWars plugin
        this.jbedwars = jbedwars;

        // Menu name
        String rawMenuName = this.getName() + " #" + this.getID();
        String menuName = ChatColor.translateAlternateColorCodes('&', rawMenuName);

        // JBedWarsGame menu creation
        this.menu = new JMenu(menuName, 36, true, this.jbedwars);

        // JBedWarsGame config menu creation
        String configMenuName = ChatColor.translateAlternateColorCodes('&', menuName + "> Config");
        this.configMenu = new JMenu(configMenuName, 36, true, this.jbedwars);

        // Add items to the menu
        JItemBuilder.createItem(menu, 10, Material.DIAMOND_SWORD, "§aDémarrer", "§7Clique ici pour lancer la partie.");

        // TODO Make a more verbose log when creating a game
        this.jbedwars.getLogger().setLevel(Level.FINEST);
        this.jbedwars.getLogger().fine(String.format("Created game #%d", this.getID()));

    }

    /**
     * Command handling for /bedwars
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only command.");
            return true; // No need to display the command usage to the console
        }

        // TODO add multiple options depending on user (op/host or player)

        Player player = (Player) sender;
        player.openInventory(menu.getInventory());

        return true;
    }
}
