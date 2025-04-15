package com.jourd1.jgames.jcore.jmenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

/**
 * Classe permettant de créer un menu d'inventaire avec un nom, une taille spécifique et une option de décoration des bords.
 */
public class JMenu implements Listener {

    private Inventory menu;
    private String nameGame;
    private int menuSize;

    /**
     * Constructeur de la classe JMenu.
     * @param nameGame
     * @param menuSize
     * @param decorated
     */
    public JMenu(String nameGame, int menuSize, boolean decorated,Plugin plugin) {
        this.nameGame=nameGame;
        this.menuSize=menuSize;

        menu = Bukkit.createInventory(null, menuSize, nameGame);

        if (decorated) {
            decorateBorders();
        }

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    /**
     *  Décore les bords du menu avec des vitres grises.
     * À appeler une seule fois après la création du menu.
     */
    public void decorateBorders() {
        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);
    
        int columns = 9;
        int lastRowStart = menuSize - columns;
        int middleSlot = lastRowStart + (columns / 2); // slot central de la dernière ligne
    
        for (int i = 0; i < menuSize; i++) {
            // Haut, Bas, Gauche, Droite
            if (i < columns || i >= menuSize - columns || i % columns == 0 || i % columns == columns - 1) {
                if (i == middleSlot) {
                    // Ajoute l'item "Retour" au centre de la dernière ligne
                    ItemStack backItem = new ItemStack(Material.EMERALD);
                    ItemMeta backMeta = backItem.getItemMeta();
                    backMeta.setDisplayName("§aRetour");
                    backItem.setItemMeta(backMeta);
                    menu.setItem(i, backItem);
                } else {
                    menu.setItem(i, glass);
                }
            }
        }
    }
    

    /**
     * Met un item dans le menu.
     * @param slot
     * @param item
     */
    public void setItem(int slot, ItemStack item) {
        menu.setItem(slot, item);
    }

    /**
     * Renvoie l'inventaire du menu.
     * @return
     */
    public Inventory getInventory() {
        return menu;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTopInventory().equals(menu)) {
            event.setCancelled(true);
        }
    }
}
