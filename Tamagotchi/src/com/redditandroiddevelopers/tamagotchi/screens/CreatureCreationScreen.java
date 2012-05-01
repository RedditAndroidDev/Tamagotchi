
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.Parallel;
import com.badlogic.gdx.scenes.scene2d.actions.RotateBy;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Scaling;
import com.redditandroiddevelopers.tamagotchi.TamagotchiAssets.TextureAtlasAsset;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;
import com.redditandroiddevelopers.tamagotchi.utils.FontHelper;

import java.util.ArrayList;

public class CreatureCreationScreen extends CommonScreen implements ClickListener {

    private static final String TAG = "Tamagotchi:CreatureCreationScreen";

    // group names
    private static final String GRP_CREATURES = "creatures";
    private static final String GRP_BACKGROUND = "background";
    private static final String GRP_TEXT = "text";

    // the x coordinates at which the creatures start moving up/down
    private float leftMark = 250;
    private float rightMark = Gdx.graphics.getWidth() - 250;

    // list of all displayed creatures
    private final ArrayList<Image> creatureList = new ArrayList<Image>();

    // GestureDetector to handle swipes
    private final GestureDetector gestureDetector = new GestureDetector(new SwipeHandler());

    // scale factor for smaller creatures
    private final float scaleFactor = 0.75f;

    // fonts
    private final String ROBOTO_REGULAR = "fonts/Roboto-Regular.ttf";

    // font styles
    private Label.LabelStyle labelStyle80;
    private Label.LabelStyle labelStyle40;

    // number of creatures to create
    final private int NUM_OF_CREATURES = 25;

    public CreatureCreationScreen(TamagotchiGame game) {
        super(game);
    }

    @Override
    protected Stage createStage(SpriteBatch batch) {
        return new Stage(game.config.stageWidth, game.config.stageHeight, false, batch);
    }

    @Override
    public void show() {
        super.show();
        initializeInput();
        initializeFonts();
        initializeLayouts();
    }

    @Override
    protected void drawBackground() {
        Gdx.gl.glClearColor(42f / 255, 42f / 255, 42f / 255, 1f);
    }

    /**
     * Adds a GestureDetector to the InputMultiplexer to listen to swipes
     */
    private void initializeInput() {
        game.inputMultiplexer.removeProcessor(stage);
        game.inputMultiplexer.addProcessor(gestureDetector);
        game.inputMultiplexer.addProcessor(stage);
    }

    /**
     * Generates fonts based on TTF files.
     */
    private void initializeFonts() {
        labelStyle80 = new Label.LabelStyle(FontHelper.createBitmapFont(
                ROBOTO_REGULAR, 80f, stage), Color.WHITE);
        labelStyle40 = new Label.LabelStyle(FontHelper.createBitmapFont(
                ROBOTO_REGULAR, 40f, stage), Color.WHITE);
    }

    /**
     * Prepares all layouts.
     */
    private void initializeLayouts() {
        initializeFirstLayout();
        initializeSecondLayout();
    }

    /**
     * Prepares the creature selection screen
     */
    private void initializeFirstLayout() {
        /* add groups for better readability and flexibility */

        // create main groups
        final Group creatureGroup = new Group(GRP_CREATURES);
        final Group backgroundGroup = new Group(GRP_BACKGROUND);
        final Group textGroup = new Group(GRP_TEXT);

        /* load textures */

        // load texture atlas
        final TextureAtlas textureAtlas = game.assets.getAsset(TextureAtlasAsset.CREATE_CREATURE);

        // get texture regions from loaded texture atlas
        final TextureRegion creatureTextureRegion = textureAtlas.findRegion("PetDefault");
        final TextureRegion spotlightTextureRegion = textureAtlas.findRegion("Spotlight");

        // create creatures

        for (int i = 0; i < NUM_OF_CREATURES; i++) {
            creatureList.add(new Image(creatureTextureRegion, Scaling.stretch, Align.CENTER,
                    "creature" + (i + 1)));
            creatureList.get(i).setClickListener(this);
            Gdx.app.log(TAG, "Creature created: " + creatureList.get(i).name);
        }

        assert creatureList.size() > 0;

        creatureList.get(0).x = camera.viewportWidth / 2 - creatureList.get(0).width / 2;
        creatureList.get(0).y = camera.viewportHeight / 2 - creatureList.get(0).height / 2;

        // adjust left and right mark

        leftMark = leftMark - creatureList.get(0).width / 2;
        rightMark = rightMark - creatureList.get(0).width / 2;

        initializeCreaturePositions();

        // create spotlight
        Image spotlight = new Image(spotlightTextureRegion, Scaling.stretch, Align.CENTER,
                "spotlight");
        spotlight.x = Gdx.graphics.getWidth() / 2 - spotlight.width / 2;
        spotlight.y = Gdx.graphics.getHeight() / 2 - spotlight.height / 2;

        // add text ("Choose a pet")
        Label labelFirstLine = new Label("Choose a pet", labelStyle80, "labelFirstLine");
        labelFirstLine.x = Gdx.graphics.getWidth() / 2 - labelFirstLine.width / 2;
        labelFirstLine.y = 35 - labelFirstLine.height;

        // add text ("Swipe either left or right to select a pet")
        Label labelSecondLine = new Label("Swipe either left or right to select a pet",
                labelStyle40, "labelSecondLine");
        labelSecondLine.x = Gdx.graphics.getWidth() / 2 - labelSecondLine.width / 2;
        labelSecondLine.y = 5 - labelSecondLine.height;

        // START TEMPORARY CODE
        Image img = new Image(textureAtlas.findRegion("PetDefault"), Scaling.stretch, Align.CENTER,
                "button");
        img.setClickListener(this);
        creatureGroup.addActor(img);
        // END TEMPORARY CODE

        /* Prepare main groups */

        // add spotlight to the 'background' group
        backgroundGroup.addActor(spotlight);

        // add creatures to the 'creature' group

        for (Image creature : creatureList) {
            creatureGroup.addActor(creature);
        }

        // add labels to the 'text' group
        textGroup.addActor(labelFirstLine);
        textGroup.addActor(labelSecondLine);

        /* Add main groups to stage */
        stage.addActor(backgroundGroup);
        stage.addActor(creatureGroup);
        stage.addActor(textGroup);
    }

