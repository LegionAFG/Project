package com.example.project;

import com.example.project.help.NaviHelper;
import com.example.project.help.ViewUrls;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Run extends Application {

    private ConfigurableApplicationContext springContext;
    private NaviHelper naviButtonHelper;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void init() {

        String[] args = getParameters().getRaw().toArray(new String[0]);
        springContext = new SpringApplicationBuilder(Run.class)
                .web(WebApplicationType.NONE)
                .run(args);

        naviButtonHelper = springContext.getBean(NaviHelper.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String url = ViewUrls.HOME_URL;
        naviButtonHelper.navigateTo(stage, url);
    }

    @Override
    public void stop() {

        springContext.close();
        Platform.exit();
    }

}
