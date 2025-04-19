package com.jourd1.jgames.jcore.jgame;

/**
 * Classe abstraite permettant de créer un jeu.
 */
public abstract class JGame {

    /*
     * Next game ID, incrementend in JGame constructor
     */
    private static int nextID = 0;

    /*
     * Game ID, every JGame instance has a different ID
     */
    private int ID;

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

        this.ID = nextID;
        nextID++;
    }

    /**
     * @return Game ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @return Game name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Game name formatted as a title
     */
    public String getTitleName() {
        // The last space character is intentional
        return String.format("[%s #%d] ", this.getName(), this.getID());
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
     * "[name #id] (playerCount/playerCap)"
     */
    public String toString() {
        return String.format("[%s #%d] (%d/%d)", name, ID, playerCount, playerCap);
    }

}
