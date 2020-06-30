package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;


import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.utils.Array;

public class Tile {
    private int x, y, index;
    private boolean walkable;
    private Array<Connection<Tile>> connections;

    /**
     * Constructor of tile class, The variables being passed are used for pathfinding
     * @param x The X position of this tile
     * @param y the Y position of this tile
     * @param walkable Boolean on whether the tile is walkable or not
     * @param index index in the tileworld list
     */
    public Tile(int x, int y, boolean walkable, int index) {
        this.x = x;
        this.y = y;
        this.walkable = walkable;
        this.index = index;
    }

    /**
     * Function returns whether the tile is walkable/traversable or not
     * @return boolean value whether this tile is walkable
     */
    public boolean isWalkable() {
        return walkable;
    }

    /**
     * Function to get the Y coordinate of this tile
     * @return the Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Function to get X coordinate of this tile
     * @return the X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the index of this tile in the tileworld list
     * @return integer value representing the index in tileworld list
     */
    public int getIndex(){
        return index;
    }

    /**
     * Function to get the connections of this tile
     * @return connections of this tile
     */
    public Array<Connection<Tile>> getConnections() {
        return connections;
    }

    /**
     * Set the connections for this tile overwriting old connections
     * @param connections the array with connections
     */
    public void setConnections(Array<Connection<Tile>> connections) {
        this.connections = connections;
    }
}

