package com.jourd1.jgames.jcore.jgame;

/**
 * Classe abstraite permettant de créer un jeu.
 */
public abstract class JGame {

    private String name = "defaultJGameName";
    private int playerCap = 2;
    private int playerCount = 0;

    /**
     * Constructeur de la classe JGame.
     * @param name
     * @param playerCap
     */
    public JGame(String name, int playerCap) {
        this.name = name;
        this.playerCap = playerCap;
    }

    /**
     * Renvoie le nom du jeu.
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Renvoie la capacité de joueurs du jeu.
     * @return
     */
    public int getPlayerCap() {
        return playerCap;
    }

    /**
     * Renvoie le nombre de joueurs dans le jeu.
     * @return
     */
    public int getPlayerCount() {
        return playerCount;
    }

    /**
     * Ajoute un joueur au jeu.
     */
    public String toString() {
        return "[" + getName() + "] " + "(" + getPlayerCount() + "/" + getPlayerCap() + ")";
    }

}
