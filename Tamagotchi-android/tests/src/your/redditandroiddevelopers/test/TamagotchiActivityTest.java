package your.redditandroiddevelopers.test;

import your.redditandroiddevelopers.TamagotchiActivity;
import android.test.ActivityInstrumentationTestCase2;


public class TamagotchiActivityTest extends
ActivityInstrumentationTestCase2<TamagotchiActivity> {

    private TamagotchiActivity mActivity;

    public TamagotchiActivityTest() {
        super("your.redditandroiddevelopers", TamagotchiActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        mActivity = getActivity();
    }

    public void testPreConditions() {
        assertNotNull(mActivity);   // placeholder
    }

}
