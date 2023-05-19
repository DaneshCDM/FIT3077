package com.nineman.morris;

// This class extends the BoardListener interface and serves as an adapter,
// providing empty implementations for the methods defined in the interface.

public class BoardListenerAdapter implements BoardListener {

    @Override
    public void onMillFormed() {
        // This method is called when a mill is formed on the board.
        // In this implementation, the method is left empty, providing no specific behavior.
        // Subclasses can override this method to define custom logic when a mill is formed.
    }

    @Override
    public void onGameOver(Color color) {
        // This method is called when the game is over.
        // The 'color' parameter indicates the color of the winning player or null if it's a draw.
        // In this implementation, the method is left empty, providing no specific behavior.
        // Subclasses can override this method to define custom logic when the game is over.
    }
}