    /**
     * Prepares the second layout.
     */
    private void initializeSecondLayout() {
        // TODO: initializeLayout
    }

    /**
     * Places the creatures at the correct positions.
     */
    private void initializeCreaturePositions() {
        for (Image c : creatureList) {
            int i = creatureList.indexOf(c);
            if (i == 0) { // one creature
                c.x = camera.viewportWidth / 2 - c.width / 2;
                c.y = camera.viewportHeight / 2 - c.height / 2;
            }
            else if (i == 1) { // two creatures
                c.x = camera.viewportWidth - (3f / 4) * c.width * c.scaleX;
                c.y = getYPositionBasedOnXValue(c.x);
                // c.scaleX = c.scaleY = scaleFactor;
            }
            else { // more than two creatures
                c.x = creatureList.get(i - 1).x + (creatureList.get(1).x - creatureList.get(0).x);
                c.y = getYPositionBasedOnXValue(c.x);
                // c.scaleX = c.scaleY = scaleFactor;
            }
            Gdx.app.debug(TAG, "Creature moved to: X: " + c.x + " Y: " + c.y);
        }
    }

    private void startTransition1() {
        // START TEMPORARY CODE
        Parallel parallel = Parallel.$(
                MoveTo.$(200, 200, 0.5f),
                RotateBy.$(360f, 1f)
                );
        stage.findActor("creature1").action(parallel);
        // END TEMPORARY CODE

        // fade out text in 0.5 seconds
        stage.findActor("labelFirstLine").action(FadeOut.$(0.5f));
        stage.findActor("labelSecondLine").action(FadeOut.$(0.5f));
    }

    /**
     * Finds correct Y value for corresponding X value to position creatures
     * 
     * @param x the X coordinate of the creature that should be positioned
     * @return int y
     */
    private float getYPositionBasedOnXValue(float x) {
        // the slope (how fast the creatures will move upwards)
        float m = 0.8f;

        // creature is left of marked area
        if (x <= leftMark) {
            int leftOfLeftMark = (int) (leftMark - x);
            return getYPositionBasedOnXValue(leftMark + 1) + m * leftOfLeftMark;
        }
        // creature is inside of marked area
        else if (x > leftMark && x < rightMark) {
            return Gdx.graphics.getHeight() / 2 - creatureList.get(0).height / 2;
        }
        // creature is right of marked area
        else if (x >= rightMark) {
            int rightOfRightMark = (int) (x - rightMark);
            return getYPositionBasedOnXValue(leftMark + 1) + m * rightOfRightMark;
        }
        // something went wrong, this code shouldn't be reachable
        assert false;
        return 0;
    }

    @Override
    public void loadResources() {
        game.assets.loadAsset(TextureAtlasAsset.CREATE_CREATURE);
    }

    @Override
    public void unloadResources() {
        creatureList.clear();
        game.assets.unloadAsset(TextureAtlasAsset.CREATE_CREATURE);
        game.inputMultiplexer.removeProcessor(gestureDetector);
    }

    @Override
    public void click(Actor actor, float x, float y) {
        if (creatureList.contains(actor)) {
            Gdx.app.debug(TAG, "Hit on " + actor.name + " detected");
        }
        else {
            startTransition1();
        }
    }

    /**
     * Basic GestureListener that handles the swiping motion.
     */
    class SwipeHandler implements GestureListener {

        @Override
        public boolean touchDown(int x, int y, int pointer) {
            return false;
        }

        @Override
        public boolean tap(int x, int y, int count) {
            return false;
        }

        @Override
        public boolean longPress(int x, int y) {
            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY) {
            return false;
        }

        @Override
        public boolean pan(int x, int y, int deltaX, int deltaY) {
            if (deltaX == 0) {
                return true;
            }
            for (Image c : creatureList) {
                c.x += deltaX;
                c.y = getYPositionBasedOnXValue(c.x);
                // Gdx.app.log(TAG, "Creature " + c.name + " placed at X: " +
                // c.x + " Y: " + c.y);
            }
            return true;
        }

        @Override
        public boolean zoom(float originalDistance, float currentDistance) {
            return false;
        }

        @Override
        public boolean pinch(Vector2 initialFirstPointer, Vector2 initialSecondPointer,
                Vector2 firstPointer, Vector2 secondPointer) {
            return false;
        }

    }
}
