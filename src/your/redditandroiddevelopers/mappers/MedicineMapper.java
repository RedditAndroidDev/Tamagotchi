package your.redditandroiddevelopers.mappers;

import your.redditandroiddevelopers.model.Medicine;
import android.database.Cursor;

public class MedicineMapper {

    public static Medicine mapRow(Cursor c) {
        Medicine m = new Medicine();
        m.id = c.getLong(c.getColumnIndex("M_ID"));
        m.name = c.getString(c.getColumnIndex("M_NAME"));
        return m;
    }
}
