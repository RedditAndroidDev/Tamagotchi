
package com.redditandroiddevelopers.tamagotchi.creatures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveBy;
import com.badlogic.gdx.scenes.scene2d.actions.Parallel;
import com.badlogic.gdx.scenes.scene2d.actions.Repeat;
import com.badlogic.gdx.scenes.scene2d.actions.RotateBy;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleTo;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.interpolators.AccelerateDecelerateInterpolator;
import com.badlogic.gdx.scenes.scene2d.interpolators.AccelerateInterpolator;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.redditandroiddevelopers.tamagotchi.model.Creature;

public abstract class CommonCreature extends Image {

    private static final String TAG = "Tamagotchi:CommonCreature";

    public Creature creatureModel;
    public boolean textureIsFlipped = false;

    public CommonCreature(TextureRegion creatureDefaultTextureRegion, String name) {
        super(creatureDefaultTextureRegion, Scaling.stretch, Align.CENTER, name);
        creatureModel = getCreatureParameters();
        originX = width / 2;
        originY = height / 2;
    }

    protected abstract Creature getCreatureParameters();

    @Override
    public Actor hit(float x, float y) {
        return this;
    }

    /* Creature controller starts here */

    // IMPORTANT: Animations are currently bugged. Will be fixed over time.

    // movement

    public void moveBy(float x, float duration) {
        // single wobble
        Sequence scaling = Sequence.$(ScaleTo.$(scaleX, scaleY + 0.05f, 0.1f),
                ScaleTo.$(scaleX, scaleY, 0.1f));

        // temporary dirty fix to prevent creature from "growing"
        scaleY = (int) scaleY;

        // calculates how often the animation should be played
        int times = Math.round(duration / 0.2f);

        // chain wobbles together
        Repeat wobble = Repeat.$(scaling, times);
        Parallel parallel = Parallel.$(MoveBy.$(x, 0, duration), wobble);

        // make sure the creature is facing the right way
        if (x > 0) {
            if (!textureIsFlipped) {
                this.getRegion().flip(true, false);
                textureIsFlipped = textureIsFlipped == false ? true : false;
            }
        }
        else if (x < 0) {
            if (textureIsFlipped) {
                this.getRegion().flip(true, false);
                textureIsFlipped = textureIsFlipped == true ? false : true;
            }
        }
        action(parallel);
    }

    public void jump(float y, float duration) {
        AccelerateDecelerateInterpolator gravity1 = AccelerateDecelerateInterpolator.$(2f);
        AccelerateInterpolator gravity2 = AccelerateInterpolator.$(1.5f);
        Sequence jump = Sequence.$(MoveBy.$(0, y, duration).setInterpolator(gravity1),
                MoveBy.$(0, -y, duration).setInterpolator(gravity2));
        action(jump);
    }

    public void roll(float x, float duration) {
        Parallel rotate = Parallel.$(MoveBy.$(x, 0, duration),
                RotateBy.$(x > 0 ? -360f : 360f, duration));
        action(rotate);
    }

    // showing speech bubbles

    public void showPoopBubble() {
    }

    public void showLowHealthBubble() {
    }

    public void showAlertBubble() {
    }

    public void showHappyBubble() {
    }

    // for state changes access creatureModel directly

    /* Creature controller ends here */
}
