
package com.redditandroiddevelopers.tamagotchi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.redditandroiddevelopers.tamagotchi.ui.Button;

public class MainMenuScreen extends CommonScreen {

    SpriteBatch batch = new SpriteBatch();
    Stage stage;

    Rectangle rectPlay;
    Rectangle rectSelect;
    Rectangle rectMemories;
    Rectangle rectSettings;

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
        Button btnPlay = new Button("button-background.png", 0, 10, 235);
        rectPlay = new Rectangle(btnPlay.x, Gdx.graphics.getHeight() - btnPlay.y - btnPlay.height,
                btnPlay.width,
                btnPlay.height);
        stage.addActor(btnPlay);

        // adding the Select button
        Button btnSelect = new Button("button-background.png", 0, 10, 160);
        rectSelect = new Rectangle(btnSelect.x, Gdx.graphics.getHeight() - btnSelect.y
                - btnSelect.height,
                btnSelect.width, btnSelect.height);
        stage.addActor(btnSelect);

        // adding the Memories button
        Button btnMemories = new Button("button-background.png", 0, 10, 85);
        rectMemories = new Rectangle(btnMemories.x, Gdx.graphics.getHeight() - btnMemories.y
                - btnMemories.height,
                btnMemories.width, btnMemories.height);
        stage.addActor(btnMemories);

        // adding the Settings button
        Button btnSettings = new Button("button-background.png", 0, 10, 10);
        rectSettings = new Rectangle(btnSettings.x, Gdx.graphics.getHeight() - btnSettings.y
                - btnSettings.height,
                btnSettings.width, btnSettings.height);
        stage.addActor(btnSettings);
    }

    @Override
    public void update(float delta) {

        // check for touch input
        if (Gdx.input.justTouched()) {
            // update touch point
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY());

            // check if touchpoint lies in one of the defined rectangles
            if (pointInRectangle(rectPlay, touchPoint.x, touchPoint.y)) {
                Gdx.app.log("Tamagotchi", "Touch on Play");
                return;
            }
            if (pointInRectangle(rectSelect, touchPoint.x, touchPoint.y)) {
                Gdx.app.log("Tamagotchi", "Touch on Select");
                return;
            }
            if (pointInRectangle(rectMemories, touchPoint.x, touchPoint.y)) {
                Gdx.app.log("Tamagotchi", "Touch on Memories");
                return;
            }
            if (pointInRectangle(rectSettings, touchPoint.x, touchPoint.y)) {
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

    /*******************************************************************************
     * Copyright 2011
     * <badlogicgames@gmail.com> and Nathan Sweet <nathan.sweet@gmail.com>
     * Licensed under the Apache License, Version 2.0 (the "License");
     * you may not use this file except in compliance with the License.
     * You may obtain a copy of the License at
     * http://www.apache.org/licenses/LICENSE-2.0
     * Unless required by applicable law or agreed to in writing, software
     * distributed under the License is distributed on an "AS IS" BASIS,
     * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     * See the License for the specific language governing permissions and
     * limitations under the License.
     ******************************************************************************/

    /**
     * Determines if a point lies within a rectangle.
     * @param rect The rectangle
     * @param x X coordinate of the point
     * @param y Y coordinate of the point
     * @return <b>true</b>, if point lies within rectangle, <b>false</b>, if not
     */
    public static boolean pointInRectangle(Rectangle rect, float x, float y) {
        return rect.x <= x && rect.x + rect.width >= x && rect.y <= y && rect.y + rect.height >= y;
    }

    /*******************************************************************************
     * End of copyrighted code
     ******************************************************************************/
}
