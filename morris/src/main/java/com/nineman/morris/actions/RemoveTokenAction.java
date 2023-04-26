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
        InputSource controller = player.getSource();
        int position = Integer.parseInt(controller.getInput());
        return board.removeToken(position, player.color);
    }
}
