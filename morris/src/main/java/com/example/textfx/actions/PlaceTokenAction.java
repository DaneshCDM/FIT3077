package com.example.textfx.actions;

import com.example.textfx.Board;
import com.example.textfx.GameController;
import com.example.textfx.Player;

public class PlaceTokenAction implements Action {

    @Override
    public void execute(Player player, Board board) {
        GameController controller = player.getSource();
        int position = Integer.getInteger(controller.getClick());
        board.placeToken(player.color, position);
    }
}

