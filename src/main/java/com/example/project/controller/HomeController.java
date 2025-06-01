package com.example.project.controller;

import com.example.project.help.NaviHelper;
import com.example.project.help.NaviRowHelper;
import com.example.project.help.ViewUrls;
import com.example.project.model.Appointment;
import com.example.project.model.Client;
import com.example.project.service.AppointmentService;
import com.example.project.service.ClientService;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class HomeController {

    private final ClientService clientService;
    private final AppointmentService appointmentService;
    private final NaviHelper naviHelper;
    private final NaviRowHelper naviRowHelper;

    private ObservableList<Client> allClients;
    private FilteredList<Client> filteredClients;

    @FXML
    private TextField searchField;

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
        institutionColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getInstitution())
        );
        statusColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getStatus())
        );
        dateColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getDate())
        );
        timeColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getTime())
        );

        clientIdColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));

        clientLastColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getLastname()));

        clientFirstColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getFirstname()));

        clientBirthdateColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getBirthdate()));

        clientGenderColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getGender()));

        clientNationalityColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getNationality()));

        clientRelationshipColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getRelationship()));

        appointmentTable.setItems(FXCollections
                .observableArrayList(appointmentService.getAllOpenAppointments()));

        setupClientSearch();

        naviRowHelper.rowNavigateToClient(clientTable);
        naviRowHelper.rowNavigateToAppointment(appointmentTable);
    }

    public void clientButton() throws Exception {
        Stage stage = (Stage) clientButton.getScene().getWindow();
        String url = ViewUrls.CLIENT_URL;
        naviHelper.navigateTo(stage, url);
    }

    private void setupClientSearch() {

        List<Client> clients = clientService.getAllClients();
        allClients = FXCollections.observableArrayList(clients);
        filteredClients = new FilteredList<>(allClients);

        if (searchField != null) {
            searchField.textProperty()
                    .addListener(
                            (obs, oldText, newText) ->
                                    filteredClients.setPredicate(client -> {
                                        if (newText == null || newText.trim().isEmpty()) {
                                            return true;
                                        }

                                        String search = newText.toLowerCase();

                                        return (client.getFirstname() != null && client.getFirstname().toLowerCase().contains(search)) ||
                                                (client.getLastname() != null && client.getLastname().toLowerCase().contains(search)) ||
                                                (client.getNationality() != null && client.getNationality().toLowerCase().contains(search)) ||
                                                (client.getGender() != null && client.getGender().toLowerCase().contains(search)) ||
                                                (client.getRelationship() != null && client.getRelationship().toLowerCase().contains(search)) ||
                                                (client.getId() != null && client.getId().toString().contains(search));
                                    }));

            searchField.setPromptText("Klienten suchen...");
        }

        clientTable.setItems(filteredClients);
    }

    public void refreshClientSearch() {
        List<Client> clients = clientService.getAllClients();
        allClients.clear();
        allClients.addAll(clients);
    }
}