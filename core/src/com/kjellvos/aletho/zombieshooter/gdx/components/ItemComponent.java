package com.kjellvos.aletho.zombieshooter.gdx.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ItemComponent implements Component {
    public int id;

    public ItemComponent(int id) {
        this.id = id;
    }
}
