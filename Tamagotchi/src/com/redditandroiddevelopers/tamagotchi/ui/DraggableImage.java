package com.redditandroiddevelopers.tamagotchi.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * A custom implementation of a libGDX Image which overrides the drag functions
 * to allow dragging via a custom Draggable interface.
 * 
 * @author Jeffrey Selk
 * 
 */
public class DraggableImage extends Image {

    /**
     * The Draggable instance which will receive delegated Drag events
     */
    private Draggable draggable;

    /**
     * Constructor (the only one needed thus far, override others as needed)
     * 
     * @param texture
     *            The texture of the Image
     */
    public DraggableImage(Texture texture) {
        super(texture);
    }

    /**
     * Set the Draggable instance
     * 
     * @param d
     *            The Draggable instance
     */
    public void setDraggable(Draggable d) {
        this.draggable = d;
    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.scenes.scene2d.ui.Image#touchDragged(float, float, int)
     */
    @Override
    public void touchDragged(float x, float y, int pointer) {
        if (draggable != null) {
            draggable.drag(this, x, y, pointer);
        }
    }
}
