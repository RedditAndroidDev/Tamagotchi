
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.redditandroiddevelopers.tamagotchi.TamagotchiAssets.TextureAsset;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;

public class MainMenuScreen extends CommonScreen implements ClickListener {

    private static final String TAG = "Tamagotchi:MainMenuScreen";

    private Button btnPlay;
    private Button btnSelect;
    private Button btnMemories;
    private Button btnSettings;

    public MainMenuScreen(TamagotchiGame game) {
        super(game);
    }

    @Override
    protected final Stage createStage(SpriteBatch batch) {
        return new Stage(game.config.stageWidth, game.config.stageHeight, false, batch);
    }

    @Override
    public final void show() {
        super.show();

        // adding the game name
        Image imgAppName = new Image(game.assets.getTexture(TextureAsset.APP_NAME));
        imgAppName.x = 10;
        imgAppName.y = 325;
        stage.addActor(imgAppName);

        // TODO: Set up a TextureRegion that encompasses all UI elements
        // involved here

        // adding the Play button
        btnPlay = new Button(new TextureRegion(
                game.assets.getTexture(TextureAsset.BTN_PLAY_UNPRESSED)));
        btnPlay.x = 10;
        btnPlay.y = 235;
        btnPlay.setClickListener(this);
        stage.addActor(btnPlay);

        // adding the Select button
        btnSelect = new Button(new TextureRegion(
                game.assets.getTexture(TextureAsset.BTN_SELECT_UNPRESSED)));
        btnSelect.x = 10;
        btnSelect.y = 160;
        btnSelect.setClickListener(this);
        stage.addActor(btnSelect);

        // adding the Memories button
        btnMemories = new Button(new TextureRegion(
                game.assets.getTexture(TextureAsset.BTN_MEMORIES_UNPRESSED)));
        btnMemories.x = 10;
        btnMemories.y = 85;
        btnMemories.setClickListener(this);
        stage.addActor(btnMemories);

        // adding the Settings button
        btnSettings = new Button(new TextureRegion(
                game.assets.getTexture(TextureAsset.BTN_SETTINGS_UNPRESSED)));
        btnSettings.x = 10;
        btnSettings.y = 10;
        btnSettings.setClickListener(this);
        stage.addActor(btnSettings);
    }

    @Override
    public final void click(Actor actor, float x, float y) {
        if (actor == btnPlay) {
            Gdx.app.debug(TAG, "Touch on Play");
            game.updateState(TamagotchiGame.STATE_MAIN_GAME);
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

}
