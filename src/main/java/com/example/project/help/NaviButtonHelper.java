package com.example.project.help;

import com.example.project.controller.AppointmentController;
import com.example.project.controller.ClientController;
import com.example.project.model.Appointment;
import com.example.project.model.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class NaviButtonHelper {

    private final ConfigurableApplicationContext springContext;

    public void fxmlLoader(Stage stage, String fxmlUrl) throws IOException {
        fxmlLoader(stage, fxmlUrl, null);
    }

    public void fxmlLoader(Stage stage, String fxmlUrl, Object model) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlUrl));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        Object controller = loader.getController();

        if (model != null) {
            if (controller instanceof ClientController && model instanceof Client) {
                ((ClientController) controller).setClient((Client) model);
                log.debug("Client-Modell an ClientController übergeben: {}", ((Client) model).getId());
            } else if (controller instanceof AppointmentController && model instanceof Appointment) {
               ((AppointmentController) controller).setAppointment((Appointment) model);
               log.debug("Appointment-Modell an AppointmentController übergeben: {}", ((Appointment) model).getId());
            }
            // TODO: Hier weitere Controller-Typen und ihre Modelle hinzufügen, HistoriesController
            // else if (controller instanceof HistoriesController && model instanceof HistoryEntry) {
            // ((HistoriesController) controller).setHistory((HistoryEntry) model);
            // }
            else if (controller != null) {
                log.warn("Modell vom Typ {} konnte nicht an Controller {} übergeben werden. Kein passender Setter implementiert/erkannt.",
                        model.getClass().getSimpleName(), controller.getClass().getSimpleName());
            }
        }

        stage.setTitle("ClientPilot");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void rowNavigateToClient(TableView<Client> tableView) {
        tableView.setRowFactory(tv -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Client selectedClient = row.getItem();
                    Stage currentStage = (Stage) tableView.getScene().getWindow();

                    try {
                        this.navigateToClient(currentStage, selectedClient);
                    } catch (Exception e) {
                        if (selectedClient != null) {
                            log.error("Fehler beim Navigieren zum Client mit ID {}: {}", selectedClient.getId(), e.getMessage(), e);
                        } else {
                            log.error("Fehler beim Navigieren zum Client: Ausgewähltes Item war null.", e);
                        }
                    }
                }
            });
            return row;
        });
    }

    public void rowNavigateToAppointment(TableView<Appointment> tableView) {
        tableView.setRowFactory(tv -> {
            TableRow<Appointment> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Appointment selectedAppointment = row.getItem();
                    Stage currentStage = (Stage) tableView.getScene().getWindow();

                    try {
                        this.navigateToAppointment(currentStage, selectedAppointment);
                    } catch (Exception e) {
                        if (selectedAppointment != null) {
                            log.error("Fehler beim Navigieren zum Termin mit ID {}: {}", selectedAppointment.getId(), e.getMessage(), e);
                        } else {
                            log.error("Fehler beim Navigieren zum Termin: Ausgewähltes Item war null.", e);
                        }
                    }
                }
            });
            return row;
        });
    }

    public void navigateTo(Stage stage, String fxmlUrl) throws IOException {
        fxmlLoader(stage, fxmlUrl);
    }

    public void navigateToClient(Stage stage, Client client) throws IOException {

        fxmlLoader(stage, ViewUrls.CLIENT_URL, client);
    }

    public void navigateToAppointment(Stage stage, Appointment appointment) throws IOException {

        fxmlLoader(stage, ViewUrls.APPOINTMENT_URL, appointment);
    }
}