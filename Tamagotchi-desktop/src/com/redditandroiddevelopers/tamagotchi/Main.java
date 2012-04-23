
package com.redditandroiddevelopers.tamagotchi;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker;
import com.badlogic.gdx.tools.imagepacker.TexturePacker.Settings;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Tamagotchi";
        cfg.useGL20 = false;
        cfg.width = 800;
        cfg.height = 480;
        cfg.resizable = false;

        // TODO: Only pack textures during development, disable for release

        Settings texturePackerSettings = new Settings();
        texturePackerSettings.padding = 2;
        texturePackerSettings.maxWidth = 4096;
        texturePackerSettings.maxHeight = 4096;
        texturePackerSettings.incremental = true;
        texturePackerSettings.rotate = true;
        texturePackerSettings.stripWhitespace = true;
        TexturePacker.process(texturePackerSettings, "../Texture-assets",
                "../Tamagotchi-android/assets");

        new LwjglApplication(new TamagotchiGame(new TamagotchiConfiguration()), cfg);
    }
}
