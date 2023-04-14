package com.nineman.morris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label turnIndicatorText;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Game game;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    protected void playGame() {
        this.game = new Game();

    }

    @FXML
    protected void switchToGameScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloController.class.getResource("game-scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        playGame();
    }
}