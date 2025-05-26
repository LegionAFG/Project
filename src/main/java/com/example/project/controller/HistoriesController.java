package com.example.project.controller;

import com.example.project.help.NaviHelper;
import com.example.project.help.ViewUrls;
import com.example.project.model.Client;
import com.example.project.model.Histories;
import com.example.project.service.HistoriesService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HistoriesController {

    private final NaviHelper naviButtonHelper;
    private final HistoriesService historiesService;

    Histories histories;
    Client client;

    @FXML
    private Button homeButton;
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

    //TODO Delete Button implementieren

    //TODO Save Button implementieren

    //TODO Back Button implementieren

}
