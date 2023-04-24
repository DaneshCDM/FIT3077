package com.example.textfx;

import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class GameController implements Initializable {
        @FXML
        private Rectangle turnIndicatorText;

        @FXML
        private Pane anchorPane;

        private final Ellipse[] whitePieces = new Ellipse[7];
        private final Ellipse[] blackPieces = new Ellipse[7];
        private final Ellipse[] unusedWhitePieces = new Ellipse[7];
        private final Ellipse[] unusedBlackPieces = new Ellipse[7];

        private final Circle[] positions = new Circle[22];


    @FXML
    Ellipse whitePiece1;
        private Game game;
        private ArrayBlockingQueue<String> clicks = new ArrayBlockingQueue<>(5);
        private final EnumMap<Color, String> COLOR_MAP = new EnumMap<>(Map.of(Color.WHITE, "wt", Color.BLACK, "bt"));
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            game = new Game(this);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            int position = 0;
            for (Node child : anchorPane.getChildren()) {
                String strPos = Integer.toString(position);

                if (child instanceof Circle) {
                    Circle circle = (Circle) child;
                    positions[position] = circle;
                    circle.setUserData(strPos);
                    position = position + 1;
                    final int positionCopy = position - 1;
                    circle.setOnMouseClicked(mouseEvent -> {
                        clicks.offer(strPos);
                        System.out.printf("current turn: %s\n", game.currentPlayerTurn());

                        executor.execute(() -> {
                            Game state = game.playTurn();
                            clicks.clear();

//                            final int positionCopy = position;
                            Platform.runLater(() -> update(state, positionCopy));
                            System.out.println(clicks);
                            System.out.printf("current turn: %s\n", game.currentPlayerTurn());
                            System.out.printf("%s clicked%n", strPos);
                        });});



                    } else if (child instanceof Ellipse) {
                    Ellipse ellipse = (Ellipse) child;
                    String color = ellipse.getFill().toString();

                    if (color.equals("0xffffffff")) { // White color
                        for (int i = 0; i < 7; i++) {
                            if (unusedWhitePieces[i] == null) {
//                                whitePieces[i] = ellipse;
                                unusedWhitePieces[i] = ellipse;
                                break;
                            }
                        }
                    } else { // Black color
                        for (int i = 0; i < 7; i++) {
                            if (unusedBlackPieces[i] == null) {
//                                blackPieces[i] = ellipse;
                                unusedBlackPieces[i] = ellipse;
                                break;
                            }
                        }
                    }
                }
            }


        }

      public String getClick() {
        try {
            return clicks.take();

        } catch (InterruptedException e) {
            throw new Error("Unexpected Interrupt");
        }


    }

    public void update(Game game, int position) {

        String strPos = Integer.toString(position);


        // Get the Circle object that was clicked
        Circle clickedCircle = positions[position];

        // Get the center position of the clicked Circle
        double centerX = clickedCircle.getLayoutX();
        double centerY = clickedCircle.getLayoutY();

        if(!game.getBoard().shouldRemoveToken()){
            // Loop through all of the unused Ellipse objects of the same color as the current player
            Ellipse[] unusedPieces = game.currentPlayerTurn() == Color.WHITE ? unusedWhitePieces : unusedBlackPieces;
            for (Ellipse unusedPiece : unusedPieces) {
                if (unusedPiece != null) {
                    // Move the unused Ellipse to the center position of the clicked Circle
                    unusedPiece.setLayoutX(centerX);
                    unusedPiece.setLayoutY(centerY);
                    game.getPosition(position).setOccupied(true);
                    unusedPiece.setUserData(String.valueOf(position));
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    final int positionCopy = position - 1;

                    unusedPiece.setOnMouseClicked(mouseEvent -> {
                        clicks.offer(strPos);
                        System.out.printf("current turn: %s\n", game.currentPlayerTurn());

                        executor.execute(() -> {
                            Game state = game.playTurn();
                            clicks.clear();

//                            final int positionCopy = position;
                            Platform.runLater(() -> update(state, positionCopy));
                            System.out.println(clicks);
                            System.out.printf("current turn: %s\n", game.currentPlayerTurn());
                            System.out.printf("%s clicked%n", strPos);
                        });});

                    // Remove the used Ellipse from the unused pieces array
                    for (int i = 0; i < unusedPieces.length; i++) {
                        if (unusedPieces[i] == unusedPiece) {
                            if( game.currentPlayerTurn() == Color.WHITE ){
                                whitePieces[i] = unusedPiece;
                            }
                            if( game.currentPlayerTurn() == Color.BLACK ){
                                blackPieces[i] = unusedPiece;
                            }
                            unusedPieces[i] = null;
                            break;
                        }
                    }
                    break;
                }
            }
//        turnIndicatorText.setText(String.format("Player %s Turn", game.currentPlayerTurn()));
        }
        else {
            Node ellipseToRemove = null;
            System.out.println("BADABADA");
            Ellipse[] currentPieces = game.currentPlayerTurn() == Color.WHITE ? blackPieces : whitePieces;
            for (Ellipse currentPiece : currentPieces) {
                System.out.println("current position" + position);
                if(currentPiece != null){
                    System.out.println(currentPiece.getUserData());

                }
                if (currentPiece != null && currentPiece.getUserData().equals(String.valueOf(position))) {
                    // Remove the Ellipse from the game board
//                    currentPiece.setVisible(false);
                    System.out.println("correct piece detected");
                    anchorPane.getChildren().remove(currentPiece);
                    game.getBoard().removeToken(position, game.currentPlayerTurn());
                    break;
                }
            }
            if (ellipseToRemove != null) {
                anchorPane.getChildren().remove(ellipseToRemove);
            }
            game.getBoard().setRemoveToken(false);
//        }


    }

//    @FXML private ImageView position0;
//    @FXML private ImageView position1;
//    @FXML private ImageView position2;

}}

