package com.github.aetherialmist.dnd.core.spells.api;

import com.github.aetherialmist.dnd.core.SpellDataException;
import com.github.aetherialmist.dnd.core.spells.SpellCastingTime;

public interface SpellCastingTimeService {

    SpellCastingTime getById(int id) throws SpellDataException;
    void save(SpellCastingTime spellCastingTime) throws SpellDataException;

}
