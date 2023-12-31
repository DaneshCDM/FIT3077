package com.nineman.morris;

import com.nineman.morris.actions.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a 'Nine Men's Morris' game, managing the game flow,
 * alternating turns between players, and responding to events such as a mill formation.
 * Game class follows the Model-View-Controller (MVC) architecture by serving as the model component for the game.
 * It has associations with the Board, Player, and Action classes,
 * and implements the MillListener interface to handle mill-related events.
 */
public class Game extends BoardListenerAdapter {

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayerTurn;
    private boolean lockPlayerTurn;
    private Action nextAction;
    private List<GameListener> gameObserver;

    /**
     * Constructs a new Nine Men's Morris game with the given input source.
     * Initializes the board, players, and sets the current player turn.
     *
     * @param sources the input source to receive player's input during the game
     */
    public Game(List<InputSource> sources) {
        this.board = new Board();

        // Assumes player 1 is white
        this.player1 = new Player(sources.get(0), Color.WHITE);
        this.player2 = new Player(sources.get(1), Color.BLACK);

        // White player goes first
        this.currentPlayerTurn = player1;
        this.lockPlayerTurn = false;
        gameObserver = new ArrayList<>();
        board.addBoardListener(this);
    }

    /**
     * The game loop to execute the game.
     * Looks for the player moves and execute it if valid.
     * Switches the player turn if the action execution is successful.
     *
     */
    public void playTurn() {
        while (!board.isGameOver(currentPlayerTurn)) {
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
            notifyNextState();
        }
    }

    /**
     * Returns the next action to be executed by the current player.
     *
     * @return the next action to be executed
     */
    public Action nextAction() {
        return nextAction;
    }

    /**
     * Registers a game listener to receive updates on the game state.
     *
     * @param listener the game listener to register
     */
    public void registerListener(GameListener listener) {
        gameObserver.add(listener);
    }

    /**
     * Notifies all game listeners of the next game state.
     */
    public void notifyNextState() {
        gameObserver.forEach(x -> x.OnNextGameState(this));
    }

    /**
     * Determines the action to be executed based on the player's move.
     *
     * @param player the player whose move will be determined
     * @return the action to be executed
     */
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

    /**
     * Returns the current player turn as a string.
     *
     * @return the current player turn
     */
    public String currentPlayerTurn() {
        return currentPlayerTurn.color.playerNumber();
    }

    /**
     * Returns the game board.
     *
     * @return the game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Called when a mill is formed on the game board.
     * Locks the player's turn, allowing them to remove an opponent's token.
     */
    @Override
    public void onMillFormed() {
        lockPlayerTurn = true;
    }

}
