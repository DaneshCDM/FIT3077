package com.nineman.morris.actions;

import com.nineman.morris.Board;
import com.nineman.morris.Player;

/**
 * Interface representing a game action in Nine Men's Morris.
 */
public interface Action {

    /**
     * Executes the action for a given player on a given board.
     * @param player The player performing the action.
     * @param board The game board on which the action is performed.
     * @return true if the action is executed successfully, false otherwise.
     */
    boolean execute(Player player, Board board);
}
