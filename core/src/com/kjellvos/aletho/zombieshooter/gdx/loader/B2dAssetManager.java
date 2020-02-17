package com.kjellvos.aletho.zombieshooter.gdx.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;

public class B2dAssetManager {
    private ZombieShooterGame parent;
    private AssetManager assetManager;

    public B2dAssetManager(ZombieShooterGame zombieShooterGame){
        parent = zombieShooterGame;
        assetManager = new AssetManager();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void load(){
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("testmap.tmx", TiledMap.class);

        assetManager.load("0x72_16x16DungeonTilesetTogether.png", Texture.class);
    }
}
