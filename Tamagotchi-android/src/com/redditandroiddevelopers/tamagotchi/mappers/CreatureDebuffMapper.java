package com.redditandroiddevelopers.tamagotchi.mappers;

import android.database.Cursor;
import com.redditandroiddevelopers.tamagotchi.model.CreatureDebuff;

public class CreatureDebuffMapper extends Mapper<CreatureDebuff> {
    @Override
    public CreatureDebuff mapRow(Object obj) {
        if(obj instanceof Cursor) {
            Cursor c = (Cursor)obj;
            CreatureDebuff debuff = new CreatureDebuff();
            debuff.id = c.getLong(c.getColumnIndex("CD_ID"));
            debuff.name = c.getString(c.getColumnIndex("CD_NAME"));
            
            return debuff;
        }
        return null;
    }
}
