
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.redditandroiddevelopers.tamagotchi.TamagotchiAssets.FontAsset;
import com.redditandroiddevelopers.tamagotchi.TamagotchiAssets.TextureAtlasAsset;
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
    public static final String ID = "main-game";

    private static final String GRP_UI = "ui";
    private static final String GRP_TOP_BUTTONS = "top_buttons";
    private static final String GRP_STATUS_PANEL = "status_panel";
    private static final String GRP_CREATURE = "creature";
    private static final String GRP_BACKGROUND = "background";

    private static final int FOOD = 0;
    private static final int TOILET = 1;
    private static final int SHOWER = 2;
    private static final int LIGHT = 3;
    private static final int NUM_BUTTONS = 4;

    private Button[] buttons;
    private DraggableImage btnDragDown;
    private Label fpsLabel;

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
        layout();
    }

    private void layout() {
        // add groups for better readability and flexibility
        final Group ui = new Group(GRP_UI);
        final Group topButtons = new Group(GRP_TOP_BUTTONS);
        final Group statusPanel = new Group(GRP_STATUS_PANEL);
        final Group creature = new Group(GRP_CREATURE);
        final Group background = new Group(GRP_BACKGROUND);

        // prepare texture regions from a loaded texture atlas
        final TextureAtlas textureAtlas = game.assets.getAsset(TextureAtlasAsset.MAIN_GAME);
        final TextureRegion arrowTextureRegion = textureAtlas.findRegion("LeftSwipeArrow");
        final String[] interactButtonIDs = new String[] {
                "MainButtonFood",
                "MainButtonToilet",
                "MainButtonShower",
                "MainButtonSleepOff"
        };
        final TextureRegion[] interactButtonTextureRegions = new TextureRegion[interactButtonIDs.length];
        for (int i = 0; i < interactButtonIDs.length; i++) {
            interactButtonTextureRegions[i] = textureAtlas.findRegion(interactButtonIDs[i]);
        }

        final int marginBetweenButtons = 10;
        // buttons have the same width and height. using this value allows
        // precise placement of the buttons
        final int width = interactButtonTextureRegions[0].getRegionWidth() + marginBetweenButtons;
        buttons = new Button[NUM_BUTTONS];
        for (int i = 0; i < NUM_BUTTONS; i++) {
            final Button button = new Button(interactButtonTextureRegions[i]);
            button.x = width * i;
            button.setClickListener(this);
            topButtons.addActor(button);
            buttons[i] = button;
        }

        // adjust width of 'topButtons'
        topButtons.width = width * topButtons.getActors().size();
        // position topButtons in top right corner
        topButtons.x = stage.right() - topButtons.width;
        topButtons.y = stage.top() - width;

        // add status panel
        btnDragDown = new DraggableImage(arrowTextureRegion);
        btnDragDown.y = stage.top() - arrowTextureRegion.getRegionHeight();
        btnDragDown.setClickListener(this);
        btnDragDown.setDragListener(this);
        statusPanel.addActor(btnDragDown);

        // add an FPS label (subject to configuration)
        if (game.config.logFps) {
            final Label.LabelStyle labelStyle = new Label.LabelStyle(
                    game.assets.getAsset(FontAsset.DEFAULT),
                    Color.RED);
            fpsLabel = new Label("FPS: " + Gdx.graphics.getFramesPerSecond(), labelStyle);
            background.addActor(fpsLabel);
        }

        // TODO: Add basic status mockup that can be pulled down
        // TODO: Position statusPanel in top left corner

        // TODO: Add creature
        // TODO: Add background
        // TODO: Add layers for background and the main stage
        // TODO: Add overlays, e.g. for speech bubbles

        // add various groups to the 'ui' group
        ui.addActor(background);
        ui.addActor(creature);
        ui.addActor(statusPanel);
        ui.addActor(topButtons);

        // add the 'ui' group to the stage
        stage.addActor(ui);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (fpsLabel != null) {
            assert game.config.logFps;
            fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        }
    }

    @Override
    public final void click(Actor actor, float x, float y) {
        // touch input was received, time to find the culprit
        if (actor == buttons[FOOD]) {
            Gdx.app.debug(TAG, "Touch on food button");
            camera.rotate(1, 0, 0, 1);
        } else if (actor == buttons[TOILET]) {
            Gdx.app.debug(TAG, "Touch on toilet button");
            camera.rotate(-1, 0, 0, 1);
        } else if (actor == buttons[SHOWER]) {
            Gdx.app.debug(TAG, "Touch on shower button");
            camera.zoom += 0.02;
        } else if (actor == buttons[LIGHT]) {
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
        if (a == btnDragDown) {
            /*
             * TODO: detect precise touch point and use it when moving the group
             * Currently, it snaps to the bottom line of the texture when
             * dragging starts. In order to make it look better, we need to
             * apply an offset for the correct touch point.
             */
            stage.findActor(GRP_STATUS_PANEL).y += y;
        }
    }

    @Override
    public void loadResources() {
        game.assets.loadAsset(TextureAtlasAsset.MAIN_GAME);
    }

    @Override
    public void unloadResources() {
        game.assets.unloadAsset(TextureAtlasAsset.MAIN_GAME);
    }

}
