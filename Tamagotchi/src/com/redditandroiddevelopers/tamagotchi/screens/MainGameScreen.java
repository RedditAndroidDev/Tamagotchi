
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
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
 * This screen instance will represent the normal game execution
 */
public class MainGameScreen extends CommonScreen implements ClickListener, AssetErrorListener {

    private static final String TAG = "Tamagotchi:MainCreatureScreen";

    AssetManager assetManager;

    private Button btnLight;
    private Button btnShower;
    private Button btnToilet;
    private Button btnFood;

    private Stage stage;
    private Group ui;
    private Group topButtons;

    @Override
    public void show() {

        // TODO document this :)
        stage = new Stage(800, 480, true, new SpriteBatch());
        ui = new Group("ui");
        topButtons = new Group("top_buttons");

        // FIXME: Change to support multiple resolutions
        Resolution resolution = new Resolution(800, 480, "");
        ResolutionFileResolver resolver = new ResolutionFileResolver(
                new InternalFileHandleResolver(), resolution);
        assetManager = TamagotchiGame.getAssetManager();
        assetManager.setErrorListener(this);
        assetManager.setLoader(Texture.class, new TextureLoader(resolver));

        assetManager.load("InGame/button.png", Texture.class);
        assetManager.finishLoading();

        final int marginBetweenButtons = 10;
        int width = assetManager.get("InGame/button.png", Texture.class).getWidth()
                + marginBetweenButtons;

        // add buttons
        btnFood = new Button(new TextureRegion(TamagotchiGame.getAssetManager().get(
                "InGame/button.png", Texture.class)));
        btnFood.x = width * 0;
        btnFood.setClickListener(this);
        topButtons.addActor(btnFood);

        btnToilet = new Button(new TextureRegion(TamagotchiGame.getAssetManager().get(
                "InGame/button.png", Texture.class)));
        btnToilet.x = width * 1;
        btnToilet.setClickListener(this);
        topButtons.addActor(btnToilet);

        btnShower = new Button(new TextureRegion(TamagotchiGame.getAssetManager().get(
                "InGame/button.png", Texture.class)));
        btnShower.x = width * 2;
        btnShower.setClickListener(this);
        topButtons.addActor(btnShower);

        btnLight = new Button(new TextureRegion(TamagotchiGame.getAssetManager().get(
                "InGame/button.png", Texture.class)));
        btnLight.x = width * 3;
        btnLight.setClickListener(this);
        topButtons.addActor(btnLight);

        // setup topButtons
        topButtons.width = width * 4; // FIXME: make this more dynamic
        topButtons.x = stage.right() - topButtons.width;
        topButtons.y = stage.top() - width;

        ui.addActor(topButtons);
        stage.addActor(ui);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta) {
        stage.draw();

    }

    @Override
    public void click(Actor actor, float x, float y) {
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
        assetManager.dispose();
    }

    @Override
    public void error(String fileName, @SuppressWarnings("rawtypes")
    Class type, Throwable throwable) {
        Gdx.app.error(TAG, "AssetManager: Cannot load asset: " + type + " " + fileName);
    }

}
