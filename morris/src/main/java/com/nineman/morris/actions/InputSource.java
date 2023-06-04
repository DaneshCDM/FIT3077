package com.nineman.morris.actions;

/**
 * This interface represents an abstraction for an input source in the Nine Men's Morris game.
 * The specific implementations of this interface could involve different types of input sources such as
 * command line input, GUI-based input, network-based input, etc. This design provides flexibility and
 * modularity by decoupling the specifics of input gathering from the game logic.
 */
public interface InputSource {
    /**
     * This method retrieves input from the implemented input source.
     * The form of this input could vary depending on the specifics of the game actions
     * and the particular implementation of the InputSource.
     *
     * @return The obtained input as a string. The details of how this string should be formatted
     * or what it should contain would be determined by the specific implementation and the game's requirements.
     * This could include things like game moves, commands, or other game-related instructions.
     */
    String getInput();
}
