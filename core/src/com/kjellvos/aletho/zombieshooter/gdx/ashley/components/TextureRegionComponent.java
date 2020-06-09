package com.kjellvos.aletho.zombieshooter.gdx.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegionComponent implements Component {
    public TextureRegion textureRegion;

    public TextureRegionComponent(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }
}
