package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.List;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.CreatureEvolution;

public class CreatureEvolutionDao {
    private CommonDatabase<CreatureEvolution> db;
    Mapper<CreatureEvolution> rowMapper;

    public CreatureEvolutionDao(CommonDatabase<CreatureEvolution> database,
            Mapper<CreatureEvolution> mapper) {
        db = database;
        rowMapper = mapper;
    }

    public CreatureEvolution create(CreatureEvolution evo) {
        return db.insert(evo, CommonDatabase.EVOLUTION_TABLE_NAME);
    }

    public CreatureEvolution update(CreatureEvolution evo) {
        db.update(CommonDatabase.EVOLUTION_TABLE_NAME, evo, "CE_ID = "
                + evo.id, null);
        return evo;
    }

    public void delete(CreatureEvolution evo) {
        db.delete(CommonDatabase.EVOLUTION_TABLE_NAME, "CE_ID = " + evo.id,
                null);
    }

    public List<CreatureEvolution> getAll() {
        return db
                .queryList(
                        rowMapper,
                        "CREATURE_EVOLUTION join CREATURE_TYPE on (CREATURE_EVOLUTION.CT_ID = CREATURE_TYPE.CT_ID)",
                        null, null, null, null, null, null);
    }

    public CreatureEvolution findById(long id) {
        return db
                .queryUnique(
                        rowMapper,
                        "CREATURE_EVOLUTION join CREATURE_TYPE on (CREATURE_EVOLUTION.CT_ID = CREATURE_TYPE.CT_ID)",
                        null, "CE_ID = " + id, null, null, null, null);
    }
}
