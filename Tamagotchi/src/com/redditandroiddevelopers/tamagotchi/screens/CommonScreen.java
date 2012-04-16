package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;


public abstract class CommonScreen implements Screen {

    // Common, default implementations go here...

    /**
     * Called when the screen should update itself, e.g. continue a simulation
     * etc.
     */
    public abstract void update(float delta);

    /**
     * Called when a screen should render itself
     */
    public abstract void draw(float delta);

    public void dispose() {
    }

    public void hide() {
    }

    public void pause() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        update(delta);
        draw(delta);
    }

    public void resize(int arg0, int arg1) {
    }

    public void resume() {
    }

    public void show() {
    }

}
