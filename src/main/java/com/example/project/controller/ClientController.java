package com.example.project.controller;

import com.example.project.help.NaviButtonHelper;
import com.example.project.help.ViewUrls;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ClientController {


    private final NaviButtonHelper naviButtonHelper;

    @FXML
    private Button homeButton;


    @FXML
    public void initialize() {
    }

    @FXML
    public void homeButton() throws IOException {

        Stage stage = (Stage) homeButton.getScene().getWindow();

        String url = ViewUrls.HOME_URL;

        naviButtonHelper.navigateTo(stage, url);
    }

}

