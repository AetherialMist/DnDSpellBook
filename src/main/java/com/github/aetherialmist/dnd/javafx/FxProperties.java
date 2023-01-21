package com.github.aetherialmist.dnd.javafx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("dnd")
public class FxProperties {

    private String title;
}
