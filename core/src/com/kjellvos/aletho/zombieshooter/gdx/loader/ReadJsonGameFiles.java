package com.kjellvos.aletho.zombieshooter.gdx.loader;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.gson.Gson;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.errorhandling.AnimationNotFoundException;
import com.kjellvos.aletho.zombieshooter.gdx.errorhandling.SpriteNotFoundException;
import com.kjellvos.aletho.zombieshooter.gdx.errorhandling.SpriteSheetNotFoundException;
import com.kjellvos.aletho.zombieshooter.gdx.loader.gson.AnimationGson;
import com.kjellvos.aletho.zombieshooter.gdx.loader.gson.GameDataGson;
import com.kjellvos.aletho.zombieshooter.gdx.loader.gson.SpriteGson;
import com.kjellvos.aletho.zombieshooter.gdx.loader.gson.SpriteSheetGson;

import java.util.Arrays;
import java.util.List;

public class ReadJsonGameFiles {
    private ZombieShooterGame parent;

    private List<SpriteSheetGson> spriteSheetGsons;
    private List<SpriteGson> sprites;
    private List<AnimationGson> animationGsons;
    private GameDataGson gameDataGson;

    /**
     * Constructor of this class
     * @param parent the parent class(ZombieShooterGame)
     * @param gameDataJSON the JSON string of the gamedata.json file
     * @param spriteSheetsJSON the JSON string of the spritesheets.json file
     * @param spritesJSON the JSON string ot the sprites.json file
     * @param animatonsJSON the JSON string of the animations.json file
     */
    public ReadJsonGameFiles(ZombieShooterGame parent, String gameDataJSON, String spriteSheetsJSON, String spritesJSON, String animatonsJSON) {
        this.parent = parent;

        Gson g = new Gson();

        gameDataGson = g.fromJson(gameDataJSON, GameDataGson[].class)[Constants.JSON_GAME_DATA];
        if (Constants.DEBUG) {
            System.out.println("Main sprite sheet id: " + gameDataGson.getMainSpriteSheet());
            System.out.println("Light off sprite id: " + gameDataGson.getLightOffSpriteId());
        }

        sprites = Arrays.asList(g.fromJson(spritesJSON, SpriteGson[].class));
        if (Constants.DEBUG) {
            System.out.println("Sprites array size is: " + sprites.size());
        }

        spriteSheetGsons = Arrays.asList(g.fromJson(spriteSheetsJSON, SpriteSheetGson[].class));
        if (Constants.DEBUG) {
            System.out.println("Spritesheet array size is:" + spriteSheetGsons.size());
        }

        animationGsons = Arrays.asList(g.fromJson(animatonsJSON, AnimationGson[].class));
        if (Constants.DEBUG) {
            System.out.println("Animations array size is: " + animationGsons.size());
        }
    }

    /**
     * Gets the GSON spritesheet list
     * @return the list containing all spritesheets as a GSON object
     */
    public List<SpriteSheetGson> getSpriteSheetGsons() {
        return spriteSheetGsons;
    }

    /**
     * Gets the GSON animation list
     * @return the list containing all the animation data as a GSON object
     */
    public List<AnimationGson> getAnimationGsons() {
        return animationGsons;
    }

    /**
     * Gets the GSON sprites list
     * @return the list containing all the sprites as a GSON object
     */
    public List<SpriteGson> getSprites() {
        return sprites;
    }

    /**
     * Gets a specific sprite object(GSON)
     * @param id the id of the specific spriteGson you want to get
     * @return the sprite that belongs the the id or throws a new exception
     */
    public SpriteGson getSpriteGson(int id){
        for(int i = 0; i < sprites.size(); i++){
            if (sprites.get(i).getId() == id) {
                return sprites.get(i);
            }
        }

        if (Constants.DEBUG) {
            try {
                throw new SpriteNotFoundException("Sprite with id: " + id + " not found.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * Gets a specific animation object(GSON)
     * @param id the id of the specific animationGson you want to get
     * @return the animation that belongs the the id or throws a new exception
     */
    public AnimationGson getAnimationGson(int id){
        for(int i = 0; i < animationGsons.size(); i++){
            if (animationGsons.get(i).getId() == id) {
                return animationGsons.get(i);
            }
        }
        if (Constants.DEBUG) {
            try {
                throw new AnimationNotFoundException("Animation with id: " + id + "' not found.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Gets a spriteGson array used for animation
     * @param id the animation id to get spriteGsons for
     * @return a spriteGson array
     */
    public SpriteGson[] getAnimationSpriteGson(int id){
        int[] spriteIds = animationGsons.get(id).getSpriteIds();
        SpriteGson[] spriteGsons = new SpriteGson[spriteIds.length];
        for (int i = 0; i < spriteIds.length; i++) {
            spriteGsons[i] = getSpriteGson(spriteIds[i]);
        }

        return spriteGsons;
    }

    /**
     * Gets a TextureRegion array for the passed animation id
     * @param id the animation id to get the textures for
     * @return texture region array for use in animation
     */
    public TextureRegion[] getAnimationTextures(int id) {
        SpriteGson[] spriteGsons = getAnimationSpriteGson(id);
        TextureRegion[] textureRegions = new TextureRegion[spriteGsons.length];
        for (int i = 0; i < spriteGsons.length; i++){
            if (Constants.DEBUG) {
                System.out.println("Animation id: " + id + " & Sprite Id: " + i);
            }

            textureRegions[i] = spriteGsons[i].getSprite();
        }
        return textureRegions;
    }

    /**
     * Gets the gamedata Gson
     * @return the gamedata Gson
     */
    public GameDataGson getGameDataGson() {
        return gameDataGson;
    }

    /**
     * Sets the on the spritesheet gson and sets the sprites from the spritesheets
     */
    public void setupWithAssets() {
        for (int i = 0; i < spriteSheetGsons.size(); i++) {
            if (Constants.DEBUG) {
                System.out.println("Setting up spritesheet for: " + "[" + spriteSheetGsons.get(i).getSpriteSheetId() + "]" + spriteSheetGsons.get(i).getSpriteSheetName());
            }

            if (parent.getAssetManager().getAssetManager().contains(spriteSheetGsons.get(i).getSpriteSheetName())) {
                spriteSheetGsons.get(i).setSpriteSheet(parent.getAssetManager().getAssetManager().get(spriteSheetGsons.get(i).getSpriteSheetName(), Texture.class));
            } else {
                try {
                    throw new SpriteSheetNotFoundException("Spritesheet '" + spriteSheetGsons.get(i).getSpriteSheetName() + "' not found.");
                } catch (Exception e) {
                    if (Constants.DEBUG) {
                        e.printStackTrace();
                    }
                }
            }

            if (Constants.DEBUG) {
                System.out.println("Loaded sprite sheet: " + spriteSheetGsons.get(i).getSpriteSheetName());
            }
        }

        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).setSprite(spriteSheetGsons.get(sprites.get(i).getSpriteData().getSpriteSheetId()).getSprite(sprites.get(i)));

            if (Constants.DEBUG) {
                System.out.println("Setup sprite: [" + sprites.get(i).getId() +"]" + sprites.get(i).getDescription());
            }
        }
    }
}
