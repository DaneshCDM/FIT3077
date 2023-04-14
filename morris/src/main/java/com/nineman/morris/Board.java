package com.nineman.morris;

public class Board {
    private Position positions[];
    private static final int MAX_TOKENS = 9;
    private int tokensLeft;

    public Board() {
        // todo: initialize and connect the board cleanly
//        positions = new Position[24];
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

    public int getTokensLeft() {
        return tokensLeft;
    }
}
