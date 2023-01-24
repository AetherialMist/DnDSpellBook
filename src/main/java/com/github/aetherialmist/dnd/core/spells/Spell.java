package com.github.aetherialmist.dnd.core.spells;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
public class Spell implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int level;
    private boolean ritual;

    private boolean verbal;
    private boolean somatic;
    private boolean material;
    private String materialCost;

    private int spellSchoolId;
    private int spellCastingTimeId;
    private int spellDurationId;
    private int spellRangeId;

    private String description;
    private String atHigherLevel;
}
