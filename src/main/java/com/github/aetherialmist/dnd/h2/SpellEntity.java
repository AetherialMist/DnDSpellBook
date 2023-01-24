package com.github.aetherialmist.dnd.h2;

import com.github.aetherialmist.dnd.core.spells.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "SPELLS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpellEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    private int level;
    private boolean ritual;
    private boolean verbal;
    private boolean somatic;
    private boolean material;
    private String materialCost;
    private int schoolId;
    private int castingTimeId;
    private int durationId;
    private int rangeId;
    private String description;
    private String atHigherLevel;

    public static SpellEntity fromSpell(Spell spell) {
        return SpellEntity.builder()
            .name(spell.getName())
            .level(spell.getLevel())
            .ritual(spell.isRitual())
            .verbal(spell.isVerbal())
            .somatic(spell.isSomatic())
            .material(spell.isMaterial())
            .materialCost(spell.getMaterialCost())
            .schoolId(spell.getSpellSchoolId())
            .castingTimeId(spell.getSpellCastingTimeId())
            .durationId(spell.getSpellDurationId())
            .rangeId(spell.getSpellRangeId())
            .description(spell.getDescription())
            .atHigherLevel(spell.getAtHigherLevel())
            .build();
    }

    public Spell asSpell() {
        return Spell.builder()
            .name(name)
            .level(level)
            .ritual(ritual)
            .verbal(verbal)
            .somatic(somatic)
            .material(material)
            .materialCost(materialCost)
            .spellSchoolId(schoolId)
            .spellCastingTimeId(castingTimeId)
            .spellDurationId(durationId)
            .spellRangeId(rangeId)
            .description(description)
            .atHigherLevel(atHigherLevel)
            .build();
    }

}
