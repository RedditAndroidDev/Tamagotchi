
package your.redditandroiddevelopers;

import org.andengine.AndEngine;

import your.redditandroiddevelopers.dao.CreatureDao;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class TamagotchiActivity extends Activity {
    private CreatureDao creatureDao;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Check if AndEngine is supported on device
        if (!AndEngine.isDeviceSupported()) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.error)
            .setMessage(R.string.error_device_not_supported)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    TamagotchiActivity.this.finish();
                }
            })
            .create().show();

            /* Note that .show() is asynchronous! We really need to block here
             * and wait for user to click "OK" and close the app. Otherwise,
             * the following code will be executed before the UI thread even has
             * a chance of rendering what was supposed to be the only thing
             * the user sees. Not only is the following code unnecessary at this
             * point, it might crash the app on unsupported devices, too!
             */

            // for simplicity sake, just don't do anything else after this point
        }
        else {
            initialize();
        }
    }

    private void initialize() {
        creatureDao = new CreatureDao(getBaseContext());
        boolean isDatabaseEmpty = creatureDao.isDatabaseEmpty();
        if (!isDatabaseEmpty) {
            int aliveCreatures = creatureDao.getNumberOfAlive();
            switch (aliveCreatures) {
            case 0:     // dead creatures
                createCreature();
                break;
            case 1:
                startActivity(new Intent(getBaseContext(), MainCreatureActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                finish();
                break;
            default:    // multiple creatures
                startActivity(new Intent(getBaseContext(), CreatureSelectionActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
                finish();
                break;
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
