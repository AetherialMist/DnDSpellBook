package com.github.aetherialmist.dnd.h2;

import com.github.aetherialmist.dnd.core.HigherLevel;
import com.github.aetherialmist.dnd.core.MaterialCost;
import com.github.aetherialmist.dnd.core.Spell;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SPELLS")
@Getter
public class SpellEntity implements HigherLevel, MaterialCost, Spell, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    private int level;
    private MagicSchool school;
    private boolean ritual;

    private CastingTime castingTime;
    private TimeUnit castingTimeUnit;

    private DurationType duration;
    private int durationLength;
    private TimeUnit durationUnit;

    private int range;
    private RangeUnit rangeUnit;

    private String description;
    private String atHigherLevel;

    private String materialCost;

    private boolean verbal;
    private boolean somatic;
    private boolean material;

    public Component[] getComponents() {
        List<Component> types = new ArrayList<>();
        if (verbal) {
            types.add(Component.VERBAL);
        }
        if (somatic) {
            types.add(Component.SOMATIC);
        }
        if (material) {
            types.add(Component.MATERIAL);
        }
        return types.toArray(new Component[0]);
    }
}
