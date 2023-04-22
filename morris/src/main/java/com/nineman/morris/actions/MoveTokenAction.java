package com.nineman.morris.actions;

import com.nineman.morris.Board;
import com.nineman.morris.Player;

public class MoveTokenAction implements Action {
    @Override
    public boolean execute(Player player, Board board) {
        InputSource controller = player.getSource();
        int position1, position2;
        position1 = Integer.parseInt(controller.getInput());
        if ((board.getPositions(position1).getColor() != player.color)) {
            return false;
        }
        position2 = Integer.parseInt(controller.getInput());
        return board.moveToken(position1, position2);
    }
}
