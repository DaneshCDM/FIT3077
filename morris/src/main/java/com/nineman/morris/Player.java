package com.nineman.morris;

import com.nineman.morris.actions.InputSource;

/**
 * Represents a player in the Nine Men's Morris game.
 * This class holds the player's color and input source.
 * The input source is used to receive player input during the game.
 */
public class Player {

    private final InputSource source;
    public final Color color;

    /**
     * Constructs a new player with the given input source and color.
     *
     * @param source the input source to receive player input during the game
     * @param color the color of the player's tokens
     */
    public Player(InputSource source, Color color) {
        this.source = source;
        this.color = color;
    }

    /**
     * Returns the input source for the player.
     *
     * @return the input source for the player
     */
    public InputSource getSource() {
        return source;
    }
}
