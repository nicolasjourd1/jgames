package com.jourd1.jgames.jcore.commands;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.jourd1.jgames.jcore.JCore;

public class JPlayerCommands implements CommandExecutor {

    JCore jCore;

    public JPlayerCommands(JCore jCore) {
        this.jCore = jCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only command.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp()) {
            player.sendMessage("Vous n'avez pas la permission de faire cela.");
            return true;
        }

        switch (label) {
            case "heal":
                switch (args.length) {
                    case 0:
                        player.setHealth(player.getAttribute(Attribute.MAX_HEALTH).getValue());
                        break;
                    case 1:
                        //
                        break;
                    default:

                        break;
                }

            default:
                break;

        }

        return false;
    }

}
