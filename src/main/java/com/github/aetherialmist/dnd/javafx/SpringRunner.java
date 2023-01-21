package com.github.aetherialmist.dnd.javafx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.github.aetherialmist.dnd.*")
@SpringBootApplication
public class SpringRunner {

    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class, args);
    }

}
