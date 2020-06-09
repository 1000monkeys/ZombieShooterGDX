package com.kjellvos.aletho.zombieshooter.gdx.ashley.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.ManyAnimationComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.SteeringComponent;
import com.kjellvos.aletho.zombieshooter.gdx.views.GameScreen;

public class PlayerMovementSystem extends EntitySystem {
    private GameScreen gameScreen;

    private ImmutableArray<Entity> entities;

    private ComponentMapper<SteeringComponent> ComMapPlayerSteerableComponent = ComponentMapper.getFor(SteeringComponent.class);
    private ComponentMapper<BodyComponent> ComMapBodyComponent = ComponentMapper.getFor(BodyComponent.class);

    public PlayerMovementSystem(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    /**
     * See {@link RenderSystem#addedToEngine(Engine)}
     */
    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        //TODO add animation component ot player
        entities = engine.getEntitiesFor(Family.all(SteeringComponent.class, BodyComponent.class, ManyAnimationComponent.class).get());
    }

    /**
     * See {@link RenderSystem#update(float)}
     * Good place to update the player steerable component for the ai steering behaviour
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);

            SteeringComponent playerSteerComp = ComMapPlayerSteerableComponent.get(entity);
            BodyComponent bodyComp = ComMapBodyComponent.get(entity);

            int horizontalForce = 0, verticalForce = 0;
            if (gameScreen.isLeftPressed()) {
                horizontalForce -= 64;
            }
            if (gameScreen.isRightPressed()) {
                horizontalForce += 64;
            }
            if (gameScreen.isUpPressed()) {
                verticalForce += 64;
            }
            if (gameScreen.isDownPressed()) {
                verticalForce -= 64;
            }

            bodyComp.body.setLinearVelocity(horizontalForce, verticalForce);
            playerSteerComp.getPosition().x = bodyComp.body.getPosition().x;
            playerSteerComp.getPosition().y = bodyComp.body.getPosition().y;
        }
    }
}