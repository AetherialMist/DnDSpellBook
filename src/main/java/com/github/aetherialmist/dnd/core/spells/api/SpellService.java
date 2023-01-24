package com.github.aetherialmist.dnd.core.spells.api;

import com.github.aetherialmist.dnd.core.SpellDataException;
import com.github.aetherialmist.dnd.core.spells.Spell;

public interface SpellService {

    Spell getById(int id) throws SpellDataException;
    void save(Spell spell) throws SpellDataException;

}
