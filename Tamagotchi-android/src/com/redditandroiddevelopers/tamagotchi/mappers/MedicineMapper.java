package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.model.Medicine;

public class MedicineMapper {

    public static Medicine mapRow(Cursor c) {
        Medicine m = new Medicine();
        m.id = c.getLong(c.getColumnIndex("M_ID"));
        m.name = c.getString(c.getColumnIndex("M_NAME"));
        return m;
    }
}
