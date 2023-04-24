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


import java.io.IOException;


public class HelloController {
    @FXML
    private Label welcomeText;
    private Stage stage;
    private Scene scene;
    private Parent root;




    @FXML
    //This is the part of the code where we switch from the main menu to the main game once start is clicked
    protected void switchToGameScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game-scene.fxml"));
        root = fxmlLoader.load();

        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
}
