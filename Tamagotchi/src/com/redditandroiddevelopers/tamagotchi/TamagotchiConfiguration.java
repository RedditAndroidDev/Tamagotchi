
package com.redditandroiddevelopers.tamagotchi;

import com.badlogic.gdx.Application;

/**
 * A place to store runtime configuration for use throughout the lifetime of a
 * {@link TamagotchiGame} object. Not to be confused with a store for game
 * settings.
 * 
 * @author Santoso Wijaya
 */
public class TamagotchiConfiguration {

    public boolean debug = true;
    public boolean logFps = true;
    public int logLevel = Application.LOG_DEBUG;
    public float stageWidth = 800;
    public float stageHeight = 480;

}
