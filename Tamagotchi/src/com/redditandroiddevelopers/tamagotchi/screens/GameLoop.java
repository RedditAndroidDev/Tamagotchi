
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
public class GameLoop extends CommonScreen implements ClickListener, AssetErrorListener {

    private static final String TAG = "Tamagotchi:MainCreatureScreen";

    AssetManager assetManager;

    private Button btnLight;
    private Button btnShower;
    private Button btnToilet;
    private Button btnFood;

    private Stage stage;
    private Group ui;

    @Override
    public void show() {

        // FIXME: Touch input area of buttons is too big

        // TODO document this :)
        stage = new Stage(800, 480, true, new SpriteBatch());
        ui = new Group("ui");

        // FIXME: Change to support multiple resolutions
        Resolution resolution = new Resolution(800, 480, "");
        ResolutionFileResolver resolver = new ResolutionFileResolver(
                new InternalFileHandleResolver(), resolution);
        assetManager = TamagotchiGame.getAssetManager();
        assetManager.setErrorListener(this);
        assetManager.setLoader(Texture.class, new TextureLoader(resolver));

        assetManager.load("InGame/button.png", Texture.class);
        assetManager.finishLoading();

        int width = assetManager.get("InGame/button.png", Texture.class).getWidth();

        btnLight = new Button(new TextureRegion(TamagotchiGame.getAssetManager().get(
                "InGame/button.png", Texture.class)));
        btnLight.x = stage.right() - width;
        btnLight.y = stage.top() - width;
        btnLight.setClickListener(this);
        ui.addActor(btnLight);

        btnShower = new Button(new TextureRegion(TamagotchiGame.getAssetManager().get(
                "InGame/button.png", Texture.class)));
        btnShower.x = stage.right() - (width * 2);
        btnShower.y = stage.top() - width;
        btnShower.setClickListener(this);
        ui.addActor(btnShower);

        btnToilet = new Button(new TextureRegion(TamagotchiGame.getAssetManager().get(
                "InGame/button.png", Texture.class)));
        btnToilet.x = stage.right() - (width * 3);
        btnToilet.y = stage.top() - width;
        btnToilet.setClickListener(this);
        ui.addActor(btnToilet);

        btnFood = new Button(new TextureRegion(TamagotchiGame.getAssetManager().get(
                "InGame/button.png", Texture.class)));
        btnFood.x = stage.right() - (width * 4);
        btnFood.y = stage.top() - width;
        btnFood.setClickListener(this);

        ui.addActor(btnFood);

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
    public void error(String fileName, @SuppressWarnings("rawtypes")
    Class type, Throwable throwable) {
        Gdx.app.error(TAG, "AssetManager: Cannot load asset: " + type + " " + fileName);
    }

}
