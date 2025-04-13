package com.jourd1.jgames.japi.jgame;

public abstract class JGame {

    private String name = "defaultJGameName";
    private int playerCap = 2;
    private int playerCount = 0;

    public JGame(String name, int playerCap) {
        this.name = name;
        this.playerCap = playerCap;
    }

    public String getName() {
        return this.name;
    }

    public int getPlayerCap() {
        return playerCap;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public String toString() {
        return "[" + getName() + "] " + "(" + getPlayerCount() + "/" + getPlayerCap() + ")";
    }

}
