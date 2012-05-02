package com.redditandroiddevelopers.tamagotchi.model;


/**
 * Model object for the CREATURE_EVOLUTION database table.
 * 
 * Objects are public with no getters/setters for performance
 * 
 * @author Jeffrey Selk
 * 
 */
public class CreatureEvolution extends CommonModel {

    public CreatureType type;
    public String name;
    public int maxHealth;
    public int maxBowel;
    public int maxDiscipline;
    public int maxHunger;
    public int maxHappy;
    public int currentXp;
}

