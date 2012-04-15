package your.redditandroiddevelopers.mappers;

import your.redditandroiddevelopers.model.CreatureRaiseType;
import android.database.Cursor;

public class CreatureRaiseTypeMapper {

    public static CreatureRaiseType mapRow(Cursor c) {
        CreatureRaiseType crt = new CreatureRaiseType();
        crt.id = c.getLong(c.getColumnIndex("CRT_ID"));
        crt.name = c.getString(c.getColumnIndex("CRT_NAME"));
        crt.multiplier = c.getDouble(c.getColumnIndex("CRT_MULTIPLIER"));
        return crt;
    }
}
