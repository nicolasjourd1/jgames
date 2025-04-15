package com.jourd1.jgames.jcore.jgame;

/**
 * Classe abstraite permettant de créer un jeu.
 */
public abstract class JGame {

    /*
     * Game name, to be overridden by child class
     */
    private String name = "JGame";

    /**
     * Game maximum number of players
     */
    private int playerCap = 2;

    /**
     * Game player count
     */
    private int playerCount = 0;

    /**
     * JGame constructor
     * 
     * @param name      Game name
     * @param playerCap Game maximum number of players
     */
    public JGame(String name, int playerCap) {
        this.name = name;
        this.playerCap = playerCap;
    }

    /**
     * @return Game name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Game maximum number of players
     */
    public int getPlayerCap() {
        return playerCap;
    }

    /**
     * @return Game player count
     */
    public int getPlayerCount() {
        return playerCount;
    }

    /**
     * "[name] (playerCount/playerCap)"
     */
    public String toString() {
        return String.format("[%s] (%d/%d)", name, playerCount, playerCap);
    }

}
