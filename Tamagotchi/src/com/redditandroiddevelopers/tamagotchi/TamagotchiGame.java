
package com.redditandroiddevelopers.tamagotchi;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
import com.redditandroiddevelopers.tamagotchi.screens.CommonScreen;
import com.redditandroiddevelopers.tamagotchi.screens.MainGameScreen;
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
    public static final int STATE_MAIN_GAME = 1;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_SELECT_PET = 3;
    public static final int STATE_MEMORIES = 4;
    public static final int STATE_SETTINGS = 5;

    public static int MY_STATE = 0;
    public static boolean STATE_CHANGE = false;

    private static AssetManager assetManager = new AssetManager();
    private static InputMultiplexer inputMultiplexer = new InputMultiplexer();

    @Override
    public void create() {
        // do first-time configuration that should live as long as the application does
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        
        Resolution resolution = new Resolution(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), "");
        ResolutionFileResolver resolver = new ResolutionFileResolver(
                new InternalFileHandleResolver(), resolution);
        assetManager.setLoader(Texture.class, new TextureLoader(resolver));
        
        Gdx.input.setInputProcessor(inputMultiplexer);

        setScreen(new SplashScreen());
    }

    @Override
    public void render() {
        // TODO FrameBuffer here?

        super.render(); /* this will take care of the case where the
                           current screen is still the same */

        if (STATE_CHANGE) {
            STATE_CHANGE = false;
            getScreen().dispose();

            switch (MY_STATE) {
                case STATE_MAIN_MENU:
                    setScreen(new MainMenuScreen());
                    break;
                case STATE_MAIN_GAME:
                    setScreen(new MainGameScreen());
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

    /**
     * Returns the AssetManager of the game.
     * 
     * @return AssetManager
     */
    public static AssetManager getAssetManager() {
        return assetManager;
    }
    
    public static InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }

}
