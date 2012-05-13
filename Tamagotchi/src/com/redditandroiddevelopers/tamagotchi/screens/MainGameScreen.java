
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.redditandroiddevelopers.tamagotchi.TamagotchiAssets.TextureAtlasAsset;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;
import com.redditandroiddevelopers.tamagotchi.creatures.Creature1;
import com.redditandroiddevelopers.tamagotchi.ui.DragListener;
import com.redditandroiddevelopers.tamagotchi.ui.DraggableImage;
import com.redditandroiddevelopers.tamagotchi.utils.FontHelper;

/**
 * This screen instance will represent the main game screen where your creature
 * will live.<br>
 * This is where the user will spend most of the time.
 */
public class MainGameScreen extends CommonScreen implements ClickListener, DragListener {

    private static final String TAG = "Tamagotchi:MainGameScreen";

    private static final String GRP_BACKGROUND = "background";
    private static final String GRP_FOREGROUND = "foreground";
    private static final String GRP_OVERLAY = "overlay";

    private static final String GRP_BACKGROUND_DISTANT = "background_distant";
    private static final String GRP_BACKGROUND_NEAR = "background_near";
    private static final String GRP_UI = "ui";
    private static final String GRP_TOP_BUTTONS = "top_buttons";
    private static final String GRP_STATUS_PANEL = "status_panel";

    private static final int FOOD = 0;
    private static final int TOILET = 1;
    private static final int SHOWER = 2;
    private static final int LIGHT = 3;
    private static final int NUM_BUTTONS = 4;

    private Button[] buttons;
    private DraggableImage btnDrag;
    private Label fpsLabel;
    private Creature1 creature;

    /**
     * Creates a new instance of the MainGameScreen.
     * 
     * @param game Needs a TamagotchiGame instance.
     */
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

