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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController implements Initializable, InputSource, BoardListener {
    @FXML
    private AnchorPane gameScene;

    @FXML
    private Label turnIndicatorText;
    private ArrayBlockingQueue<String> clicks = new ArrayBlockingQueue<>(5);
    private ExecutorService executor;
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
        game.getBoard().addBoardListener(this);
        Tooltip t = new Tooltip(getRules());
        Tooltip.install(tutorial, t);
        this.executor = Executors.newSingleThreadExecutor();
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
//        onGameOver(Color.WHITE); // backdoor game over option for debugging
    }

    private String getRules() {
        return """
                Each of the two players has 9 pieces (tokens or men) that, in turn,
                are placed on the board on one of the empty 24 line intersections,
                starting with an empty board. Once all 18 pieces have been placed\s
                onto the board, players, again in turn, slide one of their pieces along
                a board line to an empty adjacent intersection (not diagonally).
                If a player is able to form a straight row of three pieces along one
                of the board's lines (i.e. not diagonally), he/she has a "mill" and
                may remove one of his/her opponent's pieces from the board that is not part
                of a mill. This can happen either during the initial placing of the pieces
                onto the board or the subsequent sliding of pieces along the board's lines.
                A piece that has been removed from the board cannot be placed again and is "lost"
                for the corresponding player. Once a player has only three pieces left, he/she
                may jump (fly or hop) one piece per turn to an empty intersection (and hence does
                not have to slide a piece along one of the board's lines).""";
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

        whiteTokenDisplay.getChildren().forEach(x -> x.setVisible(true));
        blackTokenDisplay.getChildren().forEach(x -> x.setVisible(true));
        whiteTokenDisplay.getChildren()
                            .stream()
                            .limit(9 - game.getBoard().whiteTokensLeft())
                            .forEach(x -> x.setVisible(false));
        blackTokenDisplay.getChildren()
                            .stream()
                            .limit(9 - game.getBoard().blackTokensLeft())
                            .forEach(x -> x.setVisible(false));
    }

    @Override
    public void onGameOver(Color c) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Victory!");
            alert.setHeaderText(String.format("Player %s wins!", c.playerNumber()));
            if (alert.showAndWait().get() == ButtonType.OK) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
                Parent root = null;
                try {
                    root = fxmlLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                Stage stage = (Stage) gameScene.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                executor.close();
            }
        });
    }

    @FXML private Pane positions;
    @FXML private Node tutorial;
    @FXML private Pane whiteTokenDisplay;
    @FXML private Pane blackTokenDisplay;

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
