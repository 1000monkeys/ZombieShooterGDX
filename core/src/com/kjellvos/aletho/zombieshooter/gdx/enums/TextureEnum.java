package com.kjellvos.aletho.zombieshooter.gdx.enums;

import com.kjellvos.aletho.zombieshooter.gdx.Constants;

public enum TextureEnum {
    WALL_TOP_1(0, "The top of a wall, Mostly empty."),
    WALL_TOP_2(1, "Another wall top, mostly empty."),
    WALL_TOP_3(2, "Another wall top, mostly empty."),
    WALL_PIPE_LOW(3, "A piece of wall with a piece of pipe in it at the bottom."),
    WALL_PIPE_HIGH(4, "A piece of wall with a piece of pipe in it at the top."),

    LIGHT_OFF(327, "A light. It is off."),

    LIGHT_ANIMATION_0(328, "A light, It is on and flickering."),
    LIGHT_ANIMATION_0_TOP(296, "Top of the fire, Very hot!", 0, Constants.PPM / 2),

    LIGHT_ANIMATION_1(329, "A light, It is on and flickering."),
    LIGHT_ANIMATION_1_TOP(297, "Top of the fire, Very hot!", 0, Constants.PPM / 2),

    LIGHT_ANIMATION_2(330, "A light, It is on and flickering."),
    LIGHT_ANIMATION_2_TOP(298, "Top of the fire, Very hot!", 0, Constants.PPM / 2),

    LIGHT_ANIMATION_3(331, "A light, It is on and flickering."),
    LIGHT_ANIMATION_3_TOP(299, "Top of the fire, Very hot!", 0, Constants.PPM / 2),

    LIGHT_ANIMATION_4(332, "A light, It is on and flickering."),
    LIGHT_ANIMATION_4_TOP(300, "Top of the fire, Very hot!", 0, Constants.PPM / 2),

    LIGHT_ANIMATION_5(333, "A light, It is on and flickering."),
    LIGHT_ANIMATION_5_TOP(301, "Top of the fire, Very hot!", 0, Constants.PPM / 2),

    LIGHT_ANIMATION_6(334, "A light, It is on and flickering."),
    LIGHT_ANIMATION_6_TOP(302, "Top of the fire, Very hot!", 0, Constants.PPM / 2),

    LIGHT_ANIMATION_7(335, "A light, It is on and flickering."),
    LIGHT_ANIMATION_7_TOP(303, "Top of the fire, Very hot!", 0, Constants.PPM / 2),

    SMALL_HEALTH_POTION(423, "A small health potion."),
    SMALL_LIFEBLOOD_POTION(424, "A small lifeblood potion."),
    SMALL_MANA_POTION(425, "A small mana potion."),
    SMALL_DAMAGE_POTION(426, "A small damage potion."),
    LARGE_HEALTH_POTION(364, " A Large health potion."),
    LARGE_LIFEBLOOD_POTION(396, "A large lifeblood potion."),
    LARGE_MANA_POTION(428, "A large mana potion."),
    LARGE_DAMAGE_POTION(427, "A large damage potion"),
    PLAYER(455, "A man with a blue shirt and a black eyepatch");

    private final int id;
    private final String description;

    TextureEnum(int id, String description) {
        this.id = id;
        this.description = description;
    }

    TextureEnum(int id, String description, int offsetX, int ofsetY) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
