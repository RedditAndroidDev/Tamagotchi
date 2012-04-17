
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

    private static final float SPLASH_DURATION = .5f;

    private Stage stage;

    private float timeElapsed = 0;

    @Override
    public void show() {
        // super.show();
        stage = new Stage(800, 480, true, new SpriteBatch());

        // load Reddit alien texture
        Image splashLogo = new Image(new Texture(Gdx.files.internal("Reddit-alien.png")));
        splashLogo.x = getCenterX(splashLogo.getRegion().getTexture());
        splashLogo.y = getCenterY(splashLogo.getRegion().getTexture());
        stage.addActor(splashLogo);
    }

    @Override
    public void update(float delta) {
        // delta is the time since the last update, adding it up gives us the
        // time since the first update
        if (timeElapsed < SPLASH_DURATION) { // revert to 3 (or any other
                                             // suitable value)
            // before publishing the game
            timeElapsed += delta;
        }
        else {
            // after 3 seconds, the Splash screen is hidden and the
            // MainMenuScreen is shown
            TamagotchiGame.updateState(TamagotchiGame.STATE_MAIN_MENU);
        }
    }

    @Override
    public void draw(float delta) {
        // use white background for now
        Gdx.gl.glClearColor(1, 1, 1, 1);

        // draw stage
        stage.draw();
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
