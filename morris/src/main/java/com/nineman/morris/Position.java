package com.nineman.morris;

public class Position {

    /** The color of the token in this position. Null represents no token. */
    private Color color;
    private Position positionLeft;
    private Position positionRight;
    private Position positionUp;
    private Position positionDown;

    /** Checks if input position is adjacent to this position. */
    public boolean adjacent(Position pos) {
        if (pos == null) {
            return false;
        }
        return pos == positionLeft || pos == positionRight || pos == positionUp || pos == positionDown;
    }

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
