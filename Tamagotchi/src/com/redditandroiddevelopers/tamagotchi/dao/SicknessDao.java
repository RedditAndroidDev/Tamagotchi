package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.List;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.Sickness;

public class SicknessDao {
    private CommonDatabase<Sickness> db;
    private Mapper<Sickness> rowMapper;

    public SicknessDao(CommonDatabase<Sickness> database,
            Mapper<Sickness> mapper) {
        db = database;
        rowMapper = mapper;
    }

    /*
     * Sickness
     */
    public Sickness create(Sickness sickness) {
        return db.insert(sickness, CommonDatabase.SICKNESS_TABLE_NAME);
    }

    public Sickness update(Sickness sickness) {
        db.update(CommonDatabase.SICKNESS_TABLE_NAME,
                sickness, "S_ID = " + sickness.id, null);
        return sickness;
    }

    public void delete(Sickness sickness) {
        db.delete(CommonDatabase.SICKNESS_TABLE_NAME,
                "S_ID = " + sickness.id, null);
    }

    public List<Sickness> getAll() {
        return db.queryList(rowMapper, CommonDatabase.SICKNESS_TABLE_NAME + " join "
                + CommonDatabase.MEDICINE_TABLE_NAME + " on ("
                + CommonDatabase.SICKNESS_TABLE_NAME + ".M_ID = "
                + CommonDatabase.MEDICINE_TABLE_NAME + ".M_ID )", null, null,
                null, null, null, null);
    }

    public Sickness findById(long id) {
        return db.queryUnique(rowMapper, CommonDatabase.SICKNESS_TABLE_NAME + " join "
                + CommonDatabase.MEDICINE_TABLE_NAME + " on ("
                + CommonDatabase.SICKNESS_TABLE_NAME + ".M_ID = "
                + CommonDatabase.MEDICINE_TABLE_NAME + ".M_ID )", null,
                "S_ID = " + id, null, null, null, null);
    }
}
