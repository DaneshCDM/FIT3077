package com.nineman.morris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label turnIndicatorText;
    @FXML
    private ImageView position2;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Game game;
    private ArrayBlockingQueue<String> clicks = new ArrayBlockingQueue<>(5);

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    protected void readPositionClicked() {

    }

    @FXML
    protected void onPositionClicked() {
        System.out.println("Position 2 clicked!!");
    }

    protected void playGame() {
        this.game = new Game(this);
    }

    @FXML
    protected void switchToGameScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloController.class.getResource("game-scene.fxml"));
        String css = getClass().getResource("game.css").toExternalForm();
        root.getStylesheets().add(css);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        playGame();
    }
}