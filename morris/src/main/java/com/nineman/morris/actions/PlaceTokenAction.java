package com.nineman.morris.actions;

import com.nineman.morris.Board;
import com.nineman.morris.Player;

/**
 * The PlaceTokenAction class represents an action to place a token on the board.
 */
public class PlaceTokenAction implements Action {
    /**
     * Executes the place token action on the provided player and board.
     * @param player the player that is executing the action
     * @param board the board on which the action is being executed
     * @return true if the action was successfully executed, false otherwise
     */
    @Override
    public boolean execute(Player player, Board board) {
        // Get the input source for the player
        InputSource controller = player.getSource();
        // Parse the input as an integer representing desired position
        int position = Integer.parseInt(controller.getInput());
        // Place player's token on the board at the specified position and return
        return board.placeToken(position, player.color);
    }
}
