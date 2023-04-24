package com.example.textfx.actions;

import com.example.textfx.Board;
import com.example.textfx.GameController;
import com.example.textfx.Player;

public class JumpTokenAction implements Action {
    @Override
    public void execute(Player player, Board board, int position) {
        GameController controller = player.getSource();
//        int position = Integer.parseInt(controller.getClick());
        board.placeToken(player.color, position);
    }
}

