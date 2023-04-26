package com.nineman.morris;

/**
 * Enum representing the two colors (White and Black) used for the tokens in a Nine Men's Morris game.
 * Provides utility methods for inverting colors and obtaining player numbers.
 */
public enum Color {
    WHITE,
    BLACK;
    /**
     * Inverts the current color (switches between WHITE and BLACK).
     * @return The inverted color.
     */
    Color invert() {
        return values()[(ordinal() + 1) % values().length];
    }

    /**
     * Returns the player number corresponding to the color (1 for WHITE, 2 for BLACK).
     * @return A string representing the player number.
     */
    public String playerNumber() {
        return Integer.toString(ordinal() + 1);
    }
}
