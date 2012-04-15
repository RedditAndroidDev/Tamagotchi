package your.redditandroiddevelopers.dao;

import java.util.ArrayList;
import java.util.List;

import your.redditandroiddevelopers.mappers.MedicineMapper;
import your.redditandroiddevelopers.mappers.SicknessMapper;
import your.redditandroiddevelopers.model.Medicine;
import your.redditandroiddevelopers.model.Sickness;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class SicknessMedicineDao {
    private CreatureDatabase db;

    public SicknessMedicineDao(Context context) {
        db = new CreatureDatabase(context);
    }

    /*
     * Sickness
     */
    public Sickness creature(Sickness sickness) {
        ContentValues vals = sickness.buildContentValues();
        if (sickness.id <= 0) {
            vals.putNull("S_ID");
        }
        long retId = db.insert(CreatureDatabase.SICKNESS_TABLE_NAME, vals);
        sickness.id = retId;
        return sickness;
    }

    public Sickness update(Sickness sickness) {
        db.update(CreatureDatabase.SICKNESS_TABLE_NAME,
                sickness.buildContentValues(), "S_ID = " + sickness.id, null);
        return sickness;
    }

    public void delete(Sickness sickness) {
        db.delete(CreatureDatabase.SICKNESS_TABLE_NAME,
                "S_ID = " + sickness.id, null);
    }

    public List<Sickness> getAll() {
        Cursor c = db.query(CreatureDatabase.SICKNESS_TABLE_NAME + " join "
                + CreatureDatabase.MEDICINE_TABLE_NAME + " on ("
                + CreatureDatabase.SICKNESS_TABLE_NAME + ".M_ID = "
                + CreatureDatabase.MEDICINE_TABLE_NAME + ".M_ID )", null, null,
                null, null, null, null);
        List<Sickness> toReturn = new ArrayList<Sickness>(c.getCount());
        while (!c.isAfterLast()) {
            toReturn.add(SicknessMapper.mapRow(c));
            c.moveToNext();
        }
        c.close();
        return toReturn;
    }

    public Sickness findById(long id) {
        Cursor c = db.query(CreatureDatabase.SICKNESS_TABLE_NAME + " join "
                + CreatureDatabase.MEDICINE_TABLE_NAME + " on ("
                + CreatureDatabase.SICKNESS_TABLE_NAME + ".M_ID = "
                + CreatureDatabase.MEDICINE_TABLE_NAME + ".M_ID )", null,
                "S_ID = " + id, null, null, null, null);
        Sickness s = SicknessMapper.mapRow(c);
        c.close();
        return s;
    }

    /*
     * Medicine
     */
    public Medicine create(Medicine m) {
        ContentValues vals = m.buildContentValues();
        if (m.id <= 0) {
            vals.putNull("M_ID");
        }
        long retId = db.insert(CreatureDatabase.MEDICINE_TABLE_NAME, vals);
        m.id = retId;
        return m;
    }

    public Medicine update(Medicine m) {
        db.update(CreatureDatabase.MEDICINE_TABLE_NAME, m.buildContentValues(),
                "M_ID = " + m.id, null);
        return m;
    }

    public List<Medicine> getAllMedicine() {
        Cursor c = db.query(CreatureDatabase.MEDICINE_TABLE_NAME, null, null,
                null, null, null, null);
        List<Medicine> toReturn = new ArrayList<Medicine>(c.getCount());
        while (!c.isAfterLast()) {
            toReturn.add(MedicineMapper.mapRow(c));
            c.moveToNext();
        }
        c.close();
        return toReturn;
    }

    public Medicine findMedicineById(long id) {
        Cursor c = db.query(CreatureDatabase.MEDICINE_TABLE_NAME, null,
                "M_ID = " + id, null, null, null, null);
        Medicine m = MedicineMapper.mapRow(c);
        c.close();
        return m;
    }
}
