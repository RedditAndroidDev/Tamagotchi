
package com.redditandroiddevelopers.tamagotchi.model;

import java.util.Date;

/**
 * Model object for the CREATURE_INFO database table.
 * Objects are public with no getters/setters for performance
 * 
 * @author Jeffrey Selk
 */
public class Creature extends CommonModel {

    public String name;
    public CreatureType type;
    public Date birthDate;
    public Date deathDate;
    public CreatureState state;
    public boolean alive;
    public Gender gender;

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
        c.state = new CreatureState();
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
        c.state = new CreatureState();
        c.alive = true;
        c.gender = gender;
        return c;
    }

}
