package com.example.project.controller;

import com.example.project.help.NaviButtonHelper;
import com.example.project.help.ViewUrls;
import com.example.project.model.Appointment;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentController {

    private final NaviButtonHelper naviButtonHelper;

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

    //TODO TableView Appointment implementieren
    //TODO TableColumn Appointment implementieren

    public void initialize() {

        appointmentIdField.setEditable(false);
        datePickerField.setEditable(false);

        statusChoiceBox.getItems().addAll("Offen","Erledigt");

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

    public void setAppointment(Appointment appointment){
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

    //TODO Delete Button implementieren

    //TODO Save Button implementieren


}
