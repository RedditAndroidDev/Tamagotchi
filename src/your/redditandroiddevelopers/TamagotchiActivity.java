
package your.redditandroiddevelopers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import your.redditandroiddevelopers.dao.CreatureDao;
import your.redditandroiddevelopers.model.Creature;

import java.util.List;

public class TamagotchiActivity extends Activity {
    private CreatureDao creatureDao;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        creatureDao = new CreatureDao(getBaseContext());
        // Creature creature = creatureDao.findById(1l);
        // System.out.println(creature.name);

        // CreatureType type = new CreatureType();
        // type.id = 1;
        // type.name = "Default";

        // Creature submitted = Creature.createACreature("TestName",
        // Gender.MALE, type);
        // creatureDao.create(submitted);

        creatureDao.seedData();

        /* Check to see if the database is empty */
        Boolean isDatabaseEmpty = creatureDao.isDatabaseEmpty();

        if (!isDatabaseEmpty) {

            /* Get the number of alive creatures */
            List<Creature> aliveCreatures = creatureDao.getAlive();

            switch (aliveCreatures.size()) {
            /* Dead creatures */
                case 0: {
                    createCreature();
                    break;
                }
                /* One creature */
                case 1: {
                    startActivity(new Intent(getBaseContext(), MainCreatureActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                    finish();
                    break;
                }
                /* Multiple creatures */
                default: {
                    startActivity(new Intent(getBaseContext(), CreatureSelectionActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                    finish();
                    break;
                }
            }
        }
        /* If the database is empty create a new creature */
        createCreature();
    }

    private void createCreature() {
        startActivity(new Intent(getBaseContext(), CreateCreatureActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
        finish();
    }

}
