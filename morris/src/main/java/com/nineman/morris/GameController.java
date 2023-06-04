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
import com.nineman.morris.actions.RandomMoveGenerator;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameController extends BoardListenerAdapter implements Initializable, InputSource, GameListener {
    @FXML
    private AnchorPane gameScene;

    @FXML
    private Label turnIndicatorText;
    private ArrayBlockingQueue<String> clicks = new ArrayBlockingQueue<>(5);
    private ExecutorService executor;
    private MenuController menuController;
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
        List<InputSource> playerSources = new ArrayList<>(List.of(this, this));
        RandomMoveGenerator generator = null;
        if (menuController.gameMode() == MenuController.WHITE_VS_AI) {
            generator = new RandomMoveGenerator(Color.BLACK);
            playerSources.set(1, generator);
        } else if (menuController.gameMode() == MenuController.BLACK_VS_AI) {
            generator = new RandomMoveGenerator(Color.WHITE);
            playerSources.set(0, generator);
        }
        // Create a new instance of the Game class, passing 'this' as the InputSource
        Game game = new Game(playerSources);
        game.registerListener(this);
        game.getBoard().addBoardListener(this);
        if (generator != null) {
            generator.setGame(game);
            game.notifyNextState();
        }
        // Set up a tooltip with the game rules and attach it to the tutorial node
        Tooltip t = new Tooltip(getRules());
        Tooltip.install(tutorial, t);
        t.setStyle("-fx-font-size: 15");
        t.setShowDelay(Duration.seconds(0.2));
        // Create an executor to handle game state updates in response to user clicks
        this.executor = Executors.newSingleThreadExecutor();
        // Set up event handlers for each position on the game board
        for (int i = 0; i < positions.getChildren().size(); i++) {
            Node node = positions.getChildren().get(i);
            int finalI = i;
            node.setOnMouseClicked(mouseEvent -> {
                // Set up event handlers for each position on the game board
                clicks.offer(Integer.toString(finalI));
                // Execute the game state update in a separate thread
                executor.execute(() -> {
                    // Play the current player's turn and get the updated game state
                    Game state = game.playTurn();
                    // Clear the clicks queue and update the game view on the JavaFX application thread
                    clicks.clear();
                });
            });
        }
//        onGameOver(Color.WHITE); // backdoor game over option for debugging
    }

    public GameController(MenuController controller) {
        menuController = controller;
    }

    private String getRules() {
        return """
                Initial Phase: Each of the two players has 9 pieces (tokens or men) that, in turn,
                are placed on the board on one of the empty 24 line intersections,
                starting with an empty board. \n Mid-Game Phase: Once all 18 pieces have been placed\s
                onto the board, players, again in turn, slide one of their pieces along
                a board line to an empty adjacent intersection (not diagonally).
                If a player is able to form a straight row of three pieces along one
                of the board's lines (i.e. not diagonally), he/she has formed a "mill" and
                may remove one of his/her opponent's pieces from the board that is not part
                of a mill. This can happen either during the initial placing of the pieces
                onto the board or the subsequent sliding of pieces along the board's lines.
                A piece that has been removed from the board cannot be placed again and is "lost"
                for the corresponding player. \n End-Game Phase: Once a player has only three pieces left, he/she
                may jump (fly or hop) one piece per turn to an empty intersection (and hence does
                not have to slide a piece along one of the board's lines). \n
                The winner is decided when his/her opponent is left with only two tokens
                or does not have any more valid moves""";
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
        // Update the style classes for each token position based on the game state
        for (int i = 0; i < positions.getChildren().size(); i++) {
            Node tokenView = positions.getChildren().get(i);
            tokenView.getStyleClass().clear();
            String color = COLOR_MAP.get(game.getBoard().getPositions(i).getColor());
            if (color != null)
                tokenView.getStyleClass().add(color);
            tokenView.getStyleClass().add("clickable");
        }
        // Update the turn indicator text to reflect the current player's turn
        turnIndicatorText.setText(String.format("Player %s Turn", game.currentPlayerTurn()));

        // Update the visibility of the token display panes based on the number of remaining tokens
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
            alert.setHeaderText(String.format("\uD83C\uDF89 Congratulations! \uD83C\uDF89 \n \uD83C\uDFC6 Player %s wins! \uD83C\uDFC6", c.playerNumber()));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isEmpty() || result.get() == ButtonType.OK) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
                Parent root = null;
                try {
                    root = fxmlLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                String css = getClass().getResource("menu.css").toExternalForm();
                scene.getStylesheets().add(css);
                Stage stage = (Stage) gameScene.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                executor.close();
            }
        });
    }

    @FXML public Pane positions;
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

    @Override
    public void OnNextGameState(Game game) {
        Platform.runLater(() -> update(game));
    }
}
