package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;

public class TileConnection implements Connection<Tile> {
    @Override
    public float getCost() {
        return 0;
    }

    @Override
    public Tile getFromNode() {
        return null;
    }

    @Override
    public Tile getToNode() {
        return null;
    }
}
