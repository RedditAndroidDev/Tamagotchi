package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.redditandroiddevelopers.tamagotchi.ui.Draggable;
import com.redditandroiddevelopers.tamagotchi.ui.DraggableImage;

/**
 * This screen instance will represent the normal game execution
 */
public class GameLoop extends CommonScreen implements ClickListener, Draggable {

    private Stage stage;
    DraggableImage img;

    @Override
    public void show() {
        stage = new Stage(800, 480, true, new SpriteBatch());
//        pullDownTexture = new Texture(Gdx.files.internal("pulldown.png"));
//        sprite = new Sprite(pullDownTexture);
//        spriteBatch = new SpriteBatch();
        img = new DraggableImage(new Texture(Gdx.files.internal("pulldown.png")));
//        img.scaleX = 2;
//        img.scaleY = 2;
//        img.x = 50;
//        img.y = 50;
        img.setClickListener(this);
        img.setDraggable(this);
        stage.addActor(img);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float delta) {
        
    }

    @Override
    public void draw(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.draw();
//        spriteBatch.begin();
//        sprite.draw(spriteBatch);
//        spriteBatch.draw(pullDownTexture, 550, 550, 0, 0, pullDownTexture.getWidth(),
//                pullDownTexture.getHeight());
//        spriteBatch.end();
    }

    @Override
    public void click(Actor actor, float x, float y) {

    }

    @Override
    public void drag(Actor a, float x, float y, int pointer) {
        a.x += x;
        a.y += y;
        //System.out.println("x=" + x + ", y=" + y + ", pointer=" + pointer);
    }

}
