
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.redditandroiddevelopers.tamagotchi.TamagotchiAssets.TextureAtlasAsset;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;
import com.redditandroiddevelopers.tamagotchi.utils.FontHelper;

public class CreatureCreationScreen extends CommonScreen {

    private static final String TAG = "Tamagotchi:CreatureCreationScreen";

    private static final String GRP_CREATURES = "creatures";
    private static final String GRP_OVERLAY = "overlay";
    private static final String GRP_TEXT = "text";

    float scaleFactor = 0.75f;

    public CreatureCreationScreen(TamagotchiGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        /* add groups for better readability and flexibility */

        // create main groups
        final Group creatureGroup = new Group(GRP_CREATURES);
        final Group overlayGroup = new Group(GRP_OVERLAY);
        final Group textGroup = new Group(GRP_TEXT);

        /* load textures */

        // load texture atlas
        final TextureAtlas textureAtlas = game.assets.getAsset(TextureAtlasAsset.CREATE_CREATURE);

        // get texture regions from loaded texture atlas
        final TextureRegion creatureTextureRegion = textureAtlas.findRegion("PetDefault");
        final TextureRegion overlayGrayTextureRegion = textureAtlas.findRegion("OverlayGray");

        // create creatures
        Image creature = new Image(creatureTextureRegion);
        creature.x = camera.viewportWidth / 2 - creature.width / 2;
        creature.y = camera.viewportHeight / 2 - creature.height / 2;

        Image creature2 = new Image(creatureTextureRegion);
        creature2.x = camera.viewportWidth - (3f / 4) * creature2.width * scaleFactor;
        creature2.y = camera.viewportHeight / 2 - creature.height / 2 + 100;
        creature2.scaleX = creature2.scaleY = scaleFactor;

        Image creature3 = new Image(creatureTextureRegion);
        creature3.x = -creature3.width + (3f / 4) * creature3.width * scaleFactor;
        creature3.y = camera.viewportHeight / 2 - creature.height / 2 + 100;
        creature3.scaleX = creature3.scaleY = scaleFactor;

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

    @Override
    public void loadResources() {
        game.assets.loadAsset(TextureAtlasAsset.CREATE_CREATURE);
    }

    @Override
    public void unloadResources() {
        game.assets.unloadAsset(TextureAtlasAsset.CREATE_CREATURE);
    }

    @Override
    protected Stage createStage(SpriteBatch batch) {
        return new Stage(game.config.stageWidth, game.config.stageHeight, false, batch);
    }
}
