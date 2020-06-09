package com.kjellvos.aletho.zombieshooter.gdx.ashley.components;

import box2dLight.PointLight;
import com.badlogic.ashley.core.Component;

public class LightComponent implements Component{
    public PointLight pointLight;

    public LightComponent(PointLight pointLight) {
        this.pointLight = pointLight;
    }
}
