package com.kjellvos.aletho.zombieshooter.gdx.gson;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;

public class SpriteSheet {
    private String spriteSheetName;
    private int spriteSheetId;
    private int[] spriteSheetData;
    private TextureRegion sprite = null;

    private Texture spriteSheet = null;

    public SpriteSheet(String spriteSheetName, int spriteSheetId, int[] spriteSheetData){
        this.spriteSheetName = spriteSheetName;
        this.spriteSheetId = spriteSheetId;
        this.spriteSheetData = spriteSheetData; }

    public TextureRegion getSprite(SpriteObj spriteObj) {
        if (spriteSheet == null) {
            spriteSheet = new Texture(Gdx.files.internal(spriteSheetName));
        }

        return new TextureRegion(spriteSheet,
            spriteObj.getSpriteData().getPositionX(),
            spriteObj.getSpriteData().getPositionY(),
            spriteObj.getSpriteData().getWidthInPixels(),
            spriteObj.getSpriteData().getHeightInPixels());
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