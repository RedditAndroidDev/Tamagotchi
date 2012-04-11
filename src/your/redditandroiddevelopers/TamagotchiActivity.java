
package your.redditandroiddevelopers;

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

        // Check if game is supported on device
        if (gameIsSupported()) {

            // Run game
            initialize();

        }
        else {

            // Display error dialog if game cannot run on device.
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.error)
                    .setMessage(R.string.error_device_not_supported)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Since the game cannot run, it should close after
                            // the user has read the error message.
                            TamagotchiActivity.this.finish();
                        }
                    })
                    .create().show();

        }
    }

    /**
     * Checks if the game can run on this device.
     * 
     * @return <b>true</b> if game is supported, <b>false</b> if not
     */
    private boolean gameIsSupported() {
        // TODO: implement code that checks for compatibility
        return true;
    }

    /**
     * This method performs some initial database checks. If no database can be
     * found, a new one is created.<br>
     * In any case, the app tries to read the database and starts the
     * appropriate activity.
     */
    private void initialize() {
        creatureDao = new CreatureDao(getBaseContext());
        boolean isDatabaseEmpty = creatureDao.isDatabaseEmpty();
        if (!isDatabaseEmpty) {
            int aliveCreatures = creatureDao.getNumberOfAlive();
            switch (aliveCreatures) {
                case 0:
                    // No creature is alive, user is taken to
                    // CreateCreatureActivity
                    createNewCreature();
                    break;
                case 1:
                    // One creature is alive, user is taken to
                    // MainCreatureActivity
                    startMainCreatureActivity();
                    break;
                default:
                    // More than one creature is alive, user is taken to
                    // CreatureSelectionActivity
                    startCreatureSelectionActivity();
                    break;
            }
        }
        else {
            /* If the database is empty, create a new creature */
            createNewCreature();
        }
    }

    /**
     * Launches <i>CreateCreatureActivity</i>.
     */
    private void createNewCreature() {
        startActivity(new Intent(getBaseContext(), CreateCreatureActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
        finish();
    }

    /**
     * Launches <i>MainCreatureActivity</i>.
     */
    private void startMainCreatureActivity() {
        startActivity(new Intent(getBaseContext(), MainCreatureActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
        finish();
    }

    /**
     * Launches <i>CreatureSelectionActivity</i>.
     */
    private void startCreatureSelectionActivity() {
        startActivity(new Intent(getBaseContext(), CreatureSelectionActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT));
        finish();
    }
}
