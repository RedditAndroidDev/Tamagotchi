package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.redditandroiddevelopers.tamagotchi.ui.Button;

public class MainMenuScreen extends CommonScreen {

	Button button = new Button("button-background.png", 0, 0, 2);
	
	SpriteBatch batch = new SpriteBatch();
	Stage stage;
    @Override
    public void update(float delta) {
		 stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, batch);
		 stage.addActor(button);
    }

    @Override
    public void draw(float delta) {
    	stage.draw();
    }

}
