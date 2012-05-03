package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.List;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.CreatureAction;

public class CreatureActionDao {
    private CommonDatabase<CreatureAction> db;
    Mapper<CreatureAction> rowMapper;
    
    public CreatureActionDao(CommonDatabase<CreatureAction> database,
            Mapper<CreatureAction> mapper) {
        db = database;
        rowMapper = mapper;
    }
    
    public CreatureAction create(CreatureAction action) {
        return db.insert(action, CommonDatabase.ACTION_TABLE_NAME);
    }
    
    public CreatureAction update(CreatureAction action) {
        db.update(CommonDatabase.ACTION_TABLE_NAME, action, "CA_ID = "
                + action.id, null);
        return action;
    }
    
    public void delete(CreatureAction action) {
        db.delete(CommonDatabase.ACTION_TABLE_NAME, "CA_ID = " + action.id, null);
    }
    
    public List<CreatureAction> getAll() {
        return db.queryList(rowMapper, CommonDatabase.ACTION_TABLE_NAME, 
                null, null, null, null, null, null);
    }
    
    public CreatureAction findById(long id) {
        return db.queryUnique(rowMapper, CommonDatabase.ACTION_TABLE_NAME,
                null, "CA_ID = " + id, null, null, null, null);
    }
}
