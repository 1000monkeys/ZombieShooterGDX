package com.kjellvos.aletho.zombieshooter.gdx.loader.gson;

public class NestedSpriteData {
    private int spriteSheetId;
    private int positionX, positionY;
    private int widthInPixels, heightInPixels;

    /**
     * Sets the sprite texture data
     * @param spriteSheetId the id of the spritesheet
     * @param positionX the x position(top left corner of sprite texture)
     * @param positionY the y position(top left corner of sprite texture)
     * @param widthInPixels the width in pixels
     * @param heightInPixels the height in pixels
     */
    public NestedSpriteData(int spriteSheetId, int positionX, int positionY, int widthInPixels, int heightInPixels){
        this.spriteSheetId = spriteSheetId;
        this.positionX = positionX;
        this.positionY = positionY;
        this.widthInPixels = widthInPixels;
        this.heightInPixels = heightInPixels;
    }

    /**
     * Gets the spritesheet id
     * @return the spritesheet id
     */
    public int getSpriteSheetId() {
        return spriteSheetId;
    }

    /**
     * Gets the x position of the sprite(top left corner of texture)
     * @return the x position
     */
    public int getPositionX() {
        return positionX;
    }

    /**
     * Gets the y position of the sprite(top left corner of the texture)
     * @return the y position
     */
    public int getPositionY() {
        return positionY;
    }

    /**
     * Gets the width of the texture in pixels
     * @return width of texture in pixels
     */
    public int getWidthInPixels() {
        return widthInPixels;
    }

    /**
     * Gets the height of the texture in pixels
     * @return height of texture in pixels
     */
    public int getHeightInPixels() {
        return heightInPixels;
    }
}
