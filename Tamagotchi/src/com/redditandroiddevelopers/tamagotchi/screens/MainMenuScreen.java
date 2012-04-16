
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.redditandroiddevelopers.tamagotchi.ui.Button;

public class MainMenuScreen extends CommonScreen {

    SpriteBatch batch = new SpriteBatch();
    Stage stage;

    Button btnPlay;
    Button btnSelect;
    Button btnMemories;
    Button btnSettings;

    Vector2 touchPoint;

    @Override
    public void show() {

        // create new stage
        stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, batch);

        // point, where user last touched the screen
        touchPoint = new Vector2();

        // adding the game name
        Image imgAppName = new Image(new Texture(Gdx.files.internal("MainMenu/AppName.png")));
        imgAppName.x = 10;
        imgAppName.y = 325;
        stage.addActor(imgAppName);

        // adding the Play button
        btnPlay = new Button("MainMenu/btn_play_unpressed.png", 0, 10, 235);
        stage.addActor(btnPlay);

        // adding the Select button
        btnSelect = new Button("MainMenu/btn_select_unpressed.png", 0, 10, 160);
        stage.addActor(btnSelect);

        // adding the Memories button
        btnMemories = new Button("MainMenu/btn_memories_unpressed.png", 0, 10, 85);
        stage.addActor(btnMemories);

        // adding the Settings button
        btnSettings = new Button("MainMenu/btn_settings_unpressed.png", 0, 10, 10);
        stage.addActor(btnSettings);
    }

    @Override
    public void update(float delta) {

        // check for touch input
        if (Gdx.input.justTouched()) {
            // update touch point
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY());

            // check if touch point lies in one of the defined rectangles
            if (btnPlay.pointOnButton(touchPoint.x, touchPoint.y)) {
                Gdx.app.log("Tamagotchi", "Touch on Play");
                return;
            }
            if (btnSelect.pointOnButton(touchPoint.x, touchPoint.y)) {
                Gdx.app.log("Tamagotchi", "Touch on Select");
                return;
            }
            if (btnMemories.pointOnButton(touchPoint.x, touchPoint.y)) {
                Gdx.app.log("Tamagotchi", "Touch on Memories");
                return;
            }
            if (btnSettings.pointOnButton(touchPoint.x, touchPoint.y)) {
                Gdx.app.log("Tamagotchi", "Touch on Settings");
                return;
            }
        }
    }

    @Override
    public void draw(float delta) {
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
}
