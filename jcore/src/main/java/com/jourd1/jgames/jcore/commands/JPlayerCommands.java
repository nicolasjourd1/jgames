package com.jourd1.jgames.jcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jourd1.jgames.jcore.JCore;

/**
 * Classe permettant de gerer la versification des messages d'erreurs et de commandes dans le plugin JCore.
 */
public class JPlayerCommands implements CommandExecutor {

    JCore jcore;

    public JPlayerCommands(JCore jcore) {
        this.jcore = jcore;
    }

    /**
     * Méthode qui est appelée lorsque une commande est lancéee par un joueur
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(jcore.getLang().getMessage("PLAYER_ONLY_COMMAND"));
            return true; /* No need to display the command usage to the console */
        }

        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage(jcore.getLang().getMessage("PERMISSION_DENIED"));
            return true; /* no need to display the command usage to a non-op player */
        }

        if (command.getName().equalsIgnoreCase("heal")) {
            switch (args.length) {
                case 0:
                    player.setHealth(player.getAttribute(Attribute.MAX_HEALTH).getValue());
                    player.sendMessage(jcore.getLang().getMessage("PLAYER_HEAL_TARGET"));
                    return true; /* success : command sender is healed */
                case 1:
                    String targetName = args[0];
                    Player target = Bukkit.getPlayer(targetName);
                    if (target == null) {
                        player.sendMessage(String.format(jcore.getLang().getMessage("PLAYER_OFFLINE"),
                                targetName));
                        return true; /* command usage was fine, target was wrong */
                    } else {
                        target.setHealth(target.getAttribute(Attribute.MAX_HEALTH).getValue());
                        target.sendMessage(jcore.getLang().getMessage("PLAYER_HEAL_TARGET"));
                        if (!target.equals(player)) {
                            player.sendMessage(
                                    String.format(jcore.getLang().getMessage("PLAYER_HEAL_SENDER"), player.getName()),
                                    targetName);
                        }
                        return true; /* success : healded target */
                    }
                default:
                    player.sendMessage(jcore.getLang().getMessage("INCORRECT_COMMAND"));
                    return false;
            }
        }

        return false;
    }

}
