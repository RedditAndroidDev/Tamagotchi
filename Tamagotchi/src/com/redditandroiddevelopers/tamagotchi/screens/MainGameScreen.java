
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;

/**
 * This screen instance will represent the main game screen where your creature
 * will live.<br>
 * This is where the user will spend most of the time.
 */
public class MainGameScreen extends CommonScreen implements ClickListener, AssetErrorListener {

    private static final String TAG = "Tamagotchi:MainGameScreen";

    private final AssetManager assetManager = TamagotchiGame.getAssetManager();

    private Button btnLight;
    private Button btnShower;
    private Button btnToilet;
    private Button btnFood;

    //TODO make it drag down,not click
    private Button dragDown;
    private Stage stage;
    private Group ui;
    private Group topButtons;

    @Override
    public void show() {

        // add stage
        stage = new Stage(800, 480, true, new SpriteBatch());

        // TODO: add an OrthographicCamera

        // add groups for better readability and flexibility
        ui = new Group("ui");
        topButtons = new Group("top_buttons");

        assetManager.setErrorListener(this);

        // load needed textures for this screen
        assetManager.load("InGame/button.png", Texture.class);
        assetManager.load("InGame/arrow.png", Texture.class);
        // make sure all textures are loaded before continuing

        // TODO: Add a loading screen if loading takes too long
        assetManager.finishLoading();

        // prepare texture regions from the loaded textures
        // TODO: group the images into ONE Texture, and then create individual TextureRegions from that
        final Texture buttonTexture = assetManager.get(
                "InGame/button.png", Texture.class);
        final Texture arrowTexture = assetManager.get(
                "InGame/arrow.png", Texture.class);
        final TextureRegion buttonTextureRegion = new TextureRegion(buttonTexture);
        final TextureRegion arrowTextureRegion = new TextureRegion(arrowTexture);

        // set margin between buttons
        final int marginBetweenButtons = 10;

        // buttons have the same width and height. using this value allows
        // precise placement of the buttons
        final int width = buttonTexture.getWidth() + marginBetweenButtons;

        // create food button
        btnFood = new Button(new TextureRegion(assetManager.get(
                "InGame/button.png", Texture.class)));
        btnFood.x = width * 0;
        btnFood.setClickListener(this);

        // create toilet button
        btnToilet = new Button(buttonTextureRegion);
        btnToilet.x = width * 1;
        btnToilet.setClickListener(this);

        // create shower button
        btnShower = new Button(buttonTextureRegion);
        btnShower.x = width * 2;
        btnShower.setClickListener(this);

        // create light button
        btnLight = new Button(buttonTextureRegion);
        btnLight.x = width * 3;
        btnLight.setClickListener(this);

        // add buttons to 'topButtons'
        topButtons.addActor(btnFood);
        topButtons.addActor(btnToilet);
        topButtons.addActor(btnShower);
        topButtons.addActor(btnLight);

        // adjust width of 'topButtons'
        topButtons.width = width * topButtons.getActors().size();

        // position topButtons in top right corner
        topButtons.x = stage.right() - topButtons.width;
        topButtons.y = stage.top() - width;

        // add 'topButtons' to the 'ui' group
        ui.addActor(topButtons);


        dragDown = new Button(arrowTextureRegion);
        dragDown.y = stage.top() - 64;
        dragDown.setClickListener(this);
        ui.addActor(dragDown);

        // add the 'ui' to the stage
        stage.addActor(ui);

        // allow stage to receive touch input
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta) {
        // draw the stage
        stage.draw();
    }

    @Override
    public void click(Actor actor, float x, float y) {
        // touch input was received, time to find the culprit
        if (actor == btnFood) {
            Gdx.app.debug(TAG, "Touch on food button");
        } else if (actor == btnToilet) {
            Gdx.app.debug(TAG, "Touch on toilet button");
        } else if (actor == btnShower) {
            Gdx.app.debug(TAG, "Touch on shower button");
        } else if (actor == btnLight) {
            Gdx.app.debug(TAG, "Touch on light button");
        } else {
            Gdx.app.error(TAG, "Unknown actor");
            assert false;
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        // AssetManagers needs to be manually told to unload resources
        assetManager.unload("InGame/arrow.png");
        assetManager.unload("InGame/button.png");
        assetManager.setErrorListener(null);
    }

    @Override
    public void error(String fileName, @SuppressWarnings("rawtypes") Class type, Throwable throwable) {
        Gdx.app.error(TAG, "AssetManager: Cannot load asset: " + type + " " + fileName);
    }

}
