
package com.redditandroiddevelopers.tamagotchi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Collect all assets referenced throughout the game.
 * 
 * @author Santoso Wijaya
 */
public class TamagotchiAssets {

    private static final String TAG = "Tamagotchi:Assets";

    private final AssetManager assetManager;

    public interface Asset<T> {

        public String getFileDescriptor();

        public Class<T> getAssetType();

    }

    public enum TextureAtlasAsset implements Asset<TextureAtlas> {

        MAIN_MENU("main-menu/pack"),
        MAIN_GAME("main-game/pack"),
        CREATE_CREATURE("create-creature/pack");

        private final String textureFile;

        private TextureAtlasAsset(String textureFile) {
            this.textureFile = textureFile;
        }

        @Override
        public String getFileDescriptor() {
            return textureFile;
        }

        @Override
        public Class<TextureAtlas> getAssetType() {
            return TextureAtlas.class;
        }

    }

    public enum FontAsset implements Asset<BitmapFont> {

        ROBOTO("fonts/Roboto.fnt");
        // TODO: need moar fonts!

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
     * Asynchronously load an individual asset. Test for completion by calling
     * {@link TamagotchiAssets#update()} or
     * {@link TamagotchiAssets#getProgress()}.
     * 
     * @param asset the asset to load
     */
    public <T> void loadAsset(Asset<T> asset) {
        final String fd = asset.getFileDescriptor();
        final Class<T> type = asset.getAssetType();
        Gdx.app.log(TAG, "Loading asset:<" + fd + "> type:<" + type.toString() + ">");
        assetManager.load(fd, type);
    }

    /**
     * Asynchronously unload an individual asset.
     * 
     * @param asset the asset to unload
     */
    public <T> void unloadAsset(Asset<T> asset) {
        final String fd = asset.getFileDescriptor();
        Gdx.app.log(TAG, "Unloading asset:<" + fd + ">");
        assetManager.unload(fd);
    }

    /**
     * Asynchronously load all assets. Test for completion by calling
     * {@link TamagotchiAssets#update()} or
     * {@link TamagotchiAssets#getProgress()}.
     */
    public void loadAssets() {
        // load texture atlases
        for (TextureAtlasAsset textureAtlasAsset : TextureAtlasAsset.values()) {
            loadAsset(textureAtlasAsset);
        }
        // load fonts
        for (FontAsset fontAsset : FontAsset.values()) {
            loadAsset(fontAsset);
        }
    }

    /**
     * Asynchronously unload all assets.
     */
    public void unloadAssets() {
        // unload texture atlases
        for (TextureAtlasAsset textureAtlasAsset : TextureAtlasAsset.values()) {
            unloadAsset(textureAtlasAsset);
        }
        // load fonts
        for (FontAsset fontAsset : FontAsset.values()) {
            unloadAsset(fontAsset);
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
        final Class<T> assetType = asset.getAssetType();
        if (assetManager.isLoaded(asset.getFileDescriptor())) {
            return assetManager.get(asset.getFileDescriptor(), assetType);
        } else {
            assert false;
            throw new RuntimeException("Asset " + asset.getFileDescriptor() + " is not yet loaded");
        }
    }

}
