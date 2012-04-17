package com.redditandroiddevelopers.tamagotchi.model;

import android.content.ContentValues;

public class Experience {
    public long id;
    public CreatureType type;
    public int minXp;
    public int maxXp;
    
    public ContentValues buildContentValues() {
        ContentValues toReturn = new ContentValues();
        toReturn.put("E_ID", id);
        toReturn.put("CT_ID", type.id);
        toReturn.put("E_MIN_XP", minXp);
        toReturn.put("E_MAX_XP", maxXp);
        return toReturn;
    }
}
