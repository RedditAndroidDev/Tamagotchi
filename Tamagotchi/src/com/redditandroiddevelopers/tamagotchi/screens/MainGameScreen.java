
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.redditandroiddevelopers.tamagotchi.TamagotchiAssets.TextureAsset;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;
import com.redditandroiddevelopers.tamagotchi.ui.DragListener;
import com.redditandroiddevelopers.tamagotchi.ui.DraggableImage;

/**
 * This screen instance will represent the main game screen where your creature
 * will live.<br>
 * This is where the user will spend most of the time.
 */
public class MainGameScreen extends CommonScreen implements ClickListener, DragListener {

    private static final String TAG = "Tamagotchi:MainGameScreen";

    private Button btnLight;
    private Button btnShower;
    private Button btnToilet;
    private Button btnFood;
    private DraggableImage btnDragDown;

    public MainGameScreen(TamagotchiGame game) {
        super(game);
    }

    @Override
    protected final Stage createStage(SpriteBatch batch) {
        return new Stage(game.config.stageWidth, game.config.stageHeight, false, batch);
    }

    @Override
    public final void show() {
        super.show();

        // add groups for better readability and flexibility
        final Group ui = new Group("ui");
        final Group topButtons = new Group("top_buttons");

        // prepare texture regions from the loaded textures
        // TODO: group the images into ONE Texture, and then create individual
        // TextureRegions from that
        final Texture buttonTexture = game.assets.getTexture(TextureAsset.BTN_STATUS);
        final Texture arrowTexture = game.assets.getTexture(TextureAsset.DRG_ARROW);
        final TextureRegion buttonTextureRegion = new TextureRegion(buttonTexture);
        final TextureRegion arrowTextureRegion = new TextureRegion(arrowTexture);

        // set margin between buttons
        final int marginBetweenButtons = 10;

        // buttons have the same width and height. using this value allows
        // precise placement of the buttons
        final int width = buttonTexture.getWidth() + marginBetweenButtons;

        // create food button
        btnFood = new Button(buttonTextureRegion);
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
        btnDragDown = new DraggableImage(arrowTextureRegion);
        btnDragDown.y = stage.top() - 64;
        btnDragDown.setClickListener(this);
        btnDragDown.setDragListener(this);
        ui.addActor(btnDragDown);

        // add the 'ui' to the stage
        stage.addActor(ui);
    }

    @Override
    public final void click(Actor actor, float x, float y) {
        // touch input was received, time to find the culprit
        if (actor == btnFood) {
            Gdx.app.debug(TAG, "Touch on food button");
            camera.rotate(1, 0, 0, 1);
        } else if (actor == btnToilet) {
            Gdx.app.debug(TAG, "Touch on toilet button");
            camera.rotate(-1, 0, 0, 1);
        } else if (actor == btnShower) {
            Gdx.app.debug(TAG, "Touch on shower button");
            camera.zoom += 0.02;
        } else if (actor == btnLight) {
            Gdx.app.debug(TAG, "Touch on light button");
            camera.zoom -= 0.02;
        } else if (actor == btnDragDown) {
            Gdx.app.debug(TAG, "Touch on arrow");
            // do nothing, handle in drag()
        } else {
            Gdx.app.error(TAG, "Unknown actor");
            assert false;
        }
    }

    @Override
    public void drag(Actor a, float x, float y, int pointer) {
        camera.translate(0, -y, 0);
    }

}
