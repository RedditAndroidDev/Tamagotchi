
package com.redditandroiddevelopers.tamagotchi.creatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.redditandroiddevelopers.tamagotchi.model.Creature;

public abstract class CommonCreature extends Image {

    protected Creature creatureModel;

    public CommonCreature(TextureRegion creatureDefaultTextureRegion) {
        super(creatureDefaultTextureRegion);
        creatureModel = getCreatureParameters();
    }

    protected abstract Creature getCreatureParameters();

    @Override
    public Actor hit(float x, float y) {
        return null;
    }
}
