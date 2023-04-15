package com.nineman.morris;

import com.nineman.morris.actions.Action;
import com.nineman.morris.actions.MoveTokenAction;
import com.nineman.morris.actions.PlaceTokenAction;

public class Game {

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

    public Game playGame() {
        gameStage.action.execute(currentPlayerTurn, board);
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
