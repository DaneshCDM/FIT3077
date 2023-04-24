package com.example.textfx.actions;

import com.example.textfx.Board;
import com.example.textfx.Player;

public interface Action {

    void execute(Player player, Board board, int position);
}
