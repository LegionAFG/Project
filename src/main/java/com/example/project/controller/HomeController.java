package com.example.project.controller;

import com.example.project.help.NaviButtonHelper;
import com.example.project.help.ViewUrls;
import com.example.project.model.Appointment;
import com.example.project.model.Client;
import com.example.project.service.AppointmentService;
import com.example.project.service.ClientService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class HomeController {

    private final ClientService clientService;
    private final AppointmentService appointmentService;
    private final NaviButtonHelper naviButtonHelper;
    Client client;

    @FXML
    private TableView<Appointment> appointmentTable;
    @FXML
    private TableColumn<Appointment, String> institutionColumn;
    @FXML
    private TableColumn<Appointment, String> statusColumn;
    @FXML
    private TableColumn<Appointment, LocalDate> dateColumn;
    @FXML
    private TableColumn<Appointment, LocalTime> timeColumn;
    @FXML
    private TableColumn<Appointment, Long> idColumn;
    @FXML
    private TableColumn<Appointment, String> lastColumn;
    @FXML
    private TableColumn<Appointment, String> firstColumn;

    @FXML
    private TableView<Client> clientTable;
    @FXML
    private TableColumn<Client, Long> clientIdColumn;
    @FXML
    private TableColumn<Client, String> clientLastColumn;
    @FXML
    private TableColumn<Client, String> clientFirstColumn;
    @FXML
    private TableColumn<Client, LocalDate> clientBirthdateColumn;
    @FXML
    private TableColumn<Client, String> clientGenderColumn;
    @FXML
    private TableColumn<Client, String> clientNationalityColumn;
    @FXML
    private TableColumn<Client, String> clientRelationshipColumn;

    @FXML
    private Button clientButton;

    public void initialize() {

        idColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getClient().getId())
        );
        firstColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getClient().getFirstname())
        );
        lastColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getClient().getLastname())
        );

        appointmentTable.setItems(FXCollections.observableArrayList(appointmentService.getAllAppointmentsWithClient()));

        clientTable.setItems(FXCollections.observableArrayList(clientService.getAllClients()));

        naviButtonHelper.rowNavigateToClient(clientTable);
        naviButtonHelper.rowNavigateToAppointment(appointmentTable);

    }

    public void clientButton() throws Exception {
        Stage stage = (Stage) clientButton.getScene().getWindow();
        String url = ViewUrls.CLIENT_URL;
        naviButtonHelper.navigateTo(stage, url);
    }

    //TODO Search FIELD implementieren
}
