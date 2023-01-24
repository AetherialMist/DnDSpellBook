package com.github.aetherialmist.dnd.spring;

import com.github.aetherialmist.dnd.core.spells.service.PersistentMap;
import com.github.aetherialmist.dnd.javafx.JavaFxApplication;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Consumer;

@Slf4j
@ComponentScan("com.github.aetherialmist.dnd.*")
@SpringBootApplication
public class SpringRunner {

    public static void main(String[] args) {
        DndSpringContext application = new DndSpringContext();
        application.registerAllEvents();

        testing();

        startFxApplication(args);
    }

    private static void startFxApplication(String[] args) {
        Application.launch(JavaFxApplication.class, args);
    }

    private static void testing() {
        Consumer<Application> consumer = application -> {
            try (PersistentMap<Integer, String> testing = new PersistentMap<>("./data/mapTest.yaml")) {
//            testing.put(4, "Testing1");
//            testing.put(1, "Testing2");
//            testing.put(6, "Testing3");
//            testing.save();
                testing.load();
                log.info(testing.toString());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        JavaFxApplication.addInitializer(consumer);
    }

    private static class DndSpringContext {

        ConfigurableApplicationContext context;

        public void registerAllEvents() {
            registerInitializer();
            registerStagePostProcessor();
            registerClosingEvent();
        }

        public void registerInitializer() {
            Consumer<Application> consumer = application -> {
                ApplicationContextInitializer<GenericApplicationContext> initializer = applicationContext -> {
                    applicationContext.registerBean(Application.class, () -> application);
                    applicationContext.registerBean(Application.Parameters.class, application::getParameters);
                    applicationContext.registerBean(HostServices.class, application::getHostServices);
                };

                this.context = new SpringApplicationBuilder()
                    .sources(SpringRunner.class)
                    .initializers(initializer)
                    .run(application.getParameters().getRaw().toArray(new String[0]));
            };

            JavaFxApplication.addInitializer(consumer);
        }

        public void registerStagePostProcessor() {
            Consumer<Stage> consumer = stage -> context.publishEvent(new StageReadyEvent(stage));
            JavaFxApplication.addStagePostProcessor(consumer);
        }

        public void registerClosingEvent() {
            Consumer<Application> consumer = application -> context.close();
            JavaFxApplication.addCloser(consumer);
        }
    }

}
