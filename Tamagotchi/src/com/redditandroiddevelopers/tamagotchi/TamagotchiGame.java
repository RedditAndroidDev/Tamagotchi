
package com.redditandroiddevelopers.tamagotchi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.Texture;
import com.redditandroiddevelopers.tamagotchi.screens.CommonScreen;
import com.redditandroiddevelopers.tamagotchi.screens.CreatureCreationScreen;
import com.redditandroiddevelopers.tamagotchi.screens.MainGameScreen;
import com.redditandroiddevelopers.tamagotchi.screens.MainMenuScreen;
import com.redditandroiddevelopers.tamagotchi.screens.MemoriesScreen;
import com.redditandroiddevelopers.tamagotchi.screens.PauseScreen;
import com.redditandroiddevelopers.tamagotchi.screens.SettingsScreen;
import com.redditandroiddevelopers.tamagotchi.screens.SplashScreen;

import java.util.Stack;

/**
 * The main activity for our game. This will be the only activity running in our
 * app, it will delegate what screen instance will render to the display based
 * on the state.
 */
public class TamagotchiGame extends Game {

    private static final String TAG = "Tamagotchi:TamagotchiGame";

    public static final int STATE_MAIN_MENU = 0;
    public static final int STATE_MAIN_GAME = 1;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_SELECT_PET = 3;
    public static final int STATE_MEMORIES = 4;
    public static final int STATE_SETTINGS = 5;
    public static final int NUM_SCREENS = 6;

    private CommonScreen[] screens;
    private Stack<CommonScreen> screenHistory;

    private GameInput gameInput;

    public final TamagotchiConfiguration config;
    public InputMultiplexer inputMultiplexer;
    public AssetManager assetManager;
    public TamagotchiAssets assets;

    public TamagotchiGame(TamagotchiConfiguration config) {
        this.config = config;
    }

    @Override
    public void create() {
        // do first-time configurations that should live as long as the
        // application does
        Gdx.app.setLogLevel(config.logLevel);

        // create screen objects we're going to need throughout
        screens = new CommonScreen[] {
                new MainMenuScreen(this),
                new MainGameScreen(this),
                new PauseScreen(this),
                new CreatureCreationScreen(this), // TODO: implement
                                                  // PetSelectionScreen
                new MemoriesScreen(this), // TODO: implement MemoriesScreen
                new SettingsScreen(this), // TODO: implement SettingsScreen
        };

        screenHistory = new Stack<CommonScreen>();

        final Resolution resolution = new Resolution(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), "");
        final ResolutionFileResolver resolver = new ResolutionFileResolver(
                new InternalFileHandleResolver(), resolution);
        assetManager = new AssetManager();
        assetManager.setLoader(Texture.class, new TextureLoader(resolver));

        assets = new TamagotchiAssets(assetManager);

        gameInput = new GameInput();
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(gameInput);
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(inputMultiplexer);

        setScreen(new SplashScreen(this));
    }

    @Override
    public void render() {
        // TODO FrameBuffer here?

        // actually render the current screen
        super.render();
    }

    /**
     * Update the state, all updates outside of this class should use this
     * method
     * 
     * @param state The int val of the new state
     */
    public void updateState(int state) {
        if (state < 0 || state >= NUM_SCREENS) {
            assert false;
            throw new IllegalArgumentException("Invalid state value");
        }
        final CommonScreen screen = screens[state];
        Gdx.app.debug(TAG, "Setting screen to " + screen.getClass().getSimpleName());
        screenHistory.push(screen);
        setScreen(screen);
    }

    public void goToPreviousScreen() {
        if (screenHistory.empty()) {
            Gdx.app.log(TAG, "Screen history is empty!");
            return;
        }

        // first, pop the current screen
        screenHistory.pop();
        if (screenHistory.empty()) {
            Gdx.app.log(TAG, "Game is closing now.");
            // this means we are on the main screen and the application must
            // exit.
            Gdx.app.exit();
        } else {
            // finally, only peek into the previous screen
            final CommonScreen prevScreen = screenHistory.peek();
            Gdx.app.debug(TAG, "Going to a previous screen "
                    + prevScreen.getClass().getSimpleName());
            setScreen(prevScreen);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        assetManager.dispose();
        assetManager = null;
        assets = null;
        inputMultiplexer.removeProcessor(gameInput);
        gameInput = null;
        Gdx.input.setInputProcessor(null);
        inputMultiplexer = null;
    }

    public class GameInput extends InputAdapter {

        @Override
        public boolean keyDown(int keycode) {
            switch (keycode) {
                case Keys.BACK:
                case Keys.ESCAPE:
                    goToPreviousScreen();
                    return true;
                default:
                    break;
            }
            return false;
        }

    }

}
