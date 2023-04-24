package com.example.textfx;

public class Game {

    private Board board;
    //Create two players each fr black and white
    private Player player1;
    private Player player2;


    public Game(HelloController source) {
        this.board = new Board();
        this.player1 = new Player(source);
        this.player2 = new Player(source);

    }

    //to be coded later
    public void playGame() {

    }

    /**
     * This function is used to check if all the tokens are over ib the game whch will indicate that the game is over
     * @return true if all tokens are over false if tokens are till there
     */
    public boolean gameOver() {
        return board.getTokensLeft() == 0;
    }


}

