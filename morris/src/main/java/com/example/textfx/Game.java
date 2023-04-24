package com.example.textfx;

import com.example.textfx.actions.Action;
import com.example.textfx.actions.MoveTokenAction;
import com.example.textfx.actions.PlaceTokenAction;
import com.example.textfx.actions.RemoveTokenAction;


public class Game implements MillListener {

    private Board board;
    //Create two players each for black and white
    private Player player1;
    private Player player2;
    private Player currentPlayerTurn;
    private GameController view;
    private Stage gameStage;
    private Stage prevStage;
    private boolean lockPlayerTurn;








    public Game(GameController source) {
        this.board = new Board();
        this.player1 = new Player(source, Color.WHITE);
        this.player2 = new Player(source, Color.BLACK);

        this.view = source;
        this.currentPlayerTurn = player1; // White player goes first
        this.gameStage = Stage.PLACE_TOKEN;
        this.lockPlayerTurn = false;
        board.addMillListener(this);



    }

    //main play game function
    // gets the p
    public Game playTurn() {
        int position = Integer.parseInt(view.getClick());
        boolean status = gameStage.action.execute(currentPlayerTurn, board, position);

        if (status) { // Action execution failed, don't proceed the game
            if (!lockPlayerTurn) {
                if(gameStage == Stage.REMOVE_TOKEN){
                    gameStage = Stage.PLACE_TOKEN;
                }
                gameStage = prevStage == null? gameStage : prevStage;
                currentPlayerTurn = currentPlayerTurn == player1 ? player2 : player1;

                if (noTokensLeft() && gameStage.next() != Stage.JUMP_TOKEN) {
                    gameStage = gameStage.next();
                }
                prevStage = null;
            }
            else {
                prevStage = gameStage;
                gameStage = Stage.REMOVE_TOKEN;
                lockPlayerTurn = false;
            }
        }
        return this;



    }

    @Override
    public void onMillFormed() {
        lockPlayerTurn = true;

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
        JUMP_TOKEN(new PlaceTokenAction()),
        REMOVE_TOKEN(new RemoveTokenAction());

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

