package com.kjellvos.aletho.zombieshooter.gdx.enums;

public enum AnimationEnum {
    LIGHT(new ItemEnum[]{
                    ItemEnum.LIGHT_ANIMATION_0,
                    ItemEnum.LIGHT_ANIMATION_1,
                    ItemEnum.LIGHT_ANIMATION_2,
                    ItemEnum.LIGHT_ANIMATION_3,
                    ItemEnum.LIGHT_ANIMATION_4,
                    ItemEnum.LIGHT_ANIMATION_5,
                    ItemEnum.LIGHT_ANIMATION_6,
                    ItemEnum.LIGHT_ANIMATION_7,
    });

    private final ItemEnum[] textures;

    AnimationEnum(ItemEnum[] textures) {
        this.textures = textures;
    }

    public ItemEnum[] getTextureEnums() {
        return textures;
    }
}
