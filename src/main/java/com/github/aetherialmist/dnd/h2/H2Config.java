package com.github.aetherialmist.dnd.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@Configuration
@EnableConfigurationProperties(H2Properties.class)
@EnableJpaRepositories(basePackages = "com.github.aetherialmist.dnd.h2")
@EntityScan("com.github.aetherialmist.dnd.h2")
public class H2Config {

}
