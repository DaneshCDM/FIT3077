package com.example.textfx.actions;

import com.example.textfx.Board;
import com.example.textfx.GameController;
import com.example.textfx.Player;

public class MoveTokenAction implements Action {
    @Override
    public boolean execute(Player player, Board board, int position) {
        GameController controller = player.getSource();
        int position1, position2;
        position1 = Integer.parseInt(controller.getClick());
        position2 = Integer.parseInt(controller.getClick());
        board.removeToken(position1);
        return board.placeToken(player.color, position2);

    }
}

