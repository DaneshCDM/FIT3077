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
    private List<MillListener> listeners;
    private int currentTurn;

    /**
     * Constructs a new Nine Men's Morris game board.
     * Initializes the positions, their connections, and the listeners.
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
        this.listeners = new ArrayList<>();
    }

    /**
     * Connects three given positions horizontally.
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
     * Connects three given positions vertically.
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
     * Checks if a mill is formed horizontally or vertically by the given color at the input position.
     *
     * @param i the input position
     * @param color the color to check for a mill
     * @return true if a mill is formed, false otherwise
     */
    public boolean isPartOfMill(Position i, Color color) {
        return detectHorizontalMill(i, color) || detectVerticalMill(i, color);
    }

    /**
     * Detects if a mill is formed horizontally by the given color at the input position.
     *
     * @param pos the input position
     * @param color the color to check for a mill
     * @return true if a mill is formed, false otherwise
     */    public boolean detectHorizontalMill(Position pos, Color color) {
        Position left = pos.left();
        Position right = pos.right();
        if (left != null && right != null) {
            Color[] values = {left.getColor(), pos.getColor(), right.getColor()};
            return Arrays.stream(values).allMatch(x -> x == color);
        } else if (left == null){
            return detectHorizontalMill(right, color);
        } else {
            return detectHorizontalMill(left, color);
        }
    }

    /**
     * Detects if a mill is formed vertically by the given color at the input position.
     *
     * @param pos the input position
     * @param color the color to check for a mill
     * @return true if a mill is formed, false otherwise
     */    public boolean detectVerticalMill(Position pos, Color color) {
        Position up = pos.up();
        Position down = pos.down();
        if (up != null && down != null) {
            Color[] values = {up.getColor(), pos.getColor(), down.getColor()};
            return Arrays.stream(values).allMatch(x -> x == color);
        } else if (up == null){
            return detectVerticalMill(down, color);
        } else {
            return detectVerticalMill(up, color);
        }
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
        if (isPartOfMill(positions[position], color)) {
            notifyMillListener();
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


    public boolean removeToken(int position, Color color) {
        Color current = positions[position].getColor();
        if (current == null || current == color) {
            return false;
        }
        positions[position].setColor(null);
        return true;
    }

    public boolean allTokensPlaced() {
        return currentTurn > 18;
    }

    public Position getPositions(int i) {
        return positions[i];
    }

    public int getTokenCount(Color c) {
        return (int) Arrays.stream(positions).filter(x -> x.getColor() == c).count();
    }

    public void addMillListener(MillListener listener) {
        listeners.add(listener);
    }

    public void notifyMillListener() {
        listeners.forEach(MillListener::onMillFormed);
    }

    @NotNull
    @Override
    public Iterator<Position> iterator() {
        return Arrays.stream(positions).iterator();
    }

}
