package com.nineman.morris;

public interface BoardListener {
    /**
     * Returns the positions where the mill is formed
     */
    void onMillFormed();
    void onGameOver(Color color);

}
