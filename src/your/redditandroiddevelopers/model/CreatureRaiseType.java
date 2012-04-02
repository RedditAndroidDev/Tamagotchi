package your.redditandroiddevelopers.model;

import android.content.ContentValues;

/**
 * Model object for the CREATURE_RAISE_TYPE database table.
 * 
 * Objects are public with no getter/setters for performance
 * 
 * @author Jeffrey Selk
 *
 */
public class CreatureRaiseType {

	public int id;
	public String name;
	public double multiplier;
	
	public ContentValues buildContentValues() {
		ContentValues toReturn = new ContentValues();
		toReturn.put("CRT_ID", id);
		toReturn.put("CRT_NAME", name);
		toReturn.put("CRT_MULTIPLIER", multiplier);
		return toReturn;
	}
}
