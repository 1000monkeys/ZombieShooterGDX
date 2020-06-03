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
    private ReadJsonGameFiles readJsonGameFiles;
    private String gameDataJSON = null, spriteSheetsJSON = null, spritesJSON = null, animationsJSON = null;

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
        gameDataJSON = Gdx.files.internal(Constants.GAMEDATA_JSON).readString();
        spriteSheetsJSON = Gdx.files.internal(Constants.SPRITESHEET_JSON).readString();
        spritesJSON = Gdx.files.internal(Constants.SPRITES_JSON).readString();
        animationsJSON = Gdx.files.internal(Constants.ANIMATIONS_JSON).readString();
        readJsonGameFiles = new ReadJsonGameFiles(parent, gameDataJSON, spriteSheetsJSON, spritesJSON, animationsJSON);
        parent.setReadJsonGameFiles(readJsonGameFiles);

        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.load("testmap.tmx", TiledMap.class);

        for (int i = 0; i < readJsonGameFiles.getSpriteSheetGsons().size(); i++){
            assetManager.load(readJsonGameFiles.getSpriteSheetGsons().get(i).getSpriteSheetName(), Texture.class);
        }

        assetManager.load("music_scott_lawlor_strange_lullaby.mp3", Music.class);
        assetManager.load("music_zapsplat_hallowdream.mp3", Music.class);
        assetManager.load("music_zapsplat_night_stalker.mp3", Music.class);
        assetManager.finishLoading();

        readJsonGameFiles.setupWithAssets();
    }

    public String getGameDataJSON() {
        if (gameDataJSON == null) {
            try {
                throw new GameDataNotFoundException("The gamedata JSON file was null.");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return gameDataJSON;
    }

    public String getSpriteSheetsJSON() {
        if (spriteSheetsJSON == null) {
            try {
                throw new SpriteSheetNotFoundException("The sprite sheet JSON file was null.");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return spriteSheetsJSON;
    }

    public String getSpritesJSON() {
        if (spritesJSON == null) {
            try {
                throw new SpriteNotFoundException("The sprite JSON file was null.");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return spritesJSON;
    }

    public String getAnimationsJSON() {
        if (animationsJSON == null) {
            try {
                throw new AnimationNotFoundException("The animation JSON file was null.");
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return animationsJSON;
    }
}
