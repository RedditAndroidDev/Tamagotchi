
package com.redditandroiddevelopers.tamagotchi.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.redditandroiddevelopers.tamagotchi.TamagotchiGame;

public class Button extends Table {

    Texture background;
    int nextState;
    Rectangle rectTouchArea;

    public Button(String texture, int nextState, int x, int y) {
        this.background = new Texture(Gdx.files.internal(texture));
        this.nextState = nextState;
        this.x = x;
        this.y = y;
        this.width = background.getWidth();
        this.height = background.getHeight();
        this.rectTouchArea = new Rectangle(x, Gdx.graphics.getHeight() - y - height, width, height);
    }

    /*
     * @see
     * com.badlogic.gdx.scenes.scene2d.Actor#draw(com.badlogic.gdx.graphics.
     * g2d.SpriteBatch, float)
     * @param SpriteBatch batch - the batch to use for drawing
     * @param float parentAlpha - the alpha of the parent Draws the Button to
     * the current screen
     */
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        batch.draw(background, x, y);
    }

    @Override
    public boolean touchDown(float x, float y, int pointer) {
        TamagotchiGame.MY_STATE = nextState;
        TamagotchiGame.STATE_CHANGE = true;
        return true;
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
     * Determines if a point lies on the button.
     * 
     * @param x X coordinate of the point
     * @param y Y coordinate of the point
     * @return <b>true</b>, if point lies within Button, <b>false</b>, if not
     */
    public boolean pointOnButton(float x, float y) {
        return rectTouchArea.x <= x && rectTouchArea.x + rectTouchArea.width >= x
                && rectTouchArea.y <= y
                && rectTouchArea.y + rectTouchArea.height >= y;
    }

    /*******************************************************************************
     * End of copyrighted code
     ******************************************************************************/

}
