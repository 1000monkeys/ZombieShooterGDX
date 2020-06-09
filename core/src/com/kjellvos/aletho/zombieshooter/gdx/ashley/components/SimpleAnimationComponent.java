package com.kjellvos.aletho.zombieshooter.gdx.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SimpleAnimationComponent implements Component {
    public Animation<TextureRegion> animation;

    public SimpleAnimationComponent(Animation<TextureRegion> animation){
        this.animation = animation;
    }
}
