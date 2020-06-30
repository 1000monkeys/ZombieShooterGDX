package com.kjellvos.aletho.zombieshooter.gdx.loader.gson;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteGson {
    private int id;
    private NestedSpriteData spriteData;
    private boolean isItem;
    private NestedItemData itemData;
    private String description;

    private SpriteSheetGson spriteSheetGson;
    private TextureRegion sprite = null;

    /**
     * Constructor to be used if the sprite is an item
     * @param id The id of the sprite
     * @param spriteData The spritedata(width in pixels, which spritesheet etc)
     * @param isItem Boolean value on whether this sprite is an item or not
     * @param itemData Itemdata such as stackable/pick up text
     * @param description A small string describing the object
     */
    public SpriteGson(int id, NestedSpriteData spriteData, boolean isItem, NestedItemData itemData, String description) {
        this.id = id;
        this.spriteData = spriteData;
        this.isItem = isItem;
        this.itemData = itemData;
        this.description = description;
    }

    /**
     * Constructor to be used if sprite is not an item
     * @param id The id of the sprite
     * @param spriteData The spritedata(width in pixels, which spritesheet etc)
     * @param isItem Boolean value on whether this sprite is an item or not
     * @param description A small string describing the object
     */
    public SpriteGson(int id, NestedSpriteData spriteData, boolean isItem, String description){
        this.id = id;
        this.spriteData = spriteData;
        this.isItem = isItem;
        this.description = description;
    }

    /**
     * Gets the boolean value representing whether this sprite is an item
     * @return whether this sprite is an item
     */
    public boolean isItem() {
        return isItem;
    }

    /**
     * Gets the nested sprite data
     * @return NestedSpriteData containing x,y,widht and height for the sprite
     */
    public NestedSpriteData getSpriteData() {
        return spriteData;
    }

    /**
     * Gets nested item data
     * @return NestedItemDAta containing pickupText,stackabitity
     */
    public NestedItemData getItemData() {
        return itemData;
    }

    /**
     * Sets the sprite texture
     * @param sprite the sprite texture
     */
    public void setSprite(TextureRegion sprite) {
        this.sprite = sprite;
    }

    /**
     * Gets the sprite texture
     * @return the sprite texture
     */
    public TextureRegion getSprite() {
        return sprite;
    }

    /**
     * Gets the id of the sprite
     * @return the id of the sprite
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the spritesheetGson for this sprite
     * @return spritesheetGson for this sprite
     */
    public SpriteSheetGson getSpriteSheetGson() {
        return spriteSheetGson;
    }

    /**
     * Gets the decription string describing the item
     * @return the description of this item
     */
    public String getDescription() {
        return description;
    }
}