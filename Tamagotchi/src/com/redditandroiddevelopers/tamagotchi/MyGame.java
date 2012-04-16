package com.redditandroiddevelopers.tamagotchi;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.redditandroiddevelopers.tamagotchi.screens.CommonScreen;
import com.redditandroiddevelopers.tamagotchi.screens.GameLoop;
import com.redditandroiddevelopers.tamagotchi.screens.MainMenuScreen;
import com.redditandroiddevelopers.tamagotchi.screens.PauseScreen;

public class MyGame extends Game {

    // enums are expensive
    public static int STATE_MAIN_MENU = 0;
    public static int STATE_RUNNING = 1;
    public static int STATE_PAUSED = 2;

    public static int MY_STATE = 0;
    public static boolean STATE_CHANGE = false;

    @Override
    public void create() {
        setScreen(new MainMenuScreen());
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
            case 1:
                setScreen(new GameLoop());
                break;
            case 2:
                setScreen(new PauseScreen());
                break;
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
