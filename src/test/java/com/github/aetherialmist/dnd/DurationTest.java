package com.github.aetherialmist.dnd;

import com.github.aetherialmist.dnd.h2.SpellEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
class DurationTest {

    @Test
    void durationToString() {
        Duration duration = Duration.of(2, ChronoUnit.DAYS);
        log.info(duration.toString());
        log.info("{}", duration.getUnits());
    }

}
