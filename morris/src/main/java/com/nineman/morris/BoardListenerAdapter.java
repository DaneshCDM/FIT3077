package com.nineman.morris;

/**
 * This class extends the BoardListener interface and serves as an adapter,
 * providing empty implementations for the methods defined in the interface.
 * Subclasses can override the methods to define custom logic as needed.
 */
public class BoardListenerAdapter implements BoardListener {

    /**
     * {@inheritDoc}
     * <p>
     * This method is called when a mill is formed on the board.
     * The method is left empty in this implementation.
     * Subclasses can override this method to define custom logic when a mill is formed.
     */
    @Override
    public void onMillFormed() {

    }

    /**
     * {@inheritDoc}
     * <p>
     * This method is called when the game is over.
     * The 'color' parameter indicates the color of the winning player or null if it's a draw.
     * The method is left empty in this implementation.
     * Subclasses can override this method to define custom logic when the game is over.
     *
     * @param color The color of the winning player or null if it's a draw.
     */
    @Override
    public void onGameOver(Color color) {

    }

    /**
     * {@inheritDoc}
     * <p>
     * This method is called when a position is selected on the game board.
     * The method is left empty in this implementation.
     * Subclasses can override this method to define custom logic when a position is selected.
     *
     * @param position The index of the selected position.
     */
    @Override
    public void onPositionSelected(int position) {
        
    }

}
