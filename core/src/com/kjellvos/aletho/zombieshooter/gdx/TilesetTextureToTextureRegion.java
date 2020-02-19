package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kjellvos.aletho.zombieshooter.gdx.enums.TextureEnum;

public class TilesetTextureToTextureRegion {
    public static final int PPT = 16; //pixels per tile

    public static TextureRegion getTextureRegionByTextureEnum(Texture tileSet, TextureEnum textureEnum) {
        int column = textureEnum.getId() % 32;
        int row = (int)(Math.floor(textureEnum.getId() / 32D));
        return new TextureRegion(tileSet, (textureEnum.getOffsetX() + (column * PPT)), (textureEnum.getOffsetY() + (row * PPT)), (int)(PPT * textureEnum.getRowWidth()), (int)(PPT * textureEnum.getColumnHeight()));
    }

    public static TextureRegion getTextureRegionById(Texture tileSet, int id) {
        int column = id % 32;
        int row = (int)(Math.floor(id / 32D));
        return new TextureRegion(tileSet, column * PPT, row * PPT, PPT, PPT);
    }
}
