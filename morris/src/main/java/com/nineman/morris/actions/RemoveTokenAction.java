package com.nineman.morris.actions;

import com.nineman.morris.Board;
import com.nineman.morris.Player;

public class RemoveTokenAction implements Action {
    @Override
    public boolean execute(Player player, Board board) {
        InputSource controller = player.getSource();
        int position = Integer.parseInt(controller.getInput());
        return board.removeToken(position, player.color);
    }
}
