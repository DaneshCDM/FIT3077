package com.example.textfx;

public class Position {

    private Color color;
    //These variables will indicate details on the adjacent postions
    private Position positionLeft;
    private Position positionRight;
    private Position positionUp;
    private Position positionDown;

    public boolean adjacent(Position pos) {
        if (pos == null) {
            return false;
        }
        return pos == positionLeft || pos == positionRight || pos == positionUp || pos == positionDown;
    }


    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    private Boolean isOccupied = false;

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

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Position left() {
        return positionLeft;
    }

    public Position right() {
        return positionRight;
    }

    public Position up() {
        return positionUp;
    }

    public Position down() {
        return positionDown;
    }

}

