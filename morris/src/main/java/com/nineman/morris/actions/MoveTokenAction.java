package com.nineman.morris.actions;

import com.nineman.morris.Board;
import com.nineman.morris.GameController;
import com.nineman.morris.Player;

public class MoveTokenAction implements Action {
    @Override
    public boolean execute(Player player, Board board) {
        GameController controller = player.getSource();
        int position1, position2;
        position1 = Integer.parseInt(controller.getClick());
        position2 = Integer.parseInt(controller.getClick());
        board.removeToken(position1);
        return board.placeToken(player.color, position2);
    }
}
