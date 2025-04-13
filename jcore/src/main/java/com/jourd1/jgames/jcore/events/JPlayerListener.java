package com.jourd1.jgames.jcore.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JPlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("[§b+§r] §a" + event.getPlayer().getDisplayName());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.setQuitMessage("[§4-§r] §c" + event.getPlayer().getName());
    }

}
