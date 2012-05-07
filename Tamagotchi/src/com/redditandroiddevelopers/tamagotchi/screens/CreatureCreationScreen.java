
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.FadeTo;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Scaling;
import com.redditandroiddevelopers.tamagotchi.TamagotchiAssets.TextureAtlasAsset;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;
import com.redditandroiddevelopers.tamagotchi.utils.FontHelper;
import com.redditandroiddevelopers.tamagotchi.utils.TextUtils;

import java.util.ArrayList;

/**
 * This screen will allow the user to switch between multiple creatures and
 * create a new one.
 */
public class CreatureCreationScreen extends CommonScreen implements ClickListener {

    private static final String TAG = "Tamagotchi:CreatureCreationScreen";

    // current state
    private enum state {
        SCREEN1,
        SCREEN2,
        SCREEN3;
    }

    private state currentState;

    // group names
    private static final String GRP_CREATURES = "creatures";
    private static final String GRP_BACKGROUND = "background";
    private static final String GRP_TEXT = "text";

    // fonts
    private static final String ROBOTO_REGULAR = "fonts/Roboto-Regular.ttf";

    // number of creatures to create
    private static final int NUM_OF_CREATURES = 3;

    private final ArrayList<Image> creatureList;
    private final GestureDetector gestureDetector;

    private float leftMark, rightMark;
    private float scaleFactor;

    // font styles
    private LabelStyle labelStyle80;
    private LabelStyle labelStyle40;
    private LabelStyle labelStyle20;

    private static final float DEFAULT_FADE_OUT_TIME = 0.5f;

    /**
     * Creates a new instance of the CreatureCreationScreen.
     * 
     * @param game
     */
    public CreatureCreationScreen(TamagotchiGame game) {
        super(game);
        creatureList = new ArrayList<Image>();
        gestureDetector = new GestureDetector(new SwipeHandler());
    }

    @Override
    protected Stage createStage(SpriteBatch batch) {
        return new Stage(game.config.stageWidth, game.config.stageHeight, false, batch);
    }

    @Override
    public void show() {
        super.show();
        final float padding = 250;
        leftMark = padding;
        rightMark = Gdx.graphics.getWidth() - padding;
        scaleFactor = 0.75f;
        currentState = state.SCREEN1;
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
        labelStyle20 = new Label.LabelStyle(FontHelper.createBitmapFont(
                ROBOTO_REGULAR, 20f, stage), Color.WHITE);
    }

