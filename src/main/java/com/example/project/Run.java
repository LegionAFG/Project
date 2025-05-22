package com.example.project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Run extends Application {

    private ConfigurableApplicationContext springContext;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void init() {

        String[] args = getParameters().getRaw().toArray(new String[0]);
        springContext = new SpringApplicationBuilder(Run.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/home.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Parent root = loader.load();

        stage.setTitle("ClientPilot");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    @Override
    public void stop() {

        springContext.close();
        Platform.exit();
    }

}
