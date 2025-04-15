package com.jourd1.jgames.jcore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * JCore player related events listener
 */
public class JPlayerListener implements Listener {

    // TODO make join/quit message lang dependant

    // Join message
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("[§b+§r] §a" + event.getPlayer().getDisplayName());
    }

    // Quit message
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage("[§4-§r] §c" + event.getPlayer().getName());
    }

}
