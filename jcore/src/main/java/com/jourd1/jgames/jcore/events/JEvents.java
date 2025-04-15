package com.jourd1.jgames.jcore.events;

import org.bukkit.event.Listener;

import com.jourd1.jgames.jcore.JCore;

/**
 * JCore event listener
 */
public class JEvents implements Listener {

    JCore jcore;

    /**
     * JEvents constructor
     * 
     * @param jcore Reference to the JCore plugin
     */
    public JEvents(JCore jcore) {
        this.jcore = jcore;

        jcore.getServer().getPluginManager().registerEvents(new JPlayerListener(), jcore);
    }

}
