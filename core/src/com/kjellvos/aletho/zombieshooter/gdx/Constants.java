package com.kjellvos.aletho.zombieshooter.gdx;

public class Constants {
    /**
     * The debug variable, used in certain areas of the code to make debugging easier,
     * For release this variable should always be false.
     */
    public static boolean DEBUG = true;

    /**
     *
     * JSON PLACEMENT CONSTANTS!
     *
     */
    public static int
        JSON_GAME_DATA = 0,
            JSON_GAME_DATA_PLAYER_SPRITE_ID = 0,
            JSON_GAME_DATA_PLAYER_SPRITE_SHEET = 1,
            JSON_GAME_DATA_LIGHT_OFF_SPRITE_ID =2,

        JSON_ANIMATION_ID = 0,
        JSON_ANIMATION_DESCRIPTION = 1,
        JSON_ANIMATION_SPRITE_IDS = 2,

        JSON_SPRITESHEET_NAME = 0,
        JSON_SPRITESHEET_ID = 1,
        JSON_SPRITESHEET_DATA = 2,
            JSON_SPRITESHEET_DATA_PIXELS_PER_TILE = 0,
            JSON_SPRITESHEET_DATA_WIDTH_IN_PIXELS = 1,
            JSON_SPRITESHEET_DATA_HEIGHT_IN_PIXELS = 2,


        JSON_SPRITE_ID = 0,
        JSON_SPRITE_DATA = 1,
            JSON_SPRITE_DATA_SPRITE_SHEET = 0,
            JSON_SPRITE_DATA_WIDTH_IN_PIXELS = 1,
            JSON_SPRITE_DATA_HEIGHT_IN_PIXELS = 2,
            JSON_SPRITE_DATA_OFFSET_X = 3,
            JSON_SPRITE_DATA_OFFSET_Y = 4,
        JSON_ITEM_DATA = 2,
            JSON_ITEM_DATA_IS_ITEM = 0,
            JSON_ITEM_DATA_PICK_UP_TEXT = 1,
            JSON_ITEM_DATA_STACKABLE = 2,
        JSON_DESCRIPTION = 3;

    /**
     * The standard width/height of the game screen,
     * Used at startup.
     */
    public static int WIDTH = 1024, HEIGHT = 786;

    /**
     * The RGB(Red, Green, Blue) value of the very recognizable commodore blue color.
     */
    public static float commodoreBlueR = 0.25882354F, commodoreBlueG = 0.25882354F, commodoreBlueB = 0.90588236F;

    public static final String SPRITESHEET_JSON = "spritesheets.json";
    public static final String SPRITES_JSON = "sprites.json";
    public static final String ANIMATIONS_JSON = "animations.json";
    public static final String GAMEDATA_JSON = "gamedata.json";


    /**
     * The pixels per tile.
     */
    public static final int PPT = 16;
    /**
     * The width/height in tiles, To get the real width/height multiply with PPT.
     */
    public static final int viewWidthInTiles = 20, viewHeightInTiles = 16;

    /**
     * The distance light travels.
     */
    public static final float LIGHT_DISTANCE = 250F;
    /**
     * The amount of rays used per light, More is smoother shadows but slower performance.
     */
    public static final int LIGHT_NUM_RAYS = 64;
    /**
     * The distance from which lights go from lighting to darkness.
     */
    public static final float LIGHT_SOFTNESS_LENGTH = 62.5F;

    /**
     * The background layer id number, Should be 0.
     */
    public static final int BACKGROUND_LAYER = 0;
    /**
     * The foreground layer id number, Should be 1.
     */
    public static final int FOREGROUND_LAYER = 1;

    /**
     * The amount of different music files to uses.
     */
    public static final int AMOUNT_MUSIC_FILES = 3;
    public static final int STACK_SIZE_LIMIT = 64;

    public static final short GROUP_ITEM = -2;
    public static final short GROUP_MOB = -1;
    public static final short GROUP_LIGHT = 1;
    public static final short GROUP_WALL = 2;

    /**
     * Category bits, Always double the value for a new category.
     */
    public static final short CATEGORY_LIGHT = 1;
    public static final short CATEGORY_WALL = 2;
    public static final short CATEGORY_MOB = 4;
    public static final short CATEGORY_PLAYER = 8;
    public static final short CATEGORY_ITEM = 16;
    public static final short CATEGORY_BUILDING = 32;

    /**
     * Masks are used for collision, Both masks need to have each other as a value to collide.
     */
    public static final short MASK_LIGHT = CATEGORY_WALL | CATEGORY_MOB | CATEGORY_PLAYER;
    public static final short MASK_WALL = CATEGORY_LIGHT | CATEGORY_WALL | CATEGORY_MOB | CATEGORY_PLAYER | CATEGORY_ITEM;
    public static final short MASK_MOB = CATEGORY_LIGHT | CATEGORY_WALL | CATEGORY_MOB | CATEGORY_PLAYER | CATEGORY_ITEM | CATEGORY_BUILDING;
    public static final short MASK_PLAYER = CATEGORY_LIGHT | CATEGORY_WALL | CATEGORY_MOB | CATEGORY_PLAYER | CATEGORY_ITEM | CATEGORY_BUILDING;
    public static final short MASK_ITEM = CATEGORY_WALL | CATEGORY_MOB | CATEGORY_PLAYER | CATEGORY_ITEM | CATEGORY_BUILDING;
    public static final short MASK_BUILDING = CATEGORY_MOB | CATEGORY_PLAYER | CATEGORY_ITEM;
}
