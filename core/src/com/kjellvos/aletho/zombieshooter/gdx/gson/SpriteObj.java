package com.kjellvos.aletho.zombieshooter.gdx.gson;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;

public class SpriteObj {
    private int id;
    private int[] spriteData;
    private boolean[] itemData;
    private String description;

    private SpriteSheet spriteSheet;
    private TextureRegion sprite = null;

    public SpriteObj(int id, int[] spriteData, boolean[] itemData, String description) {
        this.id = id;
        this.spriteData = spriteData;
        this.itemData = itemData;
        this.description = description;
    }

    public void setSprite(TextureRegion sprite) {
        this.sprite = sprite;
    }

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public boolean isItem(){
        return itemData[Constants.JSON_ITEM_DATA_IS_ITEM];
    }

    public boolean hasPickUpText(){
        return itemData[Constants.JSON_ITEM_DATA_PICK_UP_TEXT];
    }

    public boolean isStackable(){
        return itemData[Constants.JSON_ITEM_DATA_STACKABLE];
    }

    public int getWidthInPixels(){
        return spriteData[Constants.JSON_SPRITE_DATA_WIDTH_IN_PIXELS];
    }

    public int getHeightInPixels(){
        return spriteData[Constants.JSON_SPRITE_DATA_HEIGHT_IN_PIXELS];
    }

    public int getOffsetX(){
        return spriteData[Constants.JSON_SPRITE_DATA_OFFSET_X];
    }

    public int getOffsetY(){
        return spriteData[Constants.JSON_SPRITE_DATA_OFFSET_Y];
    }

    public TextureRegion getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public String getDescription() {
        return description;
    }
}