package com.jourd1.jgames.jbedwars.game;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jourd1.jgames.jbedwars.JBedWars;
import com.jourd1.jgames.jcore.jgame.JGame;
import com.jourd1.jgames.jcore.jmenu.JItemBuilder;
import com.jourd1.jgames.jcore.jmenu.JMenu;

public class JBedWarsGame extends JGame implements CommandExecutor {

    JBedWars plugin;
    private JMenu configMenu; 

    /**
     * Création d'une partie de Bedwars
     * @param plugin Le plugin JBedWars
     * @param name Le nom du jeu
     * @param playerCap Le nombre maximum de joueurs autorisé
     * @param lobby La pièce de départ des joueurs
     * @param plugin Le plugin JBedWars
     */
    public JBedWarsGame(JBedWars plugin) {
        super("JBedWars", 4);
        this.plugin = plugin;

        // Création du menu
        this.configMenu = new JMenu("Bedwars §l§g> §l§eConfig", 36, true, plugin);

        // Item de démarrage de la partie
        JItemBuilder.createItem(configMenu, 10, Material.DIAMOND_SWORD, "§aDémarrer", "§7Clique ici pour lancer la partie.");

        plugin.getLogger().info(this.toString());
    }

    /**
     * Quand un joueur tape la commande /bedwars
     *
     * @param sender Le joueur qui tape la commande
     * @param command La commande utilisée
     * @param label Le label de la commande
     * @param args Les arguments de la commande
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only command.");
            return true; // no need to display the command usage to the console
        }

        Player player = (Player) sender;
        player.openInventory(configMenu.getInventory());

        return true;
    }
}
