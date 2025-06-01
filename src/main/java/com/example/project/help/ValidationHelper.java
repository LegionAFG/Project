package com.example.project.help;

import com.example.project.model.Appointment;
import com.example.project.model.Client;
import com.example.project.model.Histories;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ValidationHelper {

    private final AlertHelper alertHelper;
    private final Validator validator;

    public boolean validateClient(Client client) {
        Set<ConstraintViolation<Client>> violations = validator.validate(client);

        if (!violations.isEmpty()) {
            ConstraintViolation<Client> firstError = violations.iterator().next();
            alertHelper.showAlertError("Validierungsfehler", firstError.getMessage());
            return false;
        }
        return true;
    }

    public boolean validateAppointment(Appointment appointment) {
        Set<ConstraintViolation<Appointment>> violations = validator.validate(appointment);

        if (!violations.isEmpty()) {
            ConstraintViolation<Appointment> firstError = violations.iterator().next();
            alertHelper.showAlertError("Validierungsfehler", firstError.getMessage());
            return false;
        }
        return true;
    }

    public boolean validateHistories(Histories histories) {
        Set<ConstraintViolation<Histories>> violations = validator.validate(histories);

        if (!violations.isEmpty()) {
            ConstraintViolation<Histories> firstError = violations.iterator().next();
            alertHelper.showAlertError("Validierungsfehler", firstError.getMessage());
            return false;
        }
        return true;
    }

    public boolean checkChoiceBox(ChoiceBox<String> choiceBox, String fieldName) {
        if ("Bitte auswählen".equals(choiceBox.getValue())) {
            alertHelper.showAlertError(fieldName + " fehlt", "Bitte wählen Sie eine Option aus.");
            choiceBox.requestFocus();
            return false;
        }
        return true;
    }

    public boolean checkDatePicker(DatePicker datePicker, String fieldName) {
        if (datePicker.getValue() == null) {
            alertHelper.showAlertError(fieldName + " fehlt", "Bitte wählen Sie ein Datum aus.");
            datePicker.requestFocus();
            return false;
        }
        return true;
    }
}
