package com.kjellvos.aletho.zombieshooter.gdx.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;
import com.kjellvos.aletho.zombieshooter.gdx.ReadJsonGameFiles;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.TextureRegionComponent;


public class MonsterEntity extends Entity {
    private ZombieShooterGame parent;

    public MonsterEntity(ZombieShooterGame parent, float x, float y){
        this.parent = parent;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        Body body = parent.getGameScreen().getWorld().createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / 2F, 16 / 2F);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Constants.CATEGORY_PLAYER;
        fixtureDef.filter.maskBits = Constants.MASK_PLAYER;
        body.createFixture(fixtureDef).setUserData("player");

        ReadJsonGameFiles readJsonGameFiles = parent.getReadJsonGameFiles();


        TextureRegionComponent textureRegionComponent = new TextureRegionComponent(readJsonGameFiles.getSpriteGson(293).getSprite());

        this.add(textureRegionComponent).add(new BodyComponent(body)).add(new PathingComponent(x, y));
        parent.getGameScreen().getEngine().addEntity(this);
    }
}
