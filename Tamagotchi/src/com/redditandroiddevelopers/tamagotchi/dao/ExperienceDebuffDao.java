package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.List;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.ExperienceDebuff;

public class ExperienceDebuffDao {
    private CommonDatabase<ExperienceDebuff> db;
    private Mapper<ExperienceDebuff> rowMapper;
    
    public ExperienceDebuffDao(CommonDatabase<ExperienceDebuff> database,
            Mapper<ExperienceDebuff> mapper) {
        db = database;
        rowMapper = mapper;
    }
    
    public ExperienceDebuff create(ExperienceDebuff exp) {
        return db.insert(exp, CommonDatabase.EXPERIENCE_DEBUFF_TABLE_NAME);
    }
    
    public ExperienceDebuff update(ExperienceDebuff exp) {
        db.update(CommonDatabase.EXPERIENCE_DEBUFF_TABLE_NAME, 
                exp, 
                "ED_ID = " + exp.id, 
                null);
        return exp;
    }
    
    public void delete(ExperienceDebuff exp) {
        db.delete(CommonDatabase.EXPERIENCE_DEBUFF_TABLE_NAME, 
                "ED_ID = " + exp.id, 
                null);
    }
    
    public List<ExperienceDebuff> getAll() {
        return db.queryList(rowMapper, CommonDatabase.EXPERIENCE_DEBUFF_TABLE_NAME + " join "
                + CommonDatabase.TYPE_TABLE_NAME + " on ("
                + CommonDatabase.EXPERIENCE_DEBUFF_TABLE_NAME + ".CT_ID = "
                + CommonDatabase.TYPE_TABLE_NAME + ".CT_ID )" + " join "
                + CommonDatabase.DEBUFF_TABLE_NAME + " on ("
                + CommonDatabase.EXPERIENCE_DEBUFF_TABLE_NAME + ".CA_ID = "
                + CommonDatabase.DEBUFF_TABLE_NAME+ ".CA_ID )", null, null,
                null, null, null, null);
    }
    
    public ExperienceDebuff findExperienceById(long id) {
        return db.queryUnique(rowMapper, CommonDatabase.EXPERIENCE_DEBUFF_TABLE_NAME, null, 
                "ED_ID = " + id, null, null, null, null);
    }


}
