package com.github.aetherialmist.dnd.javafx;

import com.github.aetherialmist.dnd.h2.Database;
import com.github.aetherialmist.dnd.h2.WebServer;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@Slf4j
public class JavaFxApplication extends Application {

//    public static void main(String[] args) {
//        log.info("Starting Application");
//        launch();
//    }

    private ConfigurableApplicationContext context;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Database database;
    private WebServer webServer;

    @Override
    public void init() {
        ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
            applicationContext.registerBean(Application.class, () -> JavaFxApplication.this);
            applicationContext.registerBean(Parameters.class, this::getParameters);
            applicationContext.registerBean(HostServices.class, this::getHostServices);
        };

        this.context = new SpringApplicationBuilder()
            .sources(SpringRunner.class)
            .initializers(initializer)
            .run(getParameters().getRaw().toArray(new String[0]));

        database = new Database();
        webServer = new WebServer();
        webServer.start();
    }

    @Override
    public void start(Stage stage) {
        try {

            Platform.setImplicitExit(true);
            stage.setTitle("D&D App");

            BorderPane root = new BorderPane();
            Scene mainScene = new Scene(root, WIDTH, HEIGHT);
            stage.setScene(mainScene);
            this.context.publishEvent(new StageReadyEvent(stage));
            stage.show();
        } catch (Exception e) {
            log.error("Failed to start application.", e);
            shutdownDatabase();
        }
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        shutdownDatabase();
        webServer.stop();
        Platform.exit();
    }

    private void shutdownDatabase() {
        log.info("Shutting down database");
        if (database != null) {
            database.close();
        }
    }

}
