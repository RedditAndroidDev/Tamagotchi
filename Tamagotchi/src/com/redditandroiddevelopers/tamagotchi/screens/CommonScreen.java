package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Screen;


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
