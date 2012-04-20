package com.redditandroiddevelopers.tamagotchi.dao;

import java.util.List;

import com.redditandroiddevelopers.tamagotchi.mappers.Mapper;
import com.redditandroiddevelopers.tamagotchi.model.Medicine;

public class MedicineDao {
    private CommonDatabase<Medicine> db;
    private Mapper<Medicine> rowMapper;

    public MedicineDao(CommonDatabase<Medicine> database,
            Mapper<Medicine> mapper) {
        db = database;
        rowMapper = mapper;
    }

    public Medicine create(Medicine m) {
        return db.insert(m, CommonDatabase.MEDICINE_TABLE_NAME);
    }

    public Medicine update(Medicine m) {
        db.update(CommonDatabase.MEDICINE_TABLE_NAME, m, "M_ID = " + m.id,
                null);
        return m;
    }

    public List<Medicine> getAll() {
        return db.queryList(rowMapper, CommonDatabase.MEDICINE_TABLE_NAME,
                null, null, null, null, null, null);
    }

    public Medicine findById(long id) {
        return db.queryUnique(rowMapper, CommonDatabase.MEDICINE_TABLE_NAME,
                null, "M_ID = " + id, null, null, null, null);
    }
}
