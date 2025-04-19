package com.jourd1.jgames.jcore.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.jourd1.jgames.jcore.JCore;
import com.jourd1.jgames.jcore.jmenu.JItemBuilder;

/**
 * JCore player related command handler
 */
public class JPlayerCommands implements CommandExecutor {

    /*
     * Reference to the JCore plugin
     */
    JCore jcore;

    public JPlayerCommands(JCore jcore) {
        this.jcore = jcore;
    }

    /**
     * Command handler for /heal, /specmenu
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
            return true; /* No need to display the command usage to a non-op player */
        }

        // /heal command handling
        if (command.getName().equalsIgnoreCase("heal")) {
            switch (args.length) {
                case 0:
                    player.setHealth(player.getAttribute(Attribute.MAX_HEALTH).getValue());
                    player.sendMessage(jcore.getLang().getMessage("PLAYER_HEAL_TARGET"));
                    return true; /* Command sender is healed */
                case 1:
                    String targetName = args[0];
                    Player target = Bukkit.getPlayer(targetName);
                    if (target == null) {
                        player.sendMessage(String.format(jcore.getLang().getMessage("PLAYER_OFFLINE"),
                                targetName));
                        return true; /* Target was wrong */
                    } else {
                        target.setHealth(target.getAttribute(Attribute.MAX_HEALTH).getValue());
                        target.sendMessage(jcore.getLang().getMessage("PLAYER_HEAL_TARGET"));
                        if (!target.equals(player)) {
                            player.sendMessage(
                                    String.format(jcore.getLang().getMessage("PLAYER_HEAL_SENDER"),
                                    targetName));
                        }
                        return true; /* Healed target */
                    }
                default:
                    player.sendMessage(jcore.getLang().getMessage("INCORRECT_COMMAND"));
                    return false; // Incorrect usage
                // TODO make command usage vary with lang (ie not use the usage from
                // plugin.yml)
            }
        }

        // /specmenu command handling
        if (command.getName().equalsIgnoreCase("specmenu")) {
            // Calculate menu size depending on player count
            // FIXME this bloc code currently does nothing
            // TODO count only in game players / players from the same game => command
            // handling might move to JGame
            int playerCount = Bukkit.getOnlinePlayers().size();
            int size =  36;

            // TODO use JMenu class
            Inventory menu = Bukkit.createInventory(null, size, "Spec Menu");

            // Add players to the menu
            int slot = 0;
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                // Exclude player in spectator mode
                if (onlinePlayer.getGameMode() == GameMode.SPECTATOR) continue;

                if (slot < size - 9) {
                    ItemStack head = JItemBuilder.createPlayerHead(onlinePlayer);
                    menu.setItem(slot, head);
                    slot++;
                }
            }

            // Décoration spécifique si plus de 27 joueurs
            if (playerCount > 27) {
                // Bottom decoration
                for (int i = (size - 9); i < size; i++) {
                    ItemStack blackGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta blackGlassMeta = blackGlass.getItemMeta();
                    if (blackGlassMeta != null) {
                        blackGlassMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES); // Cacher les attributs
                        blackGlass.setItemMeta(blackGlassMeta);
                    }
                    menu.setItem(i, blackGlass);
                }

                // Left arrow (bottom 2nd slot)
                ItemStack leftArrow = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta leftArrowMeta = (SkullMeta) leftArrow.getItemMeta();
                if (leftArrowMeta != null) {
                    leftArrowMeta.setDisplayName("Flèche gauche");
                    leftArrow.setItemMeta(leftArrowMeta);
                }
                menu.setItem(size - 8, leftArrow);

                // Right arrow (bottom 8th slot)
                ItemStack rightArrow = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta rightArrowMeta = (SkullMeta) rightArrow.getItemMeta();
                if (rightArrowMeta != null) {
                    rightArrowMeta.setDisplayName("Flèche droite");
                    rightArrow.setItemMeta(rightArrowMeta);
                }
                menu.setItem(size - 2, rightArrow);

                // Slime ball (bottom 5th slot)
                ItemStack slimeBall = new ItemStack(Material.SLIME_BALL);
                ItemMeta slimeBallMeta = slimeBall.getItemMeta();
                if (slimeBallMeta != null) {
                    slimeBallMeta.setDisplayName("Config");
                    slimeBall.setItemMeta(slimeBallMeta);
                }
                menu.setItem(size - 5, slimeBall);
            } else {
                for (int i = (size - 9); i < size; i++) {
                    ItemStack blackGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta blackGlassMeta = blackGlass.getItemMeta();
                    if (blackGlassMeta != null) {
                        blackGlassMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        blackGlass.setItemMeta(blackGlassMeta);
                    }
                    menu.setItem(i, blackGlass);
                }


                // Slime ball (bottom 5th slot)
                ItemStack slimeBall = new ItemStack(Material.SLIME_BALL);
                ItemMeta slimeBallMeta = slimeBall.getItemMeta();
                if (slimeBallMeta != null) {
                    slimeBallMeta.setDisplayName("Config");
                    slimeBall.setItemMeta(slimeBallMeta);
                }
                menu.setItem(size - 5, slimeBall);
            }

            // Open the menu
            player.openInventory(menu);

            return true;
        }

        return false;
    }
}
