package your.redditandroiddevelopers.dao;

import java.util.ArrayList;
import java.util.List;

import your.redditandroiddevelopers.mappers.CreatureEvolutionMapper;
import your.redditandroiddevelopers.model.CreatureEvolution;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class CreatureEvolutionDao {
    private CreatureDatabase db;

    public CreatureEvolutionDao(Context context) {
        db = new CreatureDatabase(context);
    }

    public CreatureEvolution creature(CreatureEvolution evo) {
        ContentValues vals = evo.buildContentValues();
        if (evo.id <= 0) {
            vals.putNull("CE_ID");
        }
        long retId = db.insert(CreatureDatabase.EVOLUTION_TABLE_NAME, vals);
        evo.id = retId;
        return evo;
    }

    public CreatureEvolution update(CreatureEvolution evo) {
        db.update(CreatureDatabase.EVOLUTION_TABLE_NAME,
                evo.buildContentValues(), "CE_ID = " + evo.id, null);
        return evo;
    }

    public void delete(CreatureEvolution evo) {
        db.delete(CreatureDatabase.EVOLUTION_TABLE_NAME, "CE_ID = " + evo.id,
                null);
    }

    public List<CreatureEvolution> getAll() {
        Cursor c = db
                .query("CREATURE_EVOLUTION join CREATURE_TYPE on (CREATURE_EVOLUTION.CT_ID = CREATURE_TYPE.CT_ID)",
                        null, null, null, null, null, null);
        List<CreatureEvolution> toReturn = new ArrayList<CreatureEvolution>(
                c.getCount());
        while (!c.isAfterLast()) {
            toReturn.add(CreatureEvolutionMapper.mapRow(c));
            c.moveToNext();
        }
        c.close();
        return toReturn;
    }

    public CreatureEvolution findById(long id) {
        Cursor c = db
                .query("CREATURE_EVOLUTION join CREATURE_TYPE on (CREATURE_EVOLUTION.CT_ID = CREATURE_TYPE.CT_ID)",
                        null, "CE_ID = " + id, null, null, null, null);
        CreatureEvolution toReturn = CreatureEvolutionMapper.mapRow(c);
        c.close();
        return toReturn;
    }
}
