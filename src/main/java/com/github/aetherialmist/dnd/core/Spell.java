package com.github.aetherialmist.dnd.core;

public interface Spell {

    enum CastingTime {
        ONE_ACTION, ONE_BONUS_ACTION, ONE_REACTION, TIME
    }

    enum TimeUnit {
        SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR, DECADE
    }

    enum RangeUnit {
        SELF, FEET, YARD, MILE
    }

    enum DurationType {
        INSTANTANEOUS, CONCENTRATION
    }

    enum MagicSchool {
        ABJURATION, ENCHANTMENT, CONJURATION, EVOCATION, DIVINATION, ILLUSION, NECROMANCY, TRANSMUTATION
    }

    enum Component {
        VERBAL, SOMATIC, MATERIAL
    }

    String getName();

    int getLevel();
    MagicSchool getSchool();

    CastingTime getCastingTime();
    TimeUnit getCastingTimeUnit();

    DurationType getDuration();
    int getDurationLength();
    TimeUnit getDurationUnit();

    String getDescription();
    /**
     * Use '0' for SELF
     */
    int getRange();
    RangeUnit getRangeUnit();

    Component[] getComponents();

    boolean isRitual();

}
