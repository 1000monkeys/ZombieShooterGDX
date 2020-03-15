package com.kjellvos.aletho.zombieshooter.gdx.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;
import com.kjellvos.aletho.zombieshooter.gdx.components.AnimationComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.LightComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.TextureRegionComponent;
import com.kjellvos.aletho.zombieshooter.gdx.views.GameScreen;

public class RenderSystem extends EntitySystem {
    private GameScreen gameScreen;
    private ImmutableArray<Entity> entities, lights;

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
     * The update function is used by the ashley entity system when called in the {@link GameScreen#render} method.
     * @param deltaTime time since last time this function has ran.
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity entity : entities) {
            TextureRegion textureRegion = entity.getComponent(TextureRegionComponent.class).textureRegion;
            Body body = entity.getComponent(BodyComponent.class).body;

            batch.draw(textureRegion, body.getPosition().x - textureRegion.getRegionWidth() / 2, body.getPosition().y - textureRegion.getRegionHeight() / 2);
        }
        for (Entity entity : lights) {
            TextureRegion currentFrame = entity.getComponent(AnimationComponent.class).fireAnimation.getKeyFrame(stateTime, true);

            Body body = entity.getComponent(BodyComponent.class).body;
            batch.draw(currentFrame, body.getPosition().x - (currentFrame.getRegionWidth() / 2), body.getPosition().y  - (currentFrame.getRegionHeight() / 2));
            stateTime += deltaTime;
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
        lights = engine.getEntitiesFor(Family.all(AnimationComponent.class, BodyComponent.class, LightComponent.class).get());
    }
}
