package your.redditandroiddevelopers.model;

import android.content.ContentValues;

/**
 * Model object for the SICKNESS database table.
 * 
 * Objects are public with no getters/setters for performance
 * 
 * @author Jeffrey Selk
 * 
 */
public class Sickness {

    public long id;
    public Medicine medicine;
    public String name;

    public ContentValues buildContentValues() {
        ContentValues toReturn = new ContentValues();
        toReturn.put("S_ID", id);
        toReturn.put("M_ID", medicine.id);
        toReturn.put("S_NAME", name);
        return toReturn;
    }
}
