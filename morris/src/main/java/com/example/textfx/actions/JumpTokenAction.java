package com.example.textfx.actions;

import com.example.textfx.Board;
import com.example.textfx.GameController;
import com.example.textfx.Player;

public class JumpTokenAction implements Action {
    @Override
    public boolean execute(Player player, Board board, int position) {
        GameController controller = player.getSource();
        int position1, position2;
        position1 = Integer.parseInt(controller.getClick());
        if ((board.getPositions(position1).getColor() != player.color)) {
            return false;
        }

        position2 = Integer.parseInt(controller.getClick());
        return board.moveToken(position1, position2);

    }
}

