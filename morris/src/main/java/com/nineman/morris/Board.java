package com.nineman.morris;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Represents the Nine Men's Morris game board and manages its state.
 * This class holds the positions and their connections, and provides methods
 * for placing, moving, and removing tokens. It also detects mill formations
 * and notifies listeners when a mill is formed.
 */
public class Board implements Iterable<Position> {
    private final Position[] positions;
    private List<BoardListener> listeners;
    private int currentTurn;
    private int whiteTokensLeft;
    private int blackTokensLeft;
    /** Game Over Notification only fires once */
    private boolean gameOverNotified;


    /**
     * Constructs a new Nine Men's Morris game board.
     * It initializes the positions, their connections and sets the initial
     * state of the game, including the number of tokens left for each player and
     * initializes the listener list for mill formation and game over events.
     */
    public Board() {
        positions = new Position[24];
        for (int i = 0; i < positions.length; i++) {
            positions[i] = new Position();
        }
        for (int i = 0; i < positions.length; i = i + 3) {
            connectHorizontal(positions[i], positions[i + 1], positions[i + 2]);
        }
        connectVertical(positions[0], positions[9], positions[21]);
        connectVertical(positions[3], positions[10], positions[18]);
        connectVertical(positions[6], positions[11], positions[15]);
        connectVertical(positions[1], positions[4], positions[7]);
        connectVertical(positions[16], positions[19], positions[22]);
        connectVertical(positions[8], positions[12], positions[17]);
        connectVertical(positions[5], positions[13], positions[20]);
        connectVertical(positions[2], positions[14], positions[23]);
        this.currentTurn = 1;
        this.whiteTokensLeft = 9;
        this.blackTokensLeft = 9;
        this.listeners = new ArrayList<>();
        this.gameOverNotified = false;
    }

    /**
     * Connects three given positions horizontally, useful for establishing
     * the layout of the game board.
     *
     * @param left the leftmost position
     * @param middle the middle position
     * @param right the rightmost position
     */
    private void connectHorizontal(Position left, Position middle, Position right) {
        left.setPositionRight(middle);
        middle.setPositionLeft(left);
        middle.setPositionRight(right);
        right.setPositionLeft(middle);
    }

    /**
     * Connects three given positions vertically, useful for establishing
     * the layout of the game board.
     *
     * @param up the upper position
     * @param middle the middle position
     * @param down the lower position
     */
    private void connectVertical(Position up, Position middle, Position down) {
        up.setPositionDown(middle);
        middle.setPositionUp(up);
        middle.setPositionDown(down);
        down.setPositionUp(middle);
    }


    /**
     * Places a token of the given color at the specified position.
     * If the placement is successful and forms a mill, listeners are notified.
     *
     * @param position the position index to place the token
     * @param color the color of the token to be placed
     * @return true if placement is successful, false otherwise
     */
    public boolean placeToken(int position, Color color) {
        if (positions[position].getColor() != null) {
            return false;
        }
        Position pos = positions[position];
        pos.setColor(color);
        currentTurn += 1;
        if (pos.isPartOfMill(color)) {
            notifyMillFormed();
        }
        if (color == Color.WHITE) {
            whiteTokensLeft -= 1;
        } else {
            blackTokensLeft -= 1;
        }
        return true;
    }

    /**
     * Moves a token from one position to another if the positions are adjacent.
     *
     * @param from the source position index
     * @param destination the destination position index
     * @return true if the token is moved successfully, false otherwise
     */
    public boolean moveToken(int from, int destination) {
        if (positions[from].adjacent(positions[destination])) {
            return jumpToken(from, destination);
        } else {
            return false;
        }
    }

    /**
     * Jumps a token from one position to another if the destination is unoccupied.
     * It checks if the destination is empty and if the source position has a token,
     * then removes the token from the source position and places it on the destination.
     *
     * @param from the source position index
     * @param destination the destination position index
     * @return true if the token is jumped successfully, false otherwise
     */

    public boolean jumpToken(int from, int destination) {
        Color fromTokenColor = positions[from].getColor();
        Color destinationTokenColor = positions[destination].getColor();
        if (fromTokenColor != null && destinationTokenColor == null) {
            positions[from].setColor(null);
            return placeToken(destination, fromTokenColor);
        } else {
            return false;
        }
    }

    /**
     * Removes a token from the specified position if it belongs to the opponent.
     * It checks if the token color at the position matches the opponent's color
     * and if the token isn't part of a mill formation, then removes it.
     *
     * @param position the position index from which to remove the token
     * @param color the color of the current player
     * @return true if the token is removed successfully, false otherwise
     */
    public boolean removeToken(int position, Color color) {
        Color current = positions[position].getColor();
        if (current == null || current == color) {
            return false;
        }
        Color opponentColor = color.invert();
        if (positions[position].isPartOfMill(opponentColor) && !allTokensAreMill(opponentColor)) {
            return false;
        }
        positions[position].setColor(null);
        return true;
    }

