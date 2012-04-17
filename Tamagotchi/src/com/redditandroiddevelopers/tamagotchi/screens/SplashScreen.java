
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;

/**
 * A simple splash screen that is shown when the game is started. It
 * automatically transitions to the MainMenuScreen after 3 seconds.
 */
public class SplashScreen extends CommonScreen {

    private static final float SPLASH_DURATION = .5f;

    private SpriteBatch batch;
    private Texture splashTexture;
    private float timeElapsed = 0;

    @Override
    public void show() {
        // super.show();

        batch = new SpriteBatch();

        // load Reddit alien texture
        splashTexture = new Texture(Gdx.files.internal("Reddit-alien.png"));
    }

    @Override
    public void update(float delta) {
        // delta is the time since the last update, adding it up gives us the
        // time since the first update
        if (timeElapsed < SPLASH_DURATION) { // revert to 3 (or any other suitable value)
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

        batch.begin();
        // draw Reddit alien on the screen
        batch.draw(splashTexture, getCenterX(splashTexture), getCenterY(splashTexture));
        batch.end();
    }

    /**
     * Gets the X coordinate to display a texture centered on the X axis of the
     * screen.
     * 
     * @param t Texture to display
     * @return X coordinate
     */
    private static final int getCenterX(Texture t) {
        return (Gdx.graphics.getWidth() - t.getWidth()) / 2;
    }

    /**
     * Gets the Y coordinate to display a texture centered on the Y axis of the
     * screen.
     * 
     * @param t Texture to display
     * @return Y coordinate
     */
    private static final int getCenterY(Texture t) {
        return (Gdx.graphics.getHeight() - t.getHeight()) / 2;
    }
}
