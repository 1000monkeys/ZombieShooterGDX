package com.kjellvos.aletho.zombieshooter.gdx.loader.gson;

public class GameDataGson {
    private int playerSpriteId;
    private int mainSpriteSheet;
    private int lightOffSpriteId;

    public GameDataGson(int playerSpriteId, int playerSpriteSheet, int lightOffSpriteId){
        this.playerSpriteId = playerSpriteId;
        this.mainSpriteSheet = playerSpriteSheet;
        this.lightOffSpriteId = lightOffSpriteId;
    }

    public int getPlayerSpriteId() {
        return playerSpriteId;
    }

    public int getLightOffSpriteId() {
        return lightOffSpriteId;
    }

    public int getMainSpriteSheet() {
        return mainSpriteSheet;
    }
}
