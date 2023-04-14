package com.nineman.morris.actions;

import com.nineman.morris.Board;
import com.nineman.morris.GameController;
import com.nineman.morris.Player;

public class PlaceTokenAction implements Action {

    @Override
    public void execute(Player player, Board board) {
        GameController controller = player.getSource();
        int position = Integer.parseInt(controller.getClick());
        board.placeToken(player.color, position);
    }
}
