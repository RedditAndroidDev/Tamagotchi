package your.redditandroiddevelopers.dao;

import java.util.ArrayList;
import java.util.List;

import your.redditandroiddevelopers.mappers.CreatureStateMapper;
import your.redditandroiddevelopers.model.CreatureState;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class CreatureStateDao {
	private CreatureDatabase db;
	private static final String SELECT_JOIN = CreatureDatabase.STATE_TABLE_NAME + " join "
			+ CreatureDatabase.RAISE_TYPE_TABLE_NAME + " on ( "
			+ CreatureDatabase.STATE_TABLE_NAME + ".CRT_ID = "
			+ CreatureDatabase.RAISE_TYPE_TABLE_NAME + ".CRT_ID ) join "
			+ CreatureDatabase.INFO_TABLE_NAME + " on ( "
			+ CreatureDatabase.STATE_TABLE_NAME + ".CI_ID = "
			+ CreatureDatabase.INFO_TABLE_NAME + ".CI_ID ) join "
			+ CreatureDatabase.SICKNESS_TABLE_NAME + " on ( "
			+ CreatureDatabase.STATE_TABLE_NAME + ".S_ID = "
			+ CreatureDatabase.SICKNESS_TABLE_NAME + ".S_ID ) join "
			+ CreatureDatabase.TYPE_TABLE_NAME + " on ( "
			+ CreatureDatabase.INFO_TABLE_NAME + ".CT_ID = "
			+ CreatureDatabase.TYPE_TABLE_NAME + ".CT_ID ) join "
			+ CreatureDatabase.MEDICINE_TABLE_NAME + " on ( "
			+ CreatureDatabase.SICKNESS_TABLE_NAME + ".M_ID = "
			+ CreatureDatabase.MEDICINE_TABLE_NAME + ".M_ID )";

	public CreatureStateDao(Context context) {
		db = new CreatureDatabase(context);
	}

	public CreatureState create(CreatureState state) {
		ContentValues vals = state.buildContentValues();
		if (state.id <= 0) {
			vals.putNull("CS_ID");
		}
		long retId = db.insert(CreatureDatabase.STATE_TABLE_NAME, vals);
		state.id = retId;
		return state;
	}

	public CreatureState update(CreatureState state) {
		db.update(CreatureDatabase.STATE_TABLE_NAME,
				state.buildContentValues(), "CS_ID = " + state.id, null);
		return state;
	}

	public void delete(CreatureState state) {
		db.delete(CreatureDatabase.STATE_TABLE_NAME, "CS_ID = " + state.id,
				null);
	}

	public List<CreatureState> getAll() {
		Cursor c = db.query(SELECT_JOIN, null, null,
				null, null, null, null);
		List<CreatureState> toReturn = new ArrayList<CreatureState>(
				c.getCount());
		while (!c.isAfterLast()) {
			toReturn.add(CreatureStateMapper.mapRow(c));
			c.moveToNext();
		}
		return toReturn;
	}

	public CreatureState findById(long id) {
		Cursor c = db.query(SELECT_JOIN, null, "CS_ID = "
				+ id, null, null, null, null);
		CreatureState toReturn = CreatureStateMapper.mapRow(c);
		c.close();
		return toReturn;
	}
}
