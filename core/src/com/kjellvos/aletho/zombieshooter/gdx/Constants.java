package com.kjellvos.aletho.zombieshooter.gdx;

public class Constants {
    public static boolean DEBUG = true;

    public static int WIDTH = 1024, HEIGHT = 786;

    public static float commodoreBlueR = 0.25882354F, commodoreBlueG = 0.25882354F, commodoreBlueB = 0.90588236F;

    public static final int PPM = 16;
    public static final int viewWidthInTiles = 20, viewHeightInTiles = 16;

    public static final float LIGHT_DISTANCE = 25F;
    public static final int LIGHT_NUM_RAYS = 4096;
    public static final int LIGHT_ANIMATION_TEXTURE_COUNT = 8;

    public static final int BACKGROUND_LAYER = 0;
    public static final int FOREGROUND_LAYER = 1;

    public static final int AMOUNT_MUSIC_FILES = 3;

    public static final short GROUP_ITEM = -2;
    public static final short GROUP_MOB = -1;
    public static final short GROUP_LIGHT = 1;
    public static final short GROUP_WALL = 2;

    public static final short CATEGORY_LIGHT = 1;
    public static final short CATEGORY_WALL = 2;
    public static final short CATEGORY_MOB = 4;
    public static final short CATEGORY_PLAYER = 8;
    public static final short CATEGORY_ITEM = 16;
    public static final short CATEGORY_BUILDING = 32;

    public static final short MASK_LIGHT = CATEGORY_WALL | CATEGORY_MOB | CATEGORY_PLAYER;
    public static final short MASK_WALL = CATEGORY_LIGHT | CATEGORY_WALL | CATEGORY_MOB | CATEGORY_PLAYER | CATEGORY_ITEM;
    public static final short MASK_MOB = CATEGORY_LIGHT | CATEGORY_WALL | CATEGORY_MOB | CATEGORY_PLAYER | CATEGORY_ITEM | CATEGORY_BUILDING;
    public static final short MASK_PLAYER = CATEGORY_LIGHT | CATEGORY_WALL | CATEGORY_MOB | CATEGORY_PLAYER | CATEGORY_ITEM | CATEGORY_BUILDING;
    public static final short MASK_ITEM = CATEGORY_WALL | CATEGORY_MOB | CATEGORY_PLAYER | CATEGORY_ITEM | CATEGORY_BUILDING;
    public static final short MASK_BUILDING = CATEGORY_MOB | CATEGORY_PLAYER | CATEGORY_ITEM;
}
