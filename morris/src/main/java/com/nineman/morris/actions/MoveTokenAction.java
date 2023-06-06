package com.nineman.morris.actions;

import com.nineman.morris.Board;
import com.nineman.morris.Player;

/**
 * The MoveTokenAction class represents an action to move a token on the board
 */
public class MoveTokenAction implements Action {
    /**
     * Executes the move token action on the provided player and board.
     * @param player the player that is executing the action
     * @param board the board on which the action is being executed
     * @return true if the action was successfully executed, false otherwise
     */
    @Override
    public boolean execute(Player player, Board board) {
        // Get the input source (controller) associated with the player
        InputSource controller = player.getSource();
        // Get the first position from the input source
        int position1, position2;
        position1 = Integer.parseInt(controller.getInput());
        // Check if the color of the token at position1 is the same as the player's color
        if ((board.getPositions(position1).getColor() != player.color)) {
            return false;
        }
        board.notifyPositionSelected(position1);
        // Get the second position from the input source
        position2 = Integer.parseInt(controller.getInput());
        // Move the token from position1 to position2 on the board
        return board.moveToken(position1, position2);
    }
}
