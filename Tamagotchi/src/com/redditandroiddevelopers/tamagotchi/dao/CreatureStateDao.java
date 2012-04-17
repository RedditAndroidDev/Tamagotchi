package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.List;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.CreatureState;

public class CreatureStateDao {
    private CommonDatabase<CreatureState> db;
    private Mapper<CreatureState> rowMapper;
    private static final String SELECT_JOIN = CommonDatabase.STATE_TABLE_NAME
            + " join " + CommonDatabase.RAISE_TYPE_TABLE_NAME + " on ( "
            + CommonDatabase.STATE_TABLE_NAME + ".CRT_ID = "
            + CommonDatabase.RAISE_TYPE_TABLE_NAME + ".CRT_ID ) join "
            + CommonDatabase.INFO_TABLE_NAME + " on ( "
            + CommonDatabase.STATE_TABLE_NAME + ".CI_ID = "
            + CommonDatabase.INFO_TABLE_NAME + ".CI_ID ) join "
            + CommonDatabase.SICKNESS_TABLE_NAME + " on ( "
            + CommonDatabase.STATE_TABLE_NAME + ".S_ID = "
            + CommonDatabase.SICKNESS_TABLE_NAME + ".S_ID ) join "
            + CommonDatabase.TYPE_TABLE_NAME + " on ( "
            + CommonDatabase.INFO_TABLE_NAME + ".CT_ID = "
            + CommonDatabase.TYPE_TABLE_NAME + ".CT_ID ) join "
            + CommonDatabase.MEDICINE_TABLE_NAME + " on ( "
            + CommonDatabase.SICKNESS_TABLE_NAME + ".M_ID = "
            + CommonDatabase.MEDICINE_TABLE_NAME + ".M_ID )";

    public CreatureStateDao(CommonDatabase<CreatureState> database,
            Mapper<CreatureState> mapper) {
        db = database;
        rowMapper = mapper;
    }

    public CreatureState create(CreatureState state) {
        return db.insert(state, CommonDatabase.STATE_TABLE_NAME);
    }

    public CreatureState update(CreatureState state) {
        db.update(CommonDatabase.STATE_TABLE_NAME, state, "CS_ID = "
                + state.id, null);
        return state;
    }

    public void delete(CreatureState state) {
        db.delete(CommonDatabase.STATE_TABLE_NAME, "CS_ID = " + state.id,
                null);
    }

    public List<CreatureState> getAll() {
        return db.queryList(rowMapper, SELECT_JOIN, null, null, null, null,
                null, null);
    }

    public CreatureState findById(long id) {
        return db.queryUnique(rowMapper, SELECT_JOIN, null, "CS_ID = " + id,
                null, null, null, null);
    }
}
