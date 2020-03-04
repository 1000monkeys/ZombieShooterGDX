package com.kjellvos.aletho.zombieshooter.gdx.enums;

public enum AnimationEnum {
    LIGHT(new TextureEnum[]{
                    TextureEnum.LIGHT_ANIMATION_0,
                    TextureEnum.LIGHT_ANIMATION_1,
                    TextureEnum.LIGHT_ANIMATION_2,
                    TextureEnum.LIGHT_ANIMATION_3,
                    TextureEnum.LIGHT_ANIMATION_4,
                    TextureEnum.LIGHT_ANIMATION_5,
                    TextureEnum.LIGHT_ANIMATION_6,
                    TextureEnum.LIGHT_ANIMATION_7,
    });

    private final TextureEnum[] textures;

    AnimationEnum(TextureEnum[] textures) {
        this.textures = textures;
    }

    public TextureEnum[] getTextureEnums() {
        return textures;
    }
}
