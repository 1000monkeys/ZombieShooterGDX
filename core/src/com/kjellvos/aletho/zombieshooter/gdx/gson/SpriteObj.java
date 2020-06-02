package com.kjellvos.aletho.zombieshooter.gdx.gson;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;

public class SpriteObj {
    private int id;
    private NestedSpriteData spriteData;
    private boolean isItem;
    private NestedItemData itemData;
    private String description;

    private SpriteSheet spriteSheet;
    private TextureRegion sprite = null;

    public SpriteObj(int id, NestedSpriteData spriteData, boolean isItem, NestedItemData itemData, String description) {
        this.id = id;
        this.spriteData = spriteData;
        this.isItem = isItem;
        this.itemData = itemData;
        this.description = description;
    }

    public SpriteObj(int id, NestedSpriteData spriteData, boolean isItem, String description){
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

    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
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