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
        private Circle position0;
        @FXML
        private Circle position1;
        @FXML
        private Circle position2;
        @FXML
        private Circle position3;
        private Game game;
        private ArrayBlockingQueue<String> clicks = new ArrayBlockingQueue<>(5);

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
//            position0.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent mouseEvent) {
//                    System.out.println("0 clicked!!!");
//                    clicks.offer("0");
//
//                }
//            });
//            position1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent mouseEvent) {
//                    System.out.println("1 clicked!!!");
//                    clicks.offer("1");
//
//                }
//            });
            position2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    System.out.println("2 clicked!!!");
                    clicks.offer("2");

                }
            });
            game = new Game(this);

        }

      public String getClick() {
        try {
            return clicks.take();

        } catch (InterruptedException e) {
            throw new Error("Unexpected Interrupt");
        }
    }
//    @FXML private ImageView position0;
//    @FXML private ImageView position1;
//    @FXML private ImageView position2;

}

