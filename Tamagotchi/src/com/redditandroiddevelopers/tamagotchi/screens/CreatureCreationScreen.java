
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Scaling;
import com.redditandroiddevelopers.tamagotchi.TamagotchiAssets.TextureAtlasAsset;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;
import com.redditandroiddevelopers.tamagotchi.utils.FontHelper;

import java.util.ArrayList;

public class CreatureCreationScreen extends CommonScreen {

    private static final String TAG = "Tamagotchi:CreatureCreationScreen";

    private static final String GRP_CREATURES = "creatures";
    private static final String GRP_OVERLAY = "overlay";
    private static final String GRP_TEXT = "text";

    float leftMark = 250;
    float rightMark = Gdx.graphics.getWidth() - 250;

    ArrayList<Image> creatureList = new ArrayList<Image>();

    float scaleFactor = 0.75f;

    public CreatureCreationScreen(TamagotchiGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        initLayout();
        initInput();
    }

    private void initInput() {
        GestureDetector gestureDetector = new GestureDetector(new SwipeHandler());
        game.inputMultiplexer.removeProcessor(stage);
        game.inputMultiplexer.addProcessor(gestureDetector);
        game.inputMultiplexer.addProcessor(stage);
    }

    private void initLayout() {
        /* add groups for better readability and flexibility */

        // create main groups
        final Group creatureGroup = new Group(GRP_CREATURES);
        final Group overlayGroup = new Group(GRP_OVERLAY);
        final Group textGroup = new Group(GRP_TEXT);

        overlayGroup.touchable = false;

        /* load textures */

        // load texture atlas
        final TextureAtlas textureAtlas = game.assets.getAsset(TextureAtlasAsset.CREATE_CREATURE);

        // get texture regions from loaded texture atlas
        final TextureRegion creatureTextureRegion = textureAtlas.findRegion("PetDefault");
        final TextureRegion overlayGrayTextureRegion = textureAtlas.findRegion("OverlayGray");

        // create creatures

        Image creature = new Image(creatureTextureRegion, Scaling.stretch, Align.CENTER,
                "creature1");
        creature.x = camera.viewportWidth / 2 - creature.width / 2;
        creature.y = camera.viewportHeight / 2 - creature.height / 2;

        Image creature2 = new Image(creatureTextureRegion, Scaling.stretch, Align.CENTER,
                "creature2");

        Image creature3 = new Image(creatureTextureRegion, Scaling.stretch, Align.CENTER,
                "creature3");

        creatureList.add(creature);
        creatureList.add(creature2);
        creatureList.add(creature3);

        // adjust left and right mark

        leftMark = leftMark - creature.width / 2;
        rightMark = rightMark - creature.width / 2;

        initializeCreaturePositions();

        // create overlay

        Image overlay = new Image(overlayGrayTextureRegion);

        // add text ("select creature")

        final Label.LabelStyle labelStyle80 = new Label.LabelStyle(
                FontHelper.createBitmapFont("fonts/Roboto-Regular.ttf", 80f, stage),
                Color.WHITE);
        final Label.LabelStyle labelStyle20 = new Label.LabelStyle(
                FontHelper.createBitmapFont("fonts/Roboto-Regular.ttf", 40f, stage),
                Color.WHITE);
        Label labelFirstLine = new Label("Choose a pet", labelStyle80);
        labelFirstLine.x = Gdx.graphics.getWidth() / 2 - labelFirstLine.width / 2;
        labelFirstLine.y = -10;
        Label labelSecondLine = new Label("Swipe either left or right to select a pet",
                labelStyle20);
        labelSecondLine.x = Gdx.graphics.getWidth() / 2 - labelSecondLine.width / 2;
        labelSecondLine.y = -20;

        /* Prepare main groups */

        // add creatures to the 'creature' group

        creatureGroup.addActor(creature);
        creatureGroup.addActor(creature2);
        creatureGroup.addActor(creature3);

        // add overlay to the 'overlay' group
        overlayGroup.addActor(overlay);

        // add labels to the 'text' group
        textGroup.addActor(labelFirstLine);
        textGroup.addActor(labelSecondLine);

        /* Add main groups to stage */
        stage.addActor(creatureGroup);
        stage.addActor(overlayGroup);
        stage.addActor(textGroup);
    }

    private float getYforX(float x) {
        // x = x + creatureList.get(0).getImageWidth() / 2;
        float t = 0.8f;
        if (x <= leftMark) {
            int leftOfLeftMark = (int) (leftMark - x);
            return getYforX(leftMark + 1) + t * leftOfLeftMark;
        }
        else if (x > leftMark && x < rightMark) {
            return Gdx.graphics.getHeight() / 2 - creatureList.get(0).height / 2;
        }
        else if (x >= rightMark) {
            int rightOfRightMark = (int) (x - rightMark);
            return getYforX(leftMark + 1) + t * rightOfRightMark;
        }
        return 0;
    }

    private void initializeCreaturePositions() {
        for (Image c : creatureList) {
            int i = creatureList.indexOf(c);
            if (i == 0) {
                c.x = camera.viewportWidth / 2 - c.width / 2;
                c.y = camera.viewportHeight / 2 - c.height / 2;
            }
            else if (i == 1) {
                c.x = camera.viewportWidth - (3f / 4) * c.width * c.scaleX;
                c.y = getYforX(c.x);
                c.scaleX = c.scaleY = scaleFactor;
            }
            else {
                c.x = creatureList.get(i - 1).x + (creatureList.get(1).x - creatureList.get(0).x);
                c.y = getYforX(c.x);
                ;
                c.scaleX = c.scaleY = scaleFactor;
            }
        }
    }

    @Override
    public void loadResources() {
        game.assets.loadAsset(TextureAtlasAsset.CREATE_CREATURE);
    }

    @Override
    public void unloadResources() {
        creatureList.clear();
        game.assets.unloadAsset(TextureAtlasAsset.CREATE_CREATURE);
    }

    @Override
    protected Stage createStage(SpriteBatch batch) {
        return new Stage(game.config.stageWidth, game.config.stageHeight, false, batch);
    }

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
            // Gdx.app.log(TAG, "x: " + x + " y: " + y + " deltaX: " + deltaX +
            // " deltaY: " + deltaY);
            if (deltaX == 0) {
                return false; // not sure if this should be considered 'handled'
            }
            for (Image c : creatureList) {
                c.x += deltaX;
                c.y = getYforX(c.x);
                if (c.name.compareTo("creature2") == 0) {
                    Gdx.app.log(TAG, "Creature " + c.name + " placed at X: " + c.x + " Y: " + c.y);
                }
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
