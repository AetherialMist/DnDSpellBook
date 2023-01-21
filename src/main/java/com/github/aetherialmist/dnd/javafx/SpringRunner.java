package com.github.aetherialmist.dnd.javafx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRunner {

    public static void main(String[] args) {
        Application.launch(JavaFxApplication.class, args);
//        MainApplication.main(args);
    }

}
