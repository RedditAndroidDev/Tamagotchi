
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.redditandroiddevelopers.tamagotchi.ui.Button;

public class MainMenuScreen extends CommonScreen {

    SpriteBatch batch = new SpriteBatch();
    Stage stage;

    @Override
    public void show() {
        stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, batch);
        
        Button btnPlay = new Button("button-background.png", 0, 10, 235);
        stage.addActor(btnPlay);
        
        Button btnSelect = new Button("button-background.png", 0, 10, 160);
        stage.addActor(btnSelect);
        
        Button btnMemories = new Button("button-background.png", 0, 10, 85);
        stage.addActor(btnMemories);

        Button btnSettings = new Button("button-background.png", 0, 10, 10);
        stage.addActor(btnSettings);
        
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta) {
        stage.draw();
    }

}