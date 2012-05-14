
package com.redditandroiddevelopers.tamagotchi.creatures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
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
import com.redditandroiddevelopers.tamagotchi.screens.MainGameScreen;

import java.util.LinkedList;

/**
 * Class to handle methods common to all creatures.
 */
public abstract class CommonCreature extends Image implements OnActionCompleted {

    private static final String TAG = "Tamagotchi:CommonCreature";

    private final float initialScaleY;

    public Creature creatureModel;
    public boolean textureIsFlipped = false;

    // Some objects/variables for creature actions.
    private Boolean latestActionDone = true;
    private final LinkedList<Action> actionQueue = new LinkedList<Action>();

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

        // fixes bug #137: http://redmine.redditandroiddevelopers.com/issues/137
        initialScaleY = 1f;
    }

    /**
     * Function checking for creature actions, called in
     * {@link MainGameScreen#update(float)}.
     */
    public void lifeCycle() {
        synchronized (latestActionDone) {
            if (latestActionDone && !actionQueue.isEmpty()) {
                latestActionDone = false;
                action(actionQueue.poll());
            }
        }
    }

    /**
     * Gets the specific creature parameters.
     * This is delegated to concrete subclasses of this class.
     * 
     * @return Creature the creature model
     */
    protected abstract Creature getCreatureParameters();

    /* Creature controller starts here */

    private void offerAction(Action act) {
        act.setCompletionListener(this);
        actionQueue.offer(act);
    }

    @Override
    public void completed(Action action) {
        Gdx.app.debug(TAG, "Action " + action + " done");
        synchronized (latestActionDone) {
            latestActionDone = true;
        }
    }

    // movement

    /**
     * Makes the creature walk left or right.
     * 
     * @param x number of pixels (negative numbers are supported)
     * @param duration how long the animation will be
     */
    public void moveBy(float x, float duration) {
        // single wobble parameters
        float wobbleTime = 0.1f;
        float wobbleHeight = 0.05f;

        Sequence scaling = Sequence.$(ScaleTo.$(scaleX, initialScaleY + wobbleHeight, wobbleTime),
                ScaleTo.$(scaleX, initialScaleY, wobbleTime));

        // calculates how often the animation should be played
        int nrOfRepeats = Math.round(duration / (wobbleTime * 2));

        // chain wobbles together
        Repeat wobble = Repeat.$(scaling, nrOfRepeats);
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

        offerAction(parallel);
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

        offerAction(jump);
    }

    /**
     * Makes the creature roll around on the floor
     * 
     * @param x how far it should roll
     * @param duration how long it will roll
     */
    public void roll(float x, float duration) {
        Parallel parallel = Parallel.$(MoveBy.$(x, 0, duration),
                RotateBy.$(x > 0 ? -360f : 360f, duration));

        offerAction(parallel);
    }

    // showing speech bubbles

    /**
     * Generic method to display a speech bubble
     * 
     * @param textureRegionName
     */
    private void showBubble(String textureRegionName) {
        // TODO: to be implemented
    }

    /**
     * Shows a speech bubble to indicate poop
     */
    public void showPoopBubble() {
        // TODO: to be implemented
    }

    /**
     * Shows a speech bubble to indicate low health
     */
    public void showLowHealthBubble() {
        // TODO: to be implemented
    }

    /**
     * Shows a speech bubble to indicate a general alert
     */
    public void showAlertBubble() {
        // TODO: to be implemented
    }

    /**
     * Shows a speech bubble to indicate happiness
     */
    public void showHappyBubble() {
        // TODO: to be implemented
    }

    // for state changes access creatureModel directly

    /* Creature controller ends here */
}
