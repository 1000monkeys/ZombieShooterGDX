package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.Gson;
import com.kjellvos.aletho.zombieshooter.gdx.gson.AnimationGson;
import com.kjellvos.aletho.zombieshooter.gdx.gson.GameDataGson;
import com.kjellvos.aletho.zombieshooter.gdx.gson.SpriteGson;
import com.kjellvos.aletho.zombieshooter.gdx.gson.SpriteSheetGson;

import java.util.Arrays;
import java.util.List;

public class ReadJsonGameFiles {
    private List<SpriteSheetGson> spriteSheetGsons;
    private List<SpriteGson> sprites;
    private List<AnimationGson> animationGsons;
    private GameDataGson gameDataGson;

    public ReadJsonGameFiles(String gameDataJSON, String spriteSheetsJSON, String spritesJSON, String animatonsJSON){
        Gson g = new Gson();

        gameDataGson = g.fromJson(gameDataJSON, GameDataGson[].class)[Constants.JSON_GAME_DATA];
        System.out.println(gameDataGson.getPlayerSpriteId() + " : " + gameDataGson.getMainSpriteSheet() + " : " + gameDataGson.getLightOffSpriteId());

        sprites = Arrays.asList(g.fromJson(spritesJSON, SpriteGson[].class));
        System.out.println(sprites.size());

        spriteSheetGsons = Arrays.asList(g.fromJson(spriteSheetsJSON, SpriteSheetGson[].class));
        System.out.println(spriteSheetGsons.size());
        for (int i = 0; i < spriteSheetGsons.size(); i++) {
            System.out.println(spriteSheetGsons.get(i).getSpriteSheetName());
        }

        for (int i = 0; i < sprites.size(); i++){
            sprites.get(i).setSprite(spriteSheetGsons.get(sprites.get(i).getSpriteData().getSpriteSheetId()).getSprite(sprites.get(i)));
            System.out.println("SETUP SPRITE: " + sprites.get(i).getDescription());
        }

        animationGsons = Arrays.asList(g.fromJson(animatonsJSON, AnimationGson[].class));
        System.out.println(animationGsons.size());
    }

    public List<SpriteSheetGson> getSpriteSheetGsons() {
        return spriteSheetGsons;
    }

    public List<AnimationGson> getAnimationGsons() {
        return animationGsons;
    }

    public List<SpriteGson> getSprites() {
        return sprites;
    }

    public SpriteGson getSpriteObj(int id){
        for(int i = 0; i < sprites.size(); i++){
            if (sprites.get(i).getId() == id) {
                return sprites.get(i);
            }
        }
        return null; //TODO
    }

    public SpriteGson[] getAnimation(int id){
        int[] spriteIds = animationGsons.get(id).getSpriteIds();
        SpriteGson[] spriteGsons = new SpriteGson[spriteIds.length];
        for (int i = 0; i < spriteIds.length; i++) {
            spriteGsons[i] = getSpriteObj(spriteIds[i]);
        }

        return spriteGsons;
    }

    public TextureRegion[] getAnimationTextures(int id) {
        SpriteGson[] spriteGsons = getAnimation(id);
        TextureRegion[] textureRegions = new TextureRegion[spriteGsons.length];
        System.out.println(id + " ANIMATION ID ");
        for (int i = 0; i < spriteGsons.length; i++){
            System.out.println(i + " + GOT HERE + ");
            textureRegions[i] = spriteGsons[i].getSprite();
        }
        return textureRegions;
    }

    public GameDataGson getGameDataGson() {
        return gameDataGson;
    }
}
