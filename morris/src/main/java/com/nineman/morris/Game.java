package com.nineman.morris;

public class Game {

    private Board board;
    private Player player1;
    private Player player2;

    public Game(HelloController source) {
        this.board = new Board();
        this.player1 = new Player(source);
        this.player2 = new Player(source);
    }

    public void playGame() {

    }


    public boolean gameOver() {
        return board.getTokensLeft() == 0;
    }
}
