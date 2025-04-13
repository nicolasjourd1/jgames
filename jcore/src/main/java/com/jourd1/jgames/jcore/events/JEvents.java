package com.jourd1.jgames.jcore.events;

import org.bukkit.event.Listener;

import com.jourd1.jgames.jcore.JCore;

public class JEvents implements Listener {

    JCore jcore;

    public JEvents(JCore jcore) {
        this.jcore = jcore;

        jcore.getServer().getPluginManager().registerEvents(new JPlayerListener(), jcore);
    }

}
