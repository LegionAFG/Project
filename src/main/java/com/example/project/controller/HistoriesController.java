package com.example.project.controller;

import com.example.project.help.NaviButtonHelper;
import com.example.project.help.ViewUrls;
import com.example.project.model.Histories;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HistoriesController {

    private final NaviButtonHelper naviButtonHelper;

    Histories histories;

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

    //TODO TableView Appointment implementieren
    //TODO TableColumn Appointment implementieren


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

    public void setHistories(Histories histories){
        this.histories = histories;

        historieIdField.setText(histories.getClient().getId().toString());
        timeField.setText(String.valueOf(histories.getTime()));
        titleField.setText(histories.getTitle());
    }

    //TODO Delete Button implementieren

    //TODO Save Button implementieren

    //TODO Back Button implementieren

}
