package com.nineman.morris.actions;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * The RandomMoveGenerator class is responsible for generating random moves for a player in a Nine Men's Morris game.
 * It implements the InputSource and GameListener interfaces.
 */
public class RandomMoveGenerator implements InputSource {

    private ArrayBlockingQueue<String> moves = new ArrayBlockingQueue<>(50); // A queue to store the generated moves
    private int seed; // Seed value for random number generation
    private final Random random = new Random();// Random number generator


    /**
     * Constructs a RandomMoveGenerator object.
     *
     */
    public RandomMoveGenerator() {
        seed = random.nextInt();
        random.setSeed(seed);
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
     * Loads random positions into the moves queue.
     * Each position is represented as a string.
     */
    private void loadRandomPositions() {
        for (int i = 0; i < 24; i++){
            moves.offer(Integer.toString(random.nextInt(24)));
        }
    }
}
