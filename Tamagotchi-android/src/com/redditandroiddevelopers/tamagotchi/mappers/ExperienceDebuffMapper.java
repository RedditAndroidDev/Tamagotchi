package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.model.CreatureDebuff;
import com.redditandroiddevelopers.tamagotchi.model.CreatureType;
import com.redditandroiddevelopers.tamagotchi.model.ExperienceDebuff;

public class ExperienceDebuffMapper extends Mapper<ExperienceDebuff> {
    @Override
    public ExperienceDebuff mapRow(Object obj) {
        if (obj instanceof Cursor) {
            Cursor c = (Cursor)obj;
            ExperienceDebuff exp = new ExperienceDebuff();
            exp.id = c.getLong(c.getColumnIndex("ED_ID"));
            
            CreatureType type = new CreatureType();
            type.id = c.getLong(c.getColumnIndex("CT_ID"));
            type.name = c.getString(c.getColumnIndex("CT_NAME"));
            exp.type = type;
            
            CreatureDebuff debuff = new CreatureDebuff();
            debuff.id = c.getLong(c.getColumnIndex("CD_ID"));
            debuff.name = c.getString(c.getColumnIndex("CD_NAME"));
            exp.debuff= debuff;
            
            exp.modifier = c.getDouble(c.getColumnIndex("ED_MODIFIER"));
            
            return exp;
        }
        return null;
    }

}
