package com.example.textfx;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

public class GameController implements Initializable {
        @FXML
        private Label turnIndicatorText;
        @FXML
        private Circle position2;
        @FXML
        private Circle position3;
        private Game game;
        private ArrayBlockingQueue<String> clicks = new ArrayBlockingQueue<>(5);

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

            position2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("2 clicked!!!");
                }
            });
        }
}

