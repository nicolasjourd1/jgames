package com.jourd1.jgames.jcore.commands;

import com.jourd1.jgames.jcore.JCore;

/**
 * JCore command handling class
 */
public class JCommands {

    private JCore jcore;

    /**
     * JCommands constructor
     * 
     * @param jcore Reference to the JCore plugin
     */
    public JCommands(JCore jcore) {
        // Reference to the JCore plugin
        this.jcore = jcore;

        // Command handler binding
        // TODO if this get too long, make a separate bindHandlers() function
        jcore.getCommand("heal").setExecutor(new JPlayerCommands(this.jcore));
        jcore.getCommand("specmenu").setExecutor(new JPlayerCommands(this.jcore));
    }

}
