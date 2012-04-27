
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;

public class CreatureCreationScreen extends CommonScreen {

    private static final String TAG = "Tamagotchi:CreatureCreationScreen";

    Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);

    public CreatureCreationScreen(TamagotchiGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void loadResources() {
    }

    @Override
    public void unloadResources() {
    }

    @Override
    protected Stage createStage(SpriteBatch batch) {
        return new Stage(game.config.stageWidth, game.config.stageHeight, false, batch);
    }
}
