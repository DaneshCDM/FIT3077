package com.nineman.morris;

public class Position {
    private Position positionLeft;
    private Position positionRight;
    private Position positionUp;
    private Position positionDown;

    public void setPositionLeft(Position positionLeft) {
        this.positionLeft = positionLeft;
    }

    public void setPositionRight(Position positionRight) {
        this.positionRight = positionRight;
    }

    public void setPositionUp(Position positionUp) {
        this.positionUp = positionUp;
    }

    public void setPositionDown(Position positionDown) {
        this.positionDown = positionDown;
    }
}
