package your.redditandroiddevelopers.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String INFO_TABLE_NAME = "CREATURE_INFO";
	private static final String STATE_TABLE_NAME = "CREATURE_STATE";
	private static final String EVOLUTION_TABLE_NAME = "CREATURE_EVOLUTION";
	private static final String TYPE_TABLE_NAME = "CREATURE_TYPE";
	private static final String RAISE_TYPE_TABLE_NAME = "CREATURE_RAISE_TYPE";
	private static final String MEDICINE_TABLE_NAME = "MEDICINE";
	private static final String SICKNESS_TABLE_NAME = "SICKNESS";
	
	private static final String DB_NAME = "RAD_CREATURES.db";
	private static final int DB_VERSION = 2;
	private static final String CREATE_INFO = "" +
			"CREATE TABLE " + INFO_TABLE_NAME + " (" +
			"CI_ID INTEGER PRIMARY KEY, " +
			"CT_ID INTEGER, " +
			"CE_ID INTEGER, " +
			"CI_NAME VARCHAR2(50), " +
			"CI_BIRTH_DATE INTEGER, " +
			"CI_DEATH_DATE INTEGER, " +
			"CI_ALIVE BOOLEAN, " +
			"CI_GENDER BOOLEAN, " +
			"FOREIGN KEY(CT_ID) REFERENCES creature_type(CT_ID), " +
			"FOREIGN KEY(CE_ID) REFERENCES creature_evolution(CE_ID) " +
			");";
	private static final String CREATE_STATE = "" +
			"CREATE TABLE " + STATE_TABLE_NAME + " (" +
			"CS_ID INTEGER PRIMARY KEY, " +
			"CI_ID INTEGER, " +
			"CRT_ID INTEGER, " +
			"S_ID INTEGER, " +
			"CS_HEALTH INTEGER, " +
			"CS_BOWEL INTEGER, " +
			"CS_DISCIPLINE INTEGER, " +
			"CS_HUNGER INTEGER, " +
			"CS_HAPPY INTEGER, " +
			"CS_SICK BOOLEAN, " +
			"FOREIGN KEY(CI_ID) REFERENCES creature_info(CI_ID), " +
			"FOREIGN KEY(CRT_ID) REFERENCES creature_raise_type(CRT_ID), " +
			"FOREIGN KEY(S_ID) REFERENCES sickness(S_ID) " +
			");";
	private static final String CREATE_EVOLUTION = "" +
			"CREATE TABLE " + EVOLUTION_TABLE_NAME + " (" +
			"CE_ID INTEGER PRIMARY KEY, " +
			"CT_ID INTEGER, " +
			"CE_NAME VARCHAR2(100), " +
			"CE_MAX_HEALTH INTEGER, " +
			"CE_MAX_BOWEL INTEGER, " +
			"CE_MAX_DISCIPLINE INTEGER, " +
			"CE_MAX_HUNGER INTEGER, " +
			"CE_MAX_HAPPY INTEGER, " +
			"FOREIGN KEY(CT_ID) REFERENCES creature_type(CT_ID) " +
			");";
	private static final String CREATE_CREATURE_TYPE = "" +
			"CREATE TABLE " + TYPE_TABLE_NAME + " (" +
			"CT_ID INTEGER PRIMARY KEY, " +
			"CT_NAME VARCHAR2(100) " +
			");";
	private static final String CREATE_CREATURE_RAISE_STATE = "" +
			"CREATE TABLE " + RAISE_TYPE_TABLE_NAME + " (" +
			"CRT_ID INTEGER PRIMARY KEY, " +
			"CRT_NAME TEXT, " +
			"CRT_MULTIPLIER REAL " +
			");";
	private static final String CREATE_MEDICINE = "" +
			"CREATE TABLE " + MEDICINE_TABLE_NAME + " (" +
			"M_ID INTEGER PRIMARY KEY, " +
			"M_NAME TEXT " +
			");";
	private static final String CREATE_SICKNESS = "" +
			"CREATE TABLE " + SICKNESS_TABLE_NAME + " (" +
			"S_ID INTEGER PRIMARY KEY, " +
			"M_ID INTEGER, " +
			"S_NAME TEXT, " +
			"FOREIGN KEY (M_ID) REFERENCES medicine(M_ID) " +
			");";

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
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
		// turn on foreign keys
		db.execSQL("PRAGMA foreign_keys=ON;");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
