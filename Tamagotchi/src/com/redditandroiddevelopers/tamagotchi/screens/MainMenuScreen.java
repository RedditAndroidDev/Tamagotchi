
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;

public class MainMenuScreen extends CommonScreen implements ClickListener {

    private static final String TAG = "Tamagotchi:MainMenuScreen";

    private Stage stage;
    private Button btnPlay;
    private Button btnSelect;
    private Button btnMemories;
    private Button btnSettings;


    @Override
    public void show() {

        // create new stage
        stage = new Stage(800, 480, true, new SpriteBatch());

        // adding the game name
        Image imgAppName = new Image(new Texture(Gdx.files.internal("MainMenu/AppName.png")));
        imgAppName.x = 10;
        imgAppName.y = 325;
        stage.addActor(imgAppName);

        // TODO: Set up a TextureRegion that encompasses all UI elements
        // involved here

        // adding the Play button
        btnPlay = new Button(new TextureRegion(new Texture(Gdx.files.internal("MainMenu/btn_play_unpressed.png"))));
        btnPlay.x = 10;
        btnPlay.y = 235;
        btnPlay.setClickListener(this);
        stage.addActor(btnPlay);

        // adding the Select button
        btnSelect = new Button(new TextureRegion(new Texture(Gdx.files.internal("MainMenu/btn_select_unpressed.png"))));
        btnSelect.x = 10;
        btnSelect.y = 160;
        btnSelect.setClickListener(this);
        stage.addActor(btnSelect);

        // adding the Memories button
        btnMemories = new Button(new TextureRegion(new Texture(
                Gdx.files.internal("MainMenu/btn_memories_unpressed.png"))));
        btnMemories.x = 10;
        btnMemories.y = 85;
        btnMemories.setClickListener(this);
        stage.addActor(btnMemories);

        // adding the Settings button
        btnSettings = new Button(new TextureRegion(new Texture(
                Gdx.files.internal("MainMenu/btn_settings_unpressed.png"))));
        btnSettings.x = 10;
        btnSettings.y = 10;
        btnSettings.setClickListener(this);
        stage.addActor(btnSettings);

        // ready the input processor to receive input events for the stage
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void draw(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void click(Actor actor, float x, float y) {
        if (actor == btnPlay) {
            Gdx.app.debug(TAG, "Touch on Play");
            TamagotchiGame.updateState(TamagotchiGame.STATE_MAIN_GAME);
        } else if (actor == btnSelect) {
            Gdx.app.debug(TAG, "Touch on Select");
        } else if (actor == btnMemories) {
            Gdx.app.debug(TAG, "Touch on Memories");
        } else if (actor == btnSettings) {
            Gdx.app.debug(TAG, "Touch on Settings");
        } else {
            Gdx.app.error(TAG, "Unknown actor");
            assert false;
        }
    }

    @Override
    public void update(float delta) {
        // TODO: what to do here?
    }
}
