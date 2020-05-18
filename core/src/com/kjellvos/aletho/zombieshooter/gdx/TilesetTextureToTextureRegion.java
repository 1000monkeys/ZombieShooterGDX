package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kjellvos.aletho.zombieshooter.gdx.enums.ItemEnum;

import static com.kjellvos.aletho.zombieshooter.gdx.Constants.PPT;

public class TilesetTextureToTextureRegion {
    /**
     * This function gets the texture region from the texture enumeration.
     * @param tileSet The tileset texture to be divided into a region.
     * @param itemEnum The texture enum of the texture you are trying to get.
     * @return TextureRegion To be used to render on the screen.
     */
    public static TextureRegion getTextureRegionByTextureEnum(Texture tileSet, ItemEnum itemEnum) {
        int column = itemEnum.getId() % 32;
        int row = (int)(Math.floor(itemEnum.getId() / 32D));
        return new TextureRegion(tileSet, (itemEnum.getOffsetX() + (column * PPT)), (itemEnum.getOffsetY() + (row * PPT)), (int)(PPT * itemEnum.getRowWidth()), (int)(PPT * itemEnum.getColumnHeight()));
    }

    /**
     * This function gets the texture regions from the texture enumerations and puts them into an array to be used for animation.
     * @param tileSet The tileset texture to be divided into a region.
     * @param itemEnums The texture enum of the texture you are trying to get.
     * @return TextureRegion To be used to render on the screen.
     */
    public static TextureRegion[] getAnimationTextureRegionsByTextureEnums(Texture tileSet, ItemEnum[] itemEnums) {
        TextureRegion[] textureRegions = new TextureRegion[itemEnums.length];
        for (int i = 0; i < itemEnums.length; i++) {
            int column = itemEnums[i].getId() % 32;
            int row = (int) (Math.floor(itemEnums[i].getId() / 32D));

            textureRegions[i] = new TextureRegion(tileSet, (itemEnums[i].getOffsetX() + (column * PPT)), (itemEnums[i].getOffsetY() + (row * PPT)), (int)(PPT * itemEnums[i].getRowWidth()), (int)(PPT * itemEnums[i].getColumnHeight()));
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
