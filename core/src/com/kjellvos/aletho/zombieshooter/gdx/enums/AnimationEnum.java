package com.kjellvos.aletho.zombieshooter.gdx.enums;

public enum AnimationEnum {
    LIGHT(new SpriteEnum[]{
                    SpriteEnum.LIGHT_ANIMATION_0,
                    SpriteEnum.LIGHT_ANIMATION_1,
                    SpriteEnum.LIGHT_ANIMATION_2,
                    SpriteEnum.LIGHT_ANIMATION_3,
                    SpriteEnum.LIGHT_ANIMATION_4,
                    SpriteEnum.LIGHT_ANIMATION_5,
                    SpriteEnum.LIGHT_ANIMATION_6,
                    SpriteEnum.LIGHT_ANIMATION_7,
    });

    private final SpriteEnum[] textures;

    AnimationEnum(SpriteEnum[] textures) {
        this.textures = textures;
    }

    public SpriteEnum[] getTextureEnums() {
        return textures;
    }
}
