package com.example.textfx;

import com.example.textfx.actions.Action;
import com.example.textfx.actions.PlaceTokenAction;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;


public class  Game implements Observable {

    private Board board;
    //Create two players each for black and white
    private Player player1;
    private Player player2;


    public Game(GameController source) {
        this.board = new Board();
        this.player1 = new Player(source, Color.WHITE);
        this.player2 = new Player(source, Color.BLACK);

    }

    //main play game function
    // gets the p
    public void playGame() {
        Player currentPlayerTurn = player1; // White player goes first

        Action moveType = new PlaceTokenAction();
        while (!gameOver()) {
//            currentPlayerTurn;
            moveType.execute(currentPlayerTurn, board);
            currentPlayerTurn = currentPlayerTurn == player1 ? player2 : player1;

        }

    }

    /**

     */
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

