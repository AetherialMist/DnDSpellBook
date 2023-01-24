package com.github.aetherialmist.dnd.core.spells.api;

import com.github.aetherialmist.dnd.core.SpellDataException;
import com.github.aetherialmist.dnd.core.spells.SpellDuration;

public interface SpellDurationService {

    SpellDuration getById(int id) throws SpellDataException;
    void save(SpellDuration spellDuration) throws SpellDataException;

}
