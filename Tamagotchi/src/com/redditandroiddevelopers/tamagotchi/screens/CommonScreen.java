
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    protected OrthographicCamera camera;

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
     * etc. By default, the {@link Camera} object of the associated
     * {@link Stage} of this {@code Screen} is updated. Override this method for
     * a game state update (make it brief!) but don't forget to call
     * {@code super.update(delta)}.
     */
    public void update(float delta) {
        camera.update();
    }

    /**
     * Called when a screen should render itself. By default, the associated
     * {@link Stage} of this {@code Screen} is drawn. If necessary, override
     * this method for a more involved {@code draw()} implementation (make it
     * brief!) but don't forget to call {@code super.draw()}.
     */
    public void draw() {
        if (!Gdx.graphics.isGL20Available())
            camera.apply(Gdx.gl10);
        stage.draw();
    }

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
        draw();
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
        final float width = game.config.stageWidth;
        final float height = game.config.stageHeight;
        final boolean stretch = true;

        stage = new Stage(width, height, stretch, game.spriteBatch);
        game.inputMultiplexer.addProcessor(stage);
        game.assetManager.setErrorListener(this);

        // set up an ortographic camera for the stage
        camera = new OrthographicCamera();
        stage.setCamera(camera);
        stage.setViewport(width, height, stretch);
    }

    @Override
    public void error(String fileName, @SuppressWarnings("rawtypes")
    Class type, Throwable throwable) {
        Gdx.app.error(TAG, "AssetManager: Cannot load asset: " + type + " " + fileName);
    }

}
