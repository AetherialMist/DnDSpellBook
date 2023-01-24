package com.github.aetherialmist.dnd.h2;

import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLogFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class H2EnvPostProcessor implements EnvironmentPostProcessor {

    private final Log log;

    public H2EnvPostProcessor(DeferredLogFactory logFactory) {
        log = logFactory.getLog(H2EnvPostProcessor.class);
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("Configuring H2.");
        Map<String, Object> props = new HashMap<>();

        props.put("spring.jpa.database-platform", "org.hibernate.dialect.H2Dialect");
        props.put("spring.jpa.database", "h2");
        props.put("spring.datasource.username", "sa");
        props.put("spring.datasource.driver-class-name", "org.h2.Driver");

        if (Boolean.TRUE.equals(environment.getProperty("dnd.h2.debug", Boolean.class))) {
            log.info("Debug mode enabled");
            configureForTesting(props);
        }

        String databasePath = environment.getProperty("dnd.h2.file-path");
        if (Boolean.TRUE.equals(environment.getProperty("dnd.h2.in-memory", Boolean.class))) {
            log.warn("in-memory database enabled. Database will start empty.");
            configureInMemory(databasePath, props);
        } else {
            props.put("spring.datasource.url", "jdbc:h2:file:." + databasePath);
        }

        for (Map.Entry<String, Object> entry : props.entrySet()) {
            log.info(entry.getKey() + "=" + entry.getValue());
        }

        MapPropertySource propSource = new MapPropertySource("h2-settings", props);
        environment.getPropertySources().addFirst(propSource);
    }

    private void configureForTesting(Map<String, Object> props) {
        props.put("spring.h2.console.enabled", true);
        props.put("spring.jpa.show-sql", true);
    }

    private void configureInMemory(String databasePath, Map<String, Object> props) {
        props.put("spring.jpa.generate-ddl", true);
        props.put("spring.jpa.hibernate.ddl-auto", "create");
        props.put("spring.datasource.url", "jdbc:h2:mem:~" + databasePath);
    }
}
