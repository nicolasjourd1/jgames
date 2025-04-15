package com.jourd1.jgames.jcore.commands;

import org.bukkit.Bukkit;
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
 * Classe permettant de gerer la verification des messages d'erreurs et de commandes dans le plugin JCore.
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

        // Gestion de la commande /heal
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

        // Gestion de la commande /specmenu
        if (command.getName().equalsIgnoreCase("specmenu")) {
            // Calcul de la taille du menu en fonction du nombre de joueurs
            int playerCount = Bukkit.getOnlinePlayers().size();
            int size =  36;

            Inventory menu = Bukkit.createInventory(null, size, "Spec Menu");

            // Ajouter les joueurs dans le menu (si besoin)
            int slot = 0;
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (slot < size - 9) {
                    ItemStack head = JItemBuilder.createPlayerHead(onlinePlayer);
                    menu.setItem(slot, head); // ← C’est ça qui manquait !
                    slot++;
                }
            }

            // Décoration spécifique si plus de 27 joueurs
            if (playerCount > 27) {
                // Ajout de la décoration de la dernière ligne
                for (int i = (size - 9); i < size; i++) {
                    ItemStack blackGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta blackGlassMeta = blackGlass.getItemMeta();
                    if (blackGlassMeta != null) {
                        blackGlassMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES); // Cacher les attributs
                        blackGlass.setItemMeta(blackGlassMeta);
                    }
                    menu.setItem(i, blackGlass);
                }

                // Flèche gauche (2e case de la dernière ligne)
                ItemStack leftArrow = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta leftArrowMeta = (SkullMeta) leftArrow.getItemMeta();
                if (leftArrowMeta != null) {
                    leftArrowMeta.setDisplayName("Flèche gauche");
                    leftArrow.setItemMeta(leftArrowMeta);
                }
                menu.setItem(size - 8, leftArrow);

                // Flèche droite (8e case de la dernière ligne)
                ItemStack rightArrow = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta rightArrowMeta = (SkullMeta) rightArrow.getItemMeta();
                if (rightArrowMeta != null) {
                    rightArrowMeta.setDisplayName("Flèche droite");
                    rightArrow.setItemMeta(rightArrowMeta);
                }
                menu.setItem(size - 2, rightArrow);

                // Slime Ball (5e case de la dernière ligne)
                ItemStack slimeBall = new ItemStack(Material.SLIME_BALL);
                ItemMeta slimeBallMeta = slimeBall.getItemMeta();
                if (slimeBallMeta != null) {
                    slimeBallMeta.setDisplayName("Retour au menu");
                    slimeBall.setItemMeta(slimeBallMeta);
                }
                menu.setItem(size - 5, slimeBall);
            } else {
                for (int i = (size - 9); i < size; i++) {
                    ItemStack blackGlass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta blackGlassMeta = blackGlass.getItemMeta();
                    if (blackGlassMeta != null) {
                        blackGlassMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES); // Cacher les attributs
                        blackGlass.setItemMeta(blackGlassMeta);
                    }
                    menu.setItem(i, blackGlass);
                }


                // Slime Ball (5e case de la dernière ligne)
                ItemStack slimeBall = new ItemStack(Material.SLIME_BALL);
                ItemMeta slimeBallMeta = slimeBall.getItemMeta();
                if (slimeBallMeta != null) {
                    slimeBallMeta.setDisplayName("Retour au menu");
                    slimeBall.setItemMeta(slimeBallMeta);
                }
                menu.setItem(size - 5, slimeBall);
            }

            // Ouvrir le menu pour le joueur
            player.openInventory(menu);

            return true;
        }

        return false;
    }
}
