package com.github.aetherialmist.dnd.h2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("dnd.h2")
public class H2Properties {

    private String filePath = "./data/spells";
    private boolean inMemory = false;
    private boolean debug = false;
}