    /**
     * Prepares all layouts.
     */
    private void initializeLayouts() {
        initializeFirstLayout();
        initializeSecondLayout(false);
        initializeThirdLayout(false);
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
            creatureList.get(i).scaleX = creatureList.get(i).scaleY = scaleFactor;
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
        spotlight.y = Gdx.graphics.getHeight() - spotlight.height;

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
        img.scaleX = 0.5f;
        img.scaleY = 0.5f;
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
     * Places the creatures at the correct positions.
     */
    private void initializeCreaturePositions() {
        for (Image creature : creatureList) {
            int i = creatureList.indexOf(creature);
            if (i == 0) { // one creature
                creature.x = Gdx.graphics.getWidth() / 2 - (creature.width * creature.scaleX) / 2;
                creature.y = Gdx.graphics.getHeight() / 2 - (creature.height * creature.scaleY) / 2;
            }
            else if (i == 1) { // two creatures
                creature.x = camera.viewportWidth - (3f / 4) * creature.width * creature.scaleX;
                creature.y = getYPositionBasedOnXValue(creature.x);
            }
            else { // more than two creatures
                creature.x = creatureList.get(i - 1).x
                        + (creatureList.get(1).x - creatureList.get(0).x);
                creature.y = getYPositionBasedOnXValue(creature.x);
            }
            creature.scaleX = creature.scaleY = scaleFactor;
            Gdx.app.debug(TAG, "Creature moved to: X: " + creature.x + " Y: " + creature.y);
        }
    }

    /**
     * Prepares the second layout.
     */
    private void initializeSecondLayout(boolean visible) {
        String text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        Label summary = new Label(TextUtils.insertPeriodically(text, "\n", 30), labelStyle20,
                "summary");
        summary.x = 50;
        summary.y = Gdx.graphics.getHeight() - 100 - summary.height;
        summary.visible = visible;
        stage.addActor(summary);
    }

    /**
     * Prepares the third layout.
     */
    private void initializeThirdLayout(boolean visible) {
        // TODO: initialize third layout
    }

    private void startTransitionToScreen2() {
        // prevent second button press
        stage.findActor("button").touchable = true;

        // update state
        currentState = state.SCREEN2;

        // get actors
        Image spotlight = (Image) stage.findActor("spotlight");
        Image creature = getSelectedCreature();

        // move spotlight to the right
        spotlight.action(MoveBy.$(175, 0, DEFAULT_FADE_OUT_TIME));

        // get spotlight center
        float spotlightCenterX = spotlight.x + spotlight.width / 2;

        // move creature to center of spotlight
        creature.action(MoveTo.$(spotlightCenterX - (creature.width * creature.scaleX) / 2 + 175,
                getYPositionBasedOnXValue(leftMark + 1),
                DEFAULT_FADE_OUT_TIME));

        // fade out all other creatures
        for (Image c : creatureList) {
            if (c != getSelectedCreature()) {
                c.action(FadeOut.$(DEFAULT_FADE_OUT_TIME));
            }
        }

        // fade out bottom text
        stage.findActor("labelFirstLine").action(FadeOut.$(DEFAULT_FADE_OUT_TIME));
        stage.findActor("labelSecondLine").action(FadeOut.$(DEFAULT_FADE_OUT_TIME));

        // fade in summary
        // FIXME: find better way to fade in summary
        stage.findActor("summary").visible = true;
        stage.findActor("summary").action(
                Sequence.$(FadeTo.$(0, 0f), FadeIn.$(DEFAULT_FADE_OUT_TIME)));
    }

    private void startTransitionToScreen3() {
        // prevent second button press
        stage.findActor("button").touchable = true;

        // update state
        currentState = state.SCREEN2;

        stage.findActor("summary").action(FadeOut.$(DEFAULT_FADE_OUT_TIME));
    }

    private void startTransitionToScreen4() {
        // TODO: Add third transition
    }

    private Image getSelectedCreature() {
        for (Image creature : creatureList) {
            if (creature.x > leftMark && creature.x < rightMark) {
                return creature;
            }
        }
        return creatureList.get(0);
    }

    /**
     * Finds correct Y value for corresponding X value to position creatures
     * 
     * @param x the X coordinate of the creature that should be positioned
     * @return int y
     */
    private float getYPositionBasedOnXValue(float x) {
        // the slope (how fast the creatures will move upwards)
        float m = 0.5f;

        // creature is left of marked area
        if (x <= leftMark) {
            int leftOfLeftMark = (int) (leftMark - x);
            return getYPositionBasedOnXValue(leftMark + 1) + m * leftOfLeftMark;
        }
        // creature is inside of marked area
        else if (x > leftMark && x < rightMark) {
            return Gdx.graphics.getHeight() / 2
                    - (creatureList.get(0).height * creatureList.get(0).scaleY) / 2;
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
            if (currentState == state.SCREEN1) {
                startTransitionToScreen2();
            }
            else if (currentState == state.SCREEN2) {
                startTransitionToScreen3();
            }
            else if (currentState == state.SCREEN3) {
                startTransitionToScreen4();
            }
        }
    }

    public boolean swipe(int x, int y, int deltaX, int deltay) {
        Image firstCreature = creatureList.get(0);
        Image lastCreature = creatureList.get(creatureList.size() - 1);
        int tempDeltaX = 0;

        // FIXME: Very fast swipes can overcome those boundaries
        boolean cannotBeSwipedFurtherLeft = lastCreature.x <= Gdx.graphics.getWidth() / 2
                - (lastCreature.width * lastCreature.scaleX) / 2
                && deltaX < 0;
        boolean cannotBeSwipedFurtherRight = firstCreature.x >= Gdx.graphics.getWidth() / 2
                - (firstCreature.width * firstCreature.scaleX) / 2
                && deltaX > 0;

        if (cannotBeSwipedFurtherLeft || cannotBeSwipedFurtherRight) {
            return true;
        }
        else {
            tempDeltaX = deltaX;
            for (Image c : creatureList) {

                c.x += tempDeltaX;
                c.y = getYPositionBasedOnXValue(c.x);
                // Gdx.app.log(TAG, "Creature " + c.name + " placed at X: "
                // + c.x + " Y: " + c.y);

            }
        }

        return true;
    }

    private void moveCreaturesBackIntoBoundaries() {
        Image centerCreature = getSelectedCreature();
        for (Image c : creatureList) {
            if (centerCreature != c) {
                int dCur;
                int dNew;
                if (c.x + (c.width * c.scaleX) / 2 <= Gdx.graphics.getWidth() / 2) {
                    dNew = (int) ((Gdx.graphics.getWidth() / 2) - (c.x + (c.width * c.scaleX) / 2));
                } else {
                    dNew = (int) ((c.x + (c.width * c.scaleX) / 2) - (Gdx.graphics.getWidth() / 2));
                }

                if (centerCreature.x + (centerCreature.width * centerCreature.scaleX) / 2 <= Gdx.graphics
                        .getWidth() / 2) {
                    dCur = (int) ((Gdx.graphics.getWidth() / 2) - (centerCreature.x + (centerCreature.width * centerCreature.scaleX) / 2));
                } else {
                    dCur = (int) ((centerCreature.x + (centerCreature.width * centerCreature.scaleX) / 2) - (Gdx.graphics
                            .getWidth() / 2));
                }

                if (dNew < dCur) {
                    centerCreature = c;
                }
            }
        }
        int tempDeltaX = Math
                .round((Gdx.graphics.getWidth() / 2 - (centerCreature.x + centerCreature.width / 2)) / 100);

        for (Image c : creatureList) {

            c.x += tempDeltaX;
            c.y = getYPositionBasedOnXValue(c.x);
            // Gdx.app.log(TAG, "Creature " + c.name + " placed at X: "
            // + c.x + " Y: " + c.y);
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (currentState == state.SCREEN1) {
            moveCreaturesBackIntoBoundaries();
        }
    }

    /**
     * Basic GestureListener that handles the swiping motion.
     */
    class SwipeHandler extends GestureAdapter {

        @Override
        public boolean pan(int x, int y, int deltaX, int deltaY) {
            return swipe(x, y, deltaX, deltaY);
        }
    }
}
