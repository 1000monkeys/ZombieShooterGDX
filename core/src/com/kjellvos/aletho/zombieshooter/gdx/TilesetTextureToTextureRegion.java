package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kjellvos.aletho.zombieshooter.gdx.enums.TextureEnum;

import static com.kjellvos.aletho.zombieshooter.gdx.Constants.PPT;

public class TilesetTextureToTextureRegion {
    /**
     * This function gets the texture region from the texture enumeration.
     * @param tileSet The tileset texture to be divided into a region.
     * @param textureEnum The texture enum of the texture you are trying to get.
     * @return TextureRegion To be used to render on the screen.
     */
    public static TextureRegion getTextureRegionByTextureEnum(Texture tileSet, TextureEnum textureEnum) {
        int column = textureEnum.getId() % 32;
        int row = (int)(Math.floor(textureEnum.getId() / 32D));
        return new TextureRegion(tileSet, (textureEnum.getOffsetX() + (column * PPT)), (textureEnum.getOffsetY() + (row * PPT)), (int)(PPT * textureEnum.getRowWidth()), (int)(PPT * textureEnum.getColumnHeight()));
    }

    /**
     * This function gets the texture regions from the texture enumerations and puts them into an array to be used for animation.
     * @param tileSet The tileset texture to be divided into a region.
     * @param textureEnums The texture enum of the texture you are trying to get.
     * @return TextureRegion To be used to render on the screen.
     */
    public static TextureRegion[] getAnimationTextureRegionsByTextureEnums(Texture tileSet, TextureEnum[] textureEnums) {
        TextureRegion[] textureRegions = new TextureRegion[textureEnums.length];
        for (int i = 0; i < textureEnums.length; i++) {
            int column = textureEnums[i].getId() % 32;
            int row = (int) (Math.floor(textureEnums[i].getId() / 32D));

            textureRegions[i] = new TextureRegion(tileSet, (textureEnums[i].getOffsetX() + (column * PPT)), (textureEnums[i].getOffsetY() + (row * PPT)), (int)(PPT * textureEnums[i].getRowWidth()), (int)(PPT * textureEnums[i].getColumnHeight()));
        }
        return textureRegions;
    }

    /**
     * This function gets the texture region from the id of the sprite.
     * @param tileSet The tileset texture to be divided into a region.
     * @param id The id of the tile you are trying to get.
     * @return TextureRegion To be used to render on the screen.
     */
    public static TextureRegion getTextureRegionById(Texture tileSet, int id) {
        int column = id % 32;
        int row = (int)(Math.floor(id / 32D));
        return new TextureRegion(tileSet, column * PPT, row * PPT, PPT, PPT);
    }
}
