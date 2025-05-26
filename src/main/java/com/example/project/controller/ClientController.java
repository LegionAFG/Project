package com.example.project.controller;

import com.example.project.help.NaviHelper;
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


    private final NaviHelper naviButtonHelper;

    Client client;

    @FXML
    private Button homeButton;
    @FXML
    private Button historiesButton;
    @FXML
    private Button appointmentButton;
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

        nationalityChoiceBox.setValue("Bitte auswählen");

        //TODO doppelklick auf table view zu termin anzeigen
        //TODO doppelklick auf table view zu historie anzeigen

    }

    @FXML
    public void handleHomeButtonClick() throws IOException {

        Stage stage = (Stage) homeButton.getScene().getWindow();

        String url = ViewUrls.HOME_URL;

        naviButtonHelper.navigateTo(stage, url);
    }

    //TODO Appointment Button implementieren
    @FXML
    public void handleAppointmentButtonClick() throws IOException {
        Stage stage = (Stage) appointmentButton.getScene().getWindow();


        if (client != null) {
            naviButtonHelper.navigateToAppointmentButton(stage, client);
        } else {
            showAlert();
        }
    }

    //TODO Historie Button implementieren
    @FXML
    public void handleHistoriesButtonClick() throws IOException {
        Stage stage = (Stage) historiesButton.getScene().getWindow();


        if (client != null) {
            naviButtonHelper.navigateToHistoriesButton(stage, client);
        } else {
            showAlert();
        }
    }

    private void showAlert() {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Kein Client ausgewählt.");
        alert.showAndWait();
    }

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

