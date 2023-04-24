package com.example.textfx;

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

    //Generate a new instance of the game
    protected void playGame() {
        this.game = new Game();

    }


    @FXML
    //This is the part of the code where we switch from the main menu to the main game once start is clicked
    protected void switchToGameScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloController.class.getResource("game-scene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        //begin playing the game
        playGame();
    }
}
