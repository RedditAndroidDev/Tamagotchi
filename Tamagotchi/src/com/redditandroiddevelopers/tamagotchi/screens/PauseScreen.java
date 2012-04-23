
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;

/**
 * The Pause screen
 */
public class PauseScreen extends CommonScreen {

    public PauseScreen(TamagotchiGame game) {
        super(game);
    }

    @Override
    protected final Stage createStage(SpriteBatch batch) {
        return new Stage(game.config.stageWidth, game.config.stageHeight, false, batch);
    }

    @Override
    public void loadResources() {
        // TODO Auto-generated method stub

    }

    @Override
    public void unloadResources() {
        // TODO Auto-generated method stub

    }

}
