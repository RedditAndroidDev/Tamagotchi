
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

        /* XXX TEMPORARY! XXX */
        // TODO: Only pack textures during development, disable for release
        final Settings texturePackerSettings = new Settings();
        texturePackerSettings.padding = 2;
        texturePackerSettings.maxWidth = 1024;
        texturePackerSettings.maxHeight = 1024;
        texturePackerSettings.incremental = true;
        texturePackerSettings.rotate = false;
        texturePackerSettings.stripWhitespace = true;

        // create a texture atlas per screen
        final String[] screens = new String[] {
                "main-menu",
                "main-game",
                "create-creature"
        };
        for (String screen : screens) {
            final String srcdir = "../Texture-assets/" + screen;
            final String destdir = "../Tamagotchi-android/assets/" + screen;
            TexturePacker.process(texturePackerSettings, srcdir, destdir);
        }
        /* XXX END TEMPORARY XXX */

        new LwjglApplication(new TamagotchiGame(new TamagotchiConfiguration()), cfg);
    }
}