    /**
     * Checks if all tokens of a given color / player are part of a mill formation.
     * Mill formation: a set of three positions in a row or column that are occupied by tokens of the same color / player.
     *
     * @param color the color of the tokens to check
     * @return true if all tokens of the given color are part of a mill formation, false otherwise
     */
    private boolean allTokensAreMill(Color color) {
        return Arrays.stream(positions)
                .filter(c -> c.getColor() == color)
                .allMatch(x -> x.isPartOfMill(color));
    }

    /**
     * Checks if all tokens have been placed on the board.
     *
     * @return true if all tokens have been placed, false otherwise
     */
    public boolean allTokensPlaced() {
        return currentTurn > 18;
    }

    public int currentTurn() {
        return currentTurn;
    }

    /**
     * Returns the number of white tokens left to be placed on the board.
     *
     * @return the number of white tokens remaining
     */
    public int whiteTokensLeft() {
        return whiteTokensLeft;
    }

    /**
     * Returns the number of black tokens left to be placed on the board.
     *
     * @return the number of black tokens remaining
     */
    public int blackTokensLeft() {
        return blackTokensLeft;
    }

    /**
     * Gets the position at the specified index.
     *
     * @param i the index of the position
     * @return the position at the specified index
     */
    public Position getPositions(int i) {
        return positions[i];
    }

    /**
     * Returns the number of tokens with the specified color on the board.
     *
     * @param c the color of the tokens
     * @return the number of tokens with the specified color
     */
    public int getTokenCount(Color c) {
        return (int) Arrays.stream(positions).filter(x -> x.getColor() == c).count();
    }

    /**
     * Adds a listener to the list of listeners.
     *
     * @param listener the mill listener to add
     */
    public void addBoardListener(BoardListener listener) {
        listeners.add(listener);
    }

    /**
     * Notifies all mill listeners when a mill is formed on the board.
     */
    public void notifyMillFormed() {
        listeners.forEach(BoardListener::onMillFormed);
    }

    /**
     * Notifies all listeners that the game is over by invoking their `onGameOver()` method.
     * Method is called when the game reaches a terminal state:
     * A player with only 2 tokens left or no more valid moves.
     * Determines the winning color based on the number of tokens remaining for each player.
     */
    public void notifyGameOver() {
        if (!gameOverNotified) {
            Color c = (getTokenCount(Color.BLACK) == 2) || noMovesLeft(Color.BLACK)? Color.WHITE : Color.BLACK;
            listeners.forEach(listener -> listener.onGameOver(c));
            gameOverNotified = true;
        }
    }

    /**
     * Notifies all listeners that a position has been selected by invoking their `onPositionSelected()` method.
     *
     * @param pos the index of the selected position
     */
    public void notifyPositionSelected(int pos) {
        listeners.forEach(x -> x.onPositionSelected(pos));
    }

    /**
     * Checks if the game is over (A player has 2 tokens left or no more valid moves)
     *
     * @param player the current player
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver(Player player) {
        boolean result = allTokensPlaced() && (getTokenCount(Color.WHITE) < 3 || getTokenCount(Color.BLACK) < 3) ||
                noMovesLeft(player.color);
        if (result) {
            notifyGameOver();
        }
        return result;
    }

    /**
     * Checks if there are no valid moves left for a given player color.
     *
     * @param c the color of the current player
     * @return true if no moves are left, false otherwise
     */
    private boolean noMovesLeft(Color c) {
        // If tokens are not yet placed or the player has 3 tokens left, there are still possible moves
        if (!allTokensPlaced() || getTokenCount(c) == 3) {
            return false;
        }

        for (Position p : positions) {
            if (p.getColor() == c) {
                for (Position q : getAdjacent(p)) {
                    if (q != null && q.getColor() == null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Returns the adjacent positions to a given position.
     *
     * @param p the position to get adjacent positions for
     * @return an array of adjacent positions
     */
    private Position[] getAdjacent(Position p) {
        return new Position[]{p.up(), p.down(), p.left(), p.right()};
    }

    /**
     * The iterator allows iterating over the positions in the board class,
     * as a way to traverse and access each position.
     *
     * @return an iterator for the positions on the board
     */
    @NotNull
    @Override
    public Iterator<Position> iterator() {
        return Arrays.stream(positions).iterator();
    }

}
