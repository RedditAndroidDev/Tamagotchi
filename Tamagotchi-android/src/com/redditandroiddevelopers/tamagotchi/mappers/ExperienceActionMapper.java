package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.model.CreatureAction;
import com.redditandroiddevelopers.tamagotchi.model.CreatureType;
import com.redditandroiddevelopers.tamagotchi.model.ExperienceAction;

public class ExperienceActionMapper extends Mapper<ExperienceAction> {

    @Override
    public ExperienceAction mapRow(Object obj) {
        if (obj instanceof Cursor) {
            Cursor c = (Cursor)obj;
            ExperienceAction exp = new ExperienceAction();
            exp.id = c.getLong(c.getColumnIndex("EA_ID"));
            
            CreatureType type = new CreatureType();
            type.id = c.getLong(c.getColumnIndex("CT_ID"));
            type.name = c.getString(c.getColumnIndex("CT_NAME"));
            exp.type = type;
            
            CreatureAction action = new CreatureAction();
            action.id = c.getLong(c.getColumnIndex("CA_ID"));
            action.name = c.getString(c.getColumnIndex("CA_NAME"));
            exp.action = action;
            
            exp.modifier = c.getDouble(c.getColumnIndex("EA_MODIFIER"));
            
            return exp;
        }
        return null;
    }
}
