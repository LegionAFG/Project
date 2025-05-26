package com.example.project.controller;

import com.example.project.help.NaviHelper;
import com.example.project.help.ViewUrls;
import com.example.project.model.Appointment;
import com.example.project.model.Client;
import com.example.project.service.AppointmentService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentController {

    private final NaviHelper naviButtonHelper;
    private final AppointmentService appointmentService;

    Client client;
    Appointment appointment;

    @FXML
    private Button homeButton;
    @FXML
    private TextField appointmentIdField;
    @FXML
    private TextField institutionField;
    @FXML
    private TextField cityFiled;
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
    private TableColumn<Appointment, Long> postCodeColumn;
    @FXML
    private TableColumn<Appointment, String> cityColumn;
    @FXML
    private TableColumn<Appointment, String> streetColumn;


    public void initialize() {

        appointmentIdField.setEditable(false);
        datePickerField.setEditable(false);

        statusChoiceBox.getItems().addAll("Offen", "Erledigt");

        statusChoiceBox.setValue("Bitte auswählen");

        //TODO doppelklick auf table view zu termin anzeigen
        //TODO doppelklick auf table view zu historie anzeigen

    }

    @FXML
    public void handleHomeButtonClick() throws Exception {

        Stage stage = (Stage) homeButton.getScene().getWindow();

        String url = ViewUrls.HOME_URL;

        naviButtonHelper.navigateTo(stage, url);
    }

    public void setClient(Client client) {
        this.client = client;
        loadAppointments();
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;

        appointmentIdField.setText(appointment.getClient().getId().toString());
        institutionField.setText(appointment.getInstitution());
        cityFiled.setText(appointment.getCity());
        streetField.setText(appointment.getStreet());
        postCodeField.setText(String.valueOf(appointment.getPostalCode()));
        timeField.setText(appointment.getTime().toString());
        datePickerField.setValue(appointment.getDate());
        statusChoiceBox.setValue(appointment.getStatus());
    }

    private void loadAppointments() {
        if (client != null) {
            List<Appointment> appointmentList = appointmentService.getAppointmentsByClient(client);
            appointmentTable.getItems().setAll(appointmentList);
        }
    }

    //TODO Delete Button implementieren

    //TODO Save Button implementieren

    //TODO Back Button implementieren


}
