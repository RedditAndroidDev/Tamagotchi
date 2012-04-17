package com.redditandroiddevelopers.tamagotchi.test;

import android.test.SingleLaunchActivityTestCase;

import com.redditandroiddevelopers.tamagotchi.MainActivity;

public class MainActivityTest extends SingleLaunchActivityTestCase<MainActivity> {

    private MainActivity mActivity;

    public MainActivityTest() {
        super("com.redditandroiddevelopers.tamagotchi", MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
    }

    public void testPreConditions() {
        assertNotNull(mActivity);   // placeholder
    }

}
