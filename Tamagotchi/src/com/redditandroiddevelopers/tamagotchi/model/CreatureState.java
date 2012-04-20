package com.redditandroiddevelopers.tamagotchi.model;


/**
 * Model object for the CREATURE_STATE database table.
 * 
 * Objects are public with no getters/setters for performance
 * 
 * @author Jeffrey Selk
 * 
 */
public class CreatureState extends CommonModel {

    public Creature creature;
    public CreatureRaiseType raiseType;
    public Sickness sickness;
    public int health;
    public int bowel;
    public int discipline;
    public int hunger;
    public int happy;
    public boolean sick;
    public int experience;
}
