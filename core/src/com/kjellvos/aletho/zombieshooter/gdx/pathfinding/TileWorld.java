package com.kjellvos.aletho.zombieshooter.gdx.pathfinding;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;


public class TileWorld implements IndexedGraph<Tile> {
    protected Array<Tile> tiles;
    private int mapWidth, mapHeight;


    /**
     * Constructor of Tileworld sets up the variables.
     * @param tiles the tiles of the world
     * @param mapWidth Map width of the current world
     * @param mapHeight Map height of the current world
     */
    public TileWorld(Array<Tile> tiles, int mapWidth, int mapHeight){
        this.tiles = tiles;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }

    /**
     * Gets the index of a node.
     * @param tile node to get index of
     * @return the index of the node
     */
    @Override
    public int getIndex(Tile tile) {
        return tile.getIndex();
    }

    /**
     * Function for getting the amount of nodes in the tiles array
     * @return size of node array
     */
    @Override
    public int getNodeCount() {
        return tiles.size;
    }

    /**
     * Get connections(usually neighbours) of the passed tile.
     * @param fromTile
     * @return The passed tile's connections
     */
    @Override
    public Array<Connection<Tile>> getConnections(Tile fromTile) {
        return fromTile.getConnections();
    }
}
