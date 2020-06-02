package com.kjellvos.aletho.zombieshooter.gdx.gson;

public class AnimationGson {
    private int id;
    private String description;
    private int[] spriteIds;

    public AnimationGson(int id, String description, int[] spriteIds) {
        this.id = id;
        this.description = description;
        this.spriteIds = spriteIds;
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
}
