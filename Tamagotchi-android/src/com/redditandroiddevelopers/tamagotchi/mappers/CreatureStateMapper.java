package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.model.CreatureState;

public class CreatureStateMapper extends Mapper<CreatureState> {
    CreatureMapper creatureMapper = new CreatureMapper();
    CreatureRaiseTypeMapper creatureRaiseTypeMapper = new CreatureRaiseTypeMapper();
    SicknessMapper sicknessMapper = new SicknessMapper();

    public CreatureState mapRow(Object obj) {
        if (obj instanceof Cursor) {
            Cursor c = (Cursor)obj;
            CreatureState state = new CreatureState();
            state.id = c.getLong(c.getColumnIndex("CS_ID"));
            state.creature = creatureMapper.mapRow(c);
            state.raiseType = creatureRaiseTypeMapper.mapRow(c);
            state.sickness = sicknessMapper.mapRow(c);
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
        return null;
    }
}
