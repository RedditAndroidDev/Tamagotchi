package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;

/**
 * This screen instance will represent the normal game execution
 */
public class GameLoop extends CommonScreen implements ClickListener {

	private Button btnLight;
	private Button btnShower;
	private Button btnToilet;
	private Button btnFood;
	
	private Stage stage;
	private Group ui;
	@Override
	public void show(){
		
		//TODO document this :)
		   stage = new Stage(800, 480, true, new SpriteBatch());
		   ui = new Group("ui");
		   TamagotchiGame.getAssetManager().load("InGame/Button.png", Texture.class);
		   
		   int width = TamagotchiGame.getAssetManager().get("InGame/Button.png",Texture.class).getWidth();
		 
		   
	        btnLight = new Button(new TextureRegion(TamagotchiGame.getAssetManager().get("InGame/Button.png", Texture.class)));
	        btnLight.x = stage.right() - width;
	        btnLight.y = 0;
	        btnLight.setClickListener(this);
	        ui.addActor(btnLight);
	        
	        btnShower = new Button(new TextureRegion(TamagotchiGame.getAssetManager().get("InGame/Button.png", Texture.class)));
	        btnShower.x = stage.right() - (width * 2);
	        btnShower.y = 0;
	        btnShower.setClickListener(this);
	        ui.addActor(btnShower);
	        
	        btnToilet = new Button(new TextureRegion(TamagotchiGame.getAssetManager().get("InGame/Button.png", Texture.class)));
	        btnToilet.x = stage.right() - (width * 3);
	        btnToilet.y = 0;
	        btnToilet.setClickListener(this);
	        ui.addActor(btnToilet);
	        
	        btnFood = new Button(new TextureRegion(TamagotchiGame.getAssetManager().get("InGame/Button.png", Texture.class)));
	        btnFood.x = stage.right() - (width * 4);
	        btnFood.y = 0;
	        btnFood.setClickListener(this);
	        
	        ui.addActor(btnFood);
	        
	        
	        stage.addActor(ui);
	        
	        Gdx.input.setInputProcessor(stage);

	}
	@Override
	public void update(float delta) {
	
	}

	@Override
	public void draw(float delta) {
		stage.draw();

	}
	@Override
	public void click(Actor actor, float x, float y) {
		
		
	}

}
