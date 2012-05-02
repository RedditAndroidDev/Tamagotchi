package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.model.CreatureType;
import com.redditandroiddevelopers.tamagotchi.model.ExperienceAction;

public class ExperienceMapper extends Mapper<ExperienceAction> {

    @Override
    public ExperienceAction mapRow(Object obj) {
        if (obj instanceof Cursor) {
            Cursor c = (Cursor)obj;
            ExperienceAction exp = new ExperienceAction();
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
