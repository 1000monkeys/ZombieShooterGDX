package com.kjellvos.aletho.zombieshooter.gdx;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.*;
import com.kjellvos.aletho.zombieshooter.gdx.components.*;
import com.kjellvos.aletho.zombieshooter.gdx.enums.AnimationEnum;
import com.kjellvos.aletho.zombieshooter.gdx.enums.TextureEnum;

public class MobBuilder {

    public static void buildObjects(TiledMap map, Texture tileset, World world, Engine engine, RayHandler rayHandler) {
        MapObjects objects = map.getLayers().get("Mobs").getObjects();

        for(MapObject object : objects) {
            int id = Integer.parseInt(object.getProperties().get("gid").toString());
            id--; //the gid is always 1 too high

            if (id == TextureEnum.LIGHT_OFF.getId()) {
                buildLight(object, tileset, world, engine, rayHandler);
            }else if(TextureEnum.findById(id).isItem()) {
                Entity item = new Entity();

                TextureRegion textureRegion = TilesetTextureToTextureRegion.getTextureRegionByTextureEnum(tileset, TextureEnum.findById(id));

                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set(Float.parseFloat(object.getProperties().get("x").toString()), Float.parseFloat(object.getProperties().get("y").toString()));

                Body body = world.createBody(bodyDef);
                body.setLinearDamping(5);
                PolygonShape shape = new PolygonShape();
                shape.setAsBox(textureRegion.getRegionWidth() / 2F, textureRegion.getRegionHeight() / 2F);
                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.filter.categoryBits = Constants.CATEGORY_ITEM;
                fixtureDef.filter.maskBits = Constants.MASK_ITEM;
                body.createFixture(fixtureDef).setUserData("item");

                item.add(new BodyComponent(body)).add(new TextureRegionComponent(textureRegion)).add(new ItemComponent(id));
                engine.addEntity(item);
            }else {
                Entity mob = new Entity();

                TextureRegion textureRegion = TilesetTextureToTextureRegion.getTextureRegionByTextureEnum(tileset, TextureEnum.findById(id));

                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set(Float.parseFloat(object.getProperties().get("x").toString()), Float.parseFloat(object.getProperties().get("y").toString()));

                Body body = world.createBody(bodyDef);
                body.setLinearDamping(5);
                PolygonShape shape = new PolygonShape();
                shape.setAsBox(textureRegion.getRegionWidth() / 2F, textureRegion.getRegionHeight() / 2F);
                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.filter.categoryBits = Constants.CATEGORY_MOB;
                fixtureDef.filter.maskBits = Constants.MASK_MOB;
                body.createFixture(fixtureDef).setUserData("mob");

                mob.add(new BodyComponent(body)).add(new TextureRegionComponent(textureRegion));
                engine.addEntity(mob);
            }
        }
    }

    /**
     * Build's the lights in the world, Runs after/during loading the map
     * @param object the object(the light)
     * @param tileset the tileset used for the object
     * @param world the box2D world in which the light should be
     * @param engine the ashley entity system engine
     * @param rayHandler The ray handler, used to create the light
     */
    public static void buildLight(MapObject object, Texture tileset, World world, Engine engine, RayHandler rayHandler){
        Entity light = new Entity();

        TextureEnum[] textureEnums = AnimationEnum.LIGHT.getTextureEnums();
        TextureRegion[] textureRegions = TilesetTextureToTextureRegion.getAnimationTextureRegionsByTextureEnums(tileset, textureEnums);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        float x = Float.parseFloat(object.getProperties().get("x").toString());
        float y = Float.parseFloat(object.getProperties().get("y").toString());
        bodyDef.position.set(x, y);

        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox( textureRegions[0].getRegionWidth() / 2F, textureRegions[0].getRegionHeight() / 2F);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = Constants.CATEGORY_BUILDING;
        fixtureDef.filter.maskBits = Constants.MASK_BUILDING;
        body.createFixture(fixtureDef).setUserData("light");

        PointLight pointLight = new PointLight(rayHandler, Constants.LIGHT_NUM_RAYS, new Color(1,256,1,1), Constants.LIGHT_DISTANCE, x,  y);
        pointLight.setSoftnessLength(Constants.LIGHT_SOFTNESS_LENGTH);
        pointLight.attachToBody(body);
        fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = Constants.CATEGORY_LIGHT;
        fixtureDef.filter.maskBits = Constants.MASK_LIGHT;
        pointLight.setContactFilter(fixtureDef.filter);

        light.add(new AnimationComponent(textureRegions)).add(new BodyComponent(body)).add(new LightComponent(pointLight));
        engine.addEntity(light);
    }
}
