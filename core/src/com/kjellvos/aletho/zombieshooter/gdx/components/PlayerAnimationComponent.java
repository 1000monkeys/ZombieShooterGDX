package com.kjellvos.aletho.zombieshooter.gdx.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;

public class PlayerAnimationComponent implements Component {
    private ZombieShooterGame parent;
    public Animation<TextureRegion> upAnimation, downAnimation, rightAnimation, leftAnimation, idleAnimation;

    public PlayerAnimationComponent(ZombieShooterGame parent, Animation<TextureRegion> upAnimation, Animation<TextureRegion> downAnimation, Animation<TextureRegion> rightAnimation, Animation<TextureRegion> leftAnimation, Animation<TextureRegion> idleAnimation) {
        this.parent = parent;

        this.upAnimation = upAnimation;
        this.downAnimation = downAnimation;
        this.rightAnimation = rightAnimation;
        this.leftAnimation = leftAnimation;

        this.idleAnimation = idleAnimation;
    }

    public Animation<TextureRegion> getAnimation() {
        if (parent.getGameScreen().isUpPressed()) {
            return upAnimation;
        }else if (parent.getGameScreen().isDownPressed()){
            return downAnimation;
        }else if (parent.getGameScreen().isLeftPressed()) {
            return leftAnimation;
        }else if (parent.getGameScreen().isRightPressed()) {
            return rightAnimation;
        }else{
            return idleAnimation;
        }
    }
}
