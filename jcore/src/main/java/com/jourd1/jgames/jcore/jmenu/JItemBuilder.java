package com.jourd1.jgames.jcore.jmenu;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Classe permettant de créer un item avec son nom, son material, sa description et pour les têtes de joueurs.
 */
public class JItemBuilder {

    private ItemStack item;

   public static ItemStack createPlayerHead(Player player) {
    ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
    SkullMeta skullMeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);

    if (skullMeta != null && player.getName() != null) {
        skullMeta.setOwningPlayer(player);
        skullMeta.setDisplayName(ChatColor.YELLOW + player.getName());
        skull.setItemMeta(skullMeta);
    }

    return skull;
}

public static void createItem(JMenu menu, int slot, Material material, String name, String lore) {
    ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            if (name != null) meta.setDisplayName(name);
            if (lore != null && lore.length() > 0) meta.setLore(Arrays.asList(lore));
            // Cacher les attributs comme les dégâts et la vitesse d'attaque
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
        }

        menu.setItem(slot, item);
    }
}

