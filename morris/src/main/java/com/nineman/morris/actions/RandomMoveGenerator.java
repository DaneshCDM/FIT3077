package com.nineman.morris.actions;

import com.nineman.morris.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * The RandomMoveGenerator class is responsible for generating random moves for a player in a Nine Men's Morris game.
 * It implements the InputSource and GameListener interfaces.
 */
public class RandomMoveGenerator implements InputSource, GameListener {

    private ArrayBlockingQueue<String> moves = new ArrayBlockingQueue<>(50); // A queue to store the generated moves
    private Color playerColor; // The color of the player for which the moves are generated
    private Game lastGameState;  // The last known game state
    private int seed; // Seed value for random number generation
    private final Random random = new Random();// Random number generator


    /**
     * Constructs a RandomMoveGenerator object for the specified player color.
     *
     * @param color The color of the player.
     */
    public RandomMoveGenerator(Color color) {
        playerColor = color;
        seed = random.nextInt();
        random.setSeed(seed);
    }

    /**
     * Sets the game and registers this generator as a listener for the game events.
     *
     * @param game The game instance.
     */
    public void setGame(Game game) {
        this.lastGameState = game;
        game.registerListener(this);
    }

    /**
     * Retrieves the next move from the generator.
     *
     * @return The next move as a string.
     */
    @Override
    public String getInput() {
        if (moves.isEmpty()) {
            loadRandomPositions();
        }
        try {
            return moves.take();
        } catch (InterruptedException e) {
            throw new Error("Unexpected Interrupt");
        }
    }

    /**
     * Retrieves all positions on the board occupied by the specified color.
     *
     * @param c The color of the player.
     * @return A list of positions as strings.
     */
    private List<String> getAllPositions(Color c) {
        List<String> positions = new ArrayList<>();
        for (int i = 0; i < 24; i++){
            if (lastGameState.getBoard().getPositions(i).getColor() == c) {
                positions.add(Integer.toString(i));
            }
        }
        return positions;
    }

    /**
     * Called when a new game state is available.
     * If it is the current player's turn, clears the moves and plays a turn in the game.
     *
     * @param game The game instance.
     */
    @Override
    public void OnNextGameState(Game game) {
        if (!game.currentPlayerTurn().equals(playerColor.playerNumber())) {
            return;
        }
        moves.clear();
        lastGameState = game;
        try {
            game.playTurn();
        } catch (StackOverflowError ignored) {
            System.out.println(seed);
        }
    }

    /**
     * Loads random positions into the moves queue.
     * Each position is represented as a string.
     */
    private void loadRandomPositions() {
        for (int i = 0; i < 24; i++){
            moves.offer(Integer.toString(random.nextInt(24)));
        }
    }
}
