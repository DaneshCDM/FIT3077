package com.nineman.morris;

public class Position {

    /** The color of the token in this position. Null represents no token. */
    private Color color;
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

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
