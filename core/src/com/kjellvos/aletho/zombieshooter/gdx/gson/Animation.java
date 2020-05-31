package com.kjellvos.aletho.zombieshooter.gdx.gson;

public class Animation {
    private int id;
    private String description;
    private int[] spriteIds;

    public Animation(int id, String description, int[] spriteIds) {
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

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getSpriteIds() {
        return spriteIds;
    }

    public void setSpriteIds(int[] spriteIds) {
        this.spriteIds = spriteIds;
    }
}
