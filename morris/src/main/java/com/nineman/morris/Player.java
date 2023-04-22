package com.nineman.morris;

import com.nineman.morris.actions.InputSource;

public class Player {

    private final InputSource source;
    public final Color color;

    public Player(GameController source, Color color) {
        this.source = source;
        this.color = color;
    }

    public InputSource getSource() {
        return source;
    }
}
