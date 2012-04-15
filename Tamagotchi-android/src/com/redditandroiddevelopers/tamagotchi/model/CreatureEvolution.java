package com.redditandroiddevelopers.tamagotchi.model;

import android.content.ContentValues;

/**
 * Model object for the CREATURE_EVOLUTION database table.
 * 
 * Objects are public with no getters/setters for performance
 * 
 * @author Jeffrey Selk
 * 
 */
public class CreatureEvolution {

    public long id;
    public CreatureType type;
    public String name;
    public int maxHealth;
    public int maxBowel;
    public int maxDiscipline;
    public int maxHunger;
    public int maxHappy;

    public ContentValues buildContentValues() {
        ContentValues toReturn = new ContentValues();
        toReturn.put("CE_ID", id);
        toReturn.put("CT_ID", type.id);
        toReturn.put("CE_NAME", name);
        toReturn.put("CE_MAX_HEALTH", maxHealth);
        toReturn.put("CE_MAX_BOWEL", maxBowel);
        toReturn.put("CE_MAX_DISCIPLINE", maxDiscipline);
        toReturn.put("CE_MAX_HUNGER", maxHunger);
        toReturn.put("CE_MAX_HAPPY", maxHappy);
        return toReturn;
    }
}
