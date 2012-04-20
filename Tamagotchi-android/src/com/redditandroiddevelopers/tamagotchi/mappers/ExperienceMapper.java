package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.model.CreatureType;
import com.redditandroiddevelopers.tamagotchi.model.Experience;

public class ExperienceMapper extends Mapper<Experience> {

    @Override
    public Experience mapRow(Object obj) {
        if (obj instanceof Cursor) {
            Cursor c = (Cursor)obj;
            Experience exp = new Experience();
            exp.id = c.getLong(c.getColumnIndex("E_ID"));
            CreatureType type = new CreatureType();
            
            type.id = c.getLong(c.getColumnIndex("CT_ID"));
            type.name = c.getString(c.getColumnIndex("CT_NAME"));
            exp.type = type;
            exp.minXp = c.getInt(c.getColumnIndex("E_MIN_XP"));
            exp.maxXp = c.getInt(c.getColumnIndex("E_MAX_XP"));
            
            return exp;
        }
        return null;
    }
}
