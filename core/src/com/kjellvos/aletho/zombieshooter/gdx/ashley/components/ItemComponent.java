package com.kjellvos.aletho.zombieshooter.gdx.ashley.components;

import com.badlogic.ashley.core.Component;

public class ItemComponent implements Component {
    public int id;

    public ItemComponent(int id) {
        this.id = id;
    }
}
