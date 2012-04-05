
package your.redditandroiddevelopers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;

import org.andengine.AndEngine;

import your.redditandroiddevelopers.dao.CreatureDao;

public class TamagotchiActivity extends Activity {
    private CreatureDao creatureDao;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Check if AndEngine is supported on device
        if (AndEngine.isDeviceSupported()) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle(R.string.error);
            dialog.setMessage(R.string.error_device_not_supported);
            dialog.setPositiveButton("OK", new OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.show();
        }

        // TODO: Game init code goes here

        creatureDao = new CreatureDao(getBaseContext());
        // Creature creature = creatureDao.findById(1l);
        // System.out.println(creature.name);

        // CreatureType type = new CreatureType();
        // type.id = 1;
        // type.name = "Default";

        // Creature submitted = Creature.createACreature("TestName",
        // Gender.MALE, type);
        // creatureDao.create(submitted);

        /* Check to see if the database is empty */
        Boolean isDatabaseEmpty = creatureDao.isDatabaseEmpty();

        if (!isDatabaseEmpty) {

            /* Get the number of alive creatures */
            int aliveCreatures = creatureDao.getNumberOfAlive();

            switch (aliveCreatures) {
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
        else {
            /* If the database is empty create a new creature */
            createCreature();
        }
    }

    private void createCreature() {
        startActivity(new Intent(getBaseContext(), CreateCreatureActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
        finish();
    }

}
