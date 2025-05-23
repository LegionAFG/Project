package com.example.project.help;

import com.example.project.controller.ClientController;
import com.example.project.model.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class NaviButtonHelper {

    private final ConfigurableApplicationContext springContext;

    public void fxmlLoader(Stage stage, String url) throws IOException {

        fxmlLoader(stage, url, null);
    }

    public void fxmlLoader(Stage stage, String url, Object model) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        Object controller = loader.getController();
        if (model != null && controller instanceof ClientController) {

            ((ClientController) controller).setClient((Client) model);
        }
       //TODO Appointment Controller
        // TODO HistoriesController

        stage.setTitle("ClientPilot");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public void navigateTo(Stage stage, String url) throws IOException {
        fxmlLoader(stage, url);
    }

    public void navigateToClient(Stage stage, Client client) throws IOException {
        fxmlLoader(stage, ViewUrls.CLIENT_URL, client);
    }
}

