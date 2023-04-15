package com.nineman.morris.actions;

import com.nineman.morris.Board;
import com.nineman.morris.GameController;
import com.nineman.morris.Player;

public class JumpTokenAction implements Action {
    @Override
    public boolean execute(Player player, Board board) {
        GameController controller = player.getSource();
        int position1, position2;
        // todo: ensure tokens are moved following the positions
        position1 = Integer.parseInt(controller.getClick());
        if ((board.getPositions(position1).getColor() != player.color)) {
            return false;
        }
        position2 = Integer.parseInt(controller.getClick());
        return board.moveToken(position1, position2);
    }
}
