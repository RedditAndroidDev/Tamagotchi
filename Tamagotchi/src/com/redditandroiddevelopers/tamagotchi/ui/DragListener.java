package com.redditandroiddevelopers.tamagotchi.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Our custom Draggable interface which will receive delegated Drag events
 * from DragImage (or other classes using this implementation)
 * 
 * @author Jeffrey Selk
 *
 */
public interface DragListener {
    /**
     * Dispatch a drag event
     * @param a The actor on which the event hit
     * @param x The X coord of the touch translated to the scene
     * @param y The Y coord of the touch translated to the scene
     * @param pointer The pointer of the touch
     */
    void drag(Actor a, float x, float y, int pointer);
}
