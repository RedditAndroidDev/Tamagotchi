package com.redditandroiddevelopers.tamagotchi.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Logger;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;

public class Button extends Actor {

	Texture background;
	int nextState;

	int x;
	int y;

	public Button(String texture, int nextState,int x,int y) {
		this.background = new Texture(Gdx.files.internal(texture));
		this.nextState = nextState;
		this.x = x;
		this.y =y;
		this.width = background.getWidth();
		this.height = background.getHeight();
	}

	/*
	 * @see
	 * com.badlogic.gdx.scenes.scene2d.Actor#draw(com.badlogic.gdx.graphics.
	 * g2d.SpriteBatch, float)
	 * 
	 * @param SpriteBatch batch - the batch to use for drawing
	 * 
	 * @param float parentAlpha - the alpha of the parent Draws the Button to
	 * the current screen
	 */
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		 batch.draw(background, x,y);
	}

	@Override
	public boolean touchDown(float x, float y, int pointer) {
		TamagotchiGame.MY_STATE = nextState;
		TamagotchiGame.STATE_CHANGE = true;
		return true;
	}

	@Override
	public Actor hit(float x, float y) {
		TamagotchiGame.MY_STATE = nextState;
		TamagotchiGame.STATE_CHANGE = true;
		return this;
	}

}
