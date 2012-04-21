
package com.redditandroiddevelopers.tamagotchi;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Collect all assets referenced throughout the game.
 * 
 * @author Santoso Wijaya
 */
public class TamagotchiAssets {

    private final AssetManager assetManager;

    public interface Asset<T> {

        public String getFileDescriptor();

        public Class<T> getAssetType();

    }

    public enum TextureAsset implements Asset<Texture> {

        APP_NAME("MainMenu/AppName.png"),
        BTN_PLAY_UNPRESSED("MainMenu/btn_play_unpressed.png"),
        BTN_SELECT_UNPRESSED("MainMenu/btn_select_unpressed.png"),
        BTN_MEMORIES_UNPRESSED("MainMenu/btn_memories_unpressed.png"),
        BTN_SETTINGS_UNPRESSED("MainMenu/btn_settings_unpressed.png"),
        BTN_STATUS("InGame/button.png"),
        DRG_ARROW("InGame/arrow.png");

        protected static final Class<? extends Object> type = Texture.class;

        private final String textureFile;

        private TextureAsset(String textureFile) {
            this.textureFile = textureFile;
        }

        @Override
        public String getFileDescriptor() {
            return textureFile;
        }

        @Override
        public Class<Texture> getAssetType() {
            return Texture.class;
        }

    }

    public enum FontAsset implements Asset<BitmapFont> {

        DEFAULT("");
        // TODO: need moar fonts!

        protected static final Class<? extends Object> type = BitmapFont.class;

        /**
         * A BMFont file. The image file for texture should be in the same
         * directory.
         */
        private final String fontFile;

        private FontAsset(String fontFile) {
            this.fontFile = fontFile;
        }

        @Override
        public String getFileDescriptor() {
            return fontFile;
        }

        @Override
        public Class<BitmapFont> getAssetType() {
            return BitmapFont.class;
        }
    }

    public TamagotchiAssets(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    /**
     * Asynchronously load all assets. Test for completion by calling
     * {@link TamagotchiAssets#update()} or
     * {@link TamagotchiAssets#getProgress()}.
     */
    public void loadAssets() {
        // load textures
        for (TextureAsset textureAsset : TextureAsset.values()) {
            assetManager.load(textureAsset.textureFile, textureAsset.getAssetType());
        }

        // load fonts
        for (FontAsset fontAsset : FontAsset.values()) {
            if (fontAsset == FontAsset.DEFAULT)
                continue;
            assetManager.load(fontAsset.fontFile, fontAsset.getAssetType());
        }
    }

    /**
     * Asynchronously updates outstanding assets.
     * 
     * @return true if all assets are finished loading
     */
    public boolean update() {
        return assetManager.update();
    }

    /**
     * @return the progress of asset loading in percentage
     */
    public float getProgress() {
        return assetManager.getProgress();
    }

    public <T> T getAsset(Asset<T> asset) {
        if (asset == FontAsset.DEFAULT) {
            assert asset.getAssetType() == BitmapFont.class;
            return asset.getAssetType().cast(new BitmapFont());
        }

        if (assetManager.isLoaded(asset.getFileDescriptor())) {
            return assetManager.get(asset.getFileDescriptor(), asset.getAssetType());
        } else {
            assert false;
            throw new RuntimeException("Asset " + asset.getFileDescriptor() + " is not yet loaded");
        }
    }
}
