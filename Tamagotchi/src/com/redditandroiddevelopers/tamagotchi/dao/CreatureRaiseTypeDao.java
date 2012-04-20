package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.List;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.CreatureRaiseType;

public class CreatureRaiseTypeDao {
    private CommonDatabase<CreatureRaiseType> db;
    private Mapper<CreatureRaiseType> rowMapper;

    public CreatureRaiseTypeDao(CommonDatabase<CreatureRaiseType> database,
            Mapper<CreatureRaiseType> mapper) {
        db = database;
        rowMapper = mapper;
    }

    public CreatureRaiseType create(CreatureRaiseType crt) {
        return db.insert(crt, CommonDatabase.RAISE_TYPE_TABLE_NAME);
    }

    public CreatureRaiseType update(CreatureRaiseType crt) {
        db.update(CommonDatabase.RAISE_TYPE_TABLE_NAME, crt, "CRT_ID = "
                + crt.id, null);
        return crt;
    }

    public void delete(CreatureRaiseType crt) {
        db.delete(CommonDatabase.RAISE_TYPE_TABLE_NAME, "CRT_ID = " + crt.id,
                null);
    }

    public List<CreatureRaiseType> getAll() {
        return db.queryList(rowMapper, CommonDatabase.RAISE_TYPE_TABLE_NAME,
                null, null, null, null, null, null);
    }

    public CreatureRaiseType findById(long id) {
        return db.queryUnique(rowMapper,
                CommonDatabase.RAISE_TYPE_TABLE_NAME, null, "CRT_ID = " + id,
                null, null, null, null);
    }
}
