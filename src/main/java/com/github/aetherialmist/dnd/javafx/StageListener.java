package com.github.aetherialmist.dnd.javafx;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {

    private final FxProperties properties;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        event.getStage().setTitle(properties.getTitle());
    }

}
