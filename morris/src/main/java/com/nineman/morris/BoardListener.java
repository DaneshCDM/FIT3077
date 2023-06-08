package com.nineman.morris;

/**
 * The BoardListener interface defines the callbacks for events related to the Nine Men's Morris game board.
 */
public interface BoardListener {

    /**
     * Called when a mill is formed on the game board.
     * A mill is formed when three pieces of the same color are aligned in a row, column, or diagonal.
     * This callback can be used to notify listeners that a mill has been formed.
     */
    void onMillFormed();

    /**
     * Called when the game is over.
     *
     * @param color The color of the winning player or null if it's a draw.
     */
    void onGameOver(Color color);

    /**
     * Called when a position is selected on the game board.
     *
     * @param position The index of the selected position.
     */
    void onPositionSelected(int position);

}
