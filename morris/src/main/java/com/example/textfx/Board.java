package com.example.textfx;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Board implements Iterable{
    private Position positions[];

    //Number of tokens for the game
    private static final int MAX_TOKENS = 9;
    private int tokensLeft;


    public Board() {

        positions = new Position[3];

        positions[0] = new Position();
        positions[1] = new Position();
        positions[2] = new Position();

        positions[0].setPositionRight(positions[1]);
        positions[1].setPositionLeft(positions[0]);
        positions[1].setPositionRight(positions[1]);
        positions[2].setPositionLeft(positions[0]);

        this.tokensLeft = MAX_TOKENS;
    }

    public void placeToken(Color color, int position) {
        positions[position].setColor(color);
        tokensLeft -= 1;
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
