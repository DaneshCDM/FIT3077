package com.example.textfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;


import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;


public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label turnIndicatorText;
    @FXML
    private Circle position2;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Game game;
    private ArrayBlockingQueue<String> clicks = new ArrayBlockingQueue<>(5);

    protected void readPositionClicked() {

    }

    //gonna use this to check if the code is working
    @FXML
    protected void onPositionClicked() {
        System.out.println("Position 2 clicked!!");
    }


    //Generate a new instance of the game
    protected void playGame() {
        this.game = new Game(this);

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
