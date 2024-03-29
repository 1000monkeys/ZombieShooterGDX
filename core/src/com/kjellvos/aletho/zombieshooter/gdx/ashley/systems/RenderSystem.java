package com.kjellvos.aletho.zombieshooter.gdx.ashley.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.DirectionalWalkingAnimationComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.SimpleAnimationComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.TextureRegionComponent;
import com.kjellvos.aletho.zombieshooter.gdx.views.GameScreen;

public class RenderSystem extends EntitySystem {
    private ImmutableArray<Entity> entities, playerAnimation, simpleAnimations;

    private SpriteBatch batch;

    private float stateTime = 0;

    /**
     * The constructor of the render system, Gets called once on creation of the class,
     * We pass the {@link GameScreen} class and the {@link GameScreen#batch} variable for use in the update function.
     * @param batch passed for use in the update method.
     */
    public RenderSystem(SpriteBatch batch) {
        this.batch = batch;
    }

    /**
     * See {@link RenderSystem#update(float)}
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        stateTime += deltaTime;

        for (Entity entity : entities) {
            TextureRegion textureRegion = entity.getComponent(TextureRegionComponent.class).textureRegion;
            Body body = entity.getComponent(BodyComponent.class).body;

            batch.draw(textureRegion, body.getPosition().x - textureRegion.getRegionWidth() / 2, body.getPosition().y - textureRegion.getRegionHeight() / 2);
        }
        for (Entity entity : simpleAnimations) {
            TextureRegion currentFrame = entity.getComponent(SimpleAnimationComponent.class).animation.getKeyFrame(stateTime, true);
            Body body = entity.getComponent(BodyComponent.class).body;

            batch.draw(currentFrame, body.getPosition().x - (currentFrame.getRegionWidth() / 2), body.getPosition().y  - (currentFrame.getRegionHeight() / 2));
        }
        for (Entity entity : playerAnimation) {
            TextureRegion currentFrame = entity.getComponent(DirectionalWalkingAnimationComponent.class).getAnimation().getKeyFrame(stateTime, true);
            Body body = entity.getComponent(BodyComponent.class).body;

            batch.draw(currentFrame, body.getPosition().x - (currentFrame.getRegionWidth() / 2), body.getPosition().y  - (currentFrame.getRegionHeight() / 2));
        }
    }

    /**
     * This function gets called once you add the system to the entity management system.
     * @param engine the engine it is added to.
     */
    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        entities = engine.getEntitiesFor(Family.all(TextureRegionComponent.class, BodyComponent.class).get());
        playerAnimation = engine.getEntitiesFor(Family.all(DirectionalWalkingAnimationComponent.class, BodyComponent.class).get());
        simpleAnimations = engine.getEntitiesFor(Family.all(SimpleAnimationComponent.class).get());
    }
}
