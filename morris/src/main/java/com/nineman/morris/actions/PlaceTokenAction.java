package com.nineman.morris.actions;

import com.nineman.morris.Board;
import com.nineman.morris.GameController;
import com.nineman.morris.Player;

public class PlaceTokenAction implements Action {

    @Override
    public boolean execute(Player player, Board board) {
        GameController controller = player.getSource();
        int position = Integer.parseInt(controller.getClick());
        return board.placeToken(player.color, position);
    }
}
