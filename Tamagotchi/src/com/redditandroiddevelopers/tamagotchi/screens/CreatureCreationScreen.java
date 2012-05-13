
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
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.MoveTo;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
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

    // define possible states
    private enum ScreenState {
        SCREEN_INIT,
        SCREEN1,
        SCREEN2,
        SCREEN3,
        SCREEN4;
    }

    private ScreenState currentState;

    // define group names
    private static final String GROUP_CREATURES = "creatures";
    private static final String GROUP_BACKGROUND = "background";
    private static final String GROUP_TEXT = "text";
    private static final String GROUP_TOP_BUTTONS = "top_buttons";
    private static final String GROUP_GENDER_BUTTONS = "gender_buttons";

    // define actor names
    private static final String ACTOR_IMAGE_SPOTLIGHT = "spotlight";
    private static final String ACTOR_IMAGE_FEMALE = "female";
    private static final String ACTOR_IMAGE_MALE = "male";
    private static final String ACTOR_LABEL_SUMMARY = "summary";
    private static final String ACTOR_LABEL_GENDER = "gender";
    private static final String ACTOR_LABEL_FIRST_LINE = "labelFirstLine";
    private static final String ACTOR_LABEL_SECOND_LINE = "labelSecondLine";
    private static final String ACTOR_LABEL_CREATURE_NAME = "creaturename";
    private static final String ACTOR_TEXTFIELD_NAME = "nameField";
    private final String[] interactButtonIDs = new String[] {
            "MainButtonAccept",
            "MainButtonRemove",
    };

    // define fonts
    private static final String FONT_ROBOTO_REGULAR = "fonts/Roboto-Regular.ttf";

    private static final float DEFAULT_FADE_TIME = 0.5f;

    // number of creatures to create
    // XXX: Temporary! TODO: Hook up to the database
    private static final int NUM_OF_CREATURES = 3;

    private final ArrayList<Image> creatureList;
    private final GestureDetector gestureDetector;

    private float leftMark, rightMark;
    private float scaleFactor;

    // buttons
    private static final int TOPBTN_ACCEPT = 0;
    private static final int TOPBTN_REMOVE = 1;
    private static final int TOPBTN_NUM_BUTTONS = 2;

    private Image[] topButtons;

    // font styles
    private LabelStyle labelStyle80;
    private LabelStyle labelStyle40;
    private LabelStyle labelStyle20;

    private final TextFieldStyle textFieldStyle1;

    private final int centerX = Gdx.graphics.getWidth() / 2;
    private final int centerY = Gdx.graphics.getHeight() / 2;

    // load texture atlas
    private TextureAtlas textureAtlas;

    /**
     * Creates a new instance of the CreatureCreationScreen.
     * 
     * @param game
     */
    public CreatureCreationScreen(TamagotchiGame game) {
        super(game);
        creatureList = new ArrayList<Image>();
        textFieldStyle1 = new TextFieldStyle();
        gestureDetector = new GestureDetector(new GestureAdapter() {

            @Override
            public boolean pan(int x, int y, int deltaX, int deltaY) {
                return swipe(x, y, deltaX, deltaY);
            }
        });
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
        currentState = ScreenState.SCREEN_INIT;
        textureAtlas = game.assets.getAsset(TextureAtlasAsset.CREATE_CREATURE);
        initializeInput();
        initializeFonts();
        initializeLayouts();
        transitionToScreen1();
    }

    @Override
    protected void drawBackground() {
        final Color bgc = game.config.creatureCreationBackgroundColor;
        Gdx.gl.glClearColor(bgc.r, bgc.g, bgc.b, bgc.a);
    }

    /**
     * Adds a GestureDetector to the InputMultiplexer to listen to swipes
     */
    private void initializeInput() {
        game.inputMultiplexer.removeProcessor(stage);
        game.inputMultiplexer.removeProcessor(gestureDetector);
        game.inputMultiplexer.addProcessor(gestureDetector);
        game.inputMultiplexer.addProcessor(stage);
    }

    /**
     * Generates fonts based on TTF files.
     */
    private void initializeFonts() {
        labelStyle80 = new Label.LabelStyle(FontHelper.createBitmapFont(
                FONT_ROBOTO_REGULAR, 80f, stage), Color.WHITE);
        labelStyle40 = new Label.LabelStyle(FontHelper.createBitmapFont(
                FONT_ROBOTO_REGULAR, 40f, stage), Color.WHITE);
        labelStyle20 = new Label.LabelStyle(FontHelper.createBitmapFont(
                FONT_ROBOTO_REGULAR, 20f, stage), Color.WHITE);
    }

    /**
     * Prepares all layouts.
     */
    private void initializeLayouts() {
        initializeLayout1(false);
        initializeLayout2(false);
        initializeLayout3(false);
        initializeLayout4(false);
    }

    /**
     * Prepares the creature selection screen
     */
    private void initializeLayout1(boolean visible) {
        /* add groups for better readability and flexibility */

        // create main groups
        final Group creatureGroup = new Group(GROUP_CREATURES);
        final Group backgroundGroup = new Group(GROUP_BACKGROUND);
        final Group textGroup = new Group(GROUP_TEXT);
        final Group topButtonsGroup = new Group(GROUP_TOP_BUTTONS);

        creatureGroup.visible = visible;
        backgroundGroup.visible = visible;
        textGroup.visible = visible;
        topButtonsGroup.visible = visible;

        // get texture regions from loaded texture atlas
        final TextureRegion creatureTextureRegion = textureAtlas.findRegion("PetDefault");
        final TextureRegion spotlightTextureRegion = textureAtlas.findRegion("Spotlight");

        // create creatures
        for (int i = 0; i < NUM_OF_CREATURES; i++) {
            creatureList.add(new Image(creatureTextureRegion, Scaling.stretch, Align.CENTER,
                    "Creature " + (i + 1)));
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
                ACTOR_IMAGE_SPOTLIGHT);
        spotlight.x = Gdx.graphics.getWidth() / 2 - spotlight.width / 2;
        spotlight.y = Gdx.graphics.getHeight() - spotlight.height;

        // add text ("Choose a pet")
        Label labelFirstLine = new Label("Choose a pet", labelStyle80, ACTOR_LABEL_FIRST_LINE);
        labelFirstLine.x = Gdx.graphics.getWidth() / 2 - labelFirstLine.width / 2;
        labelFirstLine.y = 35 - labelFirstLine.height;

        // add text ("Swipe either left or right to select a pet")
        Label labelSecondLine = new Label("Swipe either left or right to select a pet",
                labelStyle40, ACTOR_LABEL_SECOND_LINE);
        labelSecondLine.x = Gdx.graphics.getWidth() / 2 - labelSecondLine.width / 2;
        labelSecondLine.y = 5 - labelSecondLine.height;

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

        // load texture regions for buttons in top right corner
        final TextureRegion[] interactButtonTextureRegions = new TextureRegion[interactButtonIDs.length];
        for (int i = 0; i < interactButtonIDs.length; i++) {
            interactButtonTextureRegions[i] = textureAtlas.findRegion(interactButtonIDs[i]);
        }

        // set margin between buttons
        final int marginBetweenButtons = 10;

        // position buttons within group and add them to the 'topButtonsGroup'
        final int width = interactButtonTextureRegions[0].getRegionWidth() + marginBetweenButtons;
        topButtons = new Image[TOPBTN_NUM_BUTTONS];
        for (int i = 0; i < TOPBTN_NUM_BUTTONS; i++) {
            final Image button = new Image(interactButtonTextureRegions[i], Scaling.stretch,
                    Align.CENTER, interactButtonIDs[i]);
            button.x = width * i;
            button.setClickListener(this);
            topButtonsGroup.addActor(button);
            topButtons[i] = button;
        }

        // adjust width of 'topButtons' group
        topButtonsGroup.width = width * topButtonsGroup.getActors().size();

        // position topButtons in top right corner
        topButtonsGroup.x = stage.centerX() - (topButtonsGroup.width / 2);
        topButtonsGroup.y = stage.top() - width;

        /* Add main groups to stage */
        stage.addActor(topButtonsGroup);
        stage.addActor(backgroundGroup);
        stage.addActor(creatureGroup);
        stage.addActor(textGroup);
    }

    /**
     * Prepares the second layout.
     */
    private void initializeLayout2(boolean visible) {

        // create summary text
        String text = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        Label summary = new Label(TextUtils.insertPeriodically(text, "\n", 30), labelStyle20,
                ACTOR_LABEL_SUMMARY);
        summary.x = 50;
        summary.y = Gdx.graphics.getHeight() - 100 - summary.height;
        summary.visible = visible;

        // create creature name
        Label name = new Label(creatureList.get(0).name, labelStyle80, ACTOR_LABEL_CREATURE_NAME);
        Image spotlight = (Image) stage.findActor(ACTOR_IMAGE_SPOTLIGHT);
        name.x = (spotlight.x + spotlight.width / 2) - name.width / 2;
        name.y = 50 - name.height;
        name.visible = false;

        stage.addActor(name);
        stage.addActor(summary);
    }

    /**
     * Prepares the third layout.
     */
    private void initializeLayout3(boolean visible) {
        // TODO: initialize third layout
        textFieldStyle1.font = FontHelper.createBitmapFont(FONT_ROBOTO_REGULAR, 20f, stage);
        textFieldStyle1.fontColor = Color.WHITE;

        // nameField
        TextField nameField = new TextField("boosh", null, textFieldStyle1, ACTOR_TEXTFIELD_NAME);
        nameField.x = centerX - (stage.width() / 3) - (nameField.width / 4);
        nameField.y = centerY;
        nameField.visible = false;

        stage.addActor(nameField);
    }

    private void initializeLayout4(boolean visible) {

        final Group genderBtnGroup = new Group(GROUP_GENDER_BUTTONS);

        // initialize gender label
        String text = "Gender";
        Label gender = new Label(text, labelStyle80, ACTOR_LABEL_GENDER);

        // positions gender label
        gender.x = centerX - (stage.width() / 3) - (gender.width / 4);
        gender.y = centerY;

        // set gender label to specified visibility
        gender.visible = visible;

        // load texture regions for gender buttons
        final TextureRegion femaleTextureRegion = textureAtlas.findRegion("CreateButtonGirl");
        final TextureRegion maleTextureRegion = textureAtlas.findRegion("CreateButtonBoy");

        // set margin between buttons
        final int marginBetweenButtons = 10;

        // create gender buttons
        final Image imgFemale = new Image(femaleTextureRegion, Scaling.stretch, Align.CENTER,
                ACTOR_IMAGE_FEMALE);
        final Image imgMale = new Image(maleTextureRegion, Scaling.stretch, Align.CENTER,
                ACTOR_IMAGE_MALE);

        // position gender buttons
        imgFemale.x = imgFemale.y = 0;
        imgMale.x = imgFemale.x + imgFemale.width + marginBetweenButtons;
        imgMale.y = 0;

        // make gender buttons touchable
        imgFemale.setClickListener(this);
        imgMale.setClickListener(this);

        // add gender buttons to stage
        genderBtnGroup.addActor(imgFemale);
        genderBtnGroup.addActor(imgMale);

        // set gender button group to specified visibility
        genderBtnGroup.visible = visible;

        // adjust width of 'genderBtnGroup' group
        genderBtnGroup.width = imgMale.x + imgMale.width;

        // position topButtons underneath gender label
        genderBtnGroup.x = gender.x + (gender.width / 4);
        genderBtnGroup.y = gender.y - (gender.height / 4);

        // add actors to stage
        stage.addActor(gender);
        stage.addActor(genderBtnGroup);
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

    private void transitionToScreen1() {
        if (currentState == ScreenState.SCREEN1) {
            return;
        }
        Gdx.app.debug(TAG, "Going to Screen1");
        fadeOutEverything();

        // set currentState to SCREEN 1
        currentState = ScreenState.SCREEN1;

        initializeInput();

        stage.findActor(GROUP_BACKGROUND).visible = true;
        stage.findActor(GROUP_CREATURES).visible = true;
        stage.findActor(GROUP_TEXT).visible = true;
        stage.findActor(GROUP_TOP_BUTTONS).visible = true;

        // handle spotlight
        Image spotlight = (Image) stage.findActor(ACTOR_IMAGE_SPOTLIGHT);
        spotlight.visible = true;
        spotlight.action(
                Sequence.$(
                        MoveTo.$(centerX - spotlight.width / 2, Gdx.graphics.getHeight()
                                - spotlight.height, DEFAULT_FADE_TIME),
                        FadeIn.$(DEFAULT_FADE_TIME)));

        // handle second text label
        Label labelFirstLine = (Label) stage.findActor(ACTOR_LABEL_FIRST_LINE);
        labelFirstLine.color.a = 0f;
        labelFirstLine.action(
                Sequence.$(
                        MoveTo.$(centerX - labelFirstLine.width / 2, 35 - labelFirstLine.height,
                                DEFAULT_FADE_TIME),
                        FadeIn.$(DEFAULT_FADE_TIME)));

        // handle first text label
        Label labelSecondLine = (Label) stage.findActor(ACTOR_LABEL_SECOND_LINE);
        labelSecondLine.color.a = 0f;
        labelSecondLine.action(
                Sequence.$(
                        MoveTo.$(centerX - labelSecondLine.width / 2, 5 - labelSecondLine.height,
                                DEFAULT_FADE_TIME),
                        FadeIn.$(DEFAULT_FADE_TIME)));

        // show creatures
        for (Image creature : creatureList) {
            creature.visible = true;
            creature.color.a = 0f;
            creature.action(Delay.$(FadeIn.$(DEFAULT_FADE_TIME), DEFAULT_FADE_TIME));
        }

        for (Image button : topButtons) {
            button.visible = true;
        }

        initializeCreaturePositions();

    }

    private void transitionToScreen2() {
        if (currentState == ScreenState.SCREEN2) {
            return;
        }
        Gdx.app.debug(TAG, "Going to Screen2");

        // set currentState to SCREEN 2
        currentState = ScreenState.SCREEN2;

        // fade out top buttons
        // TODO: Fade out
        // Leave this out for now so we can navigate
        // stage.findActor(GRP_TOP_BUTTONS).visible = false;

        // remove gestureDetector to prevent swiping after first screen
        game.inputMultiplexer.removeProcessor(gestureDetector);

        // get actors
        Image spotlight = (Image) stage.findActor(ACTOR_IMAGE_SPOTLIGHT);
        Image creature = getSelectedCreature();

        // move spotlight to the right
        spotlight.action(MoveBy.$(175, 0, DEFAULT_FADE_TIME));

        // get spotlight center
        float spotlightCenterX = spotlight.x + spotlight.width / 2;

        // move creature to center of spotlight
        creature.action(MoveTo.$(spotlightCenterX - (creature.width * creature.scaleX) / 2 + 175,
                getYPositionBasedOnXValue(leftMark + 1),
                DEFAULT_FADE_TIME));

        // fade out all other creatures
        for (Image c : creatureList) {
            if (c != getSelectedCreature()) {
                c.action(FadeOut.$(DEFAULT_FADE_TIME));
            }
        }

        // fade out bottom text
        stage.findActor(ACTOR_LABEL_FIRST_LINE).action(FadeOut.$(DEFAULT_FADE_TIME));
        stage.findActor(ACTOR_LABEL_SECOND_LINE).action(FadeOut.$(DEFAULT_FADE_TIME));

        // fade in summary
        stage.findActor(ACTOR_LABEL_SUMMARY).visible = true;
        stage.findActor(ACTOR_LABEL_SUMMARY).color.a = 0f;
        stage.findActor(ACTOR_LABEL_SUMMARY).action(FadeIn.$(DEFAULT_FADE_TIME));

        // fade in and move creature name
        Label creatureName = (Label) stage.findActor(ACTOR_LABEL_CREATURE_NAME);
        creatureName.setText(getSelectedCreature().name);
        creatureName.action(
                Sequence.$(
                        Delay.$(MoveTo.$((spotlight.x + spotlight.width / 2) - creatureName.width
                                / 2 + 175, 0, DEFAULT_FADE_TIME), DEFAULT_FADE_TIME),
                        FadeIn.$(DEFAULT_FADE_TIME)));
    }

    private void transitionToScreen3() {
        if (currentState == ScreenState.SCREEN3) {
            return;
        }
        Gdx.app.debug(TAG, "Going to Screen3");
        // set currentState to SCREEN 3
        currentState = ScreenState.SCREEN3;

        stage.findActor(ACTOR_LABEL_SUMMARY).action(FadeOut.$(DEFAULT_FADE_TIME));
    }

    private void transitionToScreen4() {
        if (currentState == ScreenState.SCREEN4) {
            return;
        }
        Gdx.app.debug(TAG, "Going to Screen4");

        // set currentState to SCREEN 4
        currentState = ScreenState.SCREEN4;

        stage.findActor(ACTOR_TEXTFIELD_NAME).action(FadeOut.$(DEFAULT_FADE_TIME));

        stage.findActor(ACTOR_LABEL_GENDER).visible = true;
        stage.findActor(ACTOR_LABEL_GENDER).color.a = 0f;
        stage.findActor(ACTOR_LABEL_GENDER).action(FadeIn.$(DEFAULT_FADE_TIME));
        stage.findActor(GROUP_GENDER_BUTTONS).visible = true;
        stage.findActor(GROUP_GENDER_BUTTONS).color.a = 0f;
        stage.findActor(GROUP_GENDER_BUTTONS).action(FadeIn.$(DEFAULT_FADE_TIME));
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
        if (actor == topButtons[TOPBTN_ACCEPT]) {
            Gdx.app.debug(TAG, "Touch on Accept button");
            goToPreviousScreen();
        } else if (actor == topButtons[TOPBTN_REMOVE]) {
            Gdx.app.debug(TAG, "Touch on Remove button");
            goToNextScreen();
        } else if (actor.name == ACTOR_IMAGE_MALE) {
            Gdx.app.debug(TAG, "Touch on BOY button");
        } else if (actor.name == ACTOR_IMAGE_FEMALE) {
            Gdx.app.debug(TAG, "Touch on GIRL button");
        } else if (creatureList.contains(actor)) {
            Gdx.app.debug(TAG, "Hit on " + actor.name + " detected");
        }
    }

    private void goToPreviousScreen() {
        if (currentState == ScreenState.SCREEN2) {
            transitionToScreen1();
        }
        else if (currentState == ScreenState.SCREEN3) {
            transitionToScreen2();
        }
        else if (currentState == ScreenState.SCREEN4) {
            transitionToScreen3();
        }
    }

    private void goToNextScreen() {
        if (currentState == ScreenState.SCREEN1) {
            transitionToScreen2();
        }
        else if (currentState == ScreenState.SCREEN2) {
            transitionToScreen3();
        }
        else if (currentState == ScreenState.SCREEN3) {
            transitionToScreen4();
        }
        else if (currentState == ScreenState.SCREEN4) {
            initializeCreaturePositions();
            initializeInput();
            fadeOutEverything();
            transitionToScreen1();
        }
    }

    private void fadeOutEverything() {
        // hide all groups but ignore individual actors
        for (Actor actor : stage.getActors()) {
            if (actor.getClass() != Group.class) {
                actor.color.a = 0f;
            }
        }

        // FIXME: those actors are not inside of a group yet
        stage.findActor(GROUP_GENDER_BUTTONS).color.a = 0f;
        stage.findActor(ACTOR_IMAGE_SPOTLIGHT).color.a = 0f;
        stage.findActor(ACTOR_LABEL_CREATURE_NAME).color.a = 0f;
    }

    public boolean swipe(int x, int y, int deltaX, int deltay) {
        Image firstCreature = creatureList.get(0);
        Image lastCreature = creatureList.get(creatureList.size() - 1);
        int tempDeltaX = 0;

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
        if (currentState == ScreenState.SCREEN1) {
            moveCreaturesBackIntoBoundaries();
        }
    }

}
