package com.example.textfx;

import com.example.textfx.actions.Action;
import com.example.textfx.actions.MoveTokenAction;
import com.example.textfx.actions.PlaceTokenAction;


public class  Game implements Board.MillListener {

    private Board board;
    //Create two players each for black and white
    private Player player1;
    private Player player2;
    private Player currentPlayerTurn;
    private GameController view;
    private Stage gameStage;




    public Game(GameController source) {
        this.board = new Board();
        this.player1 = new Player(source, Color.WHITE);
        this.player2 = new Player(source, Color.BLACK);

        this.view = source;
        this.currentPlayerTurn = player1; // White player goes first
        this.gameStage = Stage.PLACE_TOKEN;


    }

    //main play game function
    // gets the p
    public Game playTurn() {
        int position = Integer.parseInt(view.getClick());
        boolean status = gameStage.action.execute(currentPlayerTurn, board, position);

        if (!status) {
            return this; // terminate early, find a better way to do this?
        }

        this.currentPlayerTurn = this.currentPlayerTurn == player1 ? player2 : player1;

        if (noTokensLeft()) {
            gameStage = gameStage.next();
        }
        return this;


    }

    @Override
    public void onMillFormed(Board.Mill mill) {

    }


    public Color currentPlayerTurn() {
        return currentPlayerTurn.color == Color.WHITE ? Color.WHITE : Color.BLACK;
    }

    public Board getBoard() {
        return board;
    }

    public Position getPosition(int i) {
        return board.getPositions(i);
    }


    /**

     */
    public boolean noTokensLeft() {
        return board.getTokensLeft() == 0;
    }

    private enum Stage {
        PLACE_TOKEN(new PlaceTokenAction()),
        MOVE_TOKEN(new MoveTokenAction()),
        JUMP_TOKEN(new PlaceTokenAction());
        final Action action;
        private static final Stage[] vals = values();
        Stage(Action action) {
            this.action = action;
        }
        public Stage next() {
            return vals[Math.min(this.ordinal() + 1, vals.length - 1)];
        }

    }



}

