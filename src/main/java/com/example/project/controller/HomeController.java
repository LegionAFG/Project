package com.example.project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class HomeController {

    @FXML
    private Label helloLabel;

    public void initialize() {
        helloLabel.setText("Hallo aus Spring-Controller!");
    }
    //TODO: implement HomeController
}
