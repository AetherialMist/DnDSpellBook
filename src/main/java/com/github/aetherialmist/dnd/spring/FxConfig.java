package com.github.aetherialmist.dnd.spring;

import com.github.aetherialmist.dnd.spring.FxProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FxProperties.class)
public class FxConfig {

}
