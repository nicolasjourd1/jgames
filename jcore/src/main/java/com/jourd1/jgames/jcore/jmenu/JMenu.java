package com.jourd1.jgames.jcore.jmenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JMenu {

    private Inventory menu;
    private String nameGame;
    private int menuSize;

    public JMenu(String nameGame, int menuSize) {
        this.nameGame=nameGame;
        this.menuSize=menuSize;

        menu = Bukkit.createInventory(null, menuSize, nameGame);

        decorateBorders();
    }

    /**
     *  Décore les bords du menu avec des vitres grises.
     * À appeler une seule fois après la création du menu.
     */
    public void decorateBorders() {
        ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE); // Gris clair
        ItemMeta meta = glass.getItemMeta();
        meta.setDisplayName(" ");
        glass.setItemMeta(meta);

        for (int i = 0; i < menuSize; i++) {
            // Haut, Bas, Gauche, Droite
            if (i < 9 || i >= menuSize - 9 || i % 9 == 0 || i % 9 == 8) {
                menu.setItem(i, glass);
            }
        }
    }

    public Inventory getInventory() {
        return menu;
    }
}
