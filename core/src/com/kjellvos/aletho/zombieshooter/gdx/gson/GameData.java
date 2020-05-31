package com.kjellvos.aletho.zombieshooter.gdx.gson;

public class GameData {
    private int playerSpriteId;
    private int mainSpriteSheet;
    private int lightOffSpriteId;

    public GameData(int playerSpriteId, int playerSpriteSheet, int lightOffSpriteId){
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
