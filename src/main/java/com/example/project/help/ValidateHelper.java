package com.example.project.help;

import javafx.scene.control.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ValidateHelper {

    private final AlertHelper alertHelper;

    private boolean isEmptyOrNoMatch(TextInputControl field, String regex,
                                     String title, String msg) {
        String text = field.getText().trim();
        if (text.isEmpty() || !text.matches(regex)) {
            alertHelper.showAlertError(title, msg);
            field.requestFocus();
            return true;
        }
        return false;
    }

    public boolean validateAppointment(TextField postCodeField,
                                       DatePicker datePickerField,
                                       TextField institutionField,
                                       TextField cityField,
                                       TextField streetField,
                                       TextField timeField,
                                       ChoiceBox<String> statusChoiceBox) {

        if (isEmptyOrNoMatch(postCodeField, "^\\d{4,5}$",
                "Ungültige Postleitzahl", "Bitte 4‑ oder 5‑stellige Zahl eingeben."))
            return false;

        if (datePickerField.getValue() == null) {
            alertHelper.showAlertError("Datum fehlt", "Bitte wähle ein Datum aus.");
            datePickerField.requestFocus();
            return false;
        }

        if (isEmptyOrNoMatch(institutionField, "^[\\p{L} ]+$",
                "Ungültige Institution", "Nur Buchstaben und Leerzeichen erlaubt."))
            return false;

        if (isEmptyOrNoMatch(cityField, "^[\\p{L} ]+$",
                "Ungültige Stadt", "Nur Buchstaben und Leerzeichen erlaubt."))
            return false;

        if (isEmptyOrNoMatch(streetField, "^[\\p{L}\\d\\s/\\-.]+$",
                "Ungültige Straße", "Nur Buchstaben, Zahlen, Leerzeichen, '/', '-' oder '.' erlaubt."))
            return false;

        if (isEmptyOrNoMatch(timeField, "([01]\\d|2[0-3]):[0-5]\\d",
                "Ungültige Uhrzeit", "Format HH:mm, z. B. 09:45."))
            return false;

        if ("Bitte auswählen".equals(statusChoiceBox.getValue())) {
            alertHelper.showAlertError("Status fehlt", "Bitte wähle einen Status aus.");
            statusChoiceBox.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validateClient(TextField firstNameField,
                                  TextField lastNameField,
                                  DatePicker birthDatePicker,
                                  ChoiceBox<String> genderChoiceBox,
                                  ChoiceBox<String> nationalityChoiceBox,
                                  ChoiceBox<String> relationshipChoiceBox) {

        if (isEmptyOrNoMatch(firstNameField, "^[\\p{L} ]+$",
                "Ungültiger Vorname", "Nur Buchstaben und Leerzeichen erlaubt."))
            return false;

        if (isEmptyOrNoMatch(lastNameField, "^[\\p{L} ]+$",
                "Ungültiger Nachname", "Nur Buchstaben und Leerzeichen erlaubt."))
            return false;

        LocalDate dob = birthDatePicker.getValue();
        if (dob == null) {
            alertHelper.showAlertError("Geburtsdatum fehlt", "Bitte wähle ein Datum aus.");
            birthDatePicker.requestFocus();
            return false;
        }
        if (dob.isAfter(LocalDate.now())) {
            alertHelper.showAlertError("Ungültiges Datum", "Geburtsdatum darf nicht in der Zukunft liegen.");
            birthDatePicker.requestFocus();
            return false;
        }

        if ("Bitte auswählen".equals(genderChoiceBox.getValue())) {
            alertHelper.showAlertError("Geschlecht fehlt", "Bitte wähle ein Geschlecht aus.");
            genderChoiceBox.requestFocus();
            return false;
        }

        if ("Bitte auswählen".equals(nationalityChoiceBox.getValue())) {
            alertHelper.showAlertError("Nationalität fehlt", "Bitte wähle eine Nationalität aus.");
            nationalityChoiceBox.requestFocus();
            return false;
        }

        if ("Bitte auswählen".equals(relationshipChoiceBox.getValue())) {
            alertHelper.showAlertError("Beziehungsstatus fehlt", "Bitte wähle einen Status aus.");
            relationshipChoiceBox.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validateHistories(TextField titleField,
                                     DatePicker datePicker,
                                     TextArea descriptionArea,
                                     TextField timeField) {

        if (datePicker.getValue() == null) {
            alertHelper.showAlertError("Datum fehlt", "Bitte wähle ein Datum aus.");
            datePicker.requestFocus();
            return false;
        }

        if (isEmptyOrNoMatch(titleField, "^[\\p{L} ]+$",
                "Ungültiger Titel", "Nur Buchstaben und Leerzeichen erlaubt."))
            return false;

        if (descriptionArea.getText().trim().isEmpty()) {
            alertHelper.showAlertError("Beschreibung fehlt", "Bitte gib eine Beschreibung ein.");
            descriptionArea.requestFocus();
            return false;
        }

        return !isEmptyOrNoMatch(timeField, "([01]\\d|2[0-3]):[0-5]\\d",
                "Ungültige Uhrzeit", "Format HH:mm, z. B. 09:45.");
    }
}
