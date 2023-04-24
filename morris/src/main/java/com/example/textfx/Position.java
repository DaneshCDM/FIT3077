package com.example.textfx;

public class Position {

    //These variables will indicate details on the adjacent postions
    private Position positionLeft;
    private Position positionRight;
    private Position positionUp;
    private Position positionDown;

    //used to provide position data on to the left on the current postion
    public void setPositionLeft(Position positionLeft) {
        this.positionLeft = positionLeft;
    }

    //used to provide position data on to the right on the current postion
    public void setPositionRight(Position positionRight) {
        this.positionRight = positionRight;
    }

    //used to provide position data on to the upwards position relative to the current postion

    public void setPositionUp(Position positionUp) {
        this.positionUp = positionUp;
    }

    //used to provide position data on to the upwards position relative to the current postion

    public void setPositionDown(Position positionDown) {
        this.positionDown = positionDown;
    }
}

