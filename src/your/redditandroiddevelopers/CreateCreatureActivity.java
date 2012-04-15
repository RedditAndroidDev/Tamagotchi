package your.redditandroiddevelopers;

import android.app.Activity;
import android.os.Bundle;

import your.redditandroiddevelopers.dao.CreatureDao;
import your.redditandroiddevelopers.model.Creature;

public class CreateCreatureActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: user input
        Creature creature = Creature.createDefaultCreature();
        CreatureDao dao = new CreatureDao(getBaseContext());
        creature = dao.create(creature);

        setContentView(R.layout.create_creature_layout);
    }

}
