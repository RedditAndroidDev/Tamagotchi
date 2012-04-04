package your.redditandroiddevelopers;

import your.redditandroiddevelopers.dao.CreatureDao;
import your.redditandroiddevelopers.model.Creature;
import your.redditandroiddevelopers.model.CreatureType;
import your.redditandroiddevelopers.model.Gender;
import android.app.Activity;
import android.os.Bundle;

public class TamagotchiActivity extends Activity {
	private CreatureDao creatureDao;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        creatureDao = new CreatureDao(getBaseContext());
//        Creature creature = creatureDao.findById(1l);
//        System.out.println(creature.name);
        
    	CreatureType type = new CreatureType();
    	type.id = 1;
    	type.name = "Default";
    	
//        Creature submitted = Creature.createACreature("TestName", Gender.MALE, type);
//        creatureDao.create(submitted);
    }
    
 
}