package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;


import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;

public class Tile {
    private int x, y, index;
    private boolean walkable;
    private Array<Connection<Tile>> connections;

    public Tile(int x, int y, boolean walkable, int index) {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
        this.index = index;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getIndex(){
        return index;
    }

    public Array<Connection<Tile>> getConnections() {
        return connections;
    }

    public void setConnections(Array<Connection<Tile>> connections) {
        this.connections = connections;
    }
}

