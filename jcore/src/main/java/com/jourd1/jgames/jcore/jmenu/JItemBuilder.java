package com.jourd1.jgames.jcore.jmenu;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JItemBuilder {

    /**
     * Classe permettant de créer un item avec son nom, le slot de l'inventaire, son material et sa description.
     * @param menu
     * @param slot
     * @param material
     * @param name
     * @param lore
     */
    public static void create(JMenu menu, int slot, Material material, String name, String lore) {
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
