package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.List;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.ExperienceAction;

public class ExperienceActionDao {
    private CommonDatabase<ExperienceAction> db;
    private Mapper<ExperienceAction> rowMapper;
    
    public ExperienceActionDao(CommonDatabase<ExperienceAction> database,
            Mapper<ExperienceAction> mapper) {
        db = database;
        rowMapper = mapper;
    }
    
    public ExperienceAction create(ExperienceAction exp) {
        return db.insert(exp, CommonDatabase.EXPERIENCE_ACTION_TABLE_NAME);
    }
    
    public ExperienceAction update(ExperienceAction exp) {
        db.update(CommonDatabase.EXPERIENCE_ACTION_TABLE_NAME, 
                exp, 
                "EA_ID = " + exp.id, 
                null);
        return exp;
    }
    
    public void delete(ExperienceAction exp) {
        db.delete(CommonDatabase.EXPERIENCE_ACTION_TABLE_NAME, 
                "EA_ID = " + exp.id, 
                null);
    }
    
    public List<ExperienceAction> getAll() {
        return db.queryList(rowMapper, CommonDatabase.EXPERIENCE_ACTION_TABLE_NAME + " join "
                + CommonDatabase.TYPE_TABLE_NAME + " on ("
                + CommonDatabase.EXPERIENCE_ACTION_TABLE_NAME + ".CT_ID = "
                + CommonDatabase.TYPE_TABLE_NAME + ".CT_ID )" + " join "
                + CommonDatabase.ACTION_TABLE_NAME + " on ("
                + CommonDatabase.EXPERIENCE_ACTION_TABLE_NAME + ".CA_ID = "
                + CommonDatabase.ACTION_TABLE_NAME + ".CA_ID )", null, null,
                null, null, null, null);
    }
    
    public ExperienceAction findExperienceById(long id) {
        return db.queryUnique(rowMapper, CommonDatabase.EXPERIENCE_ACTION_TABLE_NAME, null, 
                "EA_ID = " + id, null, null, null, null);
    }
}
