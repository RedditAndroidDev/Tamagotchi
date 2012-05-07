package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.model.CreatureEvolution;
import com.redditandroiddevelopers.tamagotchi.model.CreatureType;

public class CreatureEvolutionMapper extends Mapper<CreatureEvolution> {

    public CreatureEvolution mapRow(Object obj) {
        if (obj instanceof Cursor) {
            Cursor c = (Cursor)obj;
            CreatureEvolution evo = new CreatureEvolution();
            evo.id = c.getLong(c.getColumnIndex("CE_ID"));
            CreatureType t = new CreatureType();
            t.id = c.getLong(c.getColumnIndex("CT_ID"));
            t.name = c.getString(c.getColumnIndex("CT_NAME"));
            evo.type = t;
            evo.name = c.getString(c.getColumnIndex("CE_NAME"));
            evo.maxHealth = c.getInt(c.getColumnIndex("CE_MAX_HEALTH"));
            evo.maxBowel = c.getInt(c.getColumnIndex("CE_MAX_BOWEL"));
            evo.maxDiscipline = c.getInt(c.getColumnIndex("CE_MAX_DISCIPLINE"));
            evo.maxHunger = c.getInt(c.getColumnIndex("CE_MAX_HUNGER"));
            evo.maxHappy = c.getInt(c.getColumnIndex("CE_MAX_HAPPY"));
            evo.currentXp = c.getInt(c.getColumnIndex("CE_CURRENT_XP"));
            return evo;
        }
        return null;
    }
}
