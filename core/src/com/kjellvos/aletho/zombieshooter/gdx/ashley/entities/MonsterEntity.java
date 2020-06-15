package com.kjellvos.aletho.zombieshooter.gdx.ashley.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;
import com.kjellvos.aletho.zombieshooter.gdx.loader.ReadJsonGameFiles;
import com.kjellvos.aletho.zombieshooter.gdx.pathfinding.SteeringPresets;
import com.kjellvos.aletho.zombieshooter.gdx.ZombieShooterGame;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.SteeringComponent;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.TextureRegionComponent;


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
        shape.setAsBox(8 / 2F, 8 / 2F);
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Constants.CATEGORY_MOB;
        fixtureDef.filter.maskBits = Constants.MASK_MOB;
        body.createFixture(fixtureDef).setUserData("player");

        ReadJsonGameFiles readJsonGameFiles = parent.getReadJsonGameFiles();


        TextureRegionComponent textureRegionComponent = new TextureRegionComponent(readJsonGameFiles.getSpriteGson(293).getSprite());

        SteeringComponent sc = new SteeringComponent(body);
        sc.steeringBehavior = SteeringPresets.getWander(sc);
        sc.currentMode = SteeringComponent.SteeringState.WANDER;

        this.add(textureRegionComponent).add(new BodyComponent(body)).add(sc);
        parent.getGameScreen().getEngine().addEntity(this);
    }
}
