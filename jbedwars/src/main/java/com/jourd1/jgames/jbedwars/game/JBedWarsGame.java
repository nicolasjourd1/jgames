package com.jourd1.jgames.jbedwars.game;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jourd1.jgames.jbedwars.JBedWars;
import com.jourd1.jgames.jcore.jgame.JGame;
import com.jourd1.jgames.jcore.jmenu.JMenu;

public class JBedWarsGame extends JGame implements CommandExecutor {

    JBedWars plugin;
    private JMenu configMenu; 

    public JBedWarsGame(JBedWars plugin) {
        super("JBedWars", 4);
        this.plugin = plugin;

        // Création du menu
        this.configMenu = new JMenu("Bedwars §l§g> §l§eConfig", 18);
        this.configMenu.decorateBorders();

        plugin.getLogger().info(this.toString());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only command.");
            return true; // console does not need to the command usage
        }

        Player player = (Player) sender;
        player.openInventory(configMenu.getInventory());

        return true;
    }
}
