package com.nineman.morris.actions;

import com.nineman.morris.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class RandomMoveGenerator extends BoardListenerAdapter implements InputSource, GameListener {

    private ArrayBlockingQueue<String> moves = new ArrayBlockingQueue<>(50);
    private Color playerColor;
    private Game lastGameState;
    private int seed;
    private final Random random = new Random();

    public RandomMoveGenerator(Color color) {
        playerColor = color;
        seed = random.nextInt();
        random.setSeed(seed);
    }

    public void setGame(Game game) {
        this.lastGameState = game;
        game.registerListener(this);
    }

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

    private List<String> getAllPositions(Color c) {
        List<String> positions = new ArrayList<>();
        for (int i = 0; i < 24; i++){
            if (lastGameState.getBoard().getPositions(i).getColor() == c) {
                positions.add(Integer.toString(i));
            }
        }
        return positions;
    }

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

    private void loadRandomPositions() {
        for (int i = 0; i < 24; i++){
            moves.offer(Integer.toString(random.nextInt(24)));
        }
    }
}
