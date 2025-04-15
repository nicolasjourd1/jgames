package com.jourd1.jgames.jcore.commands;

import com.jourd1.jgames.jcore.JCore;

/**
 * Classe permettant de gerer les commandes du plugin JCore
 */
public class JCommands {

    private JCore jcore;

    /**
     * Constructeur de la classe JCommands 
     * @param jcore
     */
    public JCommands(JCore jcore) {
        this.jcore = jcore;

        jcore.getCommand("heal").setExecutor(new JPlayerCommands(this.jcore));
        jcore.getCommand("specmenu").setExecutor(new JPlayerCommands(this.jcore));
    }

}
