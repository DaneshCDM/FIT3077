package com.example.textfx.actions;

import com.example.textfx.Board;
import com.example.textfx.GameController;
import com.example.textfx.Player;

public class PlaceTokenAction implements Action {

    @Override
    public boolean execute(Player player, Board board, int position) {
        GameController controller = player.getSource();
//        int position = Integer.parseInt(controller.getClick());
        return board.placeToken(player.color, position);

    }
}

