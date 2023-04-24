package com.example.textfx;

public class Player {

    private final GameController source;
    public final Color color;

    public Player(GameController source, Color color) {
        this.source = source;
        this.color = color;
    }

    public GameController getSource() {
        return source;
    }
}