    /**
     * Creates the basic layout of the screen.
     */
    private void layout() {

        /*
         * TODO: Add basic status mockup that can be pulled down TODO: Add
         * creature TODO: Add background TODO: Add layers for background and the
         * main stage TODO: Add overlays, e.g. for speech bubbles
         */

        /* add groups for better readability and flexibility */

        // create main groups
        final Group backgroundGroup = new Group(GRP_BACKGROUND);
        final Group foregroundGroup = new Group(GRP_FOREGROUND);
        final Group overlayGroup = new Group(GRP_OVERLAY);

        // create sub groups
        final Group bgDistantGroup = new Group(GRP_BACKGROUND_DISTANT);
        final Group bgNearGroup = new Group(GRP_BACKGROUND_NEAR);
        final Group uiGroup = new Group(GRP_UI);
        final Group topButtonsGroup = new Group(GRP_TOP_BUTTONS);
        final Group statusPanelGroup = new Group(GRP_STATUS_PANEL);

        /* load textures */

        // load texture atlas
        final TextureAtlas textureAtlas = game.assets.getAsset(TextureAtlasAsset.MAIN_GAME);

        // get texture regions from loaded texture atlas
        final TextureRegion planetsBackgroundTextureRegion = textureAtlas
                .findRegion("PlanetsBackground");
        final TextureRegion hillsMidgroundTextureRegion = textureAtlas.findRegion("HillsMidground");
        final TextureRegion hillsForegroundTextureRegion = textureAtlas
                .findRegion("HillsForeground");
        final TextureRegion groundTextureRegion = textureAtlas.findRegion("StaticGround");
        final TextureRegion creatureDefaultTextureRegion = textureAtlas.findRegion("PetDefault");
        final TextureRegion swipeArrowTextureRegion = textureAtlas.findRegion("LeftSwipeArrow");

        /* prepare layout */

        // add background
        Image bgPlanetsBackground = new Image(planetsBackgroundTextureRegion);
        Image bgHillsMidground = new Image(hillsMidgroundTextureRegion);
        Image bgHillsForeground = new Image(hillsForegroundTextureRegion);

        Image bgGround = new Image(groundTextureRegion);

        // add creature
        creature = new Creature1(creatureDefaultTextureRegion, "creature");
        creature.setClickListener(this);
        creature.x = 400;
        creature.y = 50;

        // create buttons names
        final String[] interactButtonIDs = new String[] {
                "MainButtonFood",
                "MainButtonToilet",
                "MainButtonShower",
                "MainButtonSleepOff"
        };

        // load texture regions for buttons in top right corner
        final TextureRegion[] interactButtonTextureRegions = new TextureRegion[interactButtonIDs.length];
        for (int i = 0; i < interactButtonIDs.length; i++) {
            interactButtonTextureRegions[i] = textureAtlas.findRegion(interactButtonIDs[i]);
        }

        // set margin between buttons
        final int marginBetweenButtons = 10;

        // position buttons within group and add them to the 'topButtonsGroup'
        final int width = interactButtonTextureRegions[0].getRegionWidth() + marginBetweenButtons;
        buttons = new Button[NUM_BUTTONS];
        for (int i = 0; i < NUM_BUTTONS; i++) {
            final Button button = new Button(interactButtonTextureRegions[i]);
            button.x = width * i;
            button.setClickListener(this);
            topButtonsGroup.addActor(button);
            buttons[i] = button;
        }

        // adjust width of 'topButtons' group
        topButtonsGroup.width = width * topButtonsGroup.getActors().size();
        // position topButtons in top right corner
        topButtonsGroup.x = stage.right() - topButtonsGroup.width;
        topButtonsGroup.y = stage.top() - width;

        // add drag down status panel button
        btnDrag = new DraggableImage(swipeArrowTextureRegion);
        btnDrag.setClickListener(this);
        btnDrag.setDragListener(this);
        statusPanelGroup.addActor(btnDrag);

        // add an FPS label (subject to configuration)
        if (game.config.logFps) {
            final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.:;,{}\"�`'<>";
            BitmapFont font = TrueTypeFontFactory.createBitmapFont(
                    Gdx.files.internal("fonts/Roboto-Regular.ttf"),
                    FONT_CHARACTERS, stage.width(), stage.height(), 25.0f, Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight());
            final Label.LabelStyle labelStyle = new Label.LabelStyle(
                    font,
                    Color.RED);
            fpsLabel = new Label("FPS: " + Gdx.graphics.getFramesPerSecond(), labelStyle);
            fpsLabel.y = -15;
        }

        /* Prepare sub groups */

        // add sub groups to the 'ui' group
        uiGroup.addActor(statusPanelGroup);
        uiGroup.addActor(topButtonsGroup);

        // sub groups to the 'background' group
        bgDistantGroup.addActor(bgPlanetsBackground);
        bgDistantGroup.addActor(bgHillsMidground);
        bgDistantGroup.addActor(bgHillsForeground);

        bgNearGroup.y = -50;
        bgDistantGroup.y = bgNearGroup.y + bgGround.height;

        bgNearGroup.addActor(bgGround);

        /* Prepare main groups */

        // add groups to 'background'
        backgroundGroup.addActor(bgDistantGroup);
        backgroundGroup.addActor(bgNearGroup);

        // add groups to 'foreground'

        // add creature to main group
        foregroundGroup.addActor(creature);

        // add groups to 'overlay'
        overlayGroup.addActor(uiGroup);
        // add an FPS label (subject to configuration)
        if (game.config.logFps) {
            final BitmapFont font = FontHelper.createBitmapFont(FontHelper.TTF_ROBOTO_REGULAR,
                    25f, stage);
            final Label.LabelStyle labelStyle = new Label.LabelStyle(
                    font,
                    Color.RED);
            fpsLabel = new Label("FPS: " + Gdx.graphics.getFramesPerSecond(), labelStyle);
            fpsLabel.y = -15;
            overlayGroup.addActor(fpsLabel);
        }

        /* Add main groups to stage */
        stage.addActor(backgroundGroup);
        stage.addActor(foregroundGroup);
        stage.addActor(overlayGroup);
    }

    @Override
    public void update(float delta) {
        creature.lifeCycle();
        if (fpsLabel != null) {
            assert game.config.logFps;
            fpsLabel.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        }
        super.update(delta);
    }

    @Override
    public final void click(Actor actor, float x, float y) {
        // touch input was received, time to find the culprit
        if (actor == buttons[FOOD]) {
            Gdx.app.debug(TAG, "Touch on food button");
            creature.moveBy(-100f, 1f);
        } else if (actor == buttons[TOILET]) {
            Gdx.app.debug(TAG, "Touch on toilet button");
            creature.moveBy(100f, 1f);
        } else if (actor == buttons[SHOWER]) {
            Gdx.app.debug(TAG, "Touch on shower button");
            creature.roll(-200, 1f);
        } else if (actor == buttons[LIGHT]) {
            Gdx.app.debug(TAG, "Touch on light button");
            creature.roll(200, 1f);
        } else if (actor == creature) {
            creature.jump();
        } else if (actor == btnDrag) {
            Gdx.app.debug(TAG, "Touch on arrow");
            // do nothing, handle in drag()
        } else {
            Gdx.app.error(TAG, "Unknown actor");
            assert false;
        }
    }

    @Override
    public void drag(Actor a, float x, float y, int pointer) {
        if (a == btnDrag) {
            /*
             * TODO: detect precise touch point and use it when moving the group
             * Currently, it snaps to the bottom line of the texture when
             * dragging starts. In order to make it look better, we need to
             * apply an offset for the correct touch point.
             */
            stage.findActor(GRP_STATUS_PANEL).x += x;
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
