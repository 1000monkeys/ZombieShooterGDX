package com.kjellvos.aletho.zombieshooter.gdx.enums;

import com.kjellvos.aletho.zombieshooter.gdx.Constants;

public enum TextureEnum {
    WALL_TOP_1(0, false, false, "The top of a wall, Mostly empty.", 1F, 1F, 0, 0),
    WALL_TOP_2(1, false, false, "Another wall top, mostly empty.", 1F, 1F, 0, 0),
    WALL_TOP_3(2, false, false, "Another wall top, mostly empty.", 1F, 1F, 0, 0),
    WALL_PIPE_LOW(3, false, false, "A piece of wall with a piece of pipe in it at the bottom.", 1F, 1F, 0, 0),
    WALL_PIPE_HIGH(4, false, false, "A piece of wall with a piece of pipe in it at the top.", 1F, 1F, 0, 0),

    BRONZE_SWORD(9, true, false, "A crude bronze sword.", 1F, 1.5F, 0, Constants.PPT / 2),
    HYBRID_SWORD(10, true, false, "A sword crudely made out of iron and bronze.", 1F, 1.5F, 0, Constants.PPT / 2),
    IRON_SWORD(11, true, false, "An iron sword, seems pretty sharp.", 1F, 1.5F, 0, Constants.PPT / 2),
    IRON_GEM_SWORD(12, true, false, "An iron sword, Has a gem on the hilt.", 1F, 1.5F, 0, Constants.PPT / 2),
    IRON_BROAD_SWORD(13, true, false, "An board sword.", 1F, 1.5F, 0, Constants.PPT / 3),
    IRON_LONG_SWORD(14, true, false, "AN iron long sword, It has pretty good reach.", 1F, 2F, 0, 0),
    IRON_HAMMER(15, true, false, "A long iron hammer, has very good reach.", 1F, 2.5F, 0, Constants.PPT / 2),
    IRON_SHORT_SWORD(71, true, false, "An iron short sword, You could really do some damage with this", 1F, 1.5F, 0, Constants.PPT / 2),
    IRON_CLEAVER(72, true, false, "An iron cleaver, it is heavy.", 1F, 1.5F, 0, Constants.PPT / 2),
    GOLD_SHORT_SWORD(73, true, false, "A very pretty gold short sword.", 1F, 1.5F, 0, Constants.PPT / 2),
    GOLD_LONG_SWORD(74, true, false, "A gold long sword. worth a lot.", 1F, 2F, 0, 0),
    MARMER_FENCING_SWORD(75, true, false, "A marmer fencing sword, Sturdy.", 1F, 2F, 0, 0),
    MARMER_SHORT_SWORD(76, true, false, "A marmer short sword.", 1F, 2F, 0, 0),
    MARMER_SHORT_SWORD_UPGRADED(77, true, false, "A marmer short sword, It is upgraded", 1F, 2F, 0, 0),
    THE_YELLOW_SWORD(78, true, false, "The yellow sword. The very best.", 1F, 2F, 0, 0),
    BOMB(111, true, false, "A bomb", 1F, 1F, 0, 0),

    LIGHT_ANIMATION_0(296, false, false, "Top of the fire, Very hot!", 1F, 1.5F, 0, Constants.PPT / 2),
    LIGHT_ANIMATION_1(297, false, false, "Top of the fire, Very hot!", 1F, 1.5F,0, Constants.PPT / 2),
    LIGHT_ANIMATION_2(298, false, false, "Top of the fire, Very hot!", 1F, 1.5F,0, Constants.PPT / 2),
    LIGHT_ANIMATION_3(299, false, false, "Top of the fire, Very hot!", 1F, 1.5F,0, Constants.PPT / 2),
    LIGHT_ANIMATION_4(300, false, false, "Top of the fire, Very hot!", 1F, 1.5F,0, Constants.PPT / 2),
    LIGHT_ANIMATION_5(301, false, false, "Top of the fire, Very hot!", 1F, 1.5F,0, Constants.PPT / 2),
    LIGHT_ANIMATION_6(302, false, false, "Top of the fire, Very hot!", 1F, 1.5F,0, Constants.PPT / 2),
    LIGHT_ANIMATION_7(303, false, false, "Top of the fire, Very hot!", 1F, 1.5F,0, Constants.PPT / 2),

    LIGHT_OFF(327, false, false, "A light. It is off.", 1F, 1F, 0, 0),

    LARGE_HEALTH_POTION(364, true, false, " A Large health potion.", 1F, 1F, 0, 0),
    LARGE_LIFEBLOOD_POTION(396, true, false, "A large lifeblood potion.", 1F, 1F, 0, 0),

    SMALL_HEALTH_POTION(423, true, false, "A small health potion.", 1F, 1F, 0, 0),
    SMALL_LIFEBLOOD_POTION(424, true, false, "A small lifeblood potion.", 1F, 1F, 0, 0),
    SMALL_MANA_POTION(425, true, false, "A small mana potion.", 1F, 1F, 0, 0),
    SMALL_DAMAGE_POTION(426, true, false, "A small damage potion.", 1F, 1F, 0, 0),
    LARGE_MANA_POTION(428, true, false, "A large mana potion.", 1F, 1F, 0, 0),
    LARGE_DAMAGE_POTION(427, true, false, "A large damage potion", 1F, 1F, 0, 0),

    PLAYER(455, false, false, "A man with a blue shirt and a black eyepatch", 1F, 1F, 0, 0);

    private final int id;
    private final String description;
    private final float rowWidth, columnHeight;
    private final int offsetX, offsetY;
    private final boolean isItem, isMob;

    TextureEnum(int id, boolean isItem, boolean isMob, String description, float rowWidth, float columnHeight, int offsetX, int offsetY) {
        this.id = id;
        this.isItem = isItem;
        this.isMob = isMob;
        this.description = description;
        this.rowWidth = rowWidth;
        this.columnHeight = columnHeight;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public boolean isItem() {
        return isItem;
    }

    public boolean isMob() {
        return isMob;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public float getRowWidth() {
        return rowWidth;
    }

    public float getColumnHeight() {
        return columnHeight;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static TextureEnum findById(int id){
        for(TextureEnum textureEnum : values()){
            if( textureEnum.getId() == id){
                return textureEnum;
            }
        }
        return null;
    }
}
