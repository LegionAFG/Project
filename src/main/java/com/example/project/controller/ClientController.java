package com.example.project.controller;

import com.example.project.help.NaviButtonHelper;
import com.example.project.help.ViewUrls;
import com.example.project.model.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ClientController {


    private final NaviButtonHelper naviButtonHelper;

    Client client;

    @FXML
    private Button homeButton;
    @FXML
    private TextField clientIdField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private ChoiceBox<String> nationalityChoiceBox;
    @FXML
    private ChoiceBox<String> genderChoiceBox;
    @FXML
    private ChoiceBox<String> relationshipChoiceBox;

    //TODO TableView Appointment implementieren
    //TODO TableColumn Appointment implementieren

    //TODO TableView Histories implementieren
    //TODO TableColumn Histories implementieren

    @FXML
    public void initialize() {

        clientIdField.setEditable(false);
        birthDatePicker.setEditable(false);

        nationalityChoiceBox.getItems().addAll(
                "Österreich", "Deutschland", "Schweiz", "Frankreich", "USA"
                //TODO All Countries
        );

        nationalityChoiceBox.setValue("Nationalität");

        //TODO doppelklick auf table view zu termin anzeigen
        //TODO doppelklick auf table view zu historie anzeigen

    }

    @FXML
    public void homeButton() throws IOException {

        Stage stage = (Stage) homeButton.getScene().getWindow();

        String url = ViewUrls.HOME_URL;

        naviButtonHelper.navigateTo(stage, url);
    }

    //TODO Appointment Button implementieren

    //TODO Historie Button implementieren

    //TODO Delete Button implementieren

    //TODO Save Button implementieren

    public void setClient(Client client){
        this.client = client;

        clientIdField.setText(client.getId().toString());
        firstNameField.setText(client.getFirstname());
        lastnameField.setText(client.getLastname());
        birthDatePicker.setValue(client.getBirthdate());
        genderChoiceBox.setValue(client.getGender());
        nationalityChoiceBox.setValue(client.getNationality());
        relationshipChoiceBox.setValue(client.getRelationship());


    }

}

