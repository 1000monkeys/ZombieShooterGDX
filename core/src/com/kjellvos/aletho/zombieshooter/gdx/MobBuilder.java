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
import com.kjellvos.aletho.zombieshooter.gdx.components.AnimationComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.BodyComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.LightComponent;
import com.kjellvos.aletho.zombieshooter.gdx.components.TextureRegionComponent;
import com.kjellvos.aletho.zombieshooter.gdx.enums.TextureEnum;

public class MobBuilder {

    public static void buildMobs(TiledMap map, Texture tileset, World world, Engine engine, RayHandler rayHandler) {
        MapObjects objects = map.getLayers().get("Mobs").getObjects();

        for(MapObject object : objects) {
            int id = Integer.parseInt(object.getProperties().get("gid").toString());
            id--; //the gid is always 1 too high

            if (id == TextureEnum.LIGHT_OFF.getId()) {
                Entity light = new Entity();

                TextureRegion[] textureAnimationRegion = new TextureRegion[Constants.LIGHT_ANIMATION_TEXTURE_COUNT];
                textureAnimationRegion[0] = TilesetTextureToTextureRegion.getTextureRegionByTextureEnum(tileset, TextureEnum.LIGHT_ANIMATION_0);
                textureAnimationRegion[1] = TilesetTextureToTextureRegion.getTextureRegionByTextureEnum(tileset, TextureEnum.LIGHT_ANIMATION_1);
                textureAnimationRegion[2] = TilesetTextureToTextureRegion.getTextureRegionByTextureEnum(tileset, TextureEnum.LIGHT_ANIMATION_2);
                textureAnimationRegion[3] = TilesetTextureToTextureRegion.getTextureRegionByTextureEnum(tileset, TextureEnum.LIGHT_ANIMATION_3);
                textureAnimationRegion[4] = TilesetTextureToTextureRegion.getTextureRegionByTextureEnum(tileset, TextureEnum.LIGHT_ANIMATION_4);
                textureAnimationRegion[5] = TilesetTextureToTextureRegion.getTextureRegionByTextureEnum(tileset, TextureEnum.LIGHT_ANIMATION_5);
                textureAnimationRegion[6] = TilesetTextureToTextureRegion.getTextureRegionByTextureEnum(tileset, TextureEnum.LIGHT_ANIMATION_6);
                textureAnimationRegion[7] = TilesetTextureToTextureRegion.getTextureRegionByTextureEnum(tileset, TextureEnum.LIGHT_ANIMATION_7);

                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                float x = Float.parseFloat(object.getProperties().get("x").toString()) / Constants.PPM;
                float y = Float.parseFloat(object.getProperties().get("y").toString()) / Constants.PPM;
                bodyDef.position.set(x, y);

                Body body = world.createBody(bodyDef);
                PolygonShape shape = new PolygonShape();
                shape.setAsBox( textureAnimationRegion[0].getRegionWidth() / 2F / Constants.PPM, textureAnimationRegion[0].getRegionHeight() / 2F / Constants.PPM);
                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.filter.categoryBits = Constants.CATEGORY_BUILDING;
                fixtureDef.filter.maskBits = Constants.MASK_BUILDING;
                body.createFixture(fixtureDef).setUserData("light");

                PointLight pointLight = new PointLight(rayHandler, Constants.LIGHT_NUM_RAYS, new Color(1,256,1,1), Constants.LIGHT_DISTANCE, x * Constants.PPM,  y * Constants.PPM);
                pointLight.attachToBody(body);
                fixtureDef = new FixtureDef();
                fixtureDef.filter.categoryBits = Constants.CATEGORY_LIGHT;
                fixtureDef.filter.maskBits = Constants.MASK_LIGHT;
                pointLight.setContactFilter(fixtureDef.filter);

                light.add(new AnimationComponent(textureAnimationRegion)).add(new BodyComponent(body)).add(new LightComponent(pointLight));
                engine.addEntity(light);
            }else if(TextureEnum.findById(id).isItem()) {
                Entity item = new Entity();

                TextureRegion textureRegion = TilesetTextureToTextureRegion.getTextureRegionByTextureEnum(tileset, TextureEnum.findById(id));

                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set(Float.parseFloat(object.getProperties().get("x").toString()) / Constants.PPM, Float.parseFloat(object.getProperties().get("y").toString()) / Constants.PPM);

                Body body = world.createBody(bodyDef);
                body.setLinearDamping(5);
                PolygonShape shape = new PolygonShape();
                shape.setAsBox(textureRegion.getRegionWidth() / 2F / Constants.PPM, textureRegion.getRegionHeight() / 2F / Constants.PPM);
                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.filter.categoryBits = Constants.CATEGORY_ITEM;
                fixtureDef.filter.maskBits = Constants.MASK_ITEM;
                body.createFixture(fixtureDef).setUserData("item");

                item.add(new BodyComponent(body)).add(new TextureRegionComponent(textureRegion));
                engine.addEntity(item);
            }else {
                Entity mob = new Entity();

                TextureRegion textureRegion = TilesetTextureToTextureRegion.getTextureRegionByTextureEnum(tileset, TextureEnum.findById(id));

                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.DynamicBody;
                bodyDef.position.set(Float.parseFloat(object.getProperties().get("x").toString()) / Constants.PPM, Float.parseFloat(object.getProperties().get("y").toString()) / Constants.PPM);

                Body body = world.createBody(bodyDef);
                body.setLinearDamping(5);
                PolygonShape shape = new PolygonShape();
                shape.setAsBox(textureRegion.getRegionWidth() / 2F / Constants.PPM, textureRegion.getRegionHeight() / 2F / Constants.PPM);
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
}
