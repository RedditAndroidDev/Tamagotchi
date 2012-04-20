package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.List;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.Experience;

public class ExperienceDao {
    private CommonDatabase<Experience> db;
    private Mapper<Experience> rowMapper;
    
    public ExperienceDao(CommonDatabase<Experience> database,
            Mapper<Experience> mapper) {
        db = database;
        rowMapper = mapper;
    }
    
    public Experience create(Experience exp) {
        return db.insert(exp, CommonDatabase.EXPERIENCE_TABLE_NAME);
    }
    
    public Experience update(Experience exp) {
        db.update(CommonDatabase.EXPERIENCE_TABLE_NAME, 
                exp, 
                "E_ID = " + exp.id, 
                null);
        return exp;
    }
    
    public void delete(Experience exp) {
        db.delete(CommonDatabase.EXPERIENCE_TABLE_NAME, 
                "E_ID = " + exp.id, 
                null);
    }
    
    public List<Experience> getAll() {
        return db.queryList(rowMapper, CommonDatabase.EXPERIENCE_TABLE_NAME + " join "
                + CommonDatabase.TYPE_TABLE_NAME + " on ("
                + CommonDatabase.EXPERIENCE_TABLE_NAME + ".CT_ID = "
                + CommonDatabase.TYPE_TABLE_NAME + ".CT_ID )", null, null,
                null, null, null, null);
    }
    
    public Experience findExperienceById(long id) {
        return db.queryUnique(rowMapper, CommonDatabase.EXPERIENCE_TABLE_NAME, null, 
                "E_ID = " + id, null, null, null, null);
    }
}
