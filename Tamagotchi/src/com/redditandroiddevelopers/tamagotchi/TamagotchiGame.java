package com.redditandroiddevelopers.tamagotchi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.redditandroiddevelopers.tamagotchi.screens.CommonScreen;
import com.redditandroiddevelopers.tamagotchi.screens.GameLoop;
import com.redditandroiddevelopers.tamagotchi.screens.MainMenuScreen;
import com.redditandroiddevelopers.tamagotchi.screens.PauseScreen;
import com.redditandroiddevelopers.tamagotchi.screens.SplashScreen;

/**
 * The main activity for our game. This will be the only activity running in our
 * app, it will delegate what screen instance will render to the display based
 * on the state.
 */
public class TamagotchiGame extends Game {

    // enums are expensive
    public static final int STATE_MAIN_MENU = 0;
    public static final int STATE_RUNNING = 1;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_SELECT_PET = 3;
    public static final int STATE_MEMORIES = 4;
    public static final int STATE_SETTINGS = 5;

    public static int MY_STATE = 0;
    public static boolean STATE_CHANGE = false;

    @Override
    public void create() {
        setScreen(new SplashScreen());
    }

    @Override
    public void render() {
        // TODO FrameBuffer here?

        if (!STATE_CHANGE) {
            CommonScreen currentScreen = getScreen();
            currentScreen.render(Gdx.graphics.getDeltaTime());
        } else {
            STATE_CHANGE = false;
            getScreen().dispose();

            switch (MY_STATE) {
            case STATE_MAIN_MENU:
                setScreen(new MainMenuScreen());
                break;
            case STATE_RUNNING:
                setScreen(new GameLoop());
                break;
            case STATE_PAUSED:
                setScreen(new PauseScreen());
                break;
            case STATE_SELECT_PET:
                setScreen(new PauseScreen()); // FIXME: launch correct
                                              // screen when class has been
                                              // created
                break;
            case STATE_MEMORIES:
                setScreen(new PauseScreen());// FIXME: launch correct screen
                                             // when class has been created
                break;
            case STATE_SETTINGS:
                setScreen(new PauseScreen());// FIXME: launch correct screen
                                             // when class has been created;
            default:
                break;
            }
        }
    }

    @Override
    public CommonScreen getScreen() {
        return (CommonScreen) super.getScreen();
    }

    /**
     * Update the state, all updates outside of this class should use this
     * method
     * 
     * @param newStateVal
     *            The int val of the new state
     */
    public static void updateState(int newStateVal) {
        MY_STATE = newStateVal;
        STATE_CHANGE = true;
    }

}
