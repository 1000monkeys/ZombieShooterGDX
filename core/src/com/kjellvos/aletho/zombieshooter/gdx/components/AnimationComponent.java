package com.kjellvos.aletho.zombieshooter.gdx.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationComponent implements Component {
    public Animation<TextureRegion> fireAnimation;

    public AnimationComponent(TextureRegion[] textureAnimationRegion) {
        fireAnimation = new Animation<TextureRegion>(0.5F, textureAnimationRegion);
    }
}
