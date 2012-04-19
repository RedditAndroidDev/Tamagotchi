
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;

/**
 * A common, base class for all of our app's screen implementations. Common
 * functionality for methods should go here
 */
public abstract class CommonScreen implements Screen, AssetErrorListener {

    private static final String TAG = "Tamagotchi:CommonScreen";

    protected TamagotchiGame game;
    protected Stage stage;

    /**
     * A CommonScreen must have a reference to a TamagotchiGame. You must
     * override this constructor (make sure to call this super constructor).
     * 
     * @param game a TamagotchiGame instance
     */
    public CommonScreen(TamagotchiGame game) {
        this.game = game;
    }

    /**
     * Called when the screen should update itself, e.g. continue a simulation
     * etc.
     */
    public abstract void update(float delta);

    /**
     * Called when a screen should render itself
     */
    public abstract void draw(float delta);

    @Override
    public void dispose() {
        // this isn't really used
        assert false;
    }

    @Override
    public void hide() {
        game.assetManager.setErrorListener(null);
        game.inputMultiplexer.removeProcessor(stage);
        stage.dispose();
    }

    @Override
    public void pause() {
        stage.unfocusAll();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        update(delta);
        draw(delta);
    }

    @Override
    public void resize(int arg0, int arg1) {
    }

    @Override
    public void resume() {
        stage.unfocusAll();
    }

    @Override
    public void show() {
        stage = new Stage(game.config.stageWidth, game.config.stageHeight, true, game.spriteBatch);
        game.inputMultiplexer.addProcessor(stage);
        game.assetManager.setErrorListener(this);
    }

    @Override
    public void error(String fileName, @SuppressWarnings("rawtypes")
    Class type, Throwable throwable) {
        Gdx.app.error(TAG, "AssetManager: Cannot load asset: " + type + " " + fileName);
    }

}
