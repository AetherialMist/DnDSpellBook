package com.github.aetherialmist.dnd.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
public class JavaFxApplication extends Application {

    private static final List<Consumer<Application>> initializers = new ArrayList<>();
    private static final List<Consumer<Stage>> stagePostProcessors = new ArrayList<>();
    private static final List<Consumer<Application>> closers = new ArrayList<>();

    public static void addInitializer(Consumer<Application> consumer) {
        initializers.add(consumer);
    }

    public static void addStagePostProcessor(Consumer<Stage> consumer) {
        stagePostProcessors.add(consumer);
    }

    public static void addCloser(Consumer<Application> consumer) {
        closers.add(consumer);
    }

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    @Override
    public void init() {
        consumeEvent(initializers, this);
    }

    @Override
    public void start(Stage stage) {
        try {
            Platform.setImplicitExit(true);
            stage.setTitle("D&D App");

            BorderPane root = new BorderPane();
            Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
            stage.setScene(mainScene);

            consumeEvent(stagePostProcessors, stage);

            stage.show();
        } catch (Exception e) {
            log.error("Failed to start application.", e);
        }
    }

    @Override
    public void stop() {
        consumeEvent(closers, this);
        Platform.exit();
    }

    private <T> void consumeEvent(List<Consumer<T>> consumers, T item) {
        for (Consumer<T> consumer : consumers) {
            try {
                consumer.accept(item);
            } catch (Exception e) {
                log.warn("Consumer failed to process event.", e);
            }
        }
    }

}
