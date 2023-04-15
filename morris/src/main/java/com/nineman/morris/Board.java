package com.nineman.morris;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;

public class Board implements Iterable {
    private Position positions[];
    private static final int MAX_TOKENS = 4;
    private int tokensLeft;

    public Board() {
        // todo: initialize and connect the board cleanly
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

    public void placeToken(Color color, int position) {
        positions[position].setColor(color);
        tokensLeft -= 1;
    }

    public void detectMill() {
        // todo
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

    @NotNull
    @Override
    public Iterator iterator() {
        return Arrays.stream(positions).iterator();
    }
}
