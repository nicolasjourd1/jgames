package com.jourd1.jgames.jcore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Classe permettant de gerer les events des joueurs
 */
public class JPlayerListener implements Listener {

    /**
     * Event qui modifie le message de connexion lorsque un nouveau joueur arrive sur le serveur
     * @param event
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("[§b+§r] §a" + event.getPlayer().getDisplayName());
    }

    /**
     * Event qui modifie le message de deconnexion lorsque un joueur quitte le serveur
     * @param event
     */
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage("[§4-§r] §c" + event.getPlayer().getName());
    }

}
