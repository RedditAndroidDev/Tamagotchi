package your.redditandroiddevelopers.mappers;

import java.util.Date;

import your.redditandroiddevelopers.model.Creature;
import your.redditandroiddevelopers.model.CreatureType;
import your.redditandroiddevelopers.model.Gender;
import android.database.Cursor;

/**
 * The row mapper for Creature objects (CREATURE_INFO database table).
 * 
 * @author Jeffrey Selk
 *
 */
public class CreatureMapper {

	/**
	 * Map a Cursor object from the DB to a Creature object
	 * @param cursor Result from the DB query
	 * @return The row-mapped Creature object
	 */
	public static Creature mapRow(Cursor cursor) {
		// assume only one row here
		Creature c = new Creature();
		c.id = cursor.getLong(cursor.getColumnIndex("CI_ID"));
		c.name = cursor.getString(cursor.getColumnIndex("CI_NAME"));
		CreatureType type = new CreatureType();
		type.id = cursor.getInt(cursor.getColumnIndex("CT_ID"));
		type.name = cursor.getString(cursor.getColumnIndex("CT_NAME"));
		c.type = type;
		c.birthDate = new Date(cursor.getLong(cursor
				.getColumnIndex("CI_BIRTH_DATE")));
		try {
			c.deathDate = new Date(cursor.getLong(cursor
					.getColumnIndex("CI_DEATH_DATE")));
		} catch (Exception e) {
			c.deathDate = null;
		}
		c.alive = (cursor.getInt(cursor.getColumnIndex("CI_ALIVE")) == 0) ? false
				: true;
		c.gender = Gender.fromDbValue(cursor.getString(cursor
				.getColumnIndex("CI_GENDER")));
		return c;
	}

}
