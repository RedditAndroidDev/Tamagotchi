
package your.redditandroiddevelopers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import your.redditandroiddevelopers.dao.CreatureDao;

public class MainCreatureActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_creature_layout);
        
        final CreatureDao dao = new CreatureDao(getBaseContext());
        final Button button = (Button) findViewById(R.id.purgedb);
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                dao.purgeData();
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainCreatureActivity.this);
                builder.setTitle("Purge database")
                        .setMessage("The database has been purged.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainCreatureActivity.this.finish();
                    }
                }).create().show();
            }
        });
    }

}
