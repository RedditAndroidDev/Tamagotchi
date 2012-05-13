package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.List;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.CreatureDebuff;


public class CreatureDebuffDao {
    private CommonDatabase<CreatureDebuff> db;
    Mapper<CreatureDebuff> rowMapper;
    
    public CreatureDebuffDao(CommonDatabase<CreatureDebuff> database,
            Mapper<CreatureDebuff> mapper) {
        db = database;
        rowMapper = mapper;
    }
    
    public CreatureDebuff create(CreatureDebuff action) {
        return db.insert(action, CommonDatabase.DEBUFF_TABLE_NAME);
    }
    
    public CreatureDebuff update(CreatureDebuff action) {
        db.update(CommonDatabase.DEBUFF_TABLE_NAME, action, "CD_ID = "
                + action.id, null);
        return action;
    }
    
    public void delete(CreatureDebuff action) {
        db.delete(CommonDatabase.DEBUFF_TABLE_NAME, "CD_ID = " + action.id, null);
    }
    
    public List<CreatureDebuff> getAll() {
        return db.queryList(rowMapper, CommonDatabase.DEBUFF_TABLE_NAME, 
                null, null, null, null, null, null);
    }
    
    public CreatureDebuff findById(long id) {
        return db.queryUnique(rowMapper, CommonDatabase.DEBUFF_TABLE_NAME,
                null, "CD_ID = " + id, null, null, null, null);
    }
}
