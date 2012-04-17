package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.model.CreatureState;

public class CreatureStateMapper {

    public static CreatureState mapRow(Cursor c) {
        CreatureState state = new CreatureState();
        state.id = c.getLong(c.getColumnIndex("CS_ID"));
        state.creature = CreatureMapper.mapRow(c);
        state.raiseType = CreatureRaiseTypeMapper.mapRow(c);
        state.sickness = SicknessMapper.mapRow(c);
        state.health = c.getInt(c.getColumnIndex("CS_HEALTH"));
        state.bowel = c.getInt(c.getColumnIndex("CS_BOWEL"));
        state.discipline = c.getInt(c.getColumnIndex("CS_DISCIPLINE"));
        state.hunger = c.getInt(c.getColumnIndex("CS_HUNGER"));
        state.happy = c.getInt(c.getColumnIndex("CS_HAPPY"));
        state.sick = (c.getInt(c.getColumnIndex("CS_SICK")) == 1) ? true
                : false;
        state.experience = c.getInt(c.getColumnIndex("CS_EXPERIENCE"));
        return state;
    }
}
