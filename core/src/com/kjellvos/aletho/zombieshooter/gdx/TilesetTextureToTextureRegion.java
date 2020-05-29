package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.kjellvos.aletho.zombieshooter.gdx.Constants.PPT;

public class TilesetTextureToTextureRegion {
    /**
     * This function gets the texture region from the texture enumeration.
     * @param tileSet The tileset texture to be divided into a region.
     * @param spriteEnum The texture enum of the texture you are trying to get.
     * @return TextureRegion To be used to render on the screen.
     */
    public static TextureRegion getTextureRegionByTextureEnum(Texture tileSet, SpriteEnum spriteEnum) {
        int column = spriteEnum.getId() % 32;
        int row = (int)(Math.floor(spriteEnum.getId() / 32D));
        return new TextureRegion(tileSet, (spriteEnum.getOffsetX() + (column * PPT)), (spriteEnum.getOffsetY() + (row * PPT)), (int)(PPT * spriteEnum.getRowWidth()), (int)(PPT * spriteEnum.getColumnHeight()));
    }

    /**
     * This function gets the texture regions from the texture enumerations and puts them into an array to be used for animation.
     * @param tileSet The tileset texture to be divided into a region.
     * @param spriteEnums The texture enum of the texture you are trying to get.
     * @return TextureRegion To be used to render on the screen.
     */
    public static TextureRegion[] getAnimationTextureRegionsByTextureEnums(Texture tileSet, SpriteEnum[] spriteEnums) {
        TextureRegion[] textureRegions = new TextureRegion[spriteEnums.length];
        for (int i = 0; i < spriteEnums.length; i++) {
            int column = spriteEnums[i].getId() % 32;
            int row = (int) (Math.floor(spriteEnums[i].getId() / 32D));

            textureRegions[i] = new TextureRegion(tileSet, (spriteEnums[i].getOffsetX() + (column * PPT)), (spriteEnums[i].getOffsetY() + (row * PPT)), (int)(PPT * spriteEnums[i].getRowWidth()), (int)(PPT * spriteEnums[i].getColumnHeight()));
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
