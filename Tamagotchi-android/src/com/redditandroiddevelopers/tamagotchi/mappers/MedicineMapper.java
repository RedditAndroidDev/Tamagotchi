package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.model.Medicine;

public class MedicineMapper extends Mapper<Medicine> {

    public Medicine mapRow(Object obj) {
        if (obj instanceof Cursor) {
            Cursor c = (Cursor)obj;
            Medicine m = new Medicine();
            m.id = c.getLong(c.getColumnIndex("M_ID"));
            m.name = c.getString(c.getColumnIndex("M_NAME"));
            return m;
        }
        return null;
    }
}
