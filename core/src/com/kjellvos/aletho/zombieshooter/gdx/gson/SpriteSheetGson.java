package com.kjellvos.aletho.zombieshooter.gdx.gson;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheetGson {
    private String spriteSheetName;
    private int spriteSheetId;
    private int[] spriteSheetData;
    private TextureRegion sprite = null;

    private Texture spriteSheet = null;

    public SpriteSheetGson(String spriteSheetName, int spriteSheetId, int[] spriteSheetData){
        this.spriteSheetName = spriteSheetName;
        this.spriteSheetId = spriteSheetId;
        this.spriteSheetData = spriteSheetData; }

    public TextureRegion getSprite(SpriteGson spriteGson) {
        if (spriteSheet == null) {
            spriteSheet = new Texture(Gdx.files.internal(spriteSheetName));
        }

        return new TextureRegion(spriteSheet,
            spriteGson.getSpriteData().getPositionX(),
            spriteGson.getSpriteData().getPositionY(),
            spriteGson.getSpriteData().getWidthInPixels(),
            spriteGson.getSpriteData().getHeightInPixels());
    }

    public Texture getSpriteSheet() {
        return spriteSheet;
    }

    public int getSpriteSheetId() {
        return spriteSheetId;
    }

    public int[] getSpriteSheetData() {
        return spriteSheetData;
    }

    public String getSpriteSheetName() {
        return spriteSheetName;
    }
}