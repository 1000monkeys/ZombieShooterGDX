package com.kjellvos.aletho.zombieshooter.gdx.ashley;

import com.badlogic.ashley.core.ComponentMapper;
import com.kjellvos.aletho.zombieshooter.gdx.ashley.components.*;

public class Mapper {
    public static final ComponentMapper<BodyComponent> bodyCom = ComponentMapper.getFor(BodyComponent.class);
    public static final ComponentMapper<ItemComponent> itemCom = ComponentMapper.getFor(ItemComponent.class);
    public static final ComponentMapper<LightComponent> lightCom = ComponentMapper.getFor(LightComponent.class);
    public static final ComponentMapper<DirectionalWalkingAnimationComponent> manyCom = ComponentMapper.getFor(DirectionalWalkingAnimationComponent.class);
    public static final ComponentMapper<SimpleAnimationComponent> simpleCom = ComponentMapper.getFor(SimpleAnimationComponent.class);
    public static final ComponentMapper<SteeringComponent> steerCom = ComponentMapper.getFor(SteeringComponent.class);
    public static final ComponentMapper<TextureRegionComponent> textuCom = ComponentMapper.getFor(TextureRegionComponent.class);
}
