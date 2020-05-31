package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.PlayerSteerableComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.TextureRegionComponent;

import java.util.HashMap;

public class PlayerEntity {
    private ZombieShooterGame parent;
    private Body playerBody;
    private Entity playerEntity;
    private TextureRegion playerTextureRegion;

    private Inventory inventory;
    private HashMap<Integer, Ability> abilities;

    public PlayerEntity(ZombieShooterGame parent, TextureRegion playerTextureRegion) {
        this.parent = parent;
        this.playerTextureRegion = playerTextureRegion;

        Entity entity = new Entity();

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((50 * Constants.PPT + playerTextureRegion.getRegionWidth() / 2F), (50 * Constants.PPT + playerTextureRegion.getRegionWidth() / 2F));

        Body body = parent.getGameScreen().getWorld().createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(playerTextureRegion.getRegionHeight() / 2F, playerTextureRegion.getRegionWidth() / 2F);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Constants.CATEGORY_PLAYER;
        fixtureDef.filter.maskBits = Constants.MASK_PLAYER;
        body.createFixture(fixtureDef).setUserData("player");

        entity.add(new BodyComponent(body)).add(new TextureRegionComponent(playerTextureRegion)).add(new PlayerSteerableComponent(50 * Constants.PPT, 50 * Constants.PPT));
        parent.getGameScreen().getEngine().addEntity(entity);
        parent.setPlayer(entity);

        playerEntity = entity;
        playerBody = body;
        inventory = new Inventory(parent);
        abilities = new HashMap<Integer, Ability>();
    }

    public TextureRegion getPlayerTextureRegion() {
        return playerTextureRegion;
    }

    public Body getPlayerBody() {
        return playerBody;
    }

    public Entity getPlayerEntity() {
        return playerEntity;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
