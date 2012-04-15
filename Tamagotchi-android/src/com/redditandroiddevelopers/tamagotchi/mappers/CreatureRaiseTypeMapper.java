package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.model.CreatureRaiseType;

public class CreatureRaiseTypeMapper {

    public static CreatureRaiseType mapRow(Cursor c) {
        CreatureRaiseType crt = new CreatureRaiseType();
        crt.id = c.getLong(c.getColumnIndex("CRT_ID"));
        crt.name = c.getString(c.getColumnIndex("CRT_NAME"));
        crt.multiplier = c.getDouble(c.getColumnIndex("CRT_MULTIPLIER"));
        return crt;
    }
}
