package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TilesetToSprite {
    private Texture tileset;
    private TextureRegion player;

    public static final int PPT = 16;

    public TilesetToSprite(Texture tileSet) {
        this.tileset = tileSet;
        player = getTextureRegionById(455);
    }

    public TextureRegion getPlayer() {
        return player;
    }

    public TextureRegion getTextureRegionById(int id) {
        int row = (int)(id / 32);
        int column = id % 32 + 1;

        return new TextureRegion(tileset, column, row, PPT, PPT);
    }
}
