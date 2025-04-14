package com.jourd1.jgames.jcore.commands;

import com.jourd1.jgames.jcore.JCore;

public class JCommands {

    private JCore jcore;

    public JCommands(JCore jcore) {
        this.jcore = jcore;

        jcore.getCommand("heal").setExecutor(new JPlayerCommands(this.jcore));
    }

}
