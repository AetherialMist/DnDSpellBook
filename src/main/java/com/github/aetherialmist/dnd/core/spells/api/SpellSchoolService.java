package com.github.aetherialmist.dnd.core.spells.api;

import com.github.aetherialmist.dnd.core.SpellDataException;
import com.github.aetherialmist.dnd.core.spells.SpellSchool;

public interface SpellSchoolService {

    SpellSchool getById(int id) throws SpellDataException;
    void save(SpellSchool spellSchool) throws SpellDataException;

}
