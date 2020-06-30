package com.kjellvos.aletho.zombieshooter.gdx.loader.gson;

public class GameDataGson {
    private int mainSpriteSheet;
    private int lightOffSpriteId;

    /**
     *
     * @param mainSpriteSheet the main spritesheet where most of the sprites are located
     * @param lightOffSpriteId the id of the light off sprite used for initializing lights in code
     */
    public GameDataGson(int mainSpriteSheet, int lightOffSpriteId){
        this.mainSpriteSheet = mainSpriteSheet;
        this.lightOffSpriteId = lightOffSpriteId;
    }

    /**
     * Gets the light off sprite id
     * @return the light off sprite id
     */
    public int getLightOffSpriteId() {
        return lightOffSpriteId;
    }

    /**
     * Gets the main spritesheet id
     * @return the main sprite sheet id
     */
    public int getMainSpriteSheet() {
        return mainSpriteSheet;
    }
}
