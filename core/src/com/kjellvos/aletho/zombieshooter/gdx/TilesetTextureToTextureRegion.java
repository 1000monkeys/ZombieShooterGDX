package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TilesetTextureToTextureRegion {
    public static final int PPT = 16; //pixels per tile

    public static TextureRegion getTextureRegionById(Texture tileSet, int id) {
        int column = id % 32;
        int row = (int)(Math.floor(id / 32D));
        return new TextureRegion(tileSet, column * PPT, row * PPT, PPT, PPT);
    }

    public static TextureRegion getTextureRegionById(Texture tileSet, int id, float rowWidth, float columnHeight) {
        int column = id % 32;
        int row = (int)(Math.floor(id / 32D));
        return new TextureRegion(tileSet, column * PPT, row * PPT, (int)(PPT * rowWidth), (int)(PPT * columnHeight));
    }

    public static TextureRegion getTextureRegionById(Texture tileSet, int id, float rowWidth, float columnHeight, int offsetX, int offsetY) {
        int column = id % 32;
        int row = (int)(Math.floor(id / 32D));
        return new TextureRegion(tileSet, offsetX + column * PPT, offsetY + row * PPT, (int)(PPT * rowWidth), (int)(PPT * columnHeight));
    }
}
