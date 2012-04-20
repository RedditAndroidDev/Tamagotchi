
package com.redditandroiddevelopers.tamagotchi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public static final int STATE_MAIN_MENU = 0;
    public static final int STATE_MAIN_GAME = 1;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_SELECT_PET = 3;
    public static final int STATE_MEMORIES = 4;
    public static final int STATE_SETTINGS = 5;

    private CommonScreen[] screens;

    public final TamagotchiConfiguration config;
    public AssetManager assetManager;
    public InputMultiplexer inputMultiplexer;
    public SpriteBatch spriteBatch;

    private FPSLogger fpsLogger; // XXX: temporary!

    public TamagotchiGame(TamagotchiConfiguration config) {
        this.config = config;
    }

    @Override
    public void create() {
        // do first-time configurations that should live as long as the
        // application does
        Gdx.app.setLogLevel(config.logLevel);
        fpsLogger = new FPSLogger();

        // create screen objects we're going to need throughout
        screens = new CommonScreen[] {
                new MainMenuScreen(this),
                new MainGameScreen(this),
                new PauseScreen(this),
                new PauseScreen(this), // TODO: implement PetSelectionScreen
                new PauseScreen(this), // TODO: implement MemoriesScreen
                new PauseScreen(this), // TODO: implement SettingsScreen
        };

        Resolution resolution = new Resolution(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
                "");
        ResolutionFileResolver resolver = new ResolutionFileResolver(
                new InternalFileHandleResolver(), resolution);
        assetManager = new AssetManager();
        assetManager.setLoader(Texture.class, new TextureLoader(resolver));

        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setInputProcessor(inputMultiplexer);

        spriteBatch = new SpriteBatch();

        setScreen(new SplashScreen(this));
    }

    @Override
    public void render() {
        // TODO FrameBuffer here?

        // actually render the current screen
        super.render();
        if (config.logFps)
            fpsLogger.log();
    }

    /**
     * Update the state, all updates outside of this class should use this
     * method
     * 
     * @param state The int val of the new state
     */
    public void updateState(int state) {
        if (state < 0 || state >= 6) {
            assert false;
            throw new IllegalArgumentException("Invalid state value");
        }
        setScreen(screens[state]);
    }

}
