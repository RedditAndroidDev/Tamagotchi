package your.redditandroiddevelopers.test;

import android.test.ActivityInstrumentationTestCase2;

import your.redditandroiddevelopers.CreateCreatureActivity;

public class CreateCreatureActivityTest extends
        ActivityInstrumentationTestCase2<CreateCreatureActivity> {
    
    private CreateCreatureActivity mActivity;
    
    public CreateCreatureActivityTest() {
        super("your.redditandroiddevelopers", CreateCreatureActivity.class);
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
