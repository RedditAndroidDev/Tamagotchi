package your.redditandroiddevelopers.model;

import android.content.ContentValues;

/**
 * Model object for the MEDICINE database table.
 * 
 * Objects are public with no getters/setters for performance
 * 
 * @author Jeffrey Selk
 * 
 */
public class Medicine {

    public long id;
    public String name;

    public ContentValues buildContentValues() {
        ContentValues toReturn = new ContentValues();
        toReturn.put("M_ID", id);
        toReturn.put("M_NAME", name);
        return toReturn;
    }
}
