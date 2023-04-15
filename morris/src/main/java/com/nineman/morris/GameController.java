package com.nineman.morris;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController implements Initializable {
    @FXML
    private Label turnIndicatorText;
    private Game game;
    private ArrayBlockingQueue<String> clicks = new ArrayBlockingQueue<>(5);
    private final EnumMap<Color, String> COLOR_MAP = new EnumMap<>(Map.of(Color.WHITE, "wt", Color.BLACK, "bt"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new Game(this);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < positions.getChildren().size(); i++) {
            Node node = positions.getChildren().get(i);
            int finalI = i;
            node.setOnMouseClicked(mouseEvent -> {
                clicks.offer(Integer.toString(finalI));
                executor.execute(() -> {
                    Game state = game.playTurn();
                    Platform.runLater(() -> update(state));
                });
            });
        }
    }

    public String getClick() {
        try {
            return clicks.take();

        } catch (InterruptedException e) {
            throw new Error("Unexpected Interrupt");
        }
    }

    public void update(Game game) {
        for (int i = 0; i < positions.getChildren().size(); i++) {
            Node tokenView = positions.getChildren().get(i);
            tokenView.getStyleClass().clear();
            String color = COLOR_MAP.get(game.getBoard().getPositions(i).getColor());
            if (color != null)
                tokenView.getStyleClass().add(color);
            tokenView.getStyleClass().add("clickable");
        }
        turnIndicatorText.setText(String.format("Player %s Turn", game.currentPlayerTurn()));
    }

    @FXML private Pane positions;
    /* To delete */
    @FXML private ImageView position0;
    @FXML private ImageView position1;
    @FXML private ImageView position2;
    @FXML private ImageView position3;
    @FXML private ImageView position4;
    @FXML private ImageView position5;
    @FXML private ImageView position6;
    @FXML private ImageView position7;
    @FXML private ImageView position8;
}
