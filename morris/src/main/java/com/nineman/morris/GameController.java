package com.nineman.morris;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

public class GameController implements Initializable {
    @FXML
    private Label turnIndicatorText;
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
    }

    public String getClick() {
        try {
            return clicks.take();

        } catch (InterruptedException e) {
            throw new Error("Unexpected Interrupt");
        }
    }

    public void update(Game game) {
        List<ImageView> positions = List.of(position0, position1, position2);
        for (int i = 0; i < positions.size(); i++) {
            ImageView tokenView = positions.get(i);
            tokenView.getStyleClass().clear();
            String color = COLOR_MAP.get(game.getBoard().getPositions(i).getColor());
            if (color != null)
                tokenView.getStyleClass().add(color);
            tokenView.getStyleClass().add("clickable");
        }
        turnIndicatorText.setText(String.format("Player %s Turn", game.currentPlayerTurn()));
    }


    @FXML private ImageView position0;
    @FXML private ImageView position1;
    @FXML private ImageView position2;
}
