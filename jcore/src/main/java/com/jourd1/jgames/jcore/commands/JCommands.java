package com.jourd1.jgames.jcore.commands;

import org.bukkit.command.CommandExecutor;

import com.jourd1.jgames.jcore.JCore;

public class JCommands {

    private JCore jcore;

    public JCommands(JCore jcore) {
        this.jcore = jcore;

        jcore.getCommand("").setExecutor(new JPlayerCommands(jcore));
    }

}
