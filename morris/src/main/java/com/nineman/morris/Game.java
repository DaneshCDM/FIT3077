package com.nineman.morris;

import com.nineman.morris.actions.Action;
import com.nineman.morris.actions.PlaceTokenAction;

public class Game {

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayerTurn;
    private GameController view;

    public Game(GameController source) {
        this.board = new Board();
        this.player1 = new Player(source, Color.WHITE); // Assumes player 1 is white
        this.player2 = new Player(source, Color.BLACK);
        this.view = source;
        this.currentPlayerTurn = player1; // White player goes first
    }

    public void playGame() {
        Action moveType = new PlaceTokenAction();
        moveType.execute(currentPlayerTurn, board);
        currentPlayerTurn = currentPlayerTurn == player1 ? player2 : player1;
        view.update(this);
    }

    public String currentPlayerTurn() {
        return currentPlayerTurn.color == Color.WHITE ? "1" : "2";
    }

    public Board getBoard() {
        return board;
    }

    public boolean gameOver() {
        return board.getTokensLeft() == 0;
    }


}
