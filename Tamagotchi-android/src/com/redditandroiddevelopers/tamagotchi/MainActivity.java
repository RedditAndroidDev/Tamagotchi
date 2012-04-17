package com.redditandroiddevelopers.tamagotchi;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.redditandroiddevelopers.tamagotchi.dao.CreatureDao;
import com.redditandroiddevelopers.tamagotchi.dao.CreatureDatabase;
import com.redditandroiddevelopers.tamagotchi.mappers.CreatureMapper;
import com.redditandroiddevelopers.tamagotchi.model.Creature;

public class MainActivity extends AndroidApplication {
    private CreatureDao creatureDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = false;

        initializeDatabase();

        initialize(new TamagotchiGame(creatureDao), cfg);
    }

    private void initializeDatabase() {
        CreatureDatabase<Creature> db = new CreatureDatabase<Creature>(
                getBaseContext());
        //db.seedData();
        creatureDao = new CreatureDao(db, new CreatureMapper());
        creatureDao.create(Creature.createDefaultCreature());
    }
}