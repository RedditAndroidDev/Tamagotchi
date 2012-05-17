
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.redditandroiddevelopers.tamagotchi.TamagotchiAssets.TextureAtlasAsset;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;
import com.redditandroiddevelopers.tamagotchi.utils.FontHelper;

public class MainMenuScreen extends CommonScreen implements ClickListener {

    private static final String TAG = "Tamagotchi:MainMenuScreen";

    //Main menu buttons
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
        layout();
    }

    private void layout() {
        final TextureAtlas textureAtlas = game.assets.getAsset(TextureAtlasAsset.MAIN_MENU);

        // adding the game name
        LabelStyle labelstyle = new LabelStyle(FontHelper.createBitmapFont(
                "fonts/Roboto-Regular.ttf", 120f, stage), Color.BLACK);
        Label labelAppName = new Label("Tamagotchi", labelstyle);
        labelAppName.x = 10;
        labelAppName.y = 265;
        stage.addActor(labelAppName);

        // adding the Play button
        btnPlay = new Button(textureAtlas.findRegion("BtnPlayUnpressed"));
        btnPlay.x = 10;
        btnPlay.y = 235;
        btnPlay.setClickListener(this);
        stage.addActor(btnPlay);

        // adding the Select button
        btnSelect = new Button(textureAtlas.findRegion("BtnSelectUnpressed"));
        btnSelect.x = 10;
        btnSelect.y = 160;
        btnSelect.setClickListener(this);
        stage.addActor(btnSelect);

        // adding the Memories button
        btnMemories = new Button(textureAtlas.findRegion("BtnMemoriesUnpressed"));
        btnMemories.x = 10;
        btnMemories.y = 85;
        btnMemories.setClickListener(this);
        stage.addActor(btnMemories);

        // adding the Settings button
        btnSettings = new Button(textureAtlas.findRegion("BtnSettingsUnpressed"));
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
            game.updateState(TamagotchiGame.STATE_SELECT_PET);
        } else if (actor == btnMemories) {
            Gdx.app.debug(TAG, "Touch on Memories");
            game.updateState(TamagotchiGame.STATE_MEMORIES);
        } else if (actor == btnSettings) {
            Gdx.app.debug(TAG, "Touch on Settings");
            game.updateState(TamagotchiGame.STATE_SETTINGS);
        } else {
            Gdx.app.error(TAG, "Unknown actor");
            assert false;
        }
    }

    @Override
    public void loadResources() {
        game.assets.loadAsset(TextureAtlasAsset.MAIN_MENU);
    }

    @Override
    public void unloadResources() {
        game.assets.unloadAsset(TextureAtlasAsset.MAIN_MENU);
    }

    @Override
    protected void drawBackground() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
    }
}
