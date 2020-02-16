package com.kjellvos.aletho.zombieshooter.gdx;

public enum TextureEnum {
    WALL_TOP_1(0, "The top of a wall, Mostly empty."),
    WALL_TOP_2(1, "Another wall top, mostly empty."),
    WALL_TOP_3(2, "Another wall top, mostly empty."),
    WALL_PIPE_LOW(3, "A piece of wall with a piece of pipe in it at the bottom."),
    WALL_PIPE_HIGH(4, "A piece of wall with a piece of pipe in it at the top."),

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

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
