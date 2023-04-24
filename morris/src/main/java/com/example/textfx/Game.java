package com.example.textfx;

import com.almasb.fxgl.core.collection.PropertyChangeListener;
import com.example.textfx.actions.Action;
import com.example.textfx.actions.PlaceTokenAction;



public class  Game {

    private Board board;
    //Create two players each for black and white
    private Player player1;
    private Player player2;
    private Player currentPlayerTurn;
    private GameController view;



    public Game(GameController source) {
        this.board = new Board();
        this.player1 = new Player(source, Color.WHITE);
        this.player2 = new Player(source, Color.BLACK);

        this.view = source;
        this.currentPlayerTurn = player1; // White player goes first


    }

    //main play game function
    // gets the p
    public void playGame() {
        Player currentPlayerTurn = player1; // White player goes first
        int position = Integer.parseInt(view.getClick());
        Action moveType = new PlaceTokenAction();
        moveType.execute(currentPlayerTurn, board, position);
        currentPlayerTurn = currentPlayerTurn == player1 ? player2 : player1;
        view.update(this, position);


    }

    public String currentPlayerTurn() {
        return currentPlayerTurn.color == Color.WHITE ? "1" : "2";
    }

    public Board getBoard() {
        return board;
    }

    public Position getPosition(int i) {
        return board.getPositions(i);
    }


    /**

     */
    public boolean gameOver() {
        return board.getTokensLeft() == 0;
    }




}

