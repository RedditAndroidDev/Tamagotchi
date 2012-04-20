
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;

/**
 * A simple splash screen that is shown when the game is started. It
 * automatically transitions to the MainMenuScreen after 3 seconds.
 */
public class SplashScreen extends CommonScreen {

    private static final String TAG = "Tamagotchi:SplashScreen";

    public SplashScreen(TamagotchiGame game) {
        super(game);
    }

    @Override
    protected final Stage createStage(SpriteBatch batch) {
        return new Stage(game.config.stageWidth, game.config.stageHeight, false, batch);
    }

    @Override
    public final void show() {
        super.show();

        // load Reddit alien texture
        Image splashLogo = new Image(new Texture(Gdx.files.internal("Reddit-alien.png")));
        splashLogo.x = getCenterX(splashLogo.getRegion().getTexture());
        splashLogo.y = getCenterY(splashLogo.getRegion().getTexture());
        stage.addActor(splashLogo);

        // begin loading all assets (asynchronously)
        game.assets.loadAssets();
    }

    @Override
    public final void update(float delta) {
        super.update(delta);

        final float progress = game.assets.getProgress();
        Gdx.app.log(TAG, "Loading assets ... " + (progress * 100) + "% done");
        if (game.assets.update()) {
            // we are done loading, move to main menu screen
            Gdx.app.log(TAG, "Loading assets ... finished");
            game.updateState(TamagotchiGame.STATE_MAIN_MENU);
        }
    }

    @Override
    public final void draw() {
        // use white background for now
        Gdx.gl.glClearColor(1, 1, 1, 1);
        super.draw();
    }

    /**
     * Gets the X coordinate to display a texture centered on the X axis of the
     * screen.
     * 
     * @param t Texture to display
     * @return X coordinate
     */
    private final float getCenterX(Texture t) {
        return (stage.width() - t.getWidth()) / 2;
    }

    /**
     * Gets the Y coordinate to display a texture centered on the Y axis of the
     * screen.
     * 
     * @param t Texture to display
     * @return Y coordinate
     */
    private final float getCenterY(Texture t) {
        return (stage.height() - t.getHeight()) / 2;
    }
}
