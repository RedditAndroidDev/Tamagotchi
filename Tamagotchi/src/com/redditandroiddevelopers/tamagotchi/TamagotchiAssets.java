
package com.redditandroiddevelopers.tamagotchi;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Collect all assets referenced throughout the game.
 * 
 * @author Santoso Wijaya
 */
public class TamagotchiAssets {

    private final AssetManager assetManager;

    public enum TextureAsset {
        APP_NAME("MainMenu/AppName.png"),
        BTN_PLAY_UNPRESSED("MainMenu/btn_play_unpressed.png"),
        BTN_SELECT_UNPRESSED("MainMenu/btn_select_unpressed.png"),
        BTN_MEMORIES_UNPRESSED("MainMenu/btn_memories_unpressed.png"),
        BTN_SETTINGS_UNPRESSED("MainMenu/btn_settings_unpressed.png"),
        BTN_STATUS("InGame/button.png"),
        DRG_ARROW("InGame/arrow.png");

        private final String textureFile;

        private TextureAsset(String textureFile) {
            this.textureFile = textureFile;
        }

        public String getTextureFile() {
            return textureFile;
        }
    }

    public TamagotchiAssets(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    /**
     * Asynchronously load all assets.
     */
    public void loadAssets() {
        for (TextureAsset textureAsset : TextureAsset.values()) {
            assetManager.load(textureAsset.getTextureFile(), Texture.class);
        }
    }

    public boolean update() {
        return assetManager.update();
    }

    public float getProgress() {
        return assetManager.getProgress();
    }

    public Texture getTexture(TextureAsset asset) {
        if (assetManager.isLoaded(asset.getTextureFile())) {
            return assetManager.get(asset.getTextureFile(), Texture.class);
        } else {
            assert false;
            throw new RuntimeException("Texture " + asset.getTextureFile() + " is not yet loaded");
        }
    }
}
