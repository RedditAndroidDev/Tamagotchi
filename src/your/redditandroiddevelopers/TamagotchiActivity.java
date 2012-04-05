
package your.redditandroiddevelopers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
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
    }

}
