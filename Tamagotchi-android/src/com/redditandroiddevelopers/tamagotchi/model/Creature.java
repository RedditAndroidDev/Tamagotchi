package com.redditandroiddevelopers.tamagotchi.model;

import java.util.Date;

import android.content.ContentValues;

/**
 * Model object for the CREATURE_INFO database table.
 * 
 * Objects are public with no getters/setters for performance
 * 
 * @author Jeffrey Selk
 * 
 */
public class Creature {

    public long id;
    public String name;
    public CreatureType type;
    public Date birthDate;
    public Date deathDate;
    public boolean alive;
    public Gender gender;

    /**
     * Used to return an array of mapped columns and their values
     * 
     * @return Mapped SQL Column with their value
     */
    public ContentValues buildContentValues() {
        ContentValues toReturn = new ContentValues();
        toReturn.put("CI_ID", id);
        toReturn.put("CI_NAME", name);
        toReturn.put("CT_ID", type.id);
        toReturn.put("CI_BIRTH_DATE", (birthDate != null) ? birthDate.getTime()
                : null);
        toReturn.put("CI_DEATH_DATE", (deathDate != null) ? deathDate.getTime()
                : null);
        toReturn.put("CI_ALIVE", alive);
        toReturn.put("CI_GENDER", gender.getDbValue());
        return toReturn;
    }

    /**
     * A convenience method for testing
     * 
     * @return A basic creature object
     */
    public static Creature createDefaultCreature() {
        CreatureType type = new CreatureType();
        type.id = 1;
        type.name = "Default";

        Creature c = new Creature();
        c.name = "Default Creature";
        c.type = type;
        c.birthDate = new Date();
        c.alive = true;
        c.gender = Gender.FEMALE;
        return c;
    }

    /**
     * Create A Creature Simple function to call when a person leaves the
     * creature-creation screen, and gets used to save the creature.
     * 
     * @author Hannes Vermeire
     * @return The creature.
     */
    public static Creature createCreature(String name, Gender gender,
            CreatureType type) {
        Creature c = new Creature();
        c.name = name;
        c.type = type;
        c.birthDate = new Date();
        c.gender = gender;
        c.alive = true;
        c.gender = gender;
        return c;
    }

}
