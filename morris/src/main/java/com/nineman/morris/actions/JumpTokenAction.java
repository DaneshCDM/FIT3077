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
        InputSource controller = player.getSource();
        int position1, position2;
        position1 = Integer.parseInt(controller.getInput());
        if ((board.getPositions(position1).getColor() != player.color)) {
            return false;
        }
        position2 = Integer.parseInt(controller.getInput());
        return board.jumpToken(position1, position2);
    }
}
