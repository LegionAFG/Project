package com.example.project.help;

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

    public void navigateTo(Stage stage, String url) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        stage.setTitle("ClientPilot");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

    }
}
