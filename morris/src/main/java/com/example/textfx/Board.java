package com.example.textfx;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Board implements Iterable{
    private Position positions[];

    //Number of tokens for the game
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
        this.tokensLeft = MAX_TOKENS;

    }

    private void connectHorizontal(Position left, Position middle, Position right) {
        left.setPositionRight(middle);
        middle.setPositionLeft(left);
        middle.setPositionRight(right);
        right.setPositionLeft(middle);
    }

    public void placeToken(Color color, int position) {
        positions[position].setColor(color);
        tokensLeft -= 1;
    }

    public void removeToken(int position) {
        positions[position].setOccupied(false);
    }


    /**
     * This function returns the number of tokens left in the game
     * @return tokensLeft
     */
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
