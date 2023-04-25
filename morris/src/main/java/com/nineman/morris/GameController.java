package com.nineman.morris;

import com.nineman.morris.actions.InputSource;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController implements Initializable, InputSource {
    @FXML
    private Label turnIndicatorText;
    private ArrayBlockingQueue<String> clicks = new ArrayBlockingQueue<>(5);
    private final EnumMap<Color, String> COLOR_MAP = new EnumMap<>(Map.of(Color.WHITE, "wt", Color.BLACK, "bt"));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Game game = new Game(this);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < positions.getChildren().size(); i++) {
            Node node = positions.getChildren().get(i);
            int finalI = i;
            node.setOnMouseClicked(mouseEvent -> {
                clicks.offer(Integer.toString(finalI));
                executor.execute(() -> {
                    Game state = game.playTurn();
                    clicks.clear();
                    Platform.runLater(() -> update(state));
                });
            });
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

    @Override
    public String getInput() {
        try {
            return clicks.take();

        } catch (InterruptedException e) {
            throw new Error("Unexpected Interrupt");
        }
    }
}
