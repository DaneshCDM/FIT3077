package com.nineman.morris;

import com.nineman.morris.actions.*;

public class Game implements MillListener {

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayerTurn;
    private boolean lockPlayerTurn;
    private Action nextAction;

    public Game(InputSource source) {
        this.board = new Board();
        this.player1 = new Player(source, Color.WHITE); // Assumes player 1 is white
        this.player2 = new Player(source, Color.BLACK);
        this.currentPlayerTurn = player1; // White player goes first
        this.lockPlayerTurn = false;
        board.addMillListener(this);
    }

    public Game playTurn() {
        boolean status = getPlayerMove(currentPlayerTurn).execute(currentPlayerTurn, board);
        if (status) { // Action execution failed, don't proceed the game
            nextAction = null;
            if (!lockPlayerTurn) {
                currentPlayerTurn = currentPlayerTurn == player1 ? player2 : player1;
            } else {
                nextAction = new RemoveTokenAction();
                lockPlayerTurn = false;
            }
        }
        return this;
    }

    private Action getPlayerMove(Player player) {
        if (nextAction != null) {
            return nextAction;
        } else if (!board.allTokensPlaced()) {
            return new PlaceTokenAction();
        } else if (board.getTokenCount(player.color) > 3) {
            return new MoveTokenAction();
        } else {
            return new JumpTokenAction();
        }
    }

    public String currentPlayerTurn() {
        return currentPlayerTurn.color.playerNumber();
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void onMillFormed() {
        lockPlayerTurn = true;
    }
}
