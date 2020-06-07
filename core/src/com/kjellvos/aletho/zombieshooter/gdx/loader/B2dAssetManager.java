package com.kjellvos.aletho.zombieshooter.gdx.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;
import com.kjellvos.aletho.zombieshooter.gdx.ReadJsonGameFiles;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.errorhandling.AnimationNotFoundException;
import com.kjellvos.aletho.zombieshooter.gdx.errorhandling.GameDataNotFoundException;
import com.kjellvos.aletho.zombieshooter.gdx.errorhandling.SpriteNotFoundException;
import com.kjellvos.aletho.zombieshooter.gdx.errorhandling.SpriteSheetNotFoundException;
import com.kjellvos.aletho.zombieshooter.gdx.views.GameScreen;

public class B2dAssetManager {
    /**
     * See {@link GameScreen#parent}
     */
    private ZombieShooterGame parent;

    private AssetManager assetManager;

    public B2dAssetManager(ZombieShooterGame zombieShooterGame){
        parent = zombieShooterGame;
        assetManager = new AssetManager();
    }

    /**
     * returns the assetmanager, for getting the assets.
     * @return AssetManager containing the loaded/loading assets.
     */
    public AssetManager getAssetManager() {
        return assetManager;
    }

    /**
     * function containing all the assets to be loaded.
     */
    public void load(){
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("testmap.tmx", TiledMap.class);

        for (int i = 0; i < parent.getReadJsonGameFiles().getSpriteSheetGsons().size(); i++){
            assetManager.load(parent.getReadJsonGameFiles().getSpriteSheetGsons().get(i).getSpriteSheetName(), Texture.class);
        }

        assetManager.load("music_scott_lawlor_strange_lullaby.mp3", Music.class);
        assetManager.load("music_zapsplat_hallowdream.mp3", Music.class);
        assetManager.load("music_zapsplat_night_stalker.mp3", Music.class);
    }
}
