package com.kjellvos.aletho.zombieshooter.gdx.ashley.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.ai.GdxAI;
import com.badlogic.gdx.physics.box2d.Body;
import com.kjellvos.aletho.zombieshooter.gdx.SteeringPresets;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.Mapper;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.SteeringComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.entities.PlayerEntity;
import org.omg.CORBA.MARSHAL;

public class SteeringSystem extends IteratingSystem {
    ZombieShooterGame parent;

    public SteeringSystem(ZombieShooterGame parent) {
        super(Family.all(SteeringComponent.class).get());
        this.parent = parent;
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        GdxAI.getTimepiece().update(deltaTime);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SteeringComponent steer = Mapper.steerCom.get(entity);
        Body monsterBody = Mapper.bodyCom.get(entity).body;

        PlayerEntity playerEntity = parent.getGameScreen().getPlayer();
        Body playerBody = Mapper.bodyCom.get(entity).body;


        if ((playerBody.getPosition().x + 640) > monsterBody.getPosition().x && (playerBody.getPosition().x - 640) < monsterBody.getPosition().x &&
                (playerBody.getPosition().y + 640) > monsterBody.getPosition().y && (playerBody.getPosition().y - 640) < monsterBody.getPosition().y ) {
            SteeringComponent playerSteer = Mapper.steerCom.get(playerEntity);
            steer.steeringBehavior = SteeringPresets.getArrive(steer, playerSteer);
        }

        steer.update(deltaTime);
    }
}
