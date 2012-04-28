
package com.redditandroiddevelopers.tamagotchi.creatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.redditandroiddevelopers.tamagotchi.model.Creature;
import com.redditandroiddevelopers.tamagotchi.model.Gender;

public class Creature3 extends CommonCreature {

    public Creature3(TextureRegion creatureDefaultTextureRegion) {
        super(creatureDefaultTextureRegion);
    }

    @Override
    protected Creature getCreatureParameters() {
        return Creature.createCreature("Creature3", Gender.FEMALE, null);
    }

}
