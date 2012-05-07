package com.redditandroiddevelopers.tamagotchi.mappers;

import com.redditandroiddevelopers.tamagotchi.model.CreatureAction;

import android.database.Cursor;

public class CreatureActionMapper extends Mapper<CreatureAction> {

    @Override
    public CreatureAction mapRow(Object obj) {
        if(obj instanceof Cursor) {
            Cursor c = (Cursor)obj;
            CreatureAction action = new CreatureAction();
            action.id = c.getLong(c.getColumnIndex("CA_ID"));
            action.name = c.getString(c.getColumnIndex("CA_NAME"));
            
            return action;
        }
        return null;
    }
}
