package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.redditandroiddevelopers.tamagotchi.mappers.ExperienceMapper;
import com.redditandroiddevelopers.tamagotchi.model.Experience;

public class ExperienceDao {
    private CreatureDatabase db;
    
    public ExperienceDao(Context context) {
        db = new CreatureDatabase(context);
    }
    
    public Experience create(Experience exp) {
        ContentValues vals = exp.buildContentValues();
        if(exp.id <= 0) {
            vals.putNull("E_ID");
        }
        long retId = db.insert(CreatureDatabase.EXPERIENCE_TABLE_NAME, vals);
        exp.id = retId;
        return exp;
    }
    
    public Experience update(Experience exp) {
        db.update(CreatureDatabase.EXPERIENCE_TABLE_NAME, 
                exp.buildContentValues(), 
                "E_ID = " + exp.id, 
                null);
        return exp;
    }
    
    public void delete(Experience exp) {
        db.delete(CreatureDatabase.EXPERIENCE_TABLE_NAME, 
                "E_ID = " + exp.id, 
                null);
    }
    
    public List<Experience> getAll() {
        Cursor c = db.query(CreatureDatabase.EXPERIENCE_TABLE_NAME + " join "
                + CreatureDatabase.TYPE_TABLE_NAME + " on ("
                + CreatureDatabase.EXPERIENCE_TABLE_NAME + ".CT_ID = "
                + CreatureDatabase.TYPE_TABLE_NAME + ".CT_ID )", null, null,
                null, null, null, null);
        List<Experience> toReturn = new ArrayList<Experience>(c.getCount());
        while (!c.isAfterLast()) {
            toReturn.add(ExperienceMapper.mapRow(c));
            c.moveToNext();
        }
        c.close();
        return toReturn;
    }
    
    public Experience findExperienceById(long id) {
        Cursor c = db.query(CreatureDatabase.EXPERIENCE_TABLE_NAME, null, 
                "E_ID = " + id, null, null, null, null);
        Experience exp = ExperienceMapper.mapRow(c);
        c.close();
        return exp;
    }
}
