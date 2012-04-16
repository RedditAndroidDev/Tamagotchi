package com.redditandroiddevelopers.tamagotchi.test;

import android.test.ActivityInstrumentationTestCase2;

import com.redditandroiddevelopers.tamagotchi.MainActivity;


public class MainActivityTest extends
ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;

    public MainActivityTest() {
        super(MainActivity.class);
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
