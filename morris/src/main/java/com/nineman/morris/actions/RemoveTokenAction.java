package com.nineman.morris.actions;

import com.nineman.morris.Board;
import com.nineman.morris.Player;

/**
 * The RemoveTokenAction class represents an action to remove a token from the board.
 */
public class RemoveTokenAction implements Action {
    /**
     * Executes the remove token action on the provided player and board.
     * @param player the player that is executing the action
     * @param board the board on which the action is being executed
     * @return true if the action was successfully executed, false otherwise
     */
    @Override
    public boolean execute(Player player, Board board) {
        // Get the input source for the player
        InputSource controller = player.getSource();
        // Parse input as an integer representing the position to remove the token from
        int position = Integer.parseInt(controller.getInput());
        // Remove player's token from the board at the specified position and return
        return board.removeToken(position, player.color);
    }
}
