package com.example.textfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;

public class Controller {

    @FXML
    private Circle myCircle;
    private double x;
    private double y;

    public void up(ActionEvent e) {
        System.out.println("UP");
        myCircle.setCenterY(y += 10);
    }

    public void down(ActionEvent e) {
        System.out.println("DWN");
        myCircle.setCenterY(y -= 10);
    }

    public void left(ActionEvent e) {
        System.out.println("LFT");
        myCircle.setCenterX(x -= 10);
    }

    public void right(ActionEvent e) {
        System.out.println("RGT");
        myCircle.setCenterX(x += 10);
    }
}
