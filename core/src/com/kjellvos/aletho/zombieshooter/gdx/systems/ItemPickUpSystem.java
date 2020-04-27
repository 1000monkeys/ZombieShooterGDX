package com.kjellvos.aletho.zombieshooter.gdx.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.Body;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.ItemComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.PlayerSteerableComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.TextureRegionComponent;

public class ItemPickUpSystem extends EntitySystem {
    private ImmutableArray<Entity> entities, player;

    /**
     * See {@link RenderSystem#addedToEngine(Engine)}
     */
    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        entities = engine.getEntitiesFor(Family.all(ItemComponent.class).get());
        player = engine.getEntitiesFor(Family.all(PlayerSteerableComponent.class).get());
    }

    /**
     * See {@link RenderSystem#update(float)}
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        BodyComponent playerBodyComponent = player.get(0).getComponent(BodyComponent.class);
        Body playerBody = playerBodyComponent.body;

        for (int i = 0; i < entities.size(); i++) {
            Body itemBody = entities.get(i).getComponent(BodyComponent.class).body;

            if ((playerBody.getPosition().x + 64) > itemBody.getPosition().x && (playerBody.getPosition().x - 64) < itemBody.getPosition().x &&
                (playerBody.getPosition().y + 64) > itemBody.getPosition().y && (playerBody.getPosition().y - 64) < itemBody.getPosition().y ) {

                int id = entities.get(i).getComponent(ItemComponent.class).id;

                System.out.println("Picking up item: ");
                System.out.println(id);
            }
        }
    }
}
