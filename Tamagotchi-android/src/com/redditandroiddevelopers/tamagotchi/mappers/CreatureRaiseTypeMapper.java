package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.model.CreatureRaiseType;

public class CreatureRaiseTypeMapper extends Mapper<CreatureRaiseType> {

    public CreatureRaiseType mapRow(Object obj) {
        if (obj instanceof Cursor) {
            Cursor c = (Cursor)obj;
            CreatureRaiseType crt = new CreatureRaiseType();
            crt.id = c.getLong(c.getColumnIndex("CRT_ID"));
            crt.name = c.getString(c.getColumnIndex("CRT_NAME"));
            crt.multiplier = c.getDouble(c.getColumnIndex("CRT_MULTIPLIER"));
            return crt;
        }
        return null;
    }
}
