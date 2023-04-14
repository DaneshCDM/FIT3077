package com.nineman.morris;

import com.nineman.morris.actions.Action;
import com.nineman.morris.actions.PlaceTokenAction;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Game implements Observable {

    private Board board;
    private Player player1;
    private Player player2;

    public Game(GameController source) {
        this.board = new Board();
        this.player1 = new Player(source, Color.WHITE); // Assumes player 1 is white
        this.player2 = new Player(source, Color.BLACK);
    }

    public void playGame() {
        Player currentPlayerTurn = player1; // White player goes first

        Action moveType = new PlaceTokenAction();
        while (!gameOver()) {
//            currentPlayerTurn;
            moveType.execute(currentPlayerTurn, board);
            currentPlayerTurn = currentPlayerTurn == player1 ? player2 : player1;

        }
    }


    public boolean gameOver() {
        return board.getTokensLeft() == 0;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
}
