package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.model.Sickness;

public class SicknessMapper extends Mapper<Sickness> {
    MedicineMapper medicineMapper = new MedicineMapper();

    public Sickness mapRow(Object obj) {
        if (obj instanceof Cursor) {
            Cursor c = (Cursor)obj;
            Sickness s = new Sickness();
            s.id = c.getLong(c.getColumnIndex("S_ID"));
            s.medicine = medicineMapper.mapRow(c);
            s.name = c.getString(c.getColumnIndex("S_NAME"));
            return s;
        }
        return null;
    }
}
