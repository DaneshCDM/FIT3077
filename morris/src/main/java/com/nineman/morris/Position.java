package com.nineman.morris;

import java.util.Arrays;

/**
 * Class representing a position on the Nine Men's Morris game board.
 * Manages the color of the token at the position and connections to adjacent positions.
 */
public class Position {

    /** The color of the token in this position. Null represents no token. */
    private Color color;
    private Position positionLeft;
    private Position positionRight;
    private Position positionUp;
    private Position positionDown;

    /**
     * Checks if the input position is adjacent to this position.
     * @param pos The position to check adjacency with.
     * @return true if the input position is adjacent, false otherwise.
     */
    public boolean adjacent(Position pos) {
        if (pos == null) {
            return false;
        }
        return pos == positionLeft || pos == positionRight || pos == positionUp || pos == positionDown;
    }

    /**
     * Checks if a mill is formed horizontally or vertically by the given color at the input position.
     *
     * @param color the color to check for a mill
     * @return true if a mill is formed, false otherwise
     */
    public boolean isPartOfMill(Color color) {
        return detectHorizontalMill(this, color) || detectVerticalMill(this, color);
    }

    /**
     * Detects if a mill is formed horizontally by the given color at the input position.
     *
     * @param pos the input position
     * @param color the color to check for a mill
     * @return true if a mill is formed, false otherwise
     */
    public boolean detectHorizontalMill(Position pos, Color color) {
        Position left = pos.left();
        Position right = pos.right();
        if (left != null && right != null) {
            Color[] values = {left.getColor(), pos.getColor(), right.getColor()};
            return Arrays.stream(values).allMatch(x -> x == color);
        } else if (left == null){
            return detectHorizontalMill(right, color);
        } else {
            return detectHorizontalMill(left, color);
        }
    }

    /**
     * Detects if a mill is formed vertically by the given color at the input position.
     *
     * @param pos the input position
     * @param color the color to check for a mill
     * @return true if a mill is formed, false otherwise
     */
    public boolean detectVerticalMill(Position pos, Color color) {
        Position up = pos.up();
        Position down = pos.down();
        if (up != null && down != null) {
            Color[] values = {up.getColor(), pos.getColor(), down.getColor()};
            return Arrays.stream(values).allMatch(x -> x == color);
        } else if (up == null){
            return detectVerticalMill(down, color);
        } else {
            return detectVerticalMill(up, color);
        }
    }

    /**
     * Sets the left adjacent position of this position.
     * @param positionLeft The position object to set as the left adjacent position.
     */
    public void setPositionLeft(Position positionLeft) {
        this.positionLeft = positionLeft;
    }

    /**
     * Sets the right adjacent position of this position.
     * @param positionRight The position object to set as the right adjacent position.
     */
    public void setPositionRight(Position positionRight) {
        this.positionRight = positionRight;
    }

    /**
     * Sets the up adjacent position of this position.
     * @param positionUp The position object to set as the up adjacent position.
     */
    public void setPositionUp(Position positionUp) {
        this.positionUp = positionUp;
    }

    /**
     * Sets the down adjacent position of this position.
     * @param positionDown The position object to set as the down adjacent position.
     */
    public void setPositionDown(Position positionDown) {
        this.positionDown = positionDown;
    }

    /**
     * Gets the left adjacent position of this position.
     * @return The left adjacent position.
     */
    public Position left() {
        return positionLeft;
    }

    /**
     * Gets the right adjacent position of this position.
     * @return The right adjacent position.
     */
    public Position right() {
        return positionRight;
    }

    /**
     * Gets the up adjacent position of this position.
     * @return The up adjacent position.
     */
    public Position up() {
        return positionUp;
    }

    /**
     * Gets the down adjacent position of this position.
     * @return The down adjacent position.
     */
    public Position down() {
        return positionDown;
    }

    /**
     * Sets the color of the token at this position.
     * @param color The color to set for the token at this position.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets the color of the token at this position.
     * @return The color of the token at this position, or null if no token is present.
     */
    public Color getColor() {
        return color;
    }
}
