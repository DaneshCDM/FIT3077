package com.nineman.morris;

public class Board {
    private Position positions[];

    public Board() {
        // todo: initialize and connect the board cleanly
        positions = new Position[24];
        positions[0] = new Position();
        positions[1] = new Position();
        positions[2] = new Position();

        positions[0].setPositionRight(positions[1]);
        positions[1].setPositionLeft(positions[0]);
        positions[1].setPositionRight(positions[1]);
        positions[2].setPositionLeft(positions[0]);
    }


}
