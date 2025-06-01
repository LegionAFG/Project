package com.example.project.controller;

import com.example.project.help.AlertHelper;
import com.example.project.help.NaviHelper;
import com.example.project.help.ValidationHelper;
import com.example.project.help.ViewUrls;
import com.example.project.model.Appointment;
import com.example.project.model.Client;
import com.example.project.service.AppointmentService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Scope("prototype")
@Slf4j
public class AppointmentController {

    private final NaviHelper naviButtonHelper;
    private final AppointmentService appointmentService;
    private final AlertHelper alertHelper;
    private final ValidationHelper validationHelper;

    Client client;
    Appointment appointment;

    private boolean isUpdatingSelection = false;

    @FXML
    private Button homeButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField appointmentIdField;
    @FXML
    private TextField institutionField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postCodeField;
    @FXML
    private TextField timeField;
    @FXML
    private DatePicker datePickerField;
    @FXML
    private ChoiceBox<String> statusChoiceBox;

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
    private TableColumn<Appointment, Integer> postCodeColumn;
    @FXML
    private TableColumn<Appointment, String> cityColumn;
    @FXML
    private TableColumn<Appointment, String> streetColumn;

    @FXML
    public void initialize() {

        institutionColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getInstitution()));

        statusColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getStatus()));

        dateColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getDate().toString()));

        timeColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getTime().toString()));

        postCodeColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getPostalCode()));

        cityColumn.setCellValueFactory(
                cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCity()));

        streetColumn.setCellValueFactory(
                cellData -> new ReadOnlyStringWrapper(cellData.getValue().getStreet()));

        updateSaveButtonLabel();

        appointmentIdField.setEditable(false);
        datePickerField.setEditable(false);

        statusChoiceBox.getItems().addAll("Offen", "Erledigt");
        statusChoiceBox.setValue("Bitte auswählen");

        appointmentTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!isUpdatingSelection) {
                        if (newValue != null) {
                            setAppointmentData(newValue);
                        } else {
                            clearForm();
                        }
                    }
                }
        );

    }

    @FXML
    public void handleHomeButtonClick() throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        String url = ViewUrls.HOME_URL;
        naviButtonHelper.navigateTo(stage, url);
    }

    public void setClient(Client client) {
        this.client = client;
        loadAppointments();
        clearForm();
        updateSaveButtonLabel();
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
        this.client = appointment.getClient();

        loadAppointments();
        setAppointmentData(appointment);
        updateSaveButtonLabel();
    }

    private void setAppointmentData(Appointment appointment) {
        this.appointment = appointment;

        appointmentIdField.setText(appointment.getId().toString());
        institutionField.setText(appointment.getInstitution());
        cityField.setText(appointment.getCity());
        streetField.setText(appointment.getStreet());
        postCodeField.setText(String.valueOf(appointment.getPostalCode()));
        timeField.setText(appointment.getTime().toString());
        datePickerField.setValue(appointment.getDate());
        statusChoiceBox.setValue(appointment.getStatus());

        updateSaveButtonLabel();
    }

    private void loadAppointments() {
        if (client != null) {
            isUpdatingSelection = true;
            try {
                List<Appointment> appointmentList = appointmentService.getAppointmentsByClient(client);
                appointmentTable.getItems().setAll(appointmentList);
                appointmentTable.getSelectionModel().clearSelection();
            } finally {
                isUpdatingSelection = false;
            }
        }
    }

    @FXML
    public void deleteAppointmentButtonClick() {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            alertHelper.showAlertInformation("Keine Auswahl",
                    "Bitte wählen Sie zuerst einen Termin aus der Tabelle aus, den Sie löschen möchten.");
            return;
        }

        boolean confirmed = alertHelper.showAlertDelete();
        if (confirmed) {
            appointmentService.deleteAppointment(selectedAppointment.getId());
            loadAppointments();
            clearForm();
        }
    }

    @FXML
    private void saveAppointmentButtonClick() {
        try {

            if (!validationHelper.checkDatePicker(datePickerField, "Datum")) return;
            if (!validationHelper.checkChoiceBox(statusChoiceBox, "Status")) return;

            if (appointment == null) {
                appointment = new Appointment();

                if (client == null) {
                    alertHelper.showAlertError("Fehler", "Kein Client zugewiesen.");
                    return;
                }
                appointment.setClient(client);
            }

            appointment.setInstitution(institutionField.getText().trim());
            appointment.setCity(cityField.getText().trim());
            appointment.setStreet(streetField.getText().trim());
            appointment.setDate(datePickerField.getValue());
            appointment.setStatus(statusChoiceBox.getValue());

            try {
                appointment.setPostalCode(Integer.parseInt(postCodeField.getText().trim()));
            } catch (NumberFormatException e) {
                alertHelper.showAlertError("Ungültige Postleitzahl", "Bitte geben Sie eine gültige Zahl ein.");
                postCodeField.requestFocus();
                return;
            }

            try {
                appointment.setTime(LocalTime.parse(timeField.getText().trim()));
            } catch (Exception e) {
                alertHelper.showAlertError("Ungültige Uhrzeit", "Format HH:mm, z.B. 09:45");
                timeField.requestFocus();
                return;
            }

            if (!validationHelper.validateAppointment(appointment)) {
                return;
            }

            appointment = appointmentService.saveAppointment(appointment);

            if (appointment != null && appointment.getId() != null) {
                appointmentIdField.setText(String.valueOf(appointment.getId()));
            } else {
                appointmentIdField.clear();
            }

            loadAppointments();
            clearForm();
            updateSaveButtonLabel();

        } catch (Exception e) {
            log.error("Fehler beim Speichern des Termins", e);
            alertHelper.showAlertError("Speicherfehler", "Ein unerwarteter Fehler ist aufgetreten.");
        }
    }

    private void clearForm() {
        appointment = null;
        appointmentIdField.clear();
        institutionField.clear();
        cityField.clear();
        streetField.clear();
        postCodeField.clear();
        timeField.clear();
        datePickerField.setValue(null);
        statusChoiceBox.setValue("Bitte auswählen");
        updateSaveButtonLabel();
    }

    private void updateSaveButtonLabel() {
        if (appointment != null && appointment.getId() != null) {
            saveButton.setText("Update");
        } else {
            saveButton.setText("Save");
        }
    }

    @FXML
    public void backToClientButton() throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        naviButtonHelper.navigateToClient(stage,client);
    }
}