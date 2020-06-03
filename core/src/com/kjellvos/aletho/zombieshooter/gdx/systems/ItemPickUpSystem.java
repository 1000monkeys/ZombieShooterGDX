package com.kjellvos.aletho.zombieshooter.gdx.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.Body;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.ItemComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.PlayerSteerableComponent;

public class ItemPickUpSystem extends EntitySystem {
    private ZombieShooterGame parent;
    private ImmutableArray<Entity> entities, player;

    /**
     * Pass the parent for use in this class
     * @param parent the ZombieShooterGame class
     */
    public ItemPickUpSystem(ZombieShooterGame parent) {
        this.parent = parent;
    }

    /**
     * See {@link RenderSystem#addedToEngine(Engine)}
     */
    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        entities = engine.getEntitiesFor(Family.all(ItemComponent.class, BodyComponent.class).get());
        player = engine.getEntitiesFor(Family.all(PlayerSteerableComponent.class, BodyComponent.class).get());
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

                //display pickuptext if item requires it

                int id = entities.get(i).getComponent(ItemComponent.class).id;

                ItemComponent itemComponent = entities.get(i).getComponent(ItemComponent.class);
                if (parent.getReadJsonGameFiles().getSpriteGson(id).getItemData().hasPickUpText()) {
                    parent.getGameScreen().setClosestItem(entities.get(i));

                    parent.getGameScreen().setItemText(parent.getReadJsonGameFiles().getSpriteGson(id).getDescription() + "[Press G to Pickup.]");
                } else {
                    parent.getGameScreen().getPlayer().getInventory().addItem(itemComponent);

                    parent.getGameScreen().getWorld().destroyBody(entities.get(i).getComponent(BodyComponent.class).body);
                    parent.getGameScreen().getEngine().removeEntity(entities.get(i));

                    if (Constants.DEBUG) {
                        System.out.println("Picking up item: " + id);
                    }
                }
            }
        }
    }
}
