package com.jourd1.jgames.jcore.events;

import org.bukkit.event.Listener;

import com.jourd1.jgames.jcore.JCore;

/**
 * Classe permettant de gerer les events.
 */
public class JEvents implements Listener {

    JCore jcore;

    /**
     * Constructeur de la classe JEvents.
     * @param jcore
     */
    public JEvents(JCore jcore) {
        this.jcore = jcore;

        jcore.getServer().getPluginManager().registerEvents(new JPlayerListener(), jcore);
    }

}
