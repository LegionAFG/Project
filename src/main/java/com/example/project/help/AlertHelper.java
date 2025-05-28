package com.example.project.help;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlertHelper {

    public void showAlertInformation(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void showAlertWarning(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warnung");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public boolean showAlertDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Löschen bestätigen");
        alert.setHeaderText("Soll der Client wirklich gelöscht werden?");
        alert.setContentText("Die Löschung kann nicht rückgängig gemacht werden.");

        ButtonType buttonYes = new ButtonType("Ja");
        ButtonType buttonNo  = new ButtonType("Nein");
        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonYes;
    }

    public void showAlertError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
