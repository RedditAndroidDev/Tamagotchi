
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * A simple splash screen that is shown when the game is started. It
 * automatically transitions to the MainMenuScreen after 3 seconds.
 */
public class SplashScreen extends CommonScreen
{
    private Game TamagotchiGame;
    private SpriteBatch batch;
    private Texture splashTexture;
    private float timeElapsed = 0;

    public SplashScreen(Game g)
    {
        TamagotchiGame = g;
    }

    @Override
    public void show() {
        // super.show();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        batch = new SpriteBatch();
        splashTexture = new Texture(Gdx.files.internal("Reddit-alien.png"));
    }

    @Override
    public void update(float delta) {
        if (timeElapsed < 3) {
            timeElapsed += delta;
        }
        else {
            TamagotchiGame.setScreen(new MainMenuScreen());
        }
    }

    @Override
    public void draw(float delta) {
        batch.begin();
        batch.draw(splashTexture, getCenterX(splashTexture), getCenterY(splashTexture));
        batch.end();
    }

    protected int getCenterX(Texture t) {
        return (Gdx.graphics.getWidth() - t.getWidth()) / 2;
    }

    protected int getCenterY(Texture t) {
        return (Gdx.graphics.getHeight() - t.getHeight()) / 2;
    }
}
