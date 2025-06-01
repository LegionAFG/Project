package com.example.project.controller;

import com.example.project.help.AlertHelper;
import com.example.project.help.NaviHelper;
import com.example.project.help.ValidationHelper;
import com.example.project.help.ViewUrls;
import com.example.project.model.Client;
import com.example.project.model.Histories;
import com.example.project.service.HistoriesService;
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
public class HistoriesController {

    private final NaviHelper naviButtonHelper;
    private final HistoriesService historiesService;
    private final AlertHelper alertHelper;
    private final ValidationHelper validationHelper;

    Histories histories;
    Client client;

    private boolean isUpdatingSelection = false;

    @FXML
    private Button homeButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField historieIdField;
    @FXML
    private TextField titleField;
    @FXML
    private TextField timeField;
    @FXML
    private DatePicker datePickerField;
    @FXML
    private TextArea historiesDescription;

    @FXML
    private TableView<Histories> historiesTable;
    @FXML
    private TableColumn<Histories, String> titleColumn;
    @FXML
    private TableColumn<Histories, String> timeColumn;
    @FXML
    private TableColumn<Histories, String> dateColumn;

    @FXML
    private void initialize() {
        historieIdField.setEditable(false);
        datePickerField.setEditable(false);

        historiesTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!isUpdatingSelection) {
                        if (newValue != null) {
                            setHistoriesData(newValue);
                        } else {
                            clearForm();
                        }
                    }
                }
        );

        updateSaveButtonLabel();
    }

    @FXML
    public void handleHomeButtonClick() throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        String url = ViewUrls.HOME_URL;
        naviButtonHelper.navigateTo(stage, url);
    }

    public void setClient(Client client) {
        this.client = client;
        loadHistories();
        clearForm();
        updateSaveButtonLabel();
    }

    public void setHistories(Histories histories) {
        this.histories = histories;
        this.client = histories.getClient();

        loadHistories();
        setHistoriesData(histories);
        updateSaveButtonLabel();
    }

    private void setHistoriesData(Histories histories) {
        this.histories = histories;

        historieIdField.setText(histories.getId().toString());
        timeField.setText(String.valueOf(histories.getTime()));
        titleField.setText(histories.getTitle());
        historiesDescription.setText(histories.getDescription());
        datePickerField.setValue(histories.getDate());

        updateSaveButtonLabel();
    }

    private void loadHistories() {
        if (client != null) {
            isUpdatingSelection = true;
            try {
                List<Histories> historiesList = historiesService.getHistoriesByClient(client);
                historiesTable.getItems().setAll(historiesList);
                historiesTable.getSelectionModel().clearSelection();
            } finally {
                isUpdatingSelection = false;
            }
        }
    }

    @FXML
    public void deleteHistoriesButtonClick() {
        Histories selectHistories = historiesTable.getSelectionModel().getSelectedItem();

        if (selectHistories == null) {
            alertHelper.showAlertInformation("Keine Auswahl",
                    "Bitte wählen Sie zuerst einen Eintrag aus der Tabelle aus, den Sie löschen möchten.");
            return;
        }

        boolean confirmed = alertHelper.showAlertDelete();
        if (confirmed) {
            historiesService.deleteHistories(selectHistories.getId());
            loadHistories();
            clearForm();
        }
    }

    @FXML
    private void saveHistoriesButtonClick() {
        try {

            if (!validationHelper.checkDatePicker(datePickerField, "Datum")) return;

            if (historiesDescription.getText().trim().isEmpty()) {
                alertHelper.showAlertError("Beschreibung fehlt", "Bitte geben Sie eine Beschreibung ein.");
                historiesDescription.requestFocus();
                return;
            }

            if (histories == null) {
                histories = new Histories();

                if (client == null) {
                    alertHelper.showAlertError("Fehler", "Kein Client zugewiesen.");
                    return;
                }
                histories.setClient(client);
            }

            histories.setTitle(titleField.getText().trim());
            histories.setDate(datePickerField.getValue());
            histories.setDescription(historiesDescription.getText().trim());

            try {
                histories.setTime(LocalTime.parse(timeField.getText().trim()));
            } catch (Exception e) {
                alertHelper.showAlertError("Ungültige Uhrzeit", "Format HH:mm, z.B. 09:45");
                timeField.requestFocus();
                return;
            }

            if (!validationHelper.validateHistories(histories)) {
                return;
            }

            histories = historiesService.saveHistories(histories);

            if (histories != null && histories.getId() != null) {
                historieIdField.setText(String.valueOf(histories.getId()));
            } else {
                historieIdField.clear();
            }

            loadHistories();
            clearForm();
            updateSaveButtonLabel();

        } catch (Exception e) {
            log.error("Fehler beim Speichern der Historie", e);
            alertHelper.showAlertError("Speicherfehler", "Ein unerwarteter Fehler ist aufgetreten.");
        }
    }

    private void clearForm() {
        histories = null;
        historieIdField.clear();
        titleField.clear();
        historiesDescription.clear();
        timeField.clear();
        datePickerField.setValue(null);
        updateSaveButtonLabel();
    }

    private void updateSaveButtonLabel() {
        if (histories != null && histories.getId() != null) {
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