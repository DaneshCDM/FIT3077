/**
 * MenuController is responsible for managing the user interactions within the game menu.
 * This class handles the event of switching from the menu scene to the game scene.
 *
 * @version 1.0
 * @since 2023-04-26
 */
package com.nineman.morris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    /**
     * switchToGameScene method is triggered by an event (e.g., a button click) in the game menu.
     * It loads the game-scene FXML layout file and switches the current scene to the game scene.
     *
     * @param event The event that triggered the method, typically a button click.
     * @throws IOException If there is an issue loading the game-scene FXML layout file.
     */
    @FXML
    protected void switchToGameScene(ActionEvent event) throws IOException {
        // Load the game-scene FXML layout file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game-scene.fxml"));
        Parent root = fxmlLoader.load();
        // Load the game CSS file and add it to the scene
        String css = getClass().getResource("game.css").toExternalForm();
        root.getStylesheets().add(css);

        // Create a new scene with the loaded root and switch to the game scene

        // Retrieve the stage from the event source (the button that triggered the event)
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Set the newly created scene as the scene for the stage
        stage.setScene(scene);
        // Show the stage with the updated scene

        stage.show();
    }
}