package com.nineman.morris;

public interface BoardListener {
    /**
     * Returns the positions where the mill is formed
     */
    default void onMillFormed() {}
    default void onGameOver(Color color) {}

}
