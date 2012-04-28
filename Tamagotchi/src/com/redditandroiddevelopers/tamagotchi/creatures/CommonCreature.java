
package com.redditandroiddevelopers.tamagotchi.creatures;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CommonCreature extends Image {

    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
    }

    @Override
    public Actor hit(float x, float y) {
        return null;
    }
}
