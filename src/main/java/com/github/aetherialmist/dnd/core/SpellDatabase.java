package com.github.aetherialmist.dnd.core;

public interface SpellDatabase {

    /**
     * Saves a new Spell to the Spell Database.
     *
     * @param spell The spell to save.
     * @return A unique ID for the saved Spell
     */
    int saveSpell(Spell spell) throws SpellDataException;

    /**
     * Delete a Spell from the Spell Database
     *
     * @param uniqueId The unique ID of the Spell to delete.
     */
    void deleteSpell(int uniqueId) throws SpellDataException;

    /**
     * Update/Replace an existing Spell with new information.
     *
     * @param uniqueId The unique ID of the spell to update.
     * @param spell The new details for the given ID.
     */
    void updateSpell(int uniqueId, Spell spell) throws SpellDataException;

    /**
     * Return a Spell from the Spell Database
     *
     * @param uniqueId The unique ID of a Spell in the database.
     * @return The Spell stored under the given ID.
     */
    Spell getSpellById(int uniqueId) throws SpellDataException;

}
