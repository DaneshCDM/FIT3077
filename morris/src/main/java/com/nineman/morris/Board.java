package com.nineman.morris;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Board implements Iterable<Position> {
    private Position positions[];
    private List<MillListener> listeners = List.of();
    private static final int MAX_TOKENS = 4;
    private int tokensLeft;

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
        this.tokensLeft = MAX_TOKENS;
    }

    private void connectHorizontal(Position left, Position middle, Position right) {
        left.setPositionRight(middle);
        middle.setPositionLeft(left);
        middle.setPositionRight(right);
        right.setPositionLeft(middle);
    }

    private void connectVertical(Position up, Position middle, Position down) {
        up.setPositionDown(middle);
        middle.setPositionUp(up);
        middle.setPositionDown(down);
        down.setPositionUp(middle);
    }

    /** You can only place tokens on an empty position. Return value indicates if placement
     * is successful. */
    public boolean placeToken(Color color, int position) {
        if (positions[position].getColor() != null) {
            return false;
        }
        positions[position].setColor(color);
        tokensLeft -= 1;
        return true;
    }

    public boolean detectMill(Position i, Color color) {
        return detectHorizontalMill(i, color) || detectVerticalMill(i, color);
    }

    /** Detects if a mill is formed horizontally by color at input Position */
    public boolean detectHorizontalMill(Position pos, Color color) {
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

    /** Detects if a mill is formed vertically by color at input Position */
    public boolean detectVerticalMill(Position pos, Color color) {
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

    public void removeToken(int position) {
        positions[position].setColor(null);
    }

    public int getTokensLeft() {
        return tokensLeft;
    }

    public Position getPositions(int i) {
        return positions[i];
    }

    public void addMillListener(MillListener listener) {
        listeners.add(listener);
    }

    public void notifyMillListener(Mill mill) {
        listeners.forEach(x -> x.onMillFormed(mill));
    }

    @NotNull
    @Override
    public Iterator<Position> iterator() {
        return Arrays.stream(positions).iterator();
    }

    public interface MillListener {

        /** Returns the positions where the mill is formed*/
        void onMillFormed(Mill mill);
    }

    public class Mill {
        final Position pos1;
        final Position pos2;
        final Position pos3;
        public Mill(Position pos1, Position pos2, Position pos3) {
            this.pos1 = pos1;
            this.pos2 = pos2;
            this.pos3 = pos3;
        }
        List<Position> getMillPositions() {
            return List.of(pos1, pos2, pos3);
        }
    }
}
