
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

/**
 * Class to handle methods common to all creatures.
 */
public abstract class CommonCreature extends Image {

    private static final String TAG = "Tamagotchi:CommonCreature";

    public Creature creatureModel;
    public boolean textureIsFlipped = false;
    Parallel rotate = new Parallel();
    /**
     * Creates a new CommonCreature.
     * 
     * @param creatureDefaultTextureRegion
     * @param name
     */
    public CommonCreature(TextureRegion creatureDefaultTextureRegion, String name) {
        super(creatureDefaultTextureRegion, Scaling.stretch, Align.CENTER, name);

        // get creature parameters from child class
        creatureModel = getCreatureParameters();

        // set origin for rotations in the center of the creature
        originX = width / 2;
        originY = height / 2;
    }

    /**
     * Gets the specific creature parameters.
     * 
     * @return Creature
     */
    protected abstract Creature getCreatureParameters();

    @Override
    public Actor hit(float x, float y) {
        return super.hit(x, y);
    }

    /* Creature controller starts here */

    // IMPORTANT: Animations are currently bugged. Will be fixed over time.

    // movement

    /**
     * Makes the creature walk left or right.
     * 
     * @param x number of pixels (negative numbers are supported)
     * @param duration how long the animation will be
     */
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

    /**
     * Makes the creature jump in the air.
     */
    public void jump() {
        jump(50, 0.25f);
    }

    /**
     * Makes the creature jump the specified height during a certain time.
     * 
     * @param y jump height
     * @param duration how long the jump should be
     */
    private void jump(float y, float duration) {
        AccelerateDecelerateInterpolator gravity1 = AccelerateDecelerateInterpolator.$(2f);
        AccelerateInterpolator gravity2 = AccelerateInterpolator.$(1.5f);
        Sequence jump = Sequence.$(MoveBy.$(0, y, duration).setInterpolator(gravity1),
                MoveBy.$(0, -y, duration).setInterpolator(gravity2));
        action(jump);
    }

    /**
     * Makes the creature roll around on the floor
     * 
     * @param x how far it should roll
     * @param duration how long it will roll
     */
    public void roll(float x, float duration) {
    	if(rotate.isDone()) {
        	rotate = Parallel.$(MoveBy.$(x, 0, duration),
        			RotateBy.$(x > 0 ? -360f : 360f, duration));
        	action(rotate);
    	}
    }

    // showing speech bubbles

    /**
     * Generic method to display a speech bubble
     * 
     * @param textureRegionName
     */
    private void showBubble(String textureRegionName) {
    }

    /**
     * Shows a speech bubble to indicate poop
     */
    public void showPoopBubble() {
    }

    /**
     * Shows a speech bubble to indicate low health
     */
    public void showLowHealthBubble() {
    }

    /**
     * Shows a speech bubble to indicate a general alert
     */
    public void showAlertBubble() {
    }

    /**
     * Shows a speech bubble to indicate happiness
     */
    public void showHappyBubble() {
    }

    // for state changes access creatureModel directly

    /* Creature controller ends here */
}
