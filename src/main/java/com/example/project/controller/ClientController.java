package com.example.project.controller;

import com.example.project.help.NaviHelper;
import com.example.project.help.NaviRowHelper;
import com.example.project.help.ViewUrls;
import com.example.project.model.Appointment;
import com.example.project.model.Client;
import com.example.project.model.Histories;
import com.example.project.service.AppointmentService;
import com.example.project.service.HistoriesService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientController {


    private final NaviHelper naviButtonHelper;
    private final NaviRowHelper naviRowHelper;
    private final AppointmentService appointmentService;
    private final HistoriesService historiesService;

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

    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<Appointment, String> institutionColumn;
    @FXML
    private TableColumn<Appointment, String> statusColumn;
    @FXML
    private TableColumn<Appointment, String> dateColumn;
    @FXML
    private TableColumn<Appointment, String> timeColumn;
    @FXML
    private TableColumn<Appointment, Long> postCodeColumn;
    @FXML
    private TableColumn<Appointment, String> cityColumn;
    @FXML
    private TableColumn<Appointment, String> streetColumn;

    @FXML
    private TableView<Histories> historiesTable;
    @FXML
    private TableColumn<Histories, String> titleColumn;
    @FXML
    private TableColumn<Histories, String> historiesTimeColumn;
    @FXML
    private TableColumn<Histories, String> historiesDateColumn;

    @FXML
    public void initialize() {

        clientIdField.setEditable(false);
        birthDatePicker.setEditable(false);

        nationalityChoiceBox.getItems().addAll(
                "Österreich", "Deutschland", "Schweiz", "Frankreich", "USA"
                //TODO All Countries
        );

        nationalityChoiceBox.setValue("Bitte auswählen");

        naviRowHelper.rowNavigateToAppointment(appointmentTable);
        naviRowHelper.rowNavigateToHistories(historiesTable);

    }

    @FXML
    public void handleHomeButtonClick() throws IOException {

        Stage stage = (Stage) homeButton.getScene().getWindow();

        String url = ViewUrls.HOME_URL;

        naviButtonHelper.navigateTo(stage, url);
    }

    @FXML
    public void handleAppointmentButtonClick() throws IOException {
        Stage stage = (Stage) appointmentButton.getScene().getWindow();


        if (client != null) {
            naviButtonHelper.navigateToAppointmentButton(stage, client);
        } else {
            showAlert();
        }
    }

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

        appointmentTable.setItems(
                FXCollections.observableArrayList(
                        appointmentService.getAppointmentsByClient(client)
                )
        );
        historiesTable.setItems(
                FXCollections.observableArrayList(
                        historiesService.getHistoriesByClient(client)
                )
        );

    }

}

