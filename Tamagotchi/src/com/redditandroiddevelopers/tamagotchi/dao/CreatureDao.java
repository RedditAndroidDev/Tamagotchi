package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.List;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.Creature;

/**
 * The Data Access Object for the CREATURE_INFO database table.
 * 
 * @author Jeffrey Selk
 */
public class CreatureDao {
    private CommonDatabase<Creature> db;
    private Mapper<Creature> rowMapper;

    public CreatureDao(CommonDatabase<Creature> database,
            Mapper<Creature> mapper) {
        db = database;
        rowMapper = mapper;
    }

    public Creature create(Creature creature) {
        return db.insert(creature, CommonDatabase.INFO_TABLE_NAME);
    }

    public Creature update(Creature creature) {
        db.update(CommonDatabase.INFO_TABLE_NAME, creature, "CI_ID = "
                + creature.id, null);
        return creature;
    }

    public void delete(Creature creature) {
        db.delete(CommonDatabase.INFO_TABLE_NAME, "CI_ID = " + creature.id,
                null);
    }

    public boolean isDatabaseEmpty() {
        List<Creature> c = db.queryList(rowMapper,
                CommonDatabase.INFO_TABLE_NAME, null, null, null, null, null,
                null);
        if (c == null) {
            return true;
        }
        return false;
    }

//    public void seedData() {
//        // TODO: debug guard
//        db.seedData();
//    }
//
//    public void purgeData() {
//        // TODO: debug guard
//        db.deleteDatabase();
//    }

    public List<Creature> getAll() {
        return db
                .queryList(
                        rowMapper,
                        "CREATURE_INFO join CREATURE_TYPE on (CREATURE_INFO.CT_ID = CREATURE_TYPE.CT_ID)",
                        null, null, null, null, null, null);
    }

    public int getNumberOfAlive() {
        return db.getResultCount(CommonDatabase.INFO_TABLE_NAME, null,
                "CI_ALIVE = 1", null, null, null, null);
    }

    public Creature findById(long id) {
        return db
                .queryUnique(
                        rowMapper,
                        "CREATURE_INFO join CREATURE_TYPE on (CREATURE_INFO.CT_ID = CREATURE_TYPE.CT_ID)",
                        null, "CI_ID = " + id, null, null, null, null);
    }
}
