package com.kjellvos.aletho.zombieshooter.gdx.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.PlayerSteerableComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.TextureRegionComponent;
import com.kjellvos.aletho.zombieshooter.gdx.views.GameScreen;

public class PlayerMovementSystem extends EntitySystem {
    private GameScreen gameScreen;

    private ImmutableArray<Entity> entities;

    private ComponentMapper<PlayerSteerableComponent> ComMapPlayerSteerableComponent = ComponentMapper.getFor(PlayerSteerableComponent.class);
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
        entities = engine.getEntitiesFor(Family.all(PlayerSteerableComponent.class, BodyComponent.class, TextureRegionComponent.class).get());
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

            PlayerSteerableComponent playerSteerComp = ComMapPlayerSteerableComponent.get(entity);
            BodyComponent bodyComp = ComMapBodyComponent.get(entity);

            int horizontalForce = 0, verticalForce = 0;
            if (gameScreen.leftPressed) {
                horizontalForce -= 64;
            }
            if (gameScreen.rightPressed) {
                horizontalForce += 64;
            }
            if (gameScreen.upPressed) {
                verticalForce += 64;
            }
            if (gameScreen.downPressed) {
                verticalForce -= 64;
            }

            bodyComp.body.setLinearVelocity(horizontalForce, verticalForce);
            playerSteerComp.x = bodyComp.body.getPosition().x;
            playerSteerComp.y = bodyComp.body.getPosition().y;
        }
    }
}