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

    public SpriteGson(int id, NestedSpriteData spriteData, boolean isItem, NestedItemData itemData, String description) {
        this.id = id;
        this.spriteData = spriteData;
        this.isItem = isItem;
        this.itemData = itemData;
        this.description = description;
    }

    public SpriteGson(int id, NestedSpriteData spriteData, boolean isItem, String description){
        this.id = id;
        this.spriteData = spriteData;
        this.isItem = isItem;
        this.description = description;
    }

    public boolean isItem() {
        return isItem;
    }

    public NestedSpriteData getSpriteData() {
        return spriteData;
    }

    public NestedItemData getItemData() {
        return itemData;
    }

    public void setSprite(TextureRegion sprite) {
        this.sprite = sprite;
    }

    public TextureRegion getSprite() {
        return sprite;
    }

    public int getId() {
        return id;
    }

    public SpriteSheetGson getSpriteSheetGson() {
        return spriteSheetGson;
    }

    public String getDescription() {
        return description;
    }
}