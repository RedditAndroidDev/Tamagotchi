package your.redditandroiddevelopers.model;

import android.content.ContentValues;

/**
 * Model object for the CREATURE_STATE database table.
 * 
 * Objects are public with no getters/setters for performance
 * 
 * @author Jeffrey Selk
 *
 */
public class CreatureState {

	public long id;
	public Creature creature;
	public CreatureRaiseType raiseType;
	public Sickness sickness;
	public int health;
	public int bowel;
	public int discipline;
	public int hunger;
	public int happy;
	public boolean sick;
	
	public ContentValues buildContentValues() {
		ContentValues toReturn = new ContentValues();
		toReturn.put("CS_ID", id);
		toReturn.put("CI_ID", creature.id);
		toReturn.put("CRT_ID", raiseType.id);
		toReturn.put("S_ID", sickness.id);
		toReturn.put("CS_HEALTH", health);
		toReturn.put("CS_BOWEL", bowel);
		toReturn.put("CS_DISCIPLINE", discipline);
		toReturn.put("CS_HUNGER", hunger);
		toReturn.put("CS_HAPPY", happy);
		toReturn.put("CS_SICK", sick);
		return toReturn;
	}
}
