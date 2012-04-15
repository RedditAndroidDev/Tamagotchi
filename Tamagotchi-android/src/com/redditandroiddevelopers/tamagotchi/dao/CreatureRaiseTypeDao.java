package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.mappers.CreatureRaiseTypeMapper;
import com.redditandroiddevelopers.tamagotchi.model.CreatureRaiseType;

public class CreatureRaiseTypeDao {
    private CreatureDatabase db;

    public CreatureRaiseTypeDao(Context context) {
        db = new CreatureDatabase(context);
    }

    public CreatureRaiseType create(CreatureRaiseType crt) {
        ContentValues vals = crt.buildContentValues();
        if (crt.id <= 0) {
            vals.putNull("CRT_ID");
        }
        long retId = db.insert(CreatureDatabase.RAISE_TYPE_TABLE_NAME, vals);
        crt.id = retId;
        return crt;
    }

    public CreatureRaiseType update(CreatureRaiseType crt) {
        db.update(CreatureDatabase.RAISE_TYPE_TABLE_NAME,
                crt.buildContentValues(), "CRT_ID = " + crt.id, null);
        return crt;
    }

    public void delete(CreatureRaiseType crt) {
        db.delete(CreatureDatabase.RAISE_TYPE_TABLE_NAME, "CRT_ID = " + crt.id,
                null);
    }

    public List<CreatureRaiseType> getAll() {
        Cursor c = db.query(CreatureDatabase.RAISE_TYPE_TABLE_NAME, null, null,
                null, null, null, null);
        List<CreatureRaiseType> toReturn = new ArrayList<CreatureRaiseType>(
                c.getCount());
        while (!c.isAfterLast()) {
            toReturn.add(CreatureRaiseTypeMapper.mapRow(c));
            c.moveToNext();
        }
        c.close();
        return toReturn;
    }

    public CreatureRaiseType findById(long id) {
        Cursor c = db.query(CreatureDatabase.RAISE_TYPE_TABLE_NAME, null,
                "CRT_ID = " + id, null, null, null, null);
        CreatureRaiseType toReturn = CreatureRaiseTypeMapper.mapRow(c);
        c.close();
        return toReturn;
    }
}
