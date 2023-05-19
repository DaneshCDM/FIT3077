package com.nineman.morris.actions;

import com.nineman.morris.Board;
import com.nineman.morris.Player;

/**
 * The JumpTokenAction class represents an action to jump a token on the board
 */
public class JumpTokenAction implements Action {
    /**
     * Executes the jump token action on the provided player and board.
     * @param player the player that is executing the action
     * @param board the board on which the action is being executed
     * @return true if the action was successfully executed, false otherwise
     */
    @Override
    public boolean execute(Player player, Board board) {
        InputSource controller = player.getSource();// Get the input source (presumably from the player)
        int position1, position2;// Declare variables to store the positions for the token jump

        position1 = Integer.parseInt(controller.getInput());// Read the first input and parse it as an integer
        if ((board.getPositions(position1).getColor() != player.color)) {
            // Check if the token at position1 belongs to the current player
            // If it doesn't, return false indicating that the action was not successfully executed
            return false;
        }
        position2 = Integer.parseInt(controller.getInput());// Read the second input and parse it as an integer
        // Invoke the jumpToken method on the Board object, passing the positions as arguments
        // Return the result of the jumpToken method as the result of the execute method
        return board.jumpToken(position1, position2);
    }
}
