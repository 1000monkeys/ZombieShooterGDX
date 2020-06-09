package com.kjellvos.aletho.zombieshooter.gdx.loader.gson;

public class AnimationGson {
    private int id;
    private String description;
    private int[] spriteIds;
    private float timer;

    public AnimationGson(int id, String description, int[] spriteIds, float timer) {
        this.id = id;
        this.description = description;
        this.spriteIds = spriteIds;
        this.timer = timer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public int[] getSpriteIds() {
        return spriteIds;
    }

    public float getTimer() {
        return timer;
    }
}
