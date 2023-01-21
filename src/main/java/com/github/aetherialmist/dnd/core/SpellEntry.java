package com.github.aetherialmist.dnd.core;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpellEntry implements Spell {

    private int level;
    private MagicSchool school;
    private boolean ritual;
    private CastingTime castingTime;
    private TimeUnit castingTimeUnit;
    private DurationType duration;
    private int durationLength;
    private TimeUnit durationUnit;
    private String description;
    private int range;
    private RangeUnit rangeUnit;
    private Components[] components;
    private String materialCost;

}
