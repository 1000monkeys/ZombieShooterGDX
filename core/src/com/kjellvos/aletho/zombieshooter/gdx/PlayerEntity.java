package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.PlayerAnimationComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.PlayerSteerableComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.TextureRegionComponent;

import java.util.HashMap;

public class PlayerEntity {
    private ZombieShooterGame parent;
    private Body playerBody;
    private Entity playerEntity;

    private PlayerAnimationComponent playerAnimationComponent;

    private Inventory inventory;
    private HashMap<Integer, Ability> abilities;

    //TODO remove hard coded 16's
    public PlayerEntity(ZombieShooterGame parent) {
        this.parent = parent;

        Entity entity = new Entity();

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((50 * Constants.PPT + 16 / 2F), (50 * Constants.PPT + 16 / 2F));

        Body body = parent.getGameScreen().getWorld().createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / 2F, 16 / 2F);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Constants.CATEGORY_PLAYER;
        fixtureDef.filter.maskBits = Constants.MASK_PLAYER;
        body.createFixture(fixtureDef).setUserData("player");

        ReadJsonGameFiles readJsonGameFiles = parent.getReadJsonGameFiles();

        Animation<TextureRegion> upAnimation = new Animation<TextureRegion>(readJsonGameFiles.getAnimationGson(Constants.ANIMATION_PLAYER_UP).getTimer(), readJsonGameFiles.getAnimationTextures(Constants.ANIMATION_PLAYER_UP));
        Animation<TextureRegion> downAnimation = new Animation<TextureRegion>(readJsonGameFiles.getAnimationGson(Constants.ANIMATION_PLAYER_DOWN).getTimer(), readJsonGameFiles.getAnimationTextures(Constants.ANIMATION_PLAYER_DOWN));
        Animation<TextureRegion> rightAnimation = new Animation<TextureRegion>(readJsonGameFiles.getAnimationGson(Constants.ANIMATION_PLAYER_LEFTRIGHT).getTimer(), readJsonGameFiles.getAnimationTextures(Constants.ANIMATION_PLAYER_LEFTRIGHT));

        playerAnimationComponent = new PlayerAnimationComponent(parent, upAnimation, downAnimation, rightAnimation);

        entity.add(playerAnimationComponent).add(new BodyComponent(body)).add(new PlayerSteerableComponent(50 * Constants.PPT, 50 * Constants.PPT));
        parent.getGameScreen().getEngine().addEntity(entity);
        parent.setPlayer(entity);

        playerEntity = entity;
        playerBody = body;
        inventory = new Inventory(parent);
        abilities = new HashMap<Integer, Ability>();
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

    public PlayerAnimationComponent getPlayerAnimationComponent() {
        return playerAnimationComponent;
    }
}
