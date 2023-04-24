package com.nineman.morris;

public enum Color {
    WHITE,
    BLACK;
    Color invert() {
        return values()[(ordinal() + 1) % values().length];
    }

    public String playerNumber() {
        return Integer.toString(ordinal() + 1);
    }
}
