package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.List;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.CommonModel;

public interface CommonDatabase<T extends CommonModel> {
    public static final String INFO_TABLE_NAME = "CREATURE_INFO";
    public static final String STATE_TABLE_NAME = "CREATURE_STATE";
    public static final String EVOLUTION_TABLE_NAME = "CREATURE_EVOLUTION";
    public static final String TYPE_TABLE_NAME = "CREATURE_TYPE";
    public static final String RAISE_TYPE_TABLE_NAME = "CREATURE_RAISE_TYPE";
    public static final String ACTION_TABLE_NAME = "CREATURE_ACTION";
    public static final String DEBUFF_TABLE_NAME = "CREATURE_DEBUFF";
    public static final String MEDICINE_TABLE_NAME = "MEDICINE";
    public static final String SICKNESS_TABLE_NAME = "SICKNESS";
    public static final String EXPERIENCE_ACTION_TABLE_NAME = "EXPERIENCE_ACTION";
    public static final String EXPERIENCE_DEBUFF_TABLE_NAME = "EXPERIENCE_DEBUFF";
    
    public T queryUnique(Mapper<T> rowMapper, String table,
            String[] projectionIn, String selection, String[] selectionArgs,
            String groupBy, String having, String sortOrder);

    public List<T> queryList(Mapper<T> rowMapper, String table,
            String[] projectionIn, String selection, String[] selectionArgs,
            String groupBy, String having, String sortOrder);

    public T insert(T toCreate, String table);

    public void update(String table, T toUpdate, String whereClause,
            String[] whereArgs);

    public void delete(String table, String whereClause, String[] whereArgs);
    
    public int getResultCount(String table, String[] projectionIn, String selection,
            String[] selectionArgs, String groupBy, String having,
            String sortOrder);
}
