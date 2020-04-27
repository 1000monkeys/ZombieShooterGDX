package com.kjellvos.aletho.zombieshooter.gdx.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ItemComponent implements Component {
    public int id;
    public TextureRegion itemTextureRegion;

    public ItemComponent(int id, TextureRegion itemTextureRegion) {
        this.id = id;
        this.itemTextureRegion = itemTextureRegion;
    }
}
