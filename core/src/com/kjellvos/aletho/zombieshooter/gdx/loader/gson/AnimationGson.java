package com.kjellvos.aletho.zombieshooter.gdx.loader.gson;

public class AnimationGson {
    private int id;
    private String description;
    private int[] spriteIds;
    private float timer;

    /**
     * Initializes the class with the variables
     * @param id the animation id
     * @param description description of the animation
     * @param spriteIds array of sprite ids
     * @param timer time between different sprites when animated
     */
    public AnimationGson(int id, String description, int[] spriteIds, float timer) {
        this.id = id;
        this.description = description;
        this.spriteIds = spriteIds;
        this.timer = timer;
    }

    /**
     * Gets the animation id
     * @return the animation id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the animation description
     * @return the animation description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the sprite id's this animation consists of
     * @return the sprite's id's used in this animation
     */
    public int[] getSpriteIds() {
        return spriteIds;
    }

    /**
     * Gets the amount of time in seconds between different sprites of this animation
     * @return the amount of time in seconds
     */
    public float getTimer() {
        return timer;
    }
}
