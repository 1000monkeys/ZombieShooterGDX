package com.kjellvos.aletho.zombieshooter.gdx.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

public class BodyComponent implements Component {
    public Body body;

    public BodyComponent(Body body) {
        this.body = body;
    }
}
