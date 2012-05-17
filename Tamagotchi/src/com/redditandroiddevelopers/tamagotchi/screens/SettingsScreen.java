package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;
import com.redditandroiddevelopers.tamagotchi.utils.FontHelper;

public class SettingsScreen extends CommonScreen 
{
	public SettingsScreen(TamagotchiGame game)
	{
		super(game);
	}

	@Override
    public final void show() {
        super.show();
        layout();
    }
	
	private void layout()
	{
		LabelStyle labelstyle = new LabelStyle(FontHelper.createBitmapFont(
                "fonts/Roboto-Regular.ttf", 100f, stage), Color.BLACK);
        Label labelAppName = new Label("Settings", labelstyle);
        labelAppName.x = 10;
        labelAppName.y = 277;
        stage.addActor(labelAppName);
	}
	

	@Override
	public void loadResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unloadResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Stage createStage(SpriteBatch batch) {
		return new Stage(game.config.stageWidth, game.config.stageHeight, false, batch);
	}
	
	@Override
    protected void drawBackground() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
	}
}

