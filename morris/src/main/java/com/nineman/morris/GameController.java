/**
 * GameController is the main controller for the 9 Man Morris game. It manages user interactions,
 * updates the game state, and updates the game view. It implements the InputSource interface
 * to process user input from the game view.
 *
 * @version 1.0
 * @since 2023-04-26
 */
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

    /**
     * Initializes the GameController by setting up the game and user interactions.
     * It sets up an executor to handle game state updates in response to user clicks.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
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

    /**
     * Updates the game view based on the current state of the game. This method follows the
     * Model-View-Controller (MVC) architecture by using the game model data to update the view.
     * It clears and sets appropriate style classes for the token positions and updates the turn
     * indicator text to reflect the current player's turn.
     *
     * @param game The current game state to be displayed, representing the model in the MVC architecture.
     */
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

    /**
     * Retrieves user input in the form of clicks on the game board.
     * This method blocks until input is available.
     *
     * @return A string representation of the clicked position's index.
     */
    @Override
    public String getInput() {
        try {
            return clicks.take();

        } catch (InterruptedException e) {
            throw new Error("Unexpected Interrupt");
        }
    }
}
