package com.example.project.help;

import com.example.project.controller.AppointmentController;
import com.example.project.controller.ClientController;
import com.example.project.controller.HistoriesController;
import com.example.project.model.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class NaviHelper {

    private final ConfigurableApplicationContext springContext;

    public void fxmlLoader(Stage stage, String fxmlUrl, Object model) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlUrl));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        Object controller = loader.getController();

        if (model != null && controller != null) {

            if (controller instanceof AppointmentController && model instanceof Client) {
                ((AppointmentController) controller).setClient((Client) model);
                log.debug("Client-Modell an AppointmentController übergeben: {}", ((Client) model).getId());
            }

            else if (controller instanceof HistoriesController && model instanceof Client) {
                ((HistoriesController) controller).setClient((Client) model);
                log.debug("Client-Modell an HistoriesController übergeben: {}", ((Client) model).getId());
            }

            else if (controller instanceof ClientController && model instanceof Client) {
                ((ClientController) controller).setClient((Client) model);
                log.debug("Client-Modell an ClientController übergeben: {}", ((Client) model).getId());
            }
            else {
                log.warn("Modell vom Typ {} konnte nicht an Controller {} übergeben werden. Kein passender Setter implementiert/erkannt.",
                        model.getClass().getSimpleName(), controller.getClass().getSimpleName());
            }
        }

        stage.setTitle("ClientPilot");
        stage.setScene(new Scene(root, 800, 600));
        stage.setResizable(false);
        stage.show();
    }

    public void fxmlLoader(Stage stage, String fxmlUrl) throws IOException {
        fxmlLoader(stage, fxmlUrl, null);
    }

    public void navigateTo(Stage stage, String fxmlUrl) throws IOException {
        fxmlLoader(stage, fxmlUrl);
    }

    public void navigateToClient(Stage stage, Client client) throws IOException {
        fxmlLoader(stage, ViewUrls.CLIENT_URL, client);
    }

    public void navigateToHistoriesButton(Stage stage, Client client) throws IOException {
        fxmlLoader(stage, ViewUrls.HISTORIES_URL, client);
    }

    public void navigateToAppointmentButton(Stage stage, Client client) throws IOException {
        fxmlLoader(stage, ViewUrls.APPOINTMENT_URL, client);
    }

}