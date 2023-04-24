package com.example.textfx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;


public class GameController implements Initializable {
        @FXML
        private Rectangle turnIndicatorText;
        @FXML
        private Circle position0;
        @FXML
        private Circle position1;
        @FXML
        private Circle position2;
        @FXML
        private Circle position3;

    @FXML
    Circle whitePiece1;
        private Game game;
        private ArrayBlockingQueue<String> clicks = new ArrayBlockingQueue<>(5);
        private final EnumMap<Color, String> COLOR_MAP = new EnumMap<>(Map.of(Color.WHITE, "wt", Color.BLACK, "bt"));
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            game = new Game(this);

            position0.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("0 clicked!!!");
                    clicks.offer("0");

                    game.playGame();
                }
            });
            position1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("1 clicked!!!");
                    clicks.offer("1");

                    game.playGame();
                }
            });
            position2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("2 clicked!!!");
                    clicks.offer("2");

                    game.playGame();
                }
            });
            game = new Game(this);

        }

      public String getClick() {
        try {
            return clicks.take();

        } catch (InterruptedException e) {
            throw new Error("Unexpected Interrupt");
        }


    }

    public void update(Game game, int position) {
        List<Circle> positions = List.of(position0, position1, position2);

            Circle tokenView = positions.get(position);
            double centerX = tokenView.getLayoutX();
            double centerY = tokenView.getLayoutY();
            String color = COLOR_MAP.get(game.getBoard().getPositions(position).getColor());
            Position prevPostion = null;
            if(whitePiece1.getUserData() != null){
                prevPostion =  game.getPosition(Integer.parseInt((String) whitePiece1.getUserData()));
             }
            Position currPostion = game.getPosition(position);
            if(!(currPostion.getOccupied())){
                System.out.println(whitePiece1.getUserData());
                whitePiece1.setLayoutX(centerX);
                whitePiece1.setLayoutY(centerY);
                currPostion.setOccupied(true);
                whitePiece1.setUserData((String.valueOf(position)));
                if(prevPostion != null){
                    prevPostion.setOccupied(false);
                }


            if (color != null)
                tokenView.getStyleClass().add(color);
            tokenView.getStyleClass().add("clickable");
        }
//        turnIndicatorText.setText(String.format("Player %s Turn", game.currentPlayerTurn()));
    }

//    @FXML private ImageView position0;
//    @FXML private ImageView position1;
//    @FXML private ImageView position2;

}

