package com.kjellvos.aletho.zombieshooter.gdx.ashley.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.physics.box2d.Body;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.Mapper;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.HealthComponent;

public class HealthSystem extends IteratingSystem {
    private ZombieShooterGame parent;

    public HealthSystem(ZombieShooterGame parent){
        super(Family.all(HealthComponent.class).get());
        this.parent = parent;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        GdxAI.getTimepiece().update(deltaTime);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        HealthComponent healthComponent = Mapper.healtCom.get(entity);


        if (healthComponent.health < 0) {
            Body body;
            if ((body = Mapper.bodyCom.get(entity).body) != null){
                parent.getGameScreen().getWorld().destroyBody(body);
            }
            entity.removeAll();
            parent.getGameScreen().getEngine().removeEntity(entity);
        }
    }
}
