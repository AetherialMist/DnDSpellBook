package com.github.aetherialmist.dnd.core.spells.api;

import com.github.aetherialmist.dnd.core.SpellDataException;
import com.github.aetherialmist.dnd.core.spells.SpellRange;

public interface SpellRangeService {

    SpellRange getById(int id) throws SpellDataException;
    void save(SpellRange spellRange) throws SpellDataException;

}
