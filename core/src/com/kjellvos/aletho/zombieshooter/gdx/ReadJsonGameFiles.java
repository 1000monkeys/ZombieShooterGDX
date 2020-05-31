package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.Gson;
import com.kjellvos.aletho.zombieshooter.gdx.gson.Animation;
import com.kjellvos.aletho.zombieshooter.gdx.gson.GameData;
import com.kjellvos.aletho.zombieshooter.gdx.gson.SpriteObj;
import com.kjellvos.aletho.zombieshooter.gdx.gson.SpriteSheet;

import javax.xml.soap.Text;
import java.util.Arrays;
import java.util.List;

public class ReadJsonGameFiles {
    private List<SpriteSheet> spriteSheets;
    private List<SpriteObj> sprites;
    private List<Animation> animations;
    private GameData gameData;

    public ReadJsonGameFiles(String gameDataJSON, String spriteSheetsJSON, String spritesJSON, String animatonsJSON){
        Gson g = new Gson();

        gameData = g.fromJson(gameDataJSON, GameData[].class)[Constants.JSON_GAME_DATA];
        System.out.println(gameData.getPlayerSpriteId() + " : " + gameData.getMainSpriteSheet() + " : " + gameData.getLightOffSpriteId());

        sprites = Arrays.asList(g.fromJson(spritesJSON, SpriteObj[].class));
        System.out.println(sprites.size());

        spriteSheets = Arrays.asList(g.fromJson(spriteSheetsJSON, SpriteSheet[].class));
        System.out.println(spriteSheets.size());
        for (int i = 0; i < spriteSheets.size(); i++) {
            System.out.println(spriteSheets.get(i).getSpriteSheetName());
        }

        for (int i = 0; i < sprites.size(); i++){
            sprites.get(i).setSprite(spriteSheets.get(gameData.getMainSpriteSheet()).getSprite(sprites.get(i)));
        }

        animations = Arrays.asList(g.fromJson(animatonsJSON, Animation[].class));
        System.out.println(animations.size());
    }

    public List<SpriteSheet> getSpriteSheets() {
        return spriteSheets;
    }

    public List<Animation> getAnimations() {
        return animations;
    }

    public List<SpriteObj> getSprites() {
        return sprites;
    }

    public SpriteObj getSpriteObj(int id){
        for(int i = 0; i < sprites.size(); i++){
            if (sprites.get(i).getId() == id) {
                return sprites.get(i);
            }
        }
        return null; //TODO
    }

    public SpriteObj[] getAnimation(int id){
        int[] spriteIds = animations.get(id).getSpriteIds();
        SpriteObj[] spriteObjs = new SpriteObj[spriteIds.length];
        for (int i = 0; i < spriteIds.length; i++) {
            spriteObjs[i] = getSpriteObj(spriteIds[i]);
        }

        return spriteObjs;
    }

    public TextureRegion[] getAnimationTextures(int id) {
        SpriteObj[] spriteObjs = getAnimation(id);
        TextureRegion[] textureRegions = new TextureRegion[spriteObjs.length];
        for (int i = 0; i < spriteObjs.length; i++){
            textureRegions[i] = spriteObjs[i].getSprite();
        }
        return textureRegions;
    }

    public GameData getGameData() {
        return gameData;
    }
}
