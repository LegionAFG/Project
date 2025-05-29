package com.example.project.controller;

import com.example.project.help.*;
import com.example.project.model.Appointment;
import com.example.project.model.Client;
import com.example.project.model.Histories;
import com.example.project.service.AppointmentService;
import com.example.project.service.ClientService;
import com.example.project.service.HistoriesService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final NaviHelper naviButtonHelper;
    private final NaviRowHelper naviRowHelper;
    private final AppointmentService appointmentService;
    private final HistoriesService historiesService;
    private final ClientService clientService;
    private final AlertHelper alertHelper;

    Client client;

    @FXML
    private Button homeButton;
    @FXML
    private Button saveButton;
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

        updateSaveButtonLabel();

        clientIdField.setEditable(false);
        birthDatePicker.setEditable(false);

        nationalityChoiceBox.getItems().addAll(
                CountryHelper.ALL_COUNTRIES
        );

        genderChoiceBox.getItems().addAll("Männlich", "Weiblich", "Divers");
        relationshipChoiceBox.getItems().addAll("Ledig", "Verheiratet", "Geschieden", "Verwitwet");

        nationalityChoiceBox.setValue("Bitte auswählen");
        genderChoiceBox.setValue("Bitte auswählen");
        relationshipChoiceBox.setValue("Bitte auswählen");

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
            alertHelper.showAlertInformation(null,"Kein Client ausgewählt");
        }
    }

    @FXML
    public void handleHistoriesButtonClick() throws IOException {
        Stage stage = (Stage) historiesButton.getScene().getWindow();


        if (client != null) {
            naviButtonHelper.navigateToHistoriesButton(stage, client);
        } else {
            alertHelper.showAlertInformation(null,"Kein Client ausgewählt");
        }
    }

    @FXML
    public void deleteClientButtonClick() throws IOException {

        boolean confirmed = alertHelper.showAlertDelete();

        if (confirmed) {

            clientService.deleteClientById(client.getId());
            handleHomeButtonClick();
        }
    }

    @FXML
    public void saveClientButtonClick() {
        try {
            if (client == null) {
                client = new Client();
            }

            client.setFirstname(firstNameField.getText().trim());
            client.setLastname(lastnameField.getText().trim());
            client.setBirthdate(birthDatePicker.getValue());
            client.setGender(genderChoiceBox.getValue());
            client.setNationality(nationalityChoiceBox.getValue());
            client.setRelationship(relationshipChoiceBox.getValue());

            client = clientService.saveClient(client);

            clientIdField.setText(String.valueOf(client.getId()));
            updateSaveButtonLabel();

        } catch (Exception e) {
            log.error("Fehler beim Speichern des Clients", e);
            alertHelper.showAlertError(
                    "Speichern fehlgeschlagen",
                    "Client konnte nicht gespeichert werden. " +
                            "Bitte überprüfe alle Eingaben und versuche es erneut."
            );
        }
    }


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

        updateSaveButtonLabel();

    }

    private void updateSaveButtonLabel() {
        if (client != null && client.getId() != null) {
            saveButton.setText("Update");
        } else {
            saveButton.setText("Save");
        }
    }

}

