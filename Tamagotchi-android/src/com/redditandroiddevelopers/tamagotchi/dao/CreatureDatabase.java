package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.CommonModel;
import com.redditandroiddevelopers.tamagotchi.util.ContentValueUtils;

public class CreatureDatabase<T extends CommonModel> implements
        CommonDatabase<T> {

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

    private final DatabaseHelper databaseHelper;

    public CreatureDatabase(Context context) {
        databaseHelper = new DatabaseHelper(context);
        // TODO: remove this call to getWritableDatabase before production
        databaseHelper.getWritableDatabase();
        //seedData();
    }

    public T queryUnique(Mapper<T> rowMapper, String table,
            String[] projectionIn, String selection, String[] selectionArgs,
            String groupBy, String having, String sortOrder) {
        Cursor c = query(table, projectionIn, selection, selectionArgs,
                groupBy, having, sortOrder);
        T toReturn = rowMapper.mapRow(c);
        c.close();
        return toReturn;
    }

    public List<T> queryList(Mapper<T> rowMapper, String table,
            String[] projectionIn, String selection, String[] selectionArgs,
            String groupBy, String having, String sortOrder) {
        Cursor c = query(table, projectionIn, selection, selectionArgs,
                groupBy, having, sortOrder);
        List<T> toReturn = new ArrayList<T>(c.getCount());
        while (!c.isAfterLast()) {
            toReturn.add(rowMapper.mapRow(c));
            c.moveToNext();
        }
        c.close();
        return toReturn;
    }

    public int getResultCount(String table, String[] projectionIn,
            String selection, String[] selectionArgs, String groupBy,
            String having, String sortOrder) {
        return query(table, projectionIn, selection, selectionArgs, groupBy,
                having, sortOrder).getCount();
    }

    private Cursor query(String table, String[] projectionIn, String selection,
            String[] selectionArgs, String groupBy, String having,
            String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(table);
        Cursor c = builder.query(databaseHelper.getReadableDatabase(),
                projectionIn, selection, selectionArgs, groupBy, having,
                sortOrder);
        if (c == null) {
            return null;
        } else if (!c.moveToFirst()) {
            c.close();
            return null;
        }
        return c;
    }

    public T insert(T toCreate, String table) {
        long res = databaseHelper.getWritableDatabase().insert(table, null,
                ContentValueUtils.buildContentValues(toCreate));
        toCreate.id = res;
        return toCreate;
    }

    public void update(String table, T toUpdate, String whereClause,
            String[] whereArgs) {
        databaseHelper.getWritableDatabase().update(table,
                ContentValueUtils.buildContentValues(toUpdate), whereClause, whereArgs);
    }

    public void delete(String table, String whereClause, String[] whereArgs) {
        databaseHelper.getWritableDatabase().delete(table, whereClause,
                whereArgs);
    }

    /**
     * For testing purposes during development!
     */
    public void deleteDatabase() {
        // TODO: debug guard
        databaseHelper.deleteDatabase();
    }

    /**
     * This is a basic method for use by developers during development.
     */
    public void seedData() {
        // TODO: This needs to be set up with the actual data for the
        // game
        // seed data for constants, i.e. medicine, sickness, etc.
        // TODO: debug guard
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues vals;
        
        for (int i = 0; i < 5; i++) {
            vals = new ContentValues();
            vals.put("M_ID", i);
            vals.put("M_NAME", "MEDICINE" + i);
            db.insert(MEDICINE_TABLE_NAME, null, vals);
        }

        for (int i = 0; i < 5; i++) {
            vals = new ContentValues();
            vals.put("S_ID", i);
            vals.put("M_ID", i);
            vals.put("S_NAME", "SICKNESS" + i);
            db.insert(SICKNESS_TABLE_NAME, null, vals);
        }

        vals = new ContentValues();
        vals.put("CRT_ID", 1);
        vals.put("CRT_NAME", "HEALTHY");
        vals.put("CRT_MULTIPLIER", "1.00");
        db.insert(RAISE_TYPE_TABLE_NAME, null, vals);

        vals = new ContentValues();
        vals.put("CT_ID", 1);
        vals.put("CT_NAME", "DEFAULT");
        db.insert(TYPE_TABLE_NAME, null, vals);

        vals = new ContentValues();
        vals.put("CE_ID", 1);
        vals.put("CT_ID", 1);
        vals.put("CE_NAME", "Name");
        vals.put("CE_MAX_HEALTH", 100);
        vals.put("CE_MAX_BOWEL", 100);
        vals.put("CE_MAX_DISCIPLINE", 100);
        vals.put("CE_MAX_HUNGER", 100);
        vals.put("CE_MAX_HAPPY", 100);
        vals.put("CE_CURRENT_XP", (int)(Math.random()*((500 + 20000) + 1)));
        db.insert(EVOLUTION_TABLE_NAME, null, vals);
        
        vals = new ContentValues();
        vals.put("CA_ID", 1);
        vals.put("CA_NAME", "Action1");
        db.insert(ACTION_TABLE_NAME, null, vals);
        
        vals = new ContentValues();
        vals.put("CD_ID", 1);
        vals.put("CD_NAME", "Debuff1");
        db.insert(DEBUFF_TABLE_NAME, null, vals);
        
        vals = new ContentValues();
        vals.put("EA_ID", 1);
        vals.put("CT_ID", 1);
        vals.put("CA_ID", 1);
        vals.put("EA_MODIFIER", 1.5);
        db.insert(EXPERIENCE_ACTION_TABLE_NAME, null, vals);
        
        vals = new ContentValues();
        vals.put("ED_ID", 1);
        vals.put("CT_ID", 1);
        vals.put("CD_ID", 1);
        vals.put("ED_MODIFIER", 0.8);
        db.insert(EXPERIENCE_DEBUFF_TABLE_NAME, null, vals);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String FOREIGN_KEYS_ON = "PRAGMA foreign_keys=ON;";
        private static final String DB_NAME = "RAD_CREATURES.db";
        private static final int DB_VERSION = 2;

        private static final String CREATE_INFO = "" + "CREATE TABLE "
                + INFO_TABLE_NAME + " (" + "CI_ID INTEGER PRIMARY KEY, "
                + "CT_ID INTEGER, " + "CE_ID INTEGER, "
                + "CI_NAME VARCHAR2(50), " + "CI_BIRTH_DATE INTEGER, "
                + "CI_DEATH_DATE INTEGER, " + "CI_ALIVE BOOLEAN, "
                + "CI_GENDER BOOLEAN, "
                + "FOREIGN KEY(CT_ID) REFERENCES " +  TYPE_TABLE_NAME + "(CT_ID), "
                + "FOREIGN KEY(CE_ID) REFERENCES " + EVOLUTION_TABLE_NAME + "(CE_ID) "
                + ");";
        private static final String CREATE_STATE = ""
                + "CREATE TABLE "
                + STATE_TABLE_NAME
                + " ("
                + "CS_ID INTEGER PRIMARY KEY, "
                + "CI_ID INTEGER, "
                + "CRT_ID INTEGER, "
                + "S_ID INTEGER, "
                + "CS_HEALTH INTEGER, "
                + "CS_BOWEL INTEGER, "
                + "CS_DISCIPLINE INTEGER, "
                + "CS_HUNGER INTEGER, "
                + "CS_HAPPY INTEGER, "
                + "CS_SICK BOOLEAN, "
                + "CS_EXPERIENCE INTEGER, "
                + "FOREIGN KEY(CI_ID) REFERENCES " + INFO_TABLE_NAME + "(CI_ID), "
                + "FOREIGN KEY(CRT_ID) REFERENCES " + RAISE_TYPE_TABLE_NAME + "(CRT_ID), "
                + "FOREIGN KEY(S_ID) REFERENCES " + SICKNESS_TABLE_NAME + "(S_ID) " + ");";
        private static final String CREATE_EVOLUTION = "" + "CREATE TABLE "
                + EVOLUTION_TABLE_NAME + " (" + "CE_ID INTEGER PRIMARY KEY, "
                + "CT_ID INTEGER, " + "CE_NAME VARCHAR2(100), "
                + "CE_MAX_HEALTH INTEGER, " + "CE_MAX_BOWEL INTEGER, "
                + "CE_MAX_DISCIPLINE INTEGER, " + "CE_MAX_HUNGER INTEGER, "
                + "CE_MAX_HAPPY INTEGER, " + "CE_MAX_EXPERIENCE INTEGER, "
                + "FOREIGN KEY(CT_ID) REFERENCES " + TYPE_TABLE_NAME + "(CT_ID) " + ");";
        private static final String CREATE_CREATURE_TYPE = "" + "CREATE TABLE "
                + TYPE_TABLE_NAME + " (" + "CT_ID INTEGER PRIMARY KEY, "
                + "CT_NAME VARCHAR2(100) " + ");";
        private static final String CREATE_CREATURE_RAISE_STATE = ""
                + "CREATE TABLE " + RAISE_TYPE_TABLE_NAME + " ("
                + "CRT_ID INTEGER PRIMARY KEY, " + "CRT_NAME TEXT, "
                + "CRT_MULTIPLIER REAL " + ");";
        private static final String CREATE_MEDICINE = "" + "CREATE TABLE "
                + MEDICINE_TABLE_NAME + " (" + "M_ID INTEGER PRIMARY KEY, "
                + "M_NAME TEXT " + ");";
        private static final String CREATE_SICKNESS = "" + "CREATE TABLE "
                + SICKNESS_TABLE_NAME + " (" + "S_ID INTEGER PRIMARY KEY, "
                + "M_ID INTEGER, " + "S_NAME TEXT, "
                + "FOREIGN KEY (M_ID) REFERENCES " + MEDICINE_TABLE_NAME + "(M_ID) " + ");";
        private static final String CREATE_CREATURE_ACTION = "" + "CREATE TABLE "
                + ACTION_TABLE_NAME + " (" + "CA_ID INTEGER PRIMARY KEY, "
                + "CA_NAME TEXT " + ");";
        private static final String CREATE_CREATURE_DEBUFF = "" + "CREATE TABLE "
                + DEBUFF_TABLE_NAME + " (" + "CD_ID INTEGER PRIMARY KEY, "
                + "CD_NAME TEXT " + ");";
        private static final String CREATE_EXPERIENCE_ACTION = "" + "CREATE TABLE "
                + EXPERIENCE_ACTION_TABLE_NAME + " (" + "EA_ID INTEGER PRIMARY KEY, "
                + "CT_ID INTEGER, " + "CA_ID INTEGER, " + "EA_MODIFIER REAL, "
                + "FOREIGN KEY (CT_ID) REFERENCES " + TYPE_TABLE_NAME + "(CT_ID), "
                + "FOREIGN KEY (CA_ID) REFERENCES " + ACTION_TABLE_NAME + "(CA_ID) "+ ");";

        private static final String CREATE_EXPERIENCE_DEBUFF = "" + "CREATE TABLE "
                + EXPERIENCE_DEBUFF_TABLE_NAME + " (" + "ED_ID INTEGER PRIMARY KEY, "
                + "CT_ID INTEGER, " + "CD_ID INTEGER, " + "ED_MODIFIER REAL, "
                + "FOREIGN KEY (CT_ID) REFERENCES " + TYPE_TABLE_NAME + "(CT_ID), "
                + "FOREIGN KEY (CD_ID) REFERENCES " + DEBUFF_TABLE_NAME + "(CD_ID) "+ ");";

        private Context mContext;

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            mContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_MEDICINE);
            db.execSQL(CREATE_SICKNESS);
            db.execSQL(CREATE_CREATURE_RAISE_STATE);
            db.execSQL(CREATE_CREATURE_TYPE);
            db.execSQL(CREATE_INFO);
            db.execSQL(CREATE_STATE);
            db.execSQL(CREATE_EVOLUTION);
            db.execSQL(CREATE_CREATURE_ACTION);
            db.execSQL(CREATE_CREATURE_DEBUFF);
            db.execSQL(CREATE_EXPERIENCE_ACTION);
            db.execSQL(CREATE_EXPERIENCE_DEBUFF);
            // turn on foreign keys
            db.execSQL(FOREIGN_KEYS_ON);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        @Override
        public void onOpen(SQLiteDatabase db) {
            db.execSQL(FOREIGN_KEYS_ON);
        }
        
        public void deleteDatabase() {
            mContext.deleteDatabase(DB_NAME);
        }

    }
}
