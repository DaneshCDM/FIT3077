package com.nineman.morris;

import com.nineman.morris.actions.Action;
import com.nineman.morris.actions.MoveTokenAction;
import com.nineman.morris.actions.PlaceTokenAction;
import com.nineman.morris.actions.RemoveTokenAction;

public class Game implements MillListener {

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayerTurn;
    private GameController view;
    private Stage gameStage;
    private Stage prevStage;
    private boolean lockPlayerTurn;

    public Game(GameController source) {
        this.board = new Board();
        this.player1 = new Player(source, Color.WHITE); // Assumes player 1 is white
        this.player2 = new Player(source, Color.BLACK);
        this.view = source;
        this.currentPlayerTurn = player1; // White player goes first
        this.gameStage = Stage.PLACE_TOKEN;
        this.lockPlayerTurn = false;
        board.addMillListener(this);
    }

    public Game playTurn() {
        boolean status = gameStage.action.execute(currentPlayerTurn, board);
        if (status) { // Action execution failed, don't proceed the game
            if (!lockPlayerTurn) {
                gameStage = prevStage == null? gameStage : prevStage;
                currentPlayerTurn = currentPlayerTurn == player1 ? player2 : player1;
                if (noTokensLeft() && gameStage.next() != Stage.JUMP_TOKEN) {
                    gameStage = gameStage.next();
                }
                prevStage = null;
            } else {
                prevStage = gameStage;
                gameStage = Stage.REMOVE_TOKEN;
                lockPlayerTurn = false;
            }
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
    public void onMillFormed() {
        lockPlayerTurn = true;
    }

    private enum Stage {
        PLACE_TOKEN(new PlaceTokenAction()),
        MOVE_TOKEN(new MoveTokenAction()),
        JUMP_TOKEN(new PlaceTokenAction()),
        REMOVE_TOKEN(new RemoveTokenAction());
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
