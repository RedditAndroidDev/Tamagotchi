
package com.redditandroiddevelopers.tamagotchi.creatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.redditandroiddevelopers.tamagotchi.model.Creature;
import com.redditandroiddevelopers.tamagotchi.model.Gender;

public class Creature2 extends CommonCreature {

    public Creature2(TextureRegion creatureDefaultTextureRegion, String name) {
        super(creatureDefaultTextureRegion, name);
    }

    @Override
    protected Creature getCreatureParameters() {
        return Creature.createCreature("Creature2", Gender.FEMALE, null);
    }

}
