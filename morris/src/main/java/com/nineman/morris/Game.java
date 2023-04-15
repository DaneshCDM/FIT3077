package com.nineman.morris;

import com.nineman.morris.actions.Action;
import com.nineman.morris.actions.MoveTokenAction;
import com.nineman.morris.actions.PlaceTokenAction;

public class Game implements Board.MillListener {

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayerTurn;
    private GameController view;
    private Stage gameStage;

    public Game(GameController source) {
        this.board = new Board();
        this.player1 = new Player(source, Color.WHITE); // Assumes player 1 is white
        this.player2 = new Player(source, Color.BLACK);
        this.view = source;
        this.currentPlayerTurn = player1; // White player goes first
        this.gameStage = Stage.PLACE_TOKEN;
    }

    public Game playTurn() {
        boolean status = gameStage.action.execute(currentPlayerTurn, board);
        if (!status) {
            return this; // terminate early, find a better way to do this?
        }
        currentPlayerTurn = currentPlayerTurn == player1 ? player2 : player1;
        if (noTokensLeft()) {
            gameStage = gameStage.next();
        }
        return this;
    }

    public String currentPlayerTurn() {
        return currentPlayerTurn.color.playerNumber();
    }

    public Board getBoard() {
        return board;
    }

    public boolean noTokensLeft() {
        return board.getTokensLeft() == 0;
    }

    @Override
    public void onMillFormed(Board.Mill mill) {

    }

    private enum Stage {
        PLACE_TOKEN(new PlaceTokenAction()),
        MOVE_TOKEN(new MoveTokenAction()),
        JUMP_TOKEN(new PlaceTokenAction());
        final Action action;
        private static final Stage[] vals = values();
        Stage(Action action) {
            this.action = action;
        }
        public Stage next() {
            return vals[Math.min(this.ordinal() + 1, vals.length - 1)];
        }

    }
}
