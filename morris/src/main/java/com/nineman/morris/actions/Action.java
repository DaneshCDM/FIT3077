package com.nineman.morris.actions;

import com.nineman.morris.Board;
import com.nineman.morris.Player;

public interface Action {

    void execute(Player player, Board board);
}
