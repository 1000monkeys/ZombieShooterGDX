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

    private Texture spriteSheet;

    public SpriteSheet(String spriteSheetName, int spriteSheetId, int[] spriteSheetData){
        this.spriteSheetName = spriteSheetName;
        this.spriteSheetId = spriteSheetId;
        this.spriteSheetData = spriteSheetData; }

    public TextureRegion getSprite(SpriteObj spriteObj) {
        spriteSheet = new Texture(Gdx.files.internal(spriteSheetName));


        int column = spriteObj.getId() % (spriteSheetData[Constants.JSON_SPRITESHEET_DATA_WIDTH_IN_PIXELS] / spriteSheetData[Constants.JSON_SPRITESHEET_DATA_PIXELS_PER_TILE]);
        int row = (int) (Math.floor(spriteObj.getId() / (spriteSheetData[Constants.JSON_SPRITESHEET_DATA_HEIGHT_IN_PIXELS] / spriteSheetData[Constants.JSON_SPRITESHEET_DATA_PIXELS_PER_TILE])));

        System.out.println(spriteSheet);
        System.out.println(spriteSheet);System.out.println(spriteSheet);System.out.println(spriteSheet);System.out.println(spriteSheet);

        return new TextureRegion(spriteSheet,
            spriteObj.getOffsetX() + (column * spriteSheetData[Constants.JSON_SPRITESHEET_DATA_PIXELS_PER_TILE]),
            spriteObj.getOffsetY() + (row * spriteSheetData[Constants.JSON_SPRITESHEET_DATA_PIXELS_PER_TILE]),
            spriteObj.getWidthInPixels(),
            spriteObj.getHeightInPixels());
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

    public void setSpriteSheetData(int[] spriteSheetData) {
        this.spriteSheetData = spriteSheetData;
    }

    public void setSpriteSheetId(int spriteSheetId) {

    }

    public void setSpriteSheetName(String spriteSheetName) {
        this.spriteSheetName = spriteSheetName;
    }
}