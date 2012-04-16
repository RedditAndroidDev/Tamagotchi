
package com.redditandroiddevelopers.tamagotchi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.redditandroiddevelopers.tamagotchi.screens.CommonScreen;
import com.redditandroiddevelopers.tamagotchi.screens.GameLoop;
import com.redditandroiddevelopers.tamagotchi.screens.MainMenuScreen;
import com.redditandroiddevelopers.tamagotchi.screens.PauseScreen;
import com.redditandroiddevelopers.tamagotchi.screens.SplashScreen;

public class TamagotchiGame extends Game {

    // enums are expensive
    public static int STATE_MAIN_MENU = 0;
    public static int STATE_RUNNING = 1;
    public static int STATE_PAUSED = 2;
    public static int STATE_SELECT_PET = 3;
    public static int STATE_MEMORIES = 4;
    public static int STATE_SETTINGS = 5;

    public static int MY_STATE = 0;
    public static boolean STATE_CHANGE = false;

    @Override
    public void create() {
        setScreen(new SplashScreen());
    }

    @Override
    public void render() {
        if (!STATE_CHANGE) {
            CommonScreen currentScreen = getScreen();
            currentScreen.render(Gdx.graphics.getDeltaTime());
        } else {
            STATE_CHANGE = false;
            getScreen().dispose();

            switch (MY_STATE) {
                case 0:
                    setScreen(new MainMenuScreen());
                    break;
                case 1:
                    setScreen(new GameLoop());
                    break;
                case 2:
                    setScreen(new PauseScreen());
                    break;
                case 3:
                    setScreen(new PauseScreen()); // FIXME: launch correct
                                                  // screen when class has been
                                                  // created
                    break;
                case 4:
                    setScreen(new PauseScreen());// FIXME: launch correct screen
                                                 // when class has been created
                    break;
                case 5:
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

    public void updateState(int newStateVal) {
        MY_STATE = newStateVal;
        STATE_CHANGE = true;
    }

}
