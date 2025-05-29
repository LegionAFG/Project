package com.example.project.controller;

import com.example.project.help.AlertHelper;
import com.example.project.help.NaviHelper;
import com.example.project.help.ValidateHelper;
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
    private final ValidateHelper validateHelper;
    Histories histories;
    Client client;

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


        //TODO doppelklick auf table view zu termin anzeigen
        //TODO doppelklick auf table view zu historie anzeigen


    }

    public void handleHomeButtonClick() throws Exception {

        Stage stage = (Stage) homeButton.getScene().getWindow();

        String url = ViewUrls.HOME_URL;

        naviButtonHelper.navigateTo(stage, url);
    }

    public void setClient(Client client){
        this.client = client;
        loadHistories();
    }

    public void setHistories(Histories histories){
        this.histories = histories;
        loadHistories();

        historieIdField.setText(histories.getClient().getId().toString());
        timeField.setText(String.valueOf(histories.getTime()));
        titleField.setText(histories.getTitle());
    }

    private void loadHistories() {
        if (client != null) {
            List<Histories> historiesList = historiesService.getHistoriesByClient(client);
            historiesTable.getItems().setAll(historiesList);
        }
    }

    @FXML
    public void deleteHistoriesButtonClick() {

        Histories selectHistories = historiesTable.getSelectionModel().getSelectedItem();
        boolean confirmed = alertHelper.showAlertDelete();

        if (selectHistories == null) {
            alertHelper.showAlertInformation("Keine Auswahl",
                    "Bitte wählen Sie zuerst einen Termin aus der Tabelle aus, den Sie löschen möchten.");
            return;
        }

        if (confirmed) {

            historiesService.deleteHistories(selectHistories.getId());

            loadHistories();

        }
    }

    @FXML
    private void saveHistoriesButtonClick() {

        try {

            if (!validateHelper.validateHistories(
                   titleField,datePickerField,historiesDescription,timeField)) {
                return;
            }

            if (histories == null) {
                histories = new Histories();
                histories.setClient(client);
            }

            histories.setTitle(titleField.getText().trim());
            histories.setTime(LocalTime.parse(timeField.getText().trim()));
            histories.setDate(datePickerField.getValue());
            histories.setDescription(historiesDescription.getText().trim());

            histories = historiesService.saveHistories(histories);

            historieIdField.setText(String.valueOf(client.getId()));
            loadHistories();
            clearForm();
            updateSaveButtonLabel();

        } catch (Exception e) {

            log.error("Fehler beim Speichern des Histories", e);
        }
    }

    private void clearForm() {
        histories = null;
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


    //TODO Back Button implementieren

}
