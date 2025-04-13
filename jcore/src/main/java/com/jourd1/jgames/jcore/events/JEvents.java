package com.jourd1.jgames.jcore.events;

import com.jourd1.jgames.jcore.JCore;

public class JEvents {

    JCore jcore;

    public JEvents(JCore jcore) {
        this.jcore = jcore;

        jcore.getServer().getPluginManager().registerEvents(new JPlayerListener(), jcore);
    }

}
