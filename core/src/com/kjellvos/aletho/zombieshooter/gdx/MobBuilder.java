package com.kjellvos.aletho.zombieshooter.gdx;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.*;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.TextureRegionComponent;

public class MobBuilder {

    public static void buildMobs(TiledMap map, Texture tileset, World world, Engine engine) {
        MapObjects objects = map.getLayers().get("Mobs").getObjects();

        for(MapObject object : objects) {
            int id = Integer.parseInt(object.getProperties().get("gid").toString());
            id--; //the gid is always 1 too high

            Entity mob = new Entity();

            TextureRegion textureRegion = TilesetTextureToTextureRegion.getTextureRegionById(tileset, id);

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set(Float.parseFloat(object.getProperties().get("x").toString()) / ZombieShooterGame.PPM, Float.parseFloat(object.getProperties().get("y").toString()) / ZombieShooterGame.PPM);

            Body body = world.createBody(bodyDef);
            body.setLinearDamping(5);
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(textureRegion.getRegionHeight() / 2F / ZombieShooterGame.PPM, textureRegion.getRegionWidth() / 2F / ZombieShooterGame.PPM);
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef).setUserData("mob");

            mob.add(new BodyComponent(body)).add(new TextureRegionComponent(textureRegion));
            engine.addEntity(mob);

            System.out.println(id);
        }
    }
}
