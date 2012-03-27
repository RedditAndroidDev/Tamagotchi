package your.redditandroiddevelopers.dao;

import your.redditandroiddevelopers.model.Creature;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CreatureDao {
	private DatabaseHelper databaseHelper;
	private SQLiteDatabase dbRead;

	public CreatureDao(Context context) {
		databaseHelper = new DatabaseHelper(context);
		dbRead = databaseHelper.getReadableDatabase();
	}
	
	public Creature create(Creature creature) {
		return null;
	}
	
	public Creature update(Creature creature) {
		return null;
	}
	
	public void delete(Creature creature) {
		
	}
}
