/**
 * MenuApplication is the main entry point for the Nine Men's Morris game.
 * This class is responsible for initializing the game menu and setting up the JavaFX application.
 *
 * @version 1.0
 * @since 2023-04-26
 */

package com.nineman.morris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MenuApplication extends Application {
    /**
     * The start method initializes the game menu, loading the FXML layout file and setting up the stage and scene.
     *
     * @param stage The main application stage that will be used for rendering the game menu.
     * @throws IOException If there is an issue loading the FXML layout file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the menu view
        FXMLLoader fxmlLoader = new FXMLLoader(MenuApplication.class.getResource("menu-view.fxml"));
        // Create a scene with the content using FXML file and specify dimensions
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        // Set tile of Stage (Application Window)
        stage.setTitle("Nine Men's Morris \uD83C\uDFAE");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method launches the JavaFX application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * The stop method is called when the application is closed. It ensures the application exits gracefully.
     *
     * @throws Exception If there is an issue during the application shutdown process.
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }
}