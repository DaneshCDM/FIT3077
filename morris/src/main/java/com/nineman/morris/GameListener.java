package com.nineman.morris;

/**
 * The GameListener interface defines the callback for receiving updates on the game state.
 */
public interface GameListener {

    /**
     * Called when a new game state is available.
     *
     * @param game The updated game state.
     */
    void OnNextGameState(Game game);
}
