
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;

/**
 * A common, base class for all of our app's screen implementations. Common
 * functionality for methods should go here
 */
public abstract class CommonScreen implements Screen, AssetErrorListener {

    private static final String TAG = "Tamagotchi:CommonScreen";

    protected TamagotchiGame game;
    protected SpriteBatch batch;
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
     * {@code super.update()}. If you are manipulating the {@link Camera} in any
     * way, do so <em>prior</em> to calling {@code super.update()}.
     */
    public void update(float delta) {
        camera.update();
        if (!Gdx.graphics.isGL20Available())
            camera.apply(Gdx.gl10);
        else {
            assert false; // not supporting GL20 yet
            throw new UnsupportedOperationException("OpenGL ES 2.0");
        }
    }

    /**
     * Called when a screen should render itself. By default, the associated
     * {@link Stage} of this {@code Screen} is drawn. If necessary, override
     * this method for a more involved {@code draw()} implementation (make it
     * brief!) but don't forget to call {@code super.draw()} first.
     */
    public void draw() {
        stage.draw();
    }

    @Override
    public final void dispose() {
        // this isn't really used
        assert false;
    }

    @Override
    public void hide() {
        // TODO: clear assets
        game.assetManager.setErrorListener(null);
        game.inputMultiplexer.removeProcessor(stage);
        stage.dispose();
    }

    @Override
    public void pause() {
        stage.unfocusAll();
    }

    @Override
    public final void render(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        final Color bgColor = game.config.backgroundColor;
        Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, 1);
        update(delta);
        draw();
    }

    @Override
    public final void resize(int arg0, int arg1) {
        // not supporting for now
        assert false;
    }

    @Override
    public void resume() {
        stage.unfocusAll();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = createStage(batch);
        camera = (OrthographicCamera) stage.getCamera();
        game.inputMultiplexer.addProcessor(stage);
        game.assetManager.setErrorListener(this);
        // TODO: reload assets (after clearing them in hide())
    }

    /**
     * Create a {@link Stage} for this screen, preferrably using the supplied
     * {@link SpriteBatch} object.
     * 
     * @param batch the {@link SpriteBatch} object to use
     * @return a {@link Stage} object
     */
    protected abstract Stage createStage(SpriteBatch batch);

    @Override
    public void error(String fileName, @SuppressWarnings("rawtypes")
    Class type, Throwable throwable) {
        Gdx.app.error(TAG, "AssetManager: Cannot load asset: " + type + " " + fileName);
    }

}
