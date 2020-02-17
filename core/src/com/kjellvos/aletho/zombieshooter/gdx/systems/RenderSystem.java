package com.kjellvos.aletho.zombieshooter.gdx.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.TextureRegionComponent;
import com.kjellvos.aletho.zombieshooter.gdx.views.GameScreen;

public class RenderSystem extends EntitySystem {
    private GameScreen gameScreen;
    private ImmutableArray<Entity> entities;

    private SpriteBatch batch;

    private ComponentMapper<TextureRegionComponent> ComMapSpriteComponent = ComponentMapper.getFor(TextureRegionComponent.class);

    public RenderSystem(GameScreen gameScreen, SpriteBatch batch) {
        this.gameScreen = gameScreen;
        this.batch = batch;
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (Entity entity : entities) {
            TextureRegion textureRegion = entity.getComponent(TextureRegionComponent.class).textureRegion;
            Body body = entity.getComponent(BodyComponent.class).body;

            batch.draw(textureRegion, body.getPosition().x * ZombieShooterGame.PPM - textureRegion.getRegionWidth() / 2, body.getPosition().y * ZombieShooterGame.PPM - textureRegion.getRegionHeight() / 2);
        }
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        entities = engine.getEntitiesFor(Family.all(TextureRegionComponent.class, BodyComponent.class).get());
    }
}
