
package com.redditandroiddevelopers.tamagotchi.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.stbtt.TrueTypeFontFactory;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class FontHelper {

    public static final String FONT_CHARACTERS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.:;,{}\"´`'<>";

    public static BitmapFont createBitmapFont(String fontFile, float size, Stage stage) {
        return TrueTypeFontFactory.createBitmapFont(
                Gdx.files.internal(fontFile),
                FONT_CHARACTERS, stage.width(), stage.height(), size,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
