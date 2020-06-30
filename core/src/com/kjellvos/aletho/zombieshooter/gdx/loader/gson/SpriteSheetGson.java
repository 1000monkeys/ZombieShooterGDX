package com.kjellvos.aletho.zombieshooter.gdx.loader.gson;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kjellvos.aletho.zombieshooter.gdx.errorhandling.SpriteSheetNotFoundException;

public class SpriteSheetGson {
    private String spriteSheetName;
    private int spriteSheetId;
    private int[] spriteSheetData;
    private TextureRegion sprite = null;

    private Texture spriteSheet = null;

    /**
     *
     * @param spriteSheetName the spritesheet name used for loading
     * @param spriteSheetId the id of the spritesheet
     * @param spriteSheetData the data belonging to the spritesheet such as width pixels per tile and more
     */
    public SpriteSheetGson(String spriteSheetName, int spriteSheetId, int[] spriteSheetData){
        this.spriteSheetName = spriteSheetName;
        this.spriteSheetId = spriteSheetId;
        this.spriteSheetData = spriteSheetData;
    }

    /**
     * Gets sprite by the specific SpriteGson
     * @param spriteGson the spritegson to get the texture region for
     * @return the sprite texture
     */
    public TextureRegion getSprite(SpriteGson spriteGson) {
        if (spriteSheet == null) {
            try {
                throw new SpriteSheetNotFoundException("Spritesheet '" + spriteGson.getSpriteSheetGson().getSpriteSheetName() + "' not found.");
            } catch (SpriteSheetNotFoundException e) {
                e.printStackTrace();
            }
        }

        return new TextureRegion(spriteSheet,
            spriteGson.getSpriteData().getPositionX(),
            spriteGson.getSpriteData().getPositionY(),
            spriteGson.getSpriteData().getWidthInPixels(),
            spriteGson.getSpriteData().getHeightInPixels());
    }

    /**
     * Gets the spritesheet texture
     * @return The spritesheet texture
     */
    public Texture getSpriteSheet() {
        return spriteSheet;
    }

    /**
     * Gets the spritesheet id
     * @return the spritesheet id
     */
    public int getSpriteSheetId() {
        return spriteSheetId;
    }

    /**
     * Gets the spritesheet data
     * @return int array containing the spritesheet data
     */
    public int[] getSpriteSheetData() {
        return spriteSheetData;
    }

    /**
     * Gets the spritesheet name
     * @return the spritesheet name
     */
    public String getSpriteSheetName() {
        return spriteSheetName;
    }

    /**
     * Sets the spritesheet texture
     * @param spriteSheet the spritesheet texture
     */
    public void setSpriteSheet(Texture spriteSheet) {
        this.spriteSheet = spriteSheet;
    }
}