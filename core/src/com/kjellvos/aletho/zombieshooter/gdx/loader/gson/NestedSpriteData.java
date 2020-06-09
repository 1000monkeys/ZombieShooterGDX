package com.kjellvos.aletho.zombieshooter.gdx.loader.gson;

public class NestedSpriteData {
    private int spriteSheetId;
    private int positionX, positionY;
    private int widthInPixels, heightInPixels;

    public NestedSpriteData(int spriteSheetId, int positionX, int positionY, int widthInPixels, int heightInPixels){
        this.spriteSheetId = spriteSheetId;
        this.positionX = positionX;
        this.positionY = positionY;
        this.widthInPixels = widthInPixels;
        this.heightInPixels = heightInPixels;
    }

    public int getSpriteSheetId() {
        return spriteSheetId;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getWidthInPixels() {
        return widthInPixels;
    }

    public int getHeightInPixels() {
        return heightInPixels;
    }
}
